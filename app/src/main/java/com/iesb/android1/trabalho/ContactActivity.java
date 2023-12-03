package com.iesb.android1.trabalho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iesb.android1.aulaii.R;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity implements ContatoAdapter.IOnItemClickListener {

    private Button btLight_Home, btDark_Home, btSO_Home, btIncluir_Home;
    private List<Contact> contactsList;

    private Contact contact;
    private RecyclerView recyclerViewHome;
    private ContatoAdapter contatoAdapter;
    private TextView tvWelcome;
    private EditText etNome, etTel, etEmail;

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        tvWelcome = findViewById(R.id.tvWelcome);

        Intent intent = getIntent();
        String welcomeReceived = intent.getStringExtra("KEY_NAME");

        tvWelcome.setText(" " + welcomeReceived);

        System.out.println("Nome: " + welcomeReceived);
//O código acima refere-se a view welcome

        //Botões de alteração do tema da tela Home
        btDark_Home = findViewById(R.id.btDark_Home);
        btLight_Home = findViewById(R.id.btLight_Home);
        btSO_Home = findViewById(R.id.btSO_Home);

        recyclerViewHome = findViewById(R.id.recyclerViewHome);
        etNome = findViewById(R.id.etNome_Home);
        etTel = findViewById(R.id.etTel);
        etEmail = findViewById(R.id.etEmail);
        btIncluir_Home = findViewById(R.id.btIncluir_Home);


        contactsList = new ArrayList<>();
        contatoAdapter = new ContatoAdapter(contactsList, this);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHome.setAdapter(contatoAdapter);


        if (!checkPermission()) {
            requestPermissions();
        } else {
            getContactsList();
            updateRecyclerView();
        }


        btIncluir_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addContact(etNome.getText().toString().trim(), etTel.getText().toString().trim(), etEmail.getText().toString().trim());
                updateRecyclerView();

//                System.out.println("Contato  " + contact.toString());

//                contactsList.add(contact);
//
//                contatoAdapter.notifyItemInserted(contactsList.size() - 1);
//                System.out.println("Contato  " + contactsList.size());

                //Limpa o formulário ao incluir contato
                etNome.setText("");
                etTel.setText("");
                etEmail.setText("");

            }
        });

        btDark_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });

        btLight_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        btSO_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            }
        });
    }

    private void requestPermissions() {
        if (checkSelfPermission(Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
    }

    private void addContact(String name, String phone, String email) {
        ContentResolver contentResolver = getContentResolver();
        ArrayList<ContentProviderOperation> contentProviderResults = new ArrayList<>();

        contentProviderResults.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());

        contentProviderResults.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
                .build());

        contentProviderResults.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build());
        contentProviderResults.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Email.DATA, email)
                .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                .build());

        try {
            contentResolver.applyBatch(ContactsContract.AUTHORITY, contentProviderResults);
            Toast.makeText(this, "Contato adicionado com sucesso", Toast.LENGTH_LONG).show();
        } catch (RemoteException | OperationApplicationException e) {
            Toast.makeText(this, "Erro ao adicionar contato", Toast.LENGTH_LONG).show();
        }
    }


    private ArrayList<Contact> getContactsList() {
        ArrayList<Contact> contacts = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DATA };

        @NonNull
        Cursor cursor = contentResolver.query(uri, projection, null, null, null);

        if (cursor != null) {
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int emailIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);

            while (cursor.moveToNext()) {
                String name = cursor.getString(nameIndex);
                String phoneNumber = cursor.getString(numberIndex);
                String email = cursor.getString(emailIndex);
                contacts.add(new Contact(name, phoneNumber, email));

            }

            cursor.close();
        }

        return contacts;
    }

    private void updateRecyclerView() {
        contactsList.clear();
        contactsList.addAll(getContactsList());
        contatoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                updateRecyclerView();
            }
        }
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {

    }
}
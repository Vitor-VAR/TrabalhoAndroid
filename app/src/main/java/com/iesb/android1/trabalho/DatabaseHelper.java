package com.iesb.android1.trabalho;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ContatoDB";
    private static final String TABLE_NAME = "contato";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "nome";
    private static final String PHONE = "telefone";
    private static final String EMAIL = "email";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                KEY_NAME + " TEXT, " + PHONE + " TEXT, " + EMAIL + " TEXT " + " )";
        sqLiteDatabase.execSQL(createTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int v1, int v2) { //v1 e v2: versão do banco
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addPerson(Person person) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.getNome());
        //values.put(PHONE, person.getTelefone());
        values.put(EMAIL, person.getEmail());

        long id = database.insert(TABLE_NAME, null, values);
        //Realizar teste de carga
        database.close();
        return id;
    }

    public List<Person> getAllPerson() {
        List<Person> personList = new ArrayList<>();
        String SQL = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);

        if(cursor.moveToFirst()) {
            do {
                Person person = new Person(
                        cursor.getInt(0),
                        cursor.getString(1)
                );
                personList.add(person);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return personList;
    }

    public int updatePerson (Person person) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, person.getNome());
        contentValues.put(PHONE, person.getTelefone());
        contentValues.put(EMAIL, person.getEmail());

        int rowsAffected = database.update(TABLE_NAME, contentValues, KEY_ID + " = ?",
                new String[] {String.valueOf(person.getId())});

        database.close();
        return rowsAffected;
    }

    public int deletePerson(int personId) {
        SQLiteDatabase database = this.getWritableDatabase();
        int rowsAffected = database.delete(TABLE_NAME, KEY_ID + " = ?", new String[]{String.valueOf(personId)});

        database.close();
        return rowsAffected;
    }
}

package com.iesb.android1.trabalho;

public class Contact {

    private String nome;
    private String telefone;
    private String email;


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

   public String toString(){
        return "Nome: " + this.nome + " Telefone: " + this.telefone + " Email: " + this.email;
    }


    public Contact(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }


}

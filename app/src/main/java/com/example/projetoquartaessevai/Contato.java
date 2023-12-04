package com.example.projetoquartaessevai;


public  class Contato {
    int id;
    String nome,numero,email,empresa,relacionamento;

    public Contato(){}

    public Contato(int id,String nome,String numero,String email,String empresa,String relacionamento){
        this.id = id;
        this.nome=nome;
        this.numero = numero;
        this.email= email;
        this.empresa = empresa;
        this.relacionamento= relacionamento;
    }

    public Contato(String nome,String numero,String email,String empresa,String relacionamento){
        this.nome = nome;
        this.numero = numero;
        this.email= email;
        this.empresa = empresa;
        this.relacionamento = relacionamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(String relacionamento) {
        this.relacionamento = relacionamento;
    }
}
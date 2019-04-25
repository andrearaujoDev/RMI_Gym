package model;

import java.io.Serializable;

public class Cliente implements Serializable {
    
    private int id_cli;
    private String nome,sexo,data_nasc,cidade,endereco,telefone,email;

    public Cliente() {
    }

    public Cliente(int id_cli, String nome, String sexo, String data_nasc, String cidade, String endereco, String telefone, String email) {
        this.id_cli = id_cli;
        this.nome = nome;
        this.sexo = sexo;
        this.data_nasc = data_nasc;
        this.cidade = cidade;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId_cli() {
        return id_cli;
    }

    public void setId_cli(int id_cli) {
        this.id_cli = id_cli;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}

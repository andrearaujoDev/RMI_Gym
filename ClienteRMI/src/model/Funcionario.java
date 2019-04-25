package model;

import java.io.Serializable;


public class Funcionario implements Serializable {
    private int id_fun;
    private float salario;
    private String nome,sexo,data_nasc,turno,funcao,telefone,email;

    public Funcionario() {
    }

    public Funcionario(int id_fun, float salario, String nome, String sexo, String data_nasc, String turno, String funcao, String telefone, String email) {
        this.id_fun = id_fun;
        this.salario = salario;
        this.nome = nome;
        this.sexo = sexo;
        this.data_nasc = data_nasc;
        this.turno = turno;
        this.funcao = funcao;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId_fun() {
        return id_fun;
    }

    public void setId_fun(int id_fun) {
        this.id_fun = id_fun;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
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

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
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

package model;

import java.io.Serializable;


public class Exercicio implements Serializable {
    private int id_ex;
    private String nome;
    private String grupo_musc;

    public Exercicio() {
    }

    public Exercicio(int id_ex, String nome, String grupo_musc) {
        this.id_ex = id_ex;
        this.nome = nome;
        this.grupo_musc = grupo_musc;
    }

    public int getId_ex() {
        return id_ex;
    }

    public void setId_ex(int id_ex) {
        this.id_ex = id_ex;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGrupo_musc() {
        return grupo_musc;
    }

    public void setGrupo_musc(String grupo_musc) {
        this.grupo_musc = grupo_musc;
    }
    
    
}

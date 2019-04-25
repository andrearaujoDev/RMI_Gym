package model;

import java.io.Serializable;

public class Ficha_Exercicio implements Serializable {
    private int id_ficha_exercicio,series,repeticoes;
    private int peso;
    private Ficha ficha;
    private Exercicio exercicio;

    public Ficha_Exercicio() {
    }

    public Ficha_Exercicio(int id_ficha_exercicio, int series, int repeticoes, int peso, Ficha ficha, Exercicio exercicio) {
        this.id_ficha_exercicio = id_ficha_exercicio;
        this.series = series;
        this.repeticoes = repeticoes;
        this.peso = peso;
        this.ficha = ficha;
        this.exercicio = exercicio;
    }

    public int getId_ficha_exercicio() {
        return id_ficha_exercicio;
    }

    public void setId_ficha_exercicio(int id_ficha_exercicio) {
        this.id_ficha_exercicio = id_ficha_exercicio;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }
    
    
}

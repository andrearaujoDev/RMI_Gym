package model;

import java.io.Serializable;
import java.time.LocalDate;


public class Ficha implements Serializable{
    private int id_ficha;
    private Cliente cliente;
    private Funcionario funcionario;
    private String data_inicio;
    private String data_termino;

    public Ficha() {
    }

    public Ficha(int id_ficha, Cliente cliente, Funcionario funcionario, String data_inicio, String data_termino) {
        this.id_ficha = id_ficha;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.data_inicio = data_inicio;
        this.data_termino = data_termino;
    }

    public int getId_ficha() {
        return id_ficha;
    }

    public void setId_ficha(int id_ficha) {
        this.id_ficha = id_ficha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getData_termino() {
        return data_termino;
    }

    public void setData_termino(String data_termino) {
        this.data_termino = data_termino;
    }
    
    
}

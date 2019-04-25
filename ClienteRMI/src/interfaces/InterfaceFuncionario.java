package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import model.Funcionario;

public interface InterfaceFuncionario extends Remote {
    
    public String inserirFuncionario(Funcionario f) throws RemoteException;
    public String editarFuncionario(Funcionario f) throws RemoteException;
    public String excluirFuncionario(int id_funcionario) throws RemoteException;
    public ArrayList<Funcionario> selecionarFuncionario(int idFuncionario) throws RemoteException;
    public ArrayList<Funcionario> selecionarTodosFuncionarios() throws RemoteException;
}

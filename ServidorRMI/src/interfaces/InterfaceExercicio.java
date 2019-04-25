package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import model.Exercicio;

public interface InterfaceExercicio extends Remote {
    public String inserirExercicio(Exercicio e) throws RemoteException;
    public String editarExercicio(Exercicio e) throws RemoteException;
    public String excluirExercicio(int id_exercicio) throws RemoteException;
    public ArrayList<Exercicio> selecionarExercicio(int id_exercicio) throws RemoteException;
    public ArrayList<Exercicio> selecionarTodosExercicio() throws RemoteException;
}

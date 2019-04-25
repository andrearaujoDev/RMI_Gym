package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import model.Ficha;


public interface InterfaceFicha extends Remote {
    public String inserirFicha(Ficha f) throws RemoteException;
    public String editarFicha(Ficha f) throws RemoteException;
    public String excluirFicha(int id_ficha) throws RemoteException;
    public ArrayList<Ficha> selecionarFicha(int idFicha) throws RemoteException;
    public ArrayList<Ficha> selecionarTodasFichas() throws RemoteException;
}

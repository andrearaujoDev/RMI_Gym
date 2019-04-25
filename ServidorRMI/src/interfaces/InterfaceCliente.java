package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import model.Cliente;


public interface InterfaceCliente extends Remote {
    public String inserirCliente(Cliente c) throws RemoteException;
    public String editarCliente(Cliente c) throws RemoteException;
    public String excluirCliente(int id_cliente) throws RemoteException;
    public ArrayList<Cliente> selecionarCliente(int idCliente) throws RemoteException;
    public ArrayList<Cliente> selecionarTodosClientes() throws RemoteException;
    
}

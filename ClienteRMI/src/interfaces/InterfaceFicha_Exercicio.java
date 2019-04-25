package interfaces;

import java.rmi.RemoteException;
import java.util.ArrayList;
import model.Ficha_Exercicio;

public interface InterfaceFicha_Exercicio {
    public String inserirFichaExercicio(Ficha_Exercicio f) throws RemoteException;
    public String editarFichaExercicio(Ficha_Exercicio f) throws RemoteException;
    public String excluirFichaExercicio(int id_fichaexercicio) throws RemoteException;
    public ArrayList<Ficha_Exercicio> selecionarFichaExercicio(int idFichaexercicio) throws RemoteException;
    public ArrayList<Ficha_Exercicio> selecionarTodasFichasExercicios() throws RemoteException;
    public ArrayList<Ficha_Exercicio> consultaDados(int idCliente) throws RemoteException;
    public ArrayList<Ficha_Exercicio> consultaDadosNome(String nomeCliente) throws RemoteException;
}

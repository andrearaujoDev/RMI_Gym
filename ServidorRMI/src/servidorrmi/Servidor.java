package servidorrmi;

import interfaces.InterfaceCliente;
import interfaces.InterfaceClienteImplementacao;
import interfaces.InterfaceExercicio;
import interfaces.InterfaceExercicioImplementacao;
import interfaces.InterfaceFicha;
import interfaces.InterfaceFichaImplementacao;
import interfaces.InterfaceFicha_Exercicio;
import interfaces.InterfaceFicha_ExercicioImplementacao;
import interfaces.InterfaceFuncionario;
import interfaces.InterfaceFuncionarioImplementacao;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Servidor {
   public static void main(String[] args){
       try{
            Registry conexao = LocateRegistry.createRegistry(1100);
            InterfaceCliente cliente = new InterfaceClienteImplementacao();
            InterfaceFuncionario funcionario = new InterfaceFuncionarioImplementacao();
            InterfaceExercicio exercicio = new InterfaceExercicioImplementacao();
            InterfaceFicha ficha = new InterfaceFichaImplementacao();
            InterfaceFicha_Exercicio fichaExercicio = new InterfaceFicha_ExercicioImplementacao();
            System.out.println("Servidor Iniciado!");
            
            conexao.bind("cliente", cliente);
            conexao.bind("funcionario", funcionario);
            conexao.bind("exercicio", exercicio);
            conexao.bind("ficha", ficha);
            conexao.bind("fichaExercicio", fichaExercicio);
        }catch(RemoteException | AlreadyBoundException erro){
            System.out.println("Erro na criação da conexão na porta : " + erro.getMessage());
        }
   } 
}

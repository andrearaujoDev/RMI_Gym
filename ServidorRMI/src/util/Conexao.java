package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    public Connection conexao;
    
    public void conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/academia";
            String user = "root";
            String password = "";
            conexao = DriverManager.getConnection(url, user, password);
        }catch (SQLException erro){
            System.out.println("Erro na conexao com o Banco de Dados" + erro.getMessage());
        }catch(ClassNotFoundException erro){
            System.out.println("Driver não encontrado" + erro.getMessage());
        }
    }
    
    public void desconectar(){
        try{
            conexao.close();
        }catch (SQLException erro){
            System.out.println(erro.getMessage());
        }
    }
}

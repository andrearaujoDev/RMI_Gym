package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Exercicio;
import util.Conexao;


public class InterfaceExercicioImplementacao extends UnicastRemoteObject implements InterfaceExercicio{
    
    public InterfaceExercicioImplementacao() throws RemoteException{}
    
    @Override
    public String inserirExercicio(Exercicio e) throws RemoteException {
         String retorno = "";
        Conexao cx = new Conexao();
        cx.conectar();
        String sql = "insert into exercicio(nome,grupo_musc) values (?,?)";
        try{
            PreparedStatement sentenca = cx.conexao.prepareStatement(sql);
            sentenca.setString(1, e.getNome());
            sentenca.setString(2, e.getGrupo_musc());
            if(!sentenca.execute())
                retorno = "Dados Inseridos com sucesso";
            else retorno = "Inserção não efetuada";
        }catch(SQLException erro){
            System.out.println("Erro na sentenca" + erro.getMessage());
        }
        cx.desconectar();
        return retorno;
    }

    @Override
    public String editarExercicio(Exercicio e) throws RemoteException {
        String retorno = "";
        Conexao cx = new Conexao();
        cx.conectar();
        String sSQL = "update exercicio set nome= ?, grupo_musc= ?"
                + "where id_ex= ?";
        try{
            PreparedStatement pst = cx.conexao.prepareStatement(sSQL);
            pst.setString(1, e.getNome());
            pst.setString(2, e.getGrupo_musc());
            pst.setInt(3, e.getId_ex());
            
            int n = pst.executeUpdate();
            if(n != 0){
                retorno = "Editado com sucesso";
            }else{
                retorno = "Erro ao editar";
            }
        }catch(Exception error){
            JOptionPane.showConfirmDialog(null, error);
            retorno = "Erro na sentença : " + error.getMessage();
        }
        cx.desconectar();
        return retorno;
    }

    @Override
    public String excluirExercicio(int id_exercicio) throws RemoteException {
        String retorno = "";
        Conexao cx = new Conexao();
        cx.conectar();
        String sSQL = "delete from exercicio where id_ex = ?";
        try{
            
            PreparedStatement pst = cx.conexao.prepareStatement(sSQL);
            pst.setInt(1, id_exercicio);
            
            int n = pst.executeUpdate();
            if(n != 0){
                retorno = "Deletado com sucesso";
            }else{
                retorno = "Erro ao deletar";
            }
        
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            retorno = "Erro ao Deletar : " + e.getMessage();
        }
        cx.desconectar();
        return retorno;
    }

    @Override
    public ArrayList<Exercicio> selecionarExercicio(int id_exercicio) throws RemoteException {
        Conexao cx = new Conexao();
        cx.conectar();
        String sql = "select * from exercicio where id_ex = " + id_exercicio;
        ArrayList<Exercicio> modelo = new ArrayList<Exercicio>();
        try {
            PreparedStatement sentenca = cx.conexao.prepareStatement(sql);
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()) {
                Exercicio exercicio = new Exercicio();
                exercicio.setId_ex(rs.getInt("id_ex"));
                exercicio.setNome(rs.getString("nome"));
                exercicio.setGrupo_musc(rs.getString("grupo_musc"));
                modelo.add(exercicio);
            }
        } catch (SQLException ex) {
            System.out.println("Erro na sentença SQL: " + ex.getMessage());
        }
        cx.desconectar();
        return modelo;
    }

    @Override
    public ArrayList<Exercicio> selecionarTodosExercicio() throws RemoteException {
        Conexao cx = new Conexao();
        cx.conectar();
        String sql = "select * from exercicio";
        ArrayList<Exercicio> modelo = new ArrayList<Exercicio>();
        try{
            PreparedStatement sentenca = cx.conexao.prepareStatement(sql);
           
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()){
                Exercicio exercicio = new Exercicio();
                exercicio.setId_ex(rs.getInt("id_ex"));
                exercicio.setNome(rs.getString("nome"));
                exercicio.setGrupo_musc(rs.getString("grupo_musc"));
                modelo.add(exercicio);
            }
        }catch(SQLException ex){
            System.out.println("Erro na sentença SQL: "+ex.getMessage());
        }
        cx.desconectar();
        return modelo;
    }
    
}

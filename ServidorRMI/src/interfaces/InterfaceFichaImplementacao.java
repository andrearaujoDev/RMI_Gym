package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Ficha;
import model.Funcionario;
import util.Conexao;

public class InterfaceFichaImplementacao extends UnicastRemoteObject implements InterfaceFicha {
    
    public InterfaceFichaImplementacao() throws RemoteException{}
    
    @Override
    public String inserirFicha(Ficha f) throws RemoteException {
        String retorno = "";
        Conexao cx = new Conexao();
        cx.conectar();
        String sql = "insert into ficha(id_cli,id_fun,data_inicio,data_termino) values (?,?,?,?)";
        try{
            PreparedStatement sentenca = cx.conexao.prepareStatement(sql);
            sentenca.setInt(1, f.getCliente().getId_cli());
            sentenca.setInt(2, f.getFuncionario().getId_fun());
            sentenca.setString(3, f.getData_inicio());
            sentenca.setString(4, f.getData_termino());
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
    public String editarFicha(Ficha f) throws RemoteException {
        String retorno = "";
        Conexao cx = new Conexao();
        cx.conectar();
        String sSQL = "update ficha set id_cli= ?, id_fun= ?, data_inicio= ?, data_termino=? "
                + "where id_ficha= ?";
        try{
            PreparedStatement pst = cx.conexao.prepareStatement(sSQL);
            pst.setInt(1, f.getCliente().getId_cli());
            pst.setInt(2, f.getFuncionario().getId_fun());
            pst.setString(3, f.getData_inicio());
            pst.setString(4, f.getData_termino());
            pst.setInt(5, f.getId_ficha());
            
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
    public String excluirFicha(int id_ficha) throws RemoteException {
        String retorno = "";
        Conexao cx = new Conexao();
        cx.conectar();
        String sSQL = "delete from ficha where id_ficha = ?";
        try{
            
            PreparedStatement pst = cx.conexao.prepareStatement(sSQL);
            pst.setInt(1, id_ficha);
            
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
        return retorno;
    }

    @Override
    public ArrayList<Ficha> selecionarFicha(int idFicha) throws RemoteException {
        Conexao cx = new Conexao();
        cx.conectar();
        String sql = "select * from ficha where id_ficha = " + idFicha;
        ArrayList<Ficha> modelo = new ArrayList<Ficha>();
        try {
            PreparedStatement sentenca = cx.conexao.prepareStatement(sql);
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()) {
                Ficha ficha = new Ficha();
                Cliente cliente = new Cliente();
                Funcionario funcionario = new Funcionario();  
                
                cliente.setId_cli(rs.getInt("id_cli"));
                funcionario.setId_fun(rs.getInt("id_fun"));
                
                ficha.setCliente(cliente);
                ficha.setFuncionario(funcionario);
                ficha.setId_ficha(rs.getInt("id_ficha"));
                ficha.setData_inicio(rs.getString("data_inicio"));
                ficha.setData_termino(rs.getString("data_termino"));
                modelo.add(ficha);
            }
        } catch (SQLException ex) {
            System.out.println("Erro na sentença SQL: " + ex.getMessage());
        }
        cx.desconectar();
        return modelo;
    }

    @Override
    public ArrayList<Ficha> selecionarTodasFichas() throws RemoteException {
        Conexao cx = new Conexao();
        cx.conectar();
        String sql = "select ficha.id_ficha,ficha.data_inicio,ficha.data_termino, cliente.nome_cli, funcionario.nome_fun from ((ficha "
                    + "inner join cliente on cliente.id_cli = ficha.id_cli) inner join funcionario on funcionario.id_fun = ficha.id_fun) ";
        ArrayList<Ficha> modelo = new ArrayList<Ficha>();
        try{
            PreparedStatement sentenca = cx.conexao.prepareStatement(sql);
           
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()){
                Ficha ficha = new Ficha();
                ficha.setId_ficha(rs.getInt("id_ficha"));
                
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome_cli"));
                
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(rs.getString("nome_fun"));
                
                ficha.setCliente(cliente);
                ficha.setFuncionario(funcionario);
                ficha.setData_inicio(rs.getString("data_inicio"));
                ficha.setData_termino(rs.getString("data_termino"));
                modelo.add(ficha);
            }
        }catch(SQLException ex){
            System.out.println("Erro na sentença SQL: "+ex.getMessage());
        }
        cx.desconectar();
        return modelo;
    }
    
}

package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Cliente;
import util.Conexao;


public class InterfaceClienteImplementacao extends UnicastRemoteObject implements InterfaceCliente {
    
    public InterfaceClienteImplementacao() throws RemoteException{}

    @Override
    public String inserirCliente(Cliente c) throws RemoteException {
        String retorno = "";
        Conexao cx = new Conexao();
        cx.conectar();
        String sql = "insert into cliente(nome_cli,sexo,data_nascimento,cidade,endereco,telefone,email) values (?,?,?,?,?,?,?)";
        try{
            PreparedStatement sentenca = cx.conexao.prepareStatement(sql);
            sentenca.setString(1, c.getNome());
            sentenca.setString(2, c.getSexo());
            sentenca.setString(3, c.getData_nasc());
            sentenca.setString(4, c.getCidade());
            sentenca.setString(5, c.getEndereco());
            sentenca.setString(6, c.getTelefone());
            sentenca.setString(7, c.getEmail());
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
    public String editarCliente(Cliente c) throws RemoteException {
        String retorno = "";
        Conexao cx = new Conexao();
        cx.conectar();
        String sSQL = "update cliente set nome_cli= ?, sexo= ?, data_nascimento= ?, cidade= ?, endereco= ?, telefone= ?, email= ?"
                + "where id_cli= ?";
        try{
            PreparedStatement pst = cx.conexao.prepareStatement(sSQL);
            pst.setString(1, c.getNome());
            pst.setString(2, c.getSexo());
            pst.setString(3, c.getData_nasc());
            pst.setString(4, c.getCidade());
            pst.setString(5, c.getEndereco());
            pst.setString(6, c.getTelefone());
            pst.setString(7, c.getEmail());
            pst.setInt(8, c.getId_cli());
            
            int n = pst.executeUpdate();
            if(n != 0){
                retorno = "Editado com sucesso";
            }else{
                retorno = "Erro ao editar";
            }
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            retorno = "Erro na sentença : " + e.getMessage();
        }
        return retorno;
    }

    @Override
    public String excluirCliente(int id_cliente) throws RemoteException {
        String retorno = "";
        Conexao cx = new Conexao();
        cx.conectar();
        String sSQL = "delete from cliente where id_cli = ?";
        try{
            
            PreparedStatement pst = cx.conexao.prepareStatement(sSQL);
            pst.setInt(1, id_cliente);
            
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
   public ArrayList<Cliente> selecionarCliente(int idCliente) throws RemoteException {
        Conexao cx = new Conexao();
        cx.conectar();
        String sql = "select * from cliente where id_cli = " + idCliente;
        ArrayList<Cliente> modelo = new ArrayList<Cliente>();
        try {
            PreparedStatement sentenca = cx.conexao.prepareStatement(sql);
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cli(rs.getInt("id_cli"));
                cliente.setNome(rs.getString("nome_cli"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setData_nasc(rs.getString("data_nascimento"));
                cliente.setCidade(rs.getString("cidade"));           
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                modelo.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Erro na sentença SQL: " + ex.getMessage());
        }
        cx.desconectar();
        return modelo;
    }
    
    @Override
    public ArrayList<Cliente> selecionarTodosClientes() throws RemoteException {
        Conexao cx = new Conexao();
        cx.conectar();
        String sql = "select * from cliente";
        ArrayList<Cliente> modelo = new ArrayList<Cliente>();
        try{
            PreparedStatement sentenca = cx.conexao.prepareStatement(sql);
           
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId_cli(rs.getInt("id_cli"));
                cliente.setNome(rs.getString("nome_cli"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setData_nasc(rs.getString("data_nascimento"));
                cliente.setCidade(rs.getString("cidade"));           
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                modelo.add(cliente);
            }
        }catch(SQLException ex){
            System.out.println("Erro na sentença SQL: "+ex.getMessage());
        }
        cx.desconectar();
        return modelo;
    }
    
    
}

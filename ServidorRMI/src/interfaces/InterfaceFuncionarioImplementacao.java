package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Funcionario;
import util.Conexao;

public class InterfaceFuncionarioImplementacao extends UnicastRemoteObject implements InterfaceFuncionario {
    
    public InterfaceFuncionarioImplementacao() throws RemoteException{};

    @Override
    public String inserirFuncionario(Funcionario f) throws RemoteException {
        String retorno = "";
        Conexao cx = new Conexao();
        cx.conectar();
        String sql = "insert into funcionario(nome_fun,sexo,data_nascimento,salario,turno,funcao,telefone,email) values (?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement sentenca = cx.conexao.prepareStatement(sql);
            sentenca.setString(1, f.getNome());
            sentenca.setString(2, f.getSexo());
            sentenca.setString(3, f.getData_nasc());
            sentenca.setFloat(4, f.getSalario());
            sentenca.setString(5, f.getTurno());
            sentenca.setString(6, f.getFuncao());
            sentenca.setString(7, f.getTelefone());
            sentenca.setString(8, f.getEmail());
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
    public String editarFuncionario(Funcionario f) throws RemoteException {
       String retorno = "";
        Conexao cx = new Conexao();
        cx.conectar();
        String sql = "update funcionario set nome_fun= ?, sexo= ?, data_nascimento= ?, salario= ?, turno= ?, funcao= ?,telefone=?, email= ?"
                + "where id_fun= ?";
        try{
            PreparedStatement sentenca = cx.conexao.prepareStatement(sql);
            sentenca.setString(1, f.getNome());
            sentenca.setString(2, f.getSexo());
            sentenca.setString(3, f.getData_nasc());
            sentenca.setFloat(4, f.getSalario());
            sentenca.setString(5, f.getTurno());
            sentenca.setString(6, f.getFuncao());
            sentenca.setString(7, f.getTelefone());
            sentenca.setString(8, f.getEmail());
            sentenca.setInt(9,f.getId_fun());
            if(!sentenca.execute())
                retorno = "Dados editados com sucesso";
            else retorno = "Edição não efetuada";
        }catch(SQLException erro){
            System.out.println("Erro na sentenca" + erro.getMessage());
        }
        cx.desconectar();
        return retorno;
    }

    @Override
    public String excluirFuncionario(int id_funcionario) throws RemoteException {
        String retorno = "";
        Conexao cx = new Conexao();
        cx.conectar();
        String sSQL = "delete from funcionario where id_fun = ?";
        try{
            
            PreparedStatement pst = cx.conexao.prepareStatement(sSQL);
            pst.setInt(1, id_funcionario);
            
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
    public ArrayList<Funcionario> selecionarFuncionario(int idFuncionario) throws RemoteException {
        Conexao cx = new Conexao();
        cx.conectar();
        String sql = "select * from funcionario where id_fun = " + idFuncionario;
        ArrayList<Funcionario> modelo = new ArrayList<Funcionario>();
        try {
            PreparedStatement sentenca = cx.conexao.prepareStatement(sql);
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId_fun(rs.getInt("id_fun"));
                funcionario.setNome(rs.getString("nome_fun"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setData_nasc(rs.getString("data_nascimento"));
                funcionario.setSalario(rs.getFloat("salario"));
                funcionario.setTurno(rs.getString("turno"));           
                funcionario.setFuncao(rs.getString("funcao"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEmail(rs.getString("email"));
                modelo.add(funcionario);
            }
        } catch (SQLException ex) {
            System.out.println("Erro na sentença SQL: " + ex.getMessage());
        }
        cx.desconectar();
        return modelo;
    }

    @Override
    public ArrayList<Funcionario> selecionarTodosFuncionarios() throws RemoteException {
        Conexao cx = new Conexao();
        cx.conectar();
        String sql = "select * from funcionario ";
        ArrayList<Funcionario> modelo = new ArrayList<Funcionario>();
        try{
            PreparedStatement sentenca = cx.conexao.prepareStatement(sql);
           
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setId_fun(rs.getInt("id_fun"));
                funcionario.setNome(rs.getString("nome_fun"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setData_nasc(rs.getString("data_nascimento"));
                funcionario.setSalario(rs.getFloat("salario"));
                funcionario.setTurno(rs.getString("turno"));           
                funcionario.setFuncao(rs.getString("funcao"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEmail(rs.getString("email"));
                modelo.add(funcionario);
            }
        }catch(SQLException ex){
            System.out.println("Erro na sentença SQL: "+ex.getMessage());
        }
        cx.desconectar();
        return modelo;
    }
    
    
    
}

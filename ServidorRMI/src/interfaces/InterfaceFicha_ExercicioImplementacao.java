package interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Exercicio;
import model.Ficha;
import model.Ficha_Exercicio;
import model.Funcionario;
import util.Conexao;

public class InterfaceFicha_ExercicioImplementacao extends UnicastRemoteObject implements InterfaceFicha_Exercicio {
    
    public InterfaceFicha_ExercicioImplementacao() throws RemoteException{}

    @Override
    public String inserirFichaExercicio(Ficha_Exercicio f) throws RemoteException {
        String retorno = "";
        Conexao cx = new Conexao();
        cx.conectar();
        String sql = "insert into ficha_exercicio(id_ficha,id_ex,series,peso,repeticoes) values (?,?,?,?,?)";
        try{
            PreparedStatement sentenca = cx.conexao.prepareStatement(sql);
            sentenca.setInt(1, f.getFicha().getId_ficha());
            sentenca.setInt(2, f.getExercicio().getId_ex());
            sentenca.setInt(3, f.getSeries());
            sentenca.setInt(4, f.getPeso());
            sentenca.setInt(5, f.getRepeticoes());
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
    public String editarFichaExercicio(Ficha_Exercicio f) throws RemoteException {
       String retorno = "";
        Conexao cx = new Conexao();
        cx.conectar();
        String sSQL = "update ficha_exercicio set id_ficha= ?, id_ex= ?, series= ?, peso=?, repeticoes=? "
                + "where id_ficha_exercicio= ?";
        try{
            PreparedStatement pst = cx.conexao.prepareStatement(sSQL);
            pst.setInt(1, f.getFicha().getId_ficha());
            pst.setInt(2, f.getExercicio().getId_ex());
            pst.setInt(3, f.getSeries());
            pst.setInt(4, f.getPeso());
            pst.setInt(5, f.getRepeticoes());
            pst.setInt(6, f.getId_ficha_exercicio());
            
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
    public String excluirFichaExercicio(int id_fichaexercicio) throws RemoteException {
       String retorno = "";
        Conexao cx = new Conexao();
        cx.conectar();
        String sSQL = "delete from ficha_exercicio where id_ficha_exercicio = ?";
        try{
            
            PreparedStatement pst = cx.conexao.prepareStatement(sSQL);
            pst.setInt(1, id_fichaexercicio);
            
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
    public ArrayList<Ficha_Exercicio> selecionarFichaExercicio(int idFichaexercicio) throws RemoteException {
        Conexao cx = new Conexao();
        cx.conectar();
        String sql = "select id_ficha_exercicio,series,peso,repeticoes,ficha.id_ficha,exercicio.id_ex from ficha_exercicio"
                    + " inner join ficha on ficha.id_ficha = ficha_exercicio.id_ficha "
                    + " inner join exercicio on exercicio.id_ex = ficha_exercicio.id_ex where id_ficha_exercicio = " + idFichaexercicio;
        ArrayList<Ficha_Exercicio> modelo = new ArrayList<Ficha_Exercicio>();
        try {
            PreparedStatement sentenca = cx.conexao.prepareStatement(sql);
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()) {
                Ficha_Exercicio fichaExercicio = new Ficha_Exercicio();
                Ficha ficha = new Ficha();
                Exercicio exercicio = new Exercicio();  
                
                ficha.setId_ficha(rs.getInt("id_ficha"));
                exercicio.setId_ex(rs.getInt("id_ex"));
                
                fichaExercicio.setFicha(ficha);
                fichaExercicio.setExercicio(exercicio);
                fichaExercicio.setId_ficha_exercicio(rs.getInt("id_ficha_exercicio"));
                fichaExercicio.setSeries(rs.getInt("series"));
                fichaExercicio.setPeso(rs.getInt("peso"));
                fichaExercicio.setRepeticoes(rs.getInt("repeticoes"));
                
                modelo.add(fichaExercicio);
            }
        } catch (SQLException ex) {
            System.out.println("Erro na sentença SQL: " + ex.getMessage());
        }
        cx.desconectar();
        return modelo;
    }

    @Override
    public ArrayList<Ficha_Exercicio> selecionarTodasFichasExercicios() throws RemoteException {
        Conexao cx = new Conexao();
        cx.conectar();
        String sql = "select id_ficha_exercicio,series,peso,repeticoes,ficha.id_ficha,cliente.nome_cli,exercicio.nome,exercicio.grupo_musc from "
                    + "ficha_exercicio,ficha,cliente,exercicio where ficha_exercicio.id_ficha = ficha.id_ficha "
                    + " and ficha.id_cli = cliente.id_cli and exercicio.id_ex = ficha_exercicio.id_ex";
        ArrayList<Ficha_Exercicio> modelo = new ArrayList<Ficha_Exercicio>();
        try {
            PreparedStatement sentenca = cx.conexao.prepareStatement(sql);
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()) {
                Ficha_Exercicio fichaExercicio = new Ficha_Exercicio();
                Cliente cliente = new Cliente();
                Ficha ficha = new Ficha();
                Exercicio exercicio = new Exercicio();  
                
                cliente.setNome(rs.getString("nome_cli"));
                ficha.setCliente(cliente);
                
                exercicio.setNome(rs.getString("nome"));
                exercicio.setGrupo_musc(rs.getString("grupo_musc"));
                
                fichaExercicio.setFicha(ficha);
                fichaExercicio.setExercicio(exercicio);
                fichaExercicio.setId_ficha_exercicio(rs.getInt("id_ficha_exercicio"));
                fichaExercicio.setSeries(rs.getInt("series"));
                fichaExercicio.setPeso(rs.getInt("peso"));
                fichaExercicio.setRepeticoes(rs.getInt("repeticoes"));
                
                modelo.add(fichaExercicio);
            }
        } catch (SQLException ex) {
            System.out.println("Erro na sentença SQL: " + ex.getMessage());
        }
        cx.desconectar();
        return modelo;
    }

    @Override
    public ArrayList<Ficha_Exercicio> consultaDados(int idCliente) throws RemoteException {
        Conexao cx = new Conexao();
        cx.conectar();
        String SQL1 = "select id_ficha_exercicio,series,peso,repeticoes,"
                    + "ficha.id_ficha,ficha.id_cli,ficha.id_fun,ficha.data_inicio,ficha.data_termino,"
                    + "cliente.nome_cli,funcionario.nome_fun,"
                    + "exercicio.nome,exercicio.grupo_musc "
                    + "from ficha_exercicio,ficha,cliente,funcionario,exercicio where ficha_exercicio.id_ficha = ficha.id_ficha "
                    + "and ficha.id_cli = cliente.id_cli "
                    + "and ficha.id_fun = funcionario.id_fun "
                    + "and ficha_exercicio.id_ex = exercicio.id_ex "
                    + "and cliente.id_cli = " + idCliente;
        ArrayList<Ficha_Exercicio> modelo = new ArrayList<Ficha_Exercicio>();
        try {
            PreparedStatement sentenca = cx.conexao.prepareStatement(SQL1);
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                Funcionario funcionario = new Funcionario();
                Ficha ficha = new Ficha();
                System.out.println("estou aqq");
                cliente.setId_cli(rs.getInt("id_cli"));
                cliente.setNome(rs.getString("nome_cli"));
                funcionario.setNome(rs.getString("nome_fun"));
                ficha.setCliente(cliente);
                ficha.setFuncionario(funcionario);
                ficha.setData_inicio(rs.getString("data_inicio"));
                ficha.setData_termino(rs.getString("data_termino"));
                
                Exercicio exercicio = new Exercicio();
                exercicio.setNome(rs.getString("nome"));
                exercicio.setGrupo_musc(rs.getString("grupo_musc"));
                
                Ficha_Exercicio fichaExercicio = new Ficha_Exercicio();
                fichaExercicio.setFicha(ficha);
                fichaExercicio.setExercicio(exercicio);
                fichaExercicio.setSeries(rs.getInt("series"));
                fichaExercicio.setPeso(rs.getInt("peso"));
                fichaExercicio.setRepeticoes(rs.getInt("repeticoes"));
                
                modelo.add(fichaExercicio);
            }
        } catch (SQLException ex) {
            System.out.println("Erro na sentença SQL: " + ex.getMessage());
        }
        cx.desconectar();
        return modelo;
    }

    @Override
    public ArrayList<Ficha_Exercicio> consultaDadosNome(String nomeCliente) throws RemoteException {
        Conexao cx = new Conexao();
        cx.conectar();
        String SQL1 = "select id_ficha_exercicio,series,peso,repeticoes,"
                    + "ficha.id_ficha,ficha.id_cli,ficha.id_fun,ficha.data_inicio,ficha.data_termino,"
                    + "cliente.nome_cli,funcionario.nome_fun,"
                    + "exercicio.nome,exercicio.grupo_musc "
                    + "from ficha_exercicio,ficha,cliente,funcionario,exercicio where ficha_exercicio.id_ficha = ficha.id_ficha "
                    + "and ficha.id_cli = cliente.id_cli "
                    + "and ficha.id_fun = funcionario.id_fun "
                    + "and ficha_exercicio.id_ex = exercicio.id_ex "
                    + "and cliente.nome_cli = '" + nomeCliente + "'";
        ArrayList<Ficha_Exercicio> modelo = new ArrayList<Ficha_Exercicio>();
        try {
            PreparedStatement sentenca = cx.conexao.prepareStatement(SQL1);
            ResultSet rs = sentenca.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                Funcionario funcionario = new Funcionario();
                Ficha ficha = new Ficha();
                cliente.setId_cli(rs.getInt("id_cli"));
                cliente.setNome(rs.getString("nome_cli"));
                funcionario.setNome(rs.getString("nome_fun"));
                ficha.setCliente(cliente);
                ficha.setFuncionario(funcionario);
                ficha.setData_inicio(rs.getString("data_inicio"));
                ficha.setData_termino(rs.getString("data_termino"));
                
                Exercicio exercicio = new Exercicio();
                exercicio.setNome(rs.getString("nome"));
                exercicio.setGrupo_musc(rs.getString("grupo_musc"));
                
                Ficha_Exercicio fichaExercicio = new Ficha_Exercicio();
                fichaExercicio.setFicha(ficha);
                fichaExercicio.setExercicio(exercicio);
                fichaExercicio.setSeries(rs.getInt("series"));
                fichaExercicio.setPeso(rs.getInt("peso"));
                fichaExercicio.setRepeticoes(rs.getInt("repeticoes"));
                
                modelo.add(fichaExercicio);
            }
        } catch (SQLException ex) {
            System.out.println("Erro na sentença SQL: " + ex.getMessage());
        }
        cx.desconectar();
        return modelo;
    }
    
    
}

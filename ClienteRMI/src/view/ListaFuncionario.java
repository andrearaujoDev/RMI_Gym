/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import interfaces.InterfaceFuncionario;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.table.DefaultTableModel;
import model.Funcionario;

/**
 *
 * @author arauj
 */
public class ListaFuncionario extends javax.swing.JInternalFrame {

    /**
     * Creates new form ListaFuncionario
     */
    String ip = "127.0.0.1";
    public ListaFuncionario() {
        initComponents();
        this.listarFuncionario();
    }
    
     public void listarFuncionario(){
        DefaultTableModel modelo = (DefaultTableModel)tableFuncionario.getModel();
        modelo.setNumRows(0);
        Registry conexao;
        try{
            conexao = LocateRegistry.getRegistry(ip,1100);
            InterfaceFuncionario funcionarioServico =(InterfaceFuncionario) conexao.lookup("funcionario"); 

            for(Funcionario f: funcionarioServico.selecionarTodosFuncionarios()){
                modelo.addRow(new Object[]{      
                f.getId_fun(),
                f.getNome(),
                f.getSexo(),
                f.getData_nasc(),
                f.getSalario(),
                f.getTurno(),
                f.getFuncao(),
                f.getTelefone(),
                f.getEmail(),
                });
            }
        }catch (RemoteException erro){
            System.out.println("Erro de conexao com o servidor : " + erro.getMessage());
        }catch(NotBoundException erro){
            System.out.println("Erro de conexao com o servidor : " + erro.getMessage());
        } 
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableFuncionario = new javax.swing.JTable();

        setClosable(true);

        tableFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Sexo", "Data de Nascimento", "Salario", "Turno", "Função", "Telefone", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableFuncionario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
        );

        setBounds(0, 0, 597, 360);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableFuncionario;
    // End of variables declaration//GEN-END:variables
}
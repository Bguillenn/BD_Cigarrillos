/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.EstadoRegistro;
import modelos.Nacion;
import modelos.ControllerResponse;
import controladores.EstadoRegistrosController;
import controladores.NacionesController;
import config.Connector;
import java.util.ArrayList;


/**
 *
 * @author Brayan Guillen N
 */
public class MEstadoRegistroView extends javax.swing.JFrame {
    
    private int estado = 0;
    /*
    Maneja elestado del formulario
    0 = Ninguna accion
    1 = Adicionando
    2 = Modificando
    3 = Eliminando
    4 = Inactivando
    5 = Reactivando
    */
    private EstadoRegistrosController estRegController;
    private Connector db;
    /**
     * Creates new form MNacionesView
     */
    public MEstadoRegistroView() {
        db = new Connector();
        estRegController = new EstadoRegistrosController(db.getConnection());
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);
        this.txtCodigo.setEnabled(false);
        this.txtDescripcion.setEnabled(false);
        this.setLocationRelativeTo(null);
        cargarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnAdicionar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Maestro de naciones");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ESTADO REGISTRO");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Gestiona los estados de registro del sistema");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del estado de registro"));

        jLabel3.setText("Codigo:");

        jLabel4.setText("Descripcion:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabla de registros"));

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblData.setSelectionForeground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tblData);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion del estado"));

        jLabel6.setText("Accion:");

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstado.setText("Ninguna");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEstado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdicionar)
                .addGap(18, 18, 18)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(btnActualizar)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar)
                    .addComponent(btnActualizar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed

        this.cambiarEstado(1);
        this.txtCodigo.setText("");
        this.txtDescripcion.setText("");
        this.txtCodigo.setEnabled(true);
        this.txtDescripcion.setEnabled(true);
        this.txtCodigo.requestFocus();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int indexRow = this.tblData.getSelectedRow();
        if(indexRow == -1){
            JOptionPane.showMessageDialog(this, "Tiene que selecionar un registro de la tabla", "Elegir uno", JOptionPane.ERROR_MESSAGE);
            this.btnCancelar.doClick();
            return;
        }
        this.cambiarEstado(2);
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblData.getModel();
        EstadoRegistro estReg = estRegController.search( (int) modeloTabla.getValueAt(indexRow, 0));
        this.txtCodigo.setText(estReg.getCodigo()+"");
        this.txtDescripcion.setText(estReg.getDescripcion());
        this.txtCodigo.setEnabled(false);
        this.txtDescripcion.setEnabled(true);
        this.txtDescripcion.requestFocus();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        if(estado != 0){
            int resp = JOptionPane.showConfirmDialog(this, "Estas realizando una acción ¿Realmente deseas salir?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(resp == 0) this.dispose();
        }else{
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int indexRow = this.tblData.getSelectedRow();
        if(indexRow == -1){
            JOptionPane.showMessageDialog(this, "Tiene que selecionar un registro de la tabla", "Elegir uno", JOptionPane.ERROR_MESSAGE);
            this.btnCancelar.doClick();
            return;
        }
        this.cambiarEstado(3);
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblData.getModel();
        EstadoRegistro estReg = estRegController.search((int) modeloTabla.getValueAt(indexRow, 0));
        this.txtCodigo.setText(estReg.getCodigo()+"");
        this.txtDescripcion.setText(estReg.getDescripcion());
        this.txtCodigo.setEnabled(false);
        this.txtDescripcion.setEnabled(false);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.cambiarEstado(0);
        this.txtCodigo.setText("");
        this.txtDescripcion.setText("");
        this.txtCodigo.setEnabled(false);
        this.txtDescripcion.setEnabled(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        switch(estado){
            case 0:
                JOptionPane.showMessageDialog(this, "Ninguna accion se esta realizando", "Informacion", JOptionPane.WARNING_MESSAGE);
                break;
            case 1:
                adicionarNuevo();
                break;
            case 2:
                modificarRegistro();
                break;
            case 3:
                eliminarRegistro();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Error accion no reconocida", "Error en la accion", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void cambiarEstado(int estado){
        this.estado = estado;
        String text = "";
        switch(estado){
            case 0: text = "Ninguna"; break;
            case 1: text = "Adicionando"; break;
            case 2: text = "Modificando"; break;
            case 3: text = "Eliminando"; break;
            case 4: text = "Inactivando"; break;
            case 5: text = "Reactivando"; break;
        }
        lblEstado.setText(text);
    }

    private void cargarTabla(){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        ArrayList<EstadoRegistro> data = estRegController.getAll();
        modeloTabla.setColumnIdentifiers(new Object[]{"Codigo", "Descripcion"});
        EstadoRegistro estReg;
        try{
             for(int i=0; i < data.size(); i++){
                     modeloTabla.addRow(new Object[]{
                        data.get(i).getCodigo(),
                        data.get(i).getDescripcion(),
                     });
             }
             this.tblData.setModel(modeloTabla);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Ocurrio un error durante la carga de datos", "Error inesperado", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }
    
    private void adicionarNuevo(){
        String ventanaTitle = "Informacion de adiccionar";
        if(txtCodigo.getText().isBlank() || txtDescripcion.getText().isBlank()){
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos", ventanaTitle, JOptionPane.ERROR_MESSAGE);
            return;
        }
        int codigo;
        try{
            codigo = Integer.parseInt(this.txtCodigo.getText());
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "El campo codigo debe ser un numero entero", ventanaTitle,JOptionPane.ERROR_MESSAGE);
            return;
        }
        EstadoRegistro estReg = new EstadoRegistro(
                codigo,
                this.txtDescripcion.getText()
        );
        ControllerResponse resp = estRegController.adiccionar(estReg);
        if(resp.isOk()){
            JOptionPane.showMessageDialog(this, resp.getMessage(), ventanaTitle, JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
            btnCancelar.doClick();
        }else{
            JOptionPane.showMessageDialog(this, resp.getMessage(), ventanaTitle, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void modificarRegistro(){
        String ventanaTitle = "Información de la modificacion";
        if(this.txtDescripcion.getText().isBlank()){
            JOptionPane.showMessageDialog(this, "El valor del nombre no puede ser vacio", ventanaTitle, JOptionPane.ERROR_MESSAGE);
            return;
        }
        int opc = JOptionPane.showConfirmDialog(this, "¿Realmente deseas realizar los cambios?", ventanaTitle, JOptionPane.YES_NO_OPTION);
        if(opc == JOptionPane.YES_OPTION){
            EstadoRegistro estReg = estRegController.search(Integer.parseInt(this.txtCodigo.getText()));
            if(estReg != null){
                estReg.setDescripcion(this.txtDescripcion.getText());
                ControllerResponse resp = estRegController.modificar(estReg);
                if(resp.isOk()){
                    JOptionPane.showMessageDialog(this, resp.getMessage(), ventanaTitle, JOptionPane.INFORMATION_MESSAGE);
                    cargarTabla();
                }else{
                    JOptionPane.showMessageDialog(this, resp.getMessage(), ventanaTitle, JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(this, "No se encontro el registro", ventanaTitle, JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Accion cancelada con exito", ventanaTitle, JOptionPane.INFORMATION_MESSAGE);
        }
        btnCancelar.doClick();
    }
    
    private void eliminarRegistro(){
        String ventanaTitle = "Información de la eliminación";
        int opc = JOptionPane.showConfirmDialog(this, "¿Realmente desea eliminar el registro?", ventanaTitle, JOptionPane.YES_NO_OPTION);
        if(opc == JOptionPane.YES_OPTION){
            ControllerResponse resp = estRegController.eliminar(Integer.parseInt(this.txtCodigo.getText()));
            if(resp.isOk()){
                JOptionPane.showMessageDialog(this, resp.getMessage(), ventanaTitle, JOptionPane.INFORMATION_MESSAGE);
                cargarTabla();
            }else{
                JOptionPane.showMessageDialog(this, resp.getMessage(), ventanaTitle, JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Operacion cancelada con exito", ventanaTitle, JOptionPane.INFORMATION_MESSAGE);
        }
        btnCancelar.doClick();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MEstadoRegistroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MEstadoRegistroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MEstadoRegistroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MEstadoRegistroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MEstadoRegistroView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}

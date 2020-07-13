/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelos.EstadoRegistro;

/**
 *
 * @author Brayan Guillen N
 */
public class EstadoRegistrosController {
    
    private Connection conexion;
    
    public EstadoRegistrosController(Connection con){
        this.conexion = con;
    }
    
    public ArrayList<EstadoRegistro> getAll(){
        String sql = "SELECT * FROM "+EstadoRegistro.TABLE_NAME;
        ArrayList<EstadoRegistro> items = new ArrayList<>();
        PreparedStatement pstm;
        ResultSet rs;
        try{
            pstm = conexion.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                items.add(new EstadoRegistro(
                        rs.getInt("EstRegCod"),
                        rs.getString("EstRegDes")
                ));
            }
            return items;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public EstadoRegistro search(int cod){
        String sql = "SELECT * FROM "+EstadoRegistro.TABLE_NAME+" WHERE EstRegCod = ?";
        PreparedStatement pstm;
        ResultSet rs;  
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setInt(1, cod);
            rs = pstm.executeQuery();
            if(rs.next()){
                return new EstadoRegistro(
                        rs.getInt("EstRegCod"),
                        rs.getString("EstRegDes")
                );
            }else{
                System.out.println("No se encontro el registro");
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}

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
import modelos.ControllerResponse;
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
    
    public ControllerResponse adiccionar(EstadoRegistro estReg){
        String sql = "INSERT INTO "+EstadoRegistro.TABLE_NAME+" VALUES(?,?)";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setInt(1, estReg.getCodigo());
            pstm.setString(2, estReg.getDescripcion());
            pstm.executeUpdate();
        }catch(SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            return new ControllerResponse("Codigo de estado de registro duplicado, porfavor ingresa otro", false);
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al adicionar el Estado de Registro, intentalo denuevo", false);
        }
        return new ControllerResponse("Registro añadido satisfactoriamente", true);
    }
    
    public ControllerResponse modificar(EstadoRegistro estReg){
        if(estReg.getCodigo() == -1){
            return new ControllerResponse("El registro debe tener un codigo de nacion existente", false);
        }
        String sql = "UPDATE "+EstadoRegistro.TABLE_NAME+" SET EstRegDes = ? WHERE EstRegCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, estReg.getDescripcion());
            pstm.setInt(2, estReg.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al actualizar, intentalo de nuevo", false);
        }
        return new ControllerResponse("Se actualizo el registro con exito", true);
    }
    
    public ControllerResponse eliminar(int cod){
        EstadoRegistro estReg = this.search(cod);
        if(estReg == null)
            return new ControllerResponse("No se pudo encontrar la nacion que desea eliminar", false);
        String sql = "DELETE FROM "+EstadoRegistro.TABLE_NAME+" WHERE EstRegCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setInt(1, estReg.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error eliminado el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se elimino el registro con exito", true);
    }
    
}

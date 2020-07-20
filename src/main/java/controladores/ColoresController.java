/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import modelos.ColorM;
import modelos.ControllerResponse;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Brayan Guillen N
 */
public class ColoresController {
    
    private Connection conexion;
    
    public ColoresController(Connection con){
        this.conexion = con;
    }
    
    public ArrayList<ColorM> getAll(){
        String sql = "SELECT * FROM "+ColorM.TABLE_NAME;
        ArrayList<ColorM> items = new ArrayList<>();
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                items.add(new ColorM(
                        rs.getString("ColCod"),
                        rs.getString("ColNom"),
                        rs.getInt("ColRed"),
                        rs.getInt("ColGre"),
                        rs.getInt("ColBlu"),
                        rs.getInt("ColEstReg")
                ));
            }
            return items;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }   
    }
    
    public ColorM search(String cod){
        String sql =  "SELECT * FROM "+ColorM.TABLE_NAME+" WHERE ColCod = ?";
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, cod);
            rs = pstm.executeQuery();
            if(rs.next()){
                return new ColorM(
                        rs.getString("ColCod"),
                        rs.getString("ColNom"),
                        rs.getInt("ColRed"),
                        rs.getInt("ColGre"),
                        rs.getInt("ColBlu"),
                        rs.getInt("ColEstReg")
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ControllerResponse adiccionar(ColorM color){
        String sql = "INSERT INTO "+ColorM.TABLE_NAME+" VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, color.getCodigo());
            pstm.setString(2, color.getNombre());
            pstm.setInt(3, color.getRojo());
            pstm.setInt(4, color.getVerde());
            pstm.setInt(5, color.getAzul());
            pstm.setInt(6, color.getEstadoRegistro());
            pstm.executeUpdate();
        }catch(SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            return new ControllerResponse("Codigo del color duplicado, porfavor ingresa otro", false);
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al adicionar el color, intentalo denuevo", false);
        }
        return new ControllerResponse("Registro añadido satisfactoriamente", true);
    }
    
    public ControllerResponse modificar(ColorM color){
        if(color.getCodigo() == null){
            System.err.println("Tiene que tener un codigo de color");
            return new ControllerResponse("El registro debe tener un codigo de color existente", false);
        }
        String sql = "UPDATE "+ColorM.TABLE_NAME+" SET ColNom = ?, ColRed = ?, ColGre = ?, ColBlu = ? WHERE ColCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, color.getNombre());
            pstm.setInt(2, color.getRojo());
            pstm.setInt(3, color.getVerde());
            pstm.setInt(4, color.getAzul());
            pstm.setString(5, color.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al actualizar, intentalo de nuevo", false);
        }
        return new ControllerResponse("Se actualizo el registro con exito", true);
    }
    
    public ControllerResponse eliminar(String cod){
        ColorM color = this.search(cod);
        if(color == null)
            return new ControllerResponse("No se pudo encontrar el color que desea eliminar", false);
        String sql = "UPDATE "+ColorM.TABLE_NAME+" SET ColEstReg = 2 WHERE ColCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, color.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error eliminado el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se elimino el registro con exito", true);
    }
    
    public ControllerResponse inactivar(String cod){
        ColorM color = this.search(cod);
        if(color == null)
            return new ControllerResponse("No se pudo encontrar el color que desea inactivar", false);
        String sql = "UPDATE "+ColorM.TABLE_NAME+" SET ColEstReg = 3 WHERE ColCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, color.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error durante la desactivacion del registro, intentelo denuevo", false);
        }
        return new ControllerResponse("Se inactivo el registro con exito", true);
    }
    
    public ControllerResponse reactivar(String cod){
        ColorM color = this.search(cod);
        if(color == null)
            return new ControllerResponse("No se pudo encontrar el color que desea reactivar", false);
        String sql = "UPDATE "+ColorM.TABLE_NAME+" SET ColEstReg = 1 WHERE ColCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, color.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error reactivando el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se reactivo el registro con exito", true);
    }
}

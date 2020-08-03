/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import modelos.Distrito;
import modelos.ControllerResponse;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Brayan Guillen N
 */
public class DistritosController {
    
    private Connection conexion;
    
    public DistritosController(Connection con){
        this.conexion = con;
    }
    
    public ArrayList<Distrito> getAll(){
        String sql = "SELECT * FROM "+Distrito.TABLE_NAME;
        ArrayList<Distrito> items = new ArrayList<>();
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                items.add(new Distrito(
                        rs.getString("DisCod"),
                        rs.getString("DisNom"),
                        rs.getString("ProCod"),
                        rs.getString("DepCod"),
                        rs.getInt("DisEstReg")
                ));
            }
            return items;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }   
    }
    
    public ArrayList<Distrito> getAllByProvincia(String provCod){
        String sql = "SELECT * FROM "+Distrito.TABLE_NAME+" WHERE ProCod = ?";
        PreparedStatement pstm;
        ResultSet rs;
        ArrayList<Distrito> items = new ArrayList<Distrito>();
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, provCod);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                items.add( new Distrito(
                        rs.getString("DisCod"),
                        rs.getString("DisNom"),
                        rs.getString("ProCod"),
                        rs.getString("DepCod"),
                        rs.getInt("DisEstReg")
                ));
            }
            return items;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public Distrito search(String cod){
        String sql =  "SELECT * FROM "+Distrito.TABLE_NAME+" WHERE DisCod = ?";
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, cod);
            rs = pstm.executeQuery();
            if(rs.next()){
                return new Distrito(
                        rs.getString("DisCod"),
                        rs.getString("DisNom"),
                        rs.getString("ProCod"),
                        rs.getString("DepCod"),
                        rs.getInt("DisEstReg")
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ControllerResponse adiccionar(Distrito distrito){
        String sql = "INSERT INTO "+Distrito.TABLE_NAME+" VALUES(?,?,?,?,?)";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, distrito.getCodigo());
            pstm.setString(2, distrito.getNombre());
            pstm.setString(3, distrito.getProvinciaCodigo());
            pstm.setString(4, distrito.getDepartamentoCodigo());
            pstm.setInt(5, distrito.getEstadoRegistro());
            pstm.executeUpdate();
        }catch(SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            return new ControllerResponse("Codigo del distrito duplicado, porfavor ingresa otro", false);
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al adicionar el distrito, intentalo denuevo", false);
        }
        return new ControllerResponse("Registro añadido satisfactoriamente", true);
    }
    
    public ControllerResponse modificar(Distrito distrito){
        if(distrito.getCodigo() == null){
            System.err.println("Tiene que tener un codigo de distrito");
            return new ControllerResponse("El registro debe tener un codigo de distrito existente", false);
        }
        String sql = "UPDATE "+Distrito.TABLE_NAME+" SET DisNom = ?, ProCod = ?, DepCod = ? WHERE DisCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, distrito.getNombre());
            pstm.setString(2, distrito.getProvinciaCodigo());
            pstm.setString(3, distrito.getDepartamentoCodigo());
            pstm.setString(4, distrito.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al actualizar, intentalo de nuevo", false);
        }
        return new ControllerResponse("Se actualizo el registro con exito", true);
    }
    
    public ControllerResponse eliminar(String cod){
        Distrito distrito = this.search(cod);
        if(distrito == null)
            return new ControllerResponse("No se pudo encontrar el distrito que desea eliminar", false);
        String sql = "UPDATE "+Distrito.TABLE_NAME+" SET DisEstReg = 2 WHERE DisCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, distrito.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error eliminado el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se elimino el registro con exito", true);
    }
    
    public ControllerResponse inactivar(String cod){
        Distrito distrito = this.search(cod);
        if(distrito == null)
            return new ControllerResponse("No se pudo encontrar el distrito que desea inactivar", false);
        String sql = "UPDATE "+Distrito.TABLE_NAME+" SET DisEstReg = 3 WHERE DisCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, distrito.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error durante la desactivacion del registro, intentelo denuevo", false);
        }
        return new ControllerResponse("Se inactivo el registro con exito", true);
    }
    
    public ControllerResponse reactivar(String cod){
        Distrito distrito = this.search(cod);
        if(distrito == null)
            return new ControllerResponse("No se pudo encontrar la provincia que desea reactivar", false);
        String sql = "UPDATE "+Distrito.TABLE_NAME+" SET DisEstReg = 1 WHERE DisCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, distrito.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error reactivando el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se reactivo el registro con exito", true);
    }
}

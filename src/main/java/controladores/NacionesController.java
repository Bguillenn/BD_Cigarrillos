/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import modelos.Nacion;
import modelos.ControllerResponse;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Brayan Guillen N
 */
public class NacionesController {
    
    private Connection conexion;
    
    public NacionesController(Connection con){
        this.conexion = con;
    }
    
    public ArrayList<Nacion> getAll(){
        String sql = "SELECT * FROM "+Nacion.TABLE_NAME;
        ArrayList<Nacion> items = new ArrayList<>();
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                items.add(new Nacion(
                        rs.getInt("NacCod"),
                        rs.getString("NacNom"),
                        rs.getInt("NacEstReg")
                ));
            }
            return items;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }   
    }
    
    public Nacion search(int cod){
        String sql =  "SELECT * FROM "+Nacion.TABLE_NAME+" WHERE NacCod = ?";
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setInt(1, cod);
            rs = pstm.executeQuery();
            if(rs.next()){
                return new Nacion(
                        rs.getInt("NacCod"),
                        rs.getString("NacNom"),
                        rs.getInt("NacEstReg")
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ControllerResponse adiccionar(Nacion nacion){
        String sql = "INSERT INTO "+Nacion.TABLE_NAME+" VALUES(?,?,?)";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setInt(1, nacion.getCodigo());
            pstm.setString(2, nacion.getNombre());
            pstm.setInt(3, nacion.getEstadoRegistro());
            pstm.executeUpdate();
        }catch(SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            return new ControllerResponse("Codigo de nacion duplicado, porfavor ingresa otro", false);
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al adicionar la Nacion, intentalo denuevo", false);
        }
        return new ControllerResponse("Registro añadido satisfactoriamente", true);
    }
    
    public ControllerResponse modificar(Nacion nacion){
        if(nacion.getCodigo() == -1){
            System.err.println("Tiene que tener un codigo de nacion");
            return new ControllerResponse("El registro debe tener un codigo de nacion existente", false);
        }
        String sql = "UPDATE "+Nacion.TABLE_NAME+" SET NacNom = ? WHERE NacCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, nacion.getNombre());
            pstm.setInt(2, nacion.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al actualizar, intentalo de nuevo", false);
        }
        return new ControllerResponse("Se actualizo el registro con exito", true);
    }
    
    public ControllerResponse eliminar(int cod){
        Nacion nacion = this.search(cod);
        if(nacion == null)
            return new ControllerResponse("No se pudo encontrar la nacion que desea eliminar", false);
        String sql = "UPDATE "+Nacion.TABLE_NAME+" SET NacEstReg = 2 WHERE NacCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setInt(1, nacion.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error eliminado el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se elimino el registro con exito", true);
    }
    
    public ControllerResponse inactivar(int cod){
        Nacion nacion = this.search(cod);
        if(nacion == null)
            return new ControllerResponse("No se pudo encontrar la nacion que desea inactivar", false);
        String sql = "UPDATE "+Nacion.TABLE_NAME+" SET NacEstReg = 3 WHERE NacCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setInt(1, nacion.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error durante la desactivacion del registro, intentelo denuevo", false);
        }
        return new ControllerResponse("Se inactivo el registro con exito", true);
    }
    
    public ControllerResponse reactivar(int cod){
        Nacion nacion = this.search(cod);
        if(nacion == null)
            return new ControllerResponse("No se pudo encontrar la nacion que desea reactivar", false);
        String sql = "UPDATE "+Nacion.TABLE_NAME+" SET NacEstReg = 1 WHERE NacCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setInt(1, nacion.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error reactivando el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se reactivo el registro con exito", true);
    }
}

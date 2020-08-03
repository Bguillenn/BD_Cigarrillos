/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import modelos.Provincia;
import modelos.ControllerResponse;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Brayan Guillen N
 */
public class ProvinciasController {
    
    private Connection conexion;
    
    public ProvinciasController(Connection con){
        this.conexion = con;
    }
    
    public ArrayList<Provincia> getAll(){
        String sql = "SELECT * FROM "+Provincia.TABLE_NAME;
        ArrayList<Provincia> items = new ArrayList<>();
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                items.add(new Provincia(
                        rs.getString("ProCod"),
                        rs.getString("ProNom"),
                        rs.getString("DepCod"),
                        rs.getInt("ProEstReg")
                ));
            }
            return items;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }   
    }
    
    public ArrayList<Provincia> getAllByDepartamento(String depCod){
        String sql = "SELECT * FROM "+Provincia.TABLE_NAME+" WHERE DepCod = ?";
        PreparedStatement pstm;
        ResultSet rs;
        ArrayList<Provincia> items = new ArrayList<Provincia>();
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, depCod);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                items.add( new Provincia(
                        rs.getString("ProCod"),
                        rs.getString("ProNom"),
                        rs.getString("DepCod"),
                        rs.getInt("ProEstReg")
                ));
            }
            return items;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public Provincia search(String cod){
        String sql =  "SELECT * FROM "+Provincia.TABLE_NAME+" WHERE ProCod = ?";
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, cod);
            rs = pstm.executeQuery();
            if(rs.next()){
                return new Provincia(
                        rs.getString("ProCod"),
                        rs.getString("ProNom"),
                        rs.getString("DepCod"),
                        rs.getInt("ProEstReg")
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ControllerResponse adiccionar(Provincia provincia){
        String sql = "INSERT INTO "+Provincia.TABLE_NAME+" VALUES(?,?,?,?)";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, provincia.getCodigo());
            pstm.setString(2, provincia.getNombre());
            pstm.setString(3, provincia.getDepartamentoCodigo());
            pstm.setInt(4, provincia.getEstadoRegistro());
            pstm.executeUpdate();
        }catch(SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            return new ControllerResponse("Codigo de la provincia duplicado, porfavor ingresa otro", false);
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al adicionar la provincia, intentalo denuevo", false);
        }
        return new ControllerResponse("Registro añadido satisfactoriamente", true);
    }
    
    public ControllerResponse modificar(Provincia provincia){
        if(provincia.getCodigo() == null){
            System.err.println("Tiene que tener un codigo de provincia");
            return new ControllerResponse("El registro debe tener un codigo de provincia existente", false);
        }
        String sql = "UPDATE "+Provincia.TABLE_NAME+" SET ProNom = ?, DepCod = ? WHERE ProCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, provincia.getNombre());
            pstm.setString(2, provincia.getDepartamentoCodigo());
            pstm.setString(3, provincia.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al actualizar, intentalo de nuevo", false);
        }
        return new ControllerResponse("Se actualizo el registro con exito", true);
    }
    
    public ControllerResponse eliminar(String cod){
        Provincia provincia = this.search(cod);
        if(provincia == null)
            return new ControllerResponse("No se pudo encontrar la provincia que desea eliminar", false);
        String sql = "UPDATE "+Provincia.TABLE_NAME+" SET ProEstReg = 2 WHERE ProCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, provincia.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error eliminado el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se elimino el registro con exito", true);
    }
    
    public ControllerResponse inactivar(String cod){
        Provincia provincia = this.search(cod);
        if(provincia == null)
            return new ControllerResponse("No se pudo encontrar la provincia que desea inactivar", false);
        String sql = "UPDATE "+Provincia.TABLE_NAME+" SET ProEstReg = 3 WHERE ProCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, provincia.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error durante la desactivacion del registro, intentelo denuevo", false);
        }
        return new ControllerResponse("Se inactivo el registro con exito", true);
    }
    
    public ControllerResponse reactivar(String cod){
        Provincia provincia = this.search(cod);
        if(provincia == null)
            return new ControllerResponse("No se pudo encontrar la provincia que desea reactivar", false);
        String sql = "UPDATE "+Provincia.TABLE_NAME+" SET ProEstReg = 1 WHERE ProCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, provincia.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error reactivando el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se reactivo el registro con exito", true);
    }
}

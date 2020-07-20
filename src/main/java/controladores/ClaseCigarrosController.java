/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import modelos.ClaseCigarro;
import modelos.ControllerResponse;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Brayan Guillen N
 */
public class ClaseCigarrosController {
    
    private Connection conexion;
    
    public ClaseCigarrosController(Connection con){
        this.conexion = con;
    }
    
    public ArrayList<ClaseCigarro> getAll(){
        String sql = "SELECT * FROM "+ClaseCigarro.TABLE_NAME;
        ArrayList<ClaseCigarro> items = new ArrayList<>();
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                items.add(new ClaseCigarro(
                        rs.getString("ClaCigCod"),
                        rs.getString("ClaCigNom"),
                        rs.getString("ClaCigDes"),
                        rs.getInt("ClaCigEstReg")
                ));
            }
            return items;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }   
    }
    
    public ClaseCigarro search(String cod){
        String sql =  "SELECT * FROM "+ClaseCigarro.TABLE_NAME+" WHERE ClaCigCod = ?";
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, cod);
            rs = pstm.executeQuery();
            if(rs.next()){
                return new ClaseCigarro(
                        rs.getString("ClaCigCod"),
                        rs.getString("ClaCigNom"),
                        rs.getString("ClaCigDes"),
                        rs.getInt("ClaCigEstReg")
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ControllerResponse adiccionar(ClaseCigarro claseCig){
        String sql = "INSERT INTO "+ClaseCigarro.TABLE_NAME+" VALUES(?,?,?,?)";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, claseCig.getCodigo());
            pstm.setString(2, claseCig.getNombre());
            pstm.setString(3, claseCig.getDescripcion());
            pstm.setInt(4, claseCig.getEstadoRegistro());
            pstm.executeUpdate();
        }catch(SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            return new ControllerResponse("Codigo de la clase de cigarrillo duplicado, porfavor ingresa otro", false);
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al adicionar la clase de cigarrillo, intentalo denuevo", false);
        }
        return new ControllerResponse("Registro añadido satisfactoriamente", true);
    }
    
    public ControllerResponse modificar(ClaseCigarro claseCig){
        if(claseCig.getCodigo() == null){
            System.err.println("Tiene que tener un codigo de clase de cigarrillo");
            return new ControllerResponse("El registro debe tener un codigo de clase de cigarrillo existente", false);
        }
        String sql = "UPDATE "+ClaseCigarro.TABLE_NAME+" SET ClaCigNom = ?, ClaCigDes = ? WHERE ClaCigCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, claseCig.getNombre());
            pstm.setString(2, claseCig.getDescripcion());
            pstm.setString(3, claseCig.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al actualizar, intentalo de nuevo", false);
        }
        return new ControllerResponse("Se actualizo el registro con exito", true);
    }
    
    public ControllerResponse eliminar(String cod){
        ClaseCigarro claseCig = this.search(cod);
        if(claseCig == null)
            return new ControllerResponse("No se pudo encontrar la clase de cigarro que desea eliminar", false);
        String sql = "UPDATE "+ClaseCigarro.TABLE_NAME+" SET ClaCigEstReg = 2 WHERE ClaCigCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, claseCig.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error eliminado el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se elimino el registro con exito", true);
    }
    
    public ControllerResponse inactivar(String cod){
        ClaseCigarro claseCig = this.search(cod);
        if(claseCig == null)
            return new ControllerResponse("No se pudo encontrar la clase de cigarro que desea inactivar", false);
        String sql = "UPDATE "+ClaseCigarro.TABLE_NAME+" SET ClaCigEstReg = 3 WHERE ClaCigCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, claseCig.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error durante la desactivacion del registro, intentelo denuevo", false);
        }
        return new ControllerResponse("Se inactivo el registro con exito", true);
    }
    
    public ControllerResponse reactivar(String cod){
        ClaseCigarro claseCig = this.search(cod);
        if(claseCig == null)
            return new ControllerResponse("No se pudo encontrar la clase de cigarro que desea reactivar", false);
        String sql = "UPDATE "+ClaseCigarro.TABLE_NAME+" SET ClaCigEstReg = 1 WHERE ClaCigCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, claseCig.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error reactivando el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se reactivo el registro con exito", true);
    }
}

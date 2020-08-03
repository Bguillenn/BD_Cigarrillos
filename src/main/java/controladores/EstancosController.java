/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import modelos.Estanco;
import modelos.ControllerResponse;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Brayan Guillen N
 */
public class EstancosController {
    
    private Connection conexion;
    
    public EstancosController(Connection con){
        this.conexion = con;
    }
    
    public ArrayList<Estanco> getAll(){
        String sql = "SELECT * FROM "+Estanco.TABLE_NAME;
        ArrayList<Estanco> items = new ArrayList<>();
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                items.add(new Estanco(
                        rs.getString("EntCod"),
                        rs.getInt("EntNumLic"),
                        rs.getInt("EntNumExp"),
                        rs.getString("EntNom"),
                        rs.getString("EntDir"),
                        rs.getString("EntDisCod"),
                        rs.getInt("EntEstReg")
                ));
            }
            return items;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }   
    }
    
    public Estanco search(String cod){
        String sql =  "SELECT * FROM "+Estanco.TABLE_NAME+" WHERE EntCod = ?";
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, cod);
            rs = pstm.executeQuery();
            if(rs.next()){
                return new Estanco(
                        rs.getString("EntCod"),
                        rs.getInt("EntNumLic"),
                        rs.getInt("EntNumExp"),
                        rs.getString("EntNom"),
                        rs.getString("EntDir"),
                        rs.getString("EntDisCod"),
                        rs.getInt("EntEstReg")
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ControllerResponse adiccionar(Estanco estanco){
        String sql = "INSERT INTO "+Estanco.TABLE_NAME+" VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, estanco.getCodigo());
            pstm.setInt(2, estanco.getLicenciatura());
            pstm.setInt(3, estanco.getExpenduria());
            pstm.setString(4, estanco.getNombre());
            pstm.setString(5, estanco.getDireccion());
            pstm.setString(6, estanco.getDistritoCodigo());
            pstm.setInt(7, estanco.getEstadoRegistro());
            pstm.executeUpdate();
        }catch(SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            return new ControllerResponse("Codigo del estanco duplicado, porfavor ingresa otro", false);
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al adicionar el estanco, intentalo denuevo", false);
        }
        return new ControllerResponse("Registro añadido satisfactoriamente", true);
    }
    
    public ControllerResponse modificar(Estanco estanco){
        if(estanco.getCodigo() == null){
            System.err.println("Tiene que tener un codigo de estanco");
            return new ControllerResponse("El registro debe tener un codigo de estanco existente", false);
        }
        String sql = "UPDATE "+Estanco.TABLE_NAME+" SET EntNumLic = ?, EntNumExp = ?, EntNom = ?, EntDir = ?, EntDisCod = ? WHERE EntCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setInt(1, estanco.getLicenciatura());
            pstm.setInt(2, estanco.getExpenduria());
            pstm.setString(3, estanco.getNombre());
            pstm.setString(4, estanco.getDireccion());
            pstm.setString(5, estanco.getDistritoCodigo());
            pstm.setString(6, estanco.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al actualizar, intentalo de nuevo", false);
        }
        return new ControllerResponse("Se actualizo el registro con exito", true);
    }
    
    public ControllerResponse eliminar(String cod){
        Estanco estanco = this.search(cod);
        if(estanco == null)
            return new ControllerResponse("No se pudo encontrar el estanco que desea eliminar", false);
        String sql = "UPDATE "+Estanco.TABLE_NAME+" SET EntEstReg = 2 WHERE EntCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, estanco.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error eliminado el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se elimino el registro con exito", true);
    }
    
    public ControllerResponse inactivar(String cod){
        Estanco estanco = this.search(cod);
        if(estanco == null)
            return new ControllerResponse("No se pudo encontrar el estanco que desea inactivar", false);
        String sql = "UPDATE "+Estanco.TABLE_NAME+" SET EntEstReg = 3 WHERE EntCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, estanco.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error durante la desactivacion del registro, intentelo denuevo", false);
        }
        return new ControllerResponse("Se inactivo el registro con exito", true);
    }
    
    public ControllerResponse reactivar(String cod){
        Estanco estanco = this.search(cod);
        if(estanco == null)
            return new ControllerResponse("No se pudo encontrar el estanco que desea reactivar", false);
        String sql = "UPDATE "+Estanco.TABLE_NAME+" SET EntEstReg = 1 WHERE EntCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, estanco.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error reactivando el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se reactivo el registro con exito", true);
    }
}

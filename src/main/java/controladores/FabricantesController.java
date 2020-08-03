/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import modelos.Fabricante;
import modelos.ControllerResponse;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Brayan Guillen N
 */
public class FabricantesController {
    
    private Connection conexion;
    
    public FabricantesController(Connection con){
        this.conexion = con;
    }
    
    public ArrayList<Fabricante> getAll(){
        String sql = "SELECT * FROM "+Fabricante.TABLE_NAME;
        ArrayList<Fabricante> items = new ArrayList<>();
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                items.add(new Fabricante(
                        rs.getString("FabCod"),
                        rs.getString("FabNom"),
                        rs.getInt("FabNacCod"),
                        rs.getInt("FabEstReg")
                ));
            }
            return items;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }   
    }
    
    public Fabricante search(String cod){
        String sql =  "SELECT * FROM "+Fabricante.TABLE_NAME+" WHERE FabCod = ?";
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, cod);
            rs = pstm.executeQuery();
            if(rs.next()){
                return new Fabricante(
                        rs.getString("FabCod"),
                        rs.getString("FabNom"),
                        rs.getInt("FabNacCod"),
                        rs.getInt("FabEstReg")
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ControllerResponse adiccionar(Fabricante fabricante){
        String sql = "INSERT INTO "+Fabricante.TABLE_NAME+" VALUES(?,?,?,?)";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, fabricante.getCodigo());
            pstm.setString(2, fabricante.getNombre());
            pstm.setInt(3, fabricante.getNacionCodigo());
            pstm.setInt(4, fabricante.getEstadoRegistro());
            pstm.executeUpdate();
        }catch(SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            return new ControllerResponse("Codigo del fabricante duplicado, porfavor ingresa otro", false);
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al adicionar el fabricante, intentalo denuevo", false);
        }
        return new ControllerResponse("Registro añadido satisfactoriamente", true);
    }
    
    public ControllerResponse modificar(Fabricante fabricante){
        if(fabricante.getCodigo() == null){
            System.err.println("Tiene que tener un codigo de fabricante");
            return new ControllerResponse("El registro debe tener un codigo de fabricante existente", false);
        }
        String sql = "UPDATE "+Fabricante.TABLE_NAME+" SET FabNom = ?, FabNacCod = ? WHERE FabCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, fabricante.getNombre());
            pstm.setInt(2, fabricante.getNacionCodigo());
            pstm.setString(3, fabricante.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al actualizar, intentalo de nuevo", false);
        }
        return new ControllerResponse("Se actualizo el registro con exito", true);
    }
    
    public ControllerResponse eliminar(String cod){
        Fabricante fabricante = this.search(cod);
        if(fabricante == null)
            return new ControllerResponse("No se pudo encontrar el fabricante que desea eliminar", false);
        String sql = "UPDATE "+Fabricante.TABLE_NAME+" SET FabEstReg = 2 WHERE FabCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, fabricante.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error eliminado el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se elimino el registro con exito", true);
    }
    
    public ControllerResponse inactivar(String cod){
        Fabricante fabricante = this.search(cod);
        if(fabricante == null)
            return new ControllerResponse("No se pudo encontrar el fabricante que desea inactivar", false);
        String sql = "UPDATE "+Fabricante.TABLE_NAME+" SET FabEstReg = 3 WHERE FabCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, fabricante.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error durante la desactivacion del registro, intentelo denuevo", false);
        }
        return new ControllerResponse("Se inactivo el registro con exito", true);
    }
    
    public ControllerResponse reactivar(String cod){
        Fabricante fabricante = this.search(cod);
        if(fabricante == null)
            return new ControllerResponse("No se pudo encontrar la provincia que desea reactivar", false);
        String sql = "UPDATE "+Fabricante.TABLE_NAME+" SET FabEstReg = 1 WHERE FabCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, fabricante.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error reactivando el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se reactivo el registro con exito", true);
    }
}

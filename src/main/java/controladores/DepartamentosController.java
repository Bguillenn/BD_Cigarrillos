/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import modelos.Departamento;
import modelos.ControllerResponse;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Brayan Guillen N
 */
public class DepartamentosController {
    
    private Connection conexion;
    
    public DepartamentosController(Connection con){
        this.conexion = con;
    }
    
    public ArrayList<Departamento> getAll(){
        String sql = "SELECT * FROM "+Departamento.TABLE_NAME;
        ArrayList<Departamento> items = new ArrayList<>();
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                items.add(new Departamento(
                        rs.getString("DepCod"),
                        rs.getString("DepNom"),
                        rs.getInt("DepEstReg")
                ));
            }
            return items;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }   
    }
    
    public Departamento search(String cod){
        String sql =  "SELECT * FROM "+Departamento.TABLE_NAME+" WHERE DepCod = ?";
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, cod);
            rs = pstm.executeQuery();
            if(rs.next()){
                return new Departamento(
                        rs.getString("DepCod"),
                        rs.getString("DepNom"),
                        rs.getInt("DepEstReg")
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ControllerResponse adiccionar(Departamento departamento){
        String sql = "INSERT INTO "+Departamento.TABLE_NAME+" VALUES(?,?,?)";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, departamento.getCodigo());
            pstm.setString(2, departamento.getNombre());
            pstm.setInt(3, departamento.getEstadoRegistro());
            pstm.executeUpdate();
        }catch(SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            return new ControllerResponse("Codigo del departamento duplicado, porfavor ingresa otro", false);
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al adicionar el departamento, intentalo denuevo", false);
        }
        return new ControllerResponse("Registro añadido satisfactoriamente", true);
    }
    
    public ControllerResponse modificar(Departamento departamento){
        if(departamento.getCodigo() == null){
            System.err.println("Tiene que tener un codigo de departamento");
            return new ControllerResponse("El registro debe tener un codigo de departamento existente", false);
        }
        String sql = "UPDATE "+Departamento.TABLE_NAME+" SET DepNom = ? WHERE DepCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, departamento.getNombre());
            pstm.setString(2, departamento.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al actualizar, intentalo de nuevo", false);
        }
        return new ControllerResponse("Se actualizo el registro con exito", true);
    }
    
    public ControllerResponse eliminar(String cod){
        Departamento departamento = this.search(cod);
        if(departamento == null)
            return new ControllerResponse("No se pudo encontrar el departamento que desea eliminar", false);
        String sql = "UPDATE "+Departamento.TABLE_NAME+" SET DepEstReg = 2 WHERE DepCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, departamento.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error eliminado el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se elimino el registro con exito", true);
    }
    
    public ControllerResponse inactivar(String cod){
        Departamento departamento = this.search(cod);
        if(departamento == null)
            return new ControllerResponse("No se pudo encontrar el departamento que desea inactivar", false);
        String sql = "UPDATE "+Departamento.TABLE_NAME+" SET DepEstReg = 3 WHERE DepCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, departamento.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error durante la desactivacion del registro, intentelo denuevo", false);
        }
        return new ControllerResponse("Se inactivo el registro con exito", true);
    }
    
    public ControllerResponse reactivar(String cod){
        Departamento departamento = this.search(cod);
        if(departamento == null)
            return new ControllerResponse("No se pudo encontrar el departamento que desea reactivar", false);
        String sql = "UPDATE "+Departamento.TABLE_NAME+" SET DepEstReg = 1 WHERE DepCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, departamento.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error reactivando el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se reactivo el registro con exito", true);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import modelos.Cigarrillo;
import modelos.ControllerResponse;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Brayan Guillen N
 */
public class CigarrillosController {
    
    private Connection conexion;
    
    public CigarrillosController(Connection con){
        this.conexion = con;
    }
    
    public ArrayList<Cigarrillo> getAll(){
        String sql = "SELECT * FROM "+Cigarrillo.TABLE_NAME;
        ArrayList<Cigarrillo> items = new ArrayList<>();
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                items.add(new Cigarrillo(
                        rs.getString("CigCod"),
                        rs.getString("CigMar"),
                        rs.getBoolean("CigFil"),
                        rs.getString("CigColCod"),
                        rs.getString("CigClaCod"),
                        rs.getBoolean("CigMen"),
                        rs.getInt("CigCar"),
                        rs.getFloat("CigNic"),
                        rs.getFloat("CigAlq"),
                        rs.getFloat("CigPreVen"),
                        rs.getFloat("CigPreCos"),
                        rs.getInt("CigEmb"),
                        rs.getInt("CigEstReg")
                ));
            }
            return items;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }   
    }
    
    public Cigarrillo search(String cod){
        String sql =  "SELECT * FROM "+Cigarrillo.TABLE_NAME+" WHERE CigCod = ?";
        PreparedStatement pstm;
        ResultSet rs;
        
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, cod);
            rs = pstm.executeQuery();
            if(rs.next()){
                return new Cigarrillo(
                        rs.getString("CigCod"),
                        rs.getString("CigMar"),
                        rs.getBoolean("CigFil"),
                        rs.getString("CigColCod"),
                        rs.getString("CigClaCod"),
                        rs.getBoolean("CigMen"),
                        rs.getInt("CigCar"),
                        rs.getFloat("CigNic"),
                        rs.getFloat("CigAlq"),
                        rs.getFloat("CigPreVen"),
                        rs.getFloat("CigPreCos"),
                        rs.getInt("CigEmb"),
                        rs.getInt("CigEstReg")
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ControllerResponse adiccionar(Cigarrillo cigarrillo){
        String sql = "INSERT INTO "+Cigarrillo.TABLE_NAME+" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, cigarrillo.getCodigo());
            pstm.setString(2, cigarrillo.getMarca());
            pstm.setBoolean(3, cigarrillo.getFiltro());
            pstm.setString(4, cigarrillo.getColorCodigo());
            pstm.setString(5, cigarrillo.getClaseCodigo());
            pstm.setBoolean(6, cigarrillo.getMentol());
            pstm.setInt(7, cigarrillo.getCarton());
            pstm.setFloat(8, cigarrillo.getNicotina());
            pstm.setFloat(9, cigarrillo.getAlquitran());
            pstm.setFloat(10, cigarrillo.getPrecioCompra());
            pstm.setFloat(11, cigarrillo.getPrecioVenta());
            pstm.setInt(12, cigarrillo.getEmboltura());
            pstm.setInt(13, cigarrillo.getEstadoRegistro());
            pstm.executeUpdate();
        }catch(SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            return new ControllerResponse("Codigo del cigarrillo duplicado, porfavor ingresa otro", false);
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al adicionar el cigarrillo, intentalo denuevo", false);
        }
        return new ControllerResponse("Registro añadido satisfactoriamente", true);
    }
    
    public ControllerResponse modificar(Cigarrillo cigarrillo){
        if(cigarrillo.getCodigo() == null){
            System.err.println("Tiene que tener un codigo de cigarrillo");
            return new ControllerResponse("El registro debe tener un codigo de cigarrillo existente", false);
        }
        String sql = "UPDATE "+Cigarrillo.TABLE_NAME+" SET CigMar = ?, CigFil = ?, CigColCod = ?, CigClaCod = ?, CigMen = ?, CigCar = ?, CigNic = ?, CigAlq = ?, CigPreCos = ? , CigPreVen = ?, CigEmb = ? WHERE CigCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, cigarrillo.getMarca());
            pstm.setBoolean(2, cigarrillo.getFiltro());
            pstm.setString(3, cigarrillo.getColorCodigo());
            pstm.setString(4, cigarrillo.getClaseCodigo());
            pstm.setBoolean(5, cigarrillo.getMentol());
            pstm.setInt(6, cigarrillo.getCarton());
            pstm.setFloat(7, cigarrillo.getNicotina());
            pstm.setFloat(8, cigarrillo.getAlquitran());
            pstm.setFloat(9, cigarrillo.getPrecioCompra());
            pstm.setFloat(10, cigarrillo.getPrecioVenta());
            pstm.setInt(11, cigarrillo.getEmboltura());
            pstm.setString(12, cigarrillo.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error al actualizar, intentalo de nuevo", false);
        }
        return new ControllerResponse("Se actualizo el registro con exito", true);
    }
    
    public ControllerResponse eliminar(String cod){
        Cigarrillo cigarrillo = this.search(cod);
        if(cigarrillo == null)
            return new ControllerResponse("No se pudo encontrar el cigarrillo que desea eliminar", false);
        String sql = "UPDATE "+Cigarrillo.TABLE_NAME+" SET CigEstReg = 2 WHERE CigCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, cigarrillo.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error eliminado el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se elimino el registro con exito", true);
    }
    
    public ControllerResponse inactivar(String cod){
        Cigarrillo cigarrillo = this.search(cod);
        if(cigarrillo == null)
            return new ControllerResponse("No se pudo encontrar el cigarrillo que desea inactivar", false);
        String sql = "UPDATE "+Cigarrillo.TABLE_NAME+" SET CigEstReg = 3 WHERE CigCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, cigarrillo.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error durante la desactivacion del registro, intentelo denuevo", false);
        }
        return new ControllerResponse("Se inactivo el registro con exito", true);
    }
    
    public ControllerResponse reactivar(String cod){
        Cigarrillo cigarrillo = this.search(cod);
        if(cigarrillo == null)
            return new ControllerResponse("No se pudo encontrar la provincia que desea reactivar", false);
        String sql = "UPDATE "+Cigarrillo.TABLE_NAME+" SET CigEstReg = 1 WHERE CigCod = ?";
        PreparedStatement pstm;
        try{
            pstm = conexion.prepareStatement(sql);
            pstm.setString(1, cigarrillo.getCodigo());
            pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return new ControllerResponse("Ocurrio un error reactivando el registro, intentalo nuevamente", false);
        }
        return new ControllerResponse("Se reactivo el registro con exito", true);
    }
}

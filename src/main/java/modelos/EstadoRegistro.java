/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Brayan Guillen N
 */
public class EstadoRegistro {
    public static final String TABLE_NAME = "gzz_estado_registro";
    //Atributos
    private int codigo = -1;
    private String descripcion;
    
    public EstadoRegistro(int codigo, String descripcion){
        this.codigo = codigo;
        this.descripcion = descripcion;
    }
    
    public void setCodigo(int cod){
        this.codigo = codigo;
    }
    
    public void setDescripcion(String des){
        this.descripcion = des;
    }
    
    public int getCodigo(){
        return codigo;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
}

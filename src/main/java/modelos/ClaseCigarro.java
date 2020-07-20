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
public class ClaseCigarro {
    public static final String TABLE_NAME = "lzm_clase_cigarro";
    //Atributos
    private String codigo = null;
    private String nombre;
    private String descripcion;
    private int estadoRegistro;
    
    public ClaseCigarro(String codigo, String nombre, String descripcion, int estadoRegistro){
        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setEstadoRegistro(estadoRegistro);
    }
    
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre.toUpperCase();
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public void setEstadoRegistro(int estadoRegistro){
        this.estadoRegistro = estadoRegistro;
    }
    
    public String getCodigo(){
        return this.codigo;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }
    
    public int getEstadoRegistro(){
        return this.estadoRegistro;
    }
}
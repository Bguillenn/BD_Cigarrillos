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
public class ColorM {
    public static final String TABLE_NAME = "lzm_colores";
    //Atributos
    private String codigo = null;
    private String nombre;
    private int rojo;
    private int verde;
    private int azul;
    private int estadoRegistro;
    
    public ColorM(String codigo, String nombre,int rojo, int verde, int azul ,int estadoRegistro){
        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.setRojo(rojo);
        this.setVerde(verde);
        this.setAzul(azul);
        this.setEstadoRegistro(estadoRegistro);
    }
    
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre.toUpperCase();
    }
    
    public void setRojo(int rojo){
        this.rojo = rojo;
    }
    
    public void setVerde(int verde){
        this.verde = verde;
    }
    
    public void setAzul(int azul){
        this.azul = azul;
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
    
    public int getRojo(){
        return this.rojo;
    }
    
    public int getVerde(){
        return this.verde;
    }
    
    public int getAzul(){
        return this.azul;
    }
    
    public int getEstadoRegistro(){
        return this.estadoRegistro;
    }
}
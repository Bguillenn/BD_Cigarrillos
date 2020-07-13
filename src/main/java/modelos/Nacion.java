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
public class Nacion {
    public static final String TABLE_NAME = "gzz_naciones";
    //Atributos
    private int codigo = -1;
    private String nombre;
    private int estadoRegistro;
    
    public Nacion(int codigo, String nombre, int estadoRegistro){
        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.setEstadoRegistro(estadoRegistro);
    }
    
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre.toUpperCase();
    }
    
    public void setEstadoRegistro(int estadoRegistro){
        this.estadoRegistro = estadoRegistro;
    }
    
    public int getCodigo(){
        return this.codigo;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public int getEstadoRegistro(){
        return this.estadoRegistro;
    }
}
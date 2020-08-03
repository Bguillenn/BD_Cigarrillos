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
public class Provincia {
    public static final String TABLE_NAME = "gzz_provincias";
    //Atributos
    private String codigo = null;
    private String nombre;
    private String departamentoCodigo;
    private int estadoRegistro;
    
    public Provincia(String codigo, String nombre,String departamentoCodigo ,int estadoRegistro){
        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.setDepartamentoCodigo(departamentoCodigo);
        this.setEstadoRegistro(estadoRegistro);
    }
    
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre.toUpperCase();
    }
    
    public void setDepartamentoCodigo(String depaCod){
        this.departamentoCodigo = depaCod;
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
    
    public String getDepartamentoCodigo(){
        return this.departamentoCodigo;
    }
    
    public int getEstadoRegistro(){
        return this.estadoRegistro;
    }
}
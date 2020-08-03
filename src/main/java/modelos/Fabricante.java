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
public class Fabricante {
    public static final String TABLE_NAME = "l2z_fabricantes";
    //Atributos
    private String codigo = null;
    private String nombre;
    private int nacionCodigo;
    private int estadoRegistro;
    
    public Fabricante(String codigo, String nombre,int nacionCodigo ,int estadoRegistro){
        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.setNacionCodigo(nacionCodigo);
        this.setEstadoRegistro(estadoRegistro);
    }
    
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre.toUpperCase();
    }
    
    public void setNacionCodigo(int nacionCodigo){
        this.nacionCodigo = nacionCodigo;
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
    
    public int getNacionCodigo(){
        return this.nacionCodigo;
    }
    
    public int getEstadoRegistro(){
        return this.estadoRegistro;
    }
}
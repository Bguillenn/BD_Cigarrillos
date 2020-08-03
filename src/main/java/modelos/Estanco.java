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
public class Estanco {
    public static final String TABLE_NAME = "l3c_estancos";
    //Atributos
    private String codigo = null;
    private int licenciatura;
    private int expenduria;
    private String nombre;
    private String direccion;
    private String distritoCodigo;
    private int estadoRegistro;
    
    public Estanco(String codigo, int licenciatura, int expenduria, String nombre, String direccion, String distritoCodigo ,int estadoRegistro){
        this.setCodigo(codigo);
        this.setLicenciatura(licenciatura);
        this.setExpenduria(expenduria);
        this.setNombre(nombre);
        this.setDireccion(direccion);
        this.setDistritoCodigo(distritoCodigo);
        this.setEstadoRegistro(estadoRegistro);
    }
    
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    
    public void setLicenciatura(int licenciatura){
        this.licenciatura = licenciatura;
    }
    
    public void setExpenduria(int expenduria){
        this.expenduria = expenduria;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre.toUpperCase();
    }
    
    public void setDireccion(String direccion){
        this.direccion = direccion.toUpperCase();
    }
    
    public void setDistritoCodigo(String distritoCodigo){
        this.distritoCodigo = distritoCodigo;
    }
    
    public void setEstadoRegistro(int estadoRegistro){
        this.estadoRegistro = estadoRegistro;
    }
    
    public String getCodigo(){
        return this.codigo;
    }
    
    public int getLicenciatura(){
        return this.licenciatura;
    }
    
    public int getExpenduria(){
        return this.expenduria;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getDireccion(){
        return this.direccion;
    }
    
    public String getDistritoCodigo(){
        return this.distritoCodigo;
    }
    
    public int getEstadoRegistro(){
        return this.estadoRegistro;
    }
}
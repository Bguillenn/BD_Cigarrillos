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
public class Cigarrillo {
    public static final String TABLE_NAME = "l2m_cigarrillos";
    //Atributos
    private String codigo = null;
    private String marca;
    private boolean filtro;
    private String colorCodigo;
    private String claseCodigo;
    private boolean mentol;
    private int carton;
    private float nicotina;
    private float alquitran;
    private float precioVenta;
    private float precioCompra;
    private int emboltura;
    private int estadoRegistro;
    
    public Cigarrillo(String codigo, String marca, boolean filtro, String colorCodigo, String claseCodigo, boolean mentol, int carton, float nicotina, float alquitran, float precioVenta, float precioCompra, int emboltura ,int estadoRegistro){
        this.setCodigo(codigo);
        this.setMarca(marca);
        this.setFiltro(filtro);
        this.setColorCodigo(colorCodigo);
        this.setClaseCodigo(claseCodigo);
        this.setMentol(mentol);
        this.setCarton(carton);
        this.setNicotina(nicotina);
        this.setAlquitran(alquitran);
        this.setPrecioVenta(precioVenta);
        this.setPrecioCompra(precioCompra);
        this.setEmboltura(emboltura);
        this.setEstadoRegistro(estadoRegistro);
    }
    
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    
    public void setMarca(String marca){
        this.marca = marca.toUpperCase();
    }
    
    public void setFiltro(boolean filtro){
        this.filtro = filtro;
    }
    
    public void setColorCodigo(String colorCodigo){
        this.colorCodigo = colorCodigo;
    }

    public void setClaseCodigo(String claseCodigo){
        this.claseCodigo = claseCodigo;
    }
    
    public void setMentol(boolean mentol){
        this.mentol = mentol;
    }
    
    public void setCarton(int carton){
        this.carton = carton;
    }
    
    public void setNicotina(float nicotina){
        this.nicotina = nicotina;
    }
    
    public void setAlquitran(float alquitran){
        this.alquitran = alquitran;
    }
    
    public void setPrecioVenta(float precioVenta){
        this.precioVenta = precioVenta;
    }
    
    public void setPrecioCompra(float precioCompra){
        this.precioCompra = precioCompra;
    }
    
    public void setEmboltura(int emboltura){
        this.emboltura= emboltura;
    }
    
    public void setEstadoRegistro(int estadoRegistro){
        this.estadoRegistro = estadoRegistro;
    }
    
    public String getCodigo(){
        return this.codigo;
    }
    
    public String getMarca(){
        return this.marca;
    }
    
    public boolean getFiltro(){
        return this.filtro;
    }
    
    public String getColorCodigo(){
        return this.colorCodigo;
    }
    
    public String getClaseCodigo(){
        return this.claseCodigo;
    }
    
    public boolean getMentol(){
        return this.mentol;
    }

    public int getCarton(){
        return this.carton;
    }
    
    public float getNicotina(){
        return this.nicotina;
    }
    
    public float getAlquitran(){
        return this.alquitran;
    }
    
    public float getPrecioVenta(){
        return this.precioVenta;
    }
    
    public float getPrecioCompra(){
        return this.precioCompra;
    }
    
    public int getEmboltura(){
        return this.emboltura;
    }
    
    public int getEstadoRegistro(){
        return this.estadoRegistro;
    }
}
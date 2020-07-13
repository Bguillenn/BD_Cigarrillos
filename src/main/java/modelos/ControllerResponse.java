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
public class ControllerResponse {
    private String mensaje;
    private boolean estado;
    
    public ControllerResponse(String mensaje, boolean estado){
        this.mensaje = mensaje;
        this.estado  = estado;
    }
    
    public String getMessage(){
        return mensaje;
    }
    
    public boolean isOk(){
        return estado;
    }
}

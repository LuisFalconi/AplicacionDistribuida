/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.managebean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author DELL
 */
public interface ManageBeanInterfaces <T>{
    
    public void nuevo();
    public void grabar();
    public void seleccionar(T t);
    public void eliminar(T t);
    public void cancelar();
    public String iniciarSesion();
    
      default void mostrarMensajeTry(String mensaje, FacesMessage.Severity tipo){
        FacesMessage msg= new FacesMessage(tipo,mensaje,"");
        FacesContext fc= FacesContext.getCurrentInstance();
        fc.addMessage(null, msg);
    }
}

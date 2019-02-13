/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.managebean;

import com.tienda.entidades.Usuarios;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author DELL
 */
@Named(value = "seguridadManagedBean")
@ViewScoped
public class SeguridadManagedBean implements Serializable {
    private String usuario;
    
    public void verificar() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuarios us = (Usuarios) context.getExternalContext().getSessionMap().get("usuario");
            if (us == null) {
                context.getExternalContext().redirect("login");
                //System.out.println("NO HAY USUARIO");
            } else {
                usuario = us.getNombre();
                //System.out.println(usuario);
            }
        } catch (IOException e) {
            //log para un error
            System.out.println("error seguridad");
        }
    }
    
    public void cerrar() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    public SeguridadManagedBean() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
    
}

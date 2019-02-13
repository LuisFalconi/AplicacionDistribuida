package com.tienda.managebean;

import com.tienda.entidades.Usuarios;
import com.tienda.session.UsuariosFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author DELL
 */
@Named(value = "usuarioManagedBean")
@ViewScoped
public class UsuarioManagedBean implements Serializable, ManageBeanInterfaces<Usuarios> {

@EJB
    private UsuariosFacadeLocal usuariosFacadeLocal;

    private Usuarios usuario;

    @PostConstruct
    public void init() {
        usuario = new Usuarios();
    }
    
    public UsuarioManagedBean() {
    }

    @Override
    public void nuevo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void grabar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void seleccionar(Usuarios t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Usuarios t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String iniciarSesion() {
        Usuarios user;
        String redireccion = null;
        try {
            user = usuariosFacadeLocal.iniciarSesion(usuario);
            if (user != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
                redireccion = "Persona";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "AVISO: ", "Datos de usuario incorrectos"));

            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "aviso", "error"));
        }
        return redireccion;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    }


  

    


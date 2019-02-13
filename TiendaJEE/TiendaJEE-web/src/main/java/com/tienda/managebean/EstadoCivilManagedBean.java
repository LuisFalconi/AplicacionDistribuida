/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.managebean;

import com.tienda.entidades.EstadoCivil;
import com.tienda.session.EstadoCivilFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author DELL
 */
@Named(value = "estadoCivilManagedBean")
@ViewScoped
public class EstadoCivilManagedBean implements Serializable, ManageBeanInterfaces<EstadoCivil> {
    //INSTANCIAR SESSIONBEAN ESTADO CIVIL

    @EJB
    private EstadoCivilFacadeLocal estadoFacadeLocal;

    //LISTA PARA ESTADO CIVIL
    private List<EstadoCivil> listaEstado;

    public EstadoCivilManagedBean() {
    }

    //metodo para inicializar 
    @PostConstruct
    public void init() {
        listaEstado = estadoFacadeLocal.findAll();
    }

    //agregar o editar campo
    private EstadoCivil estadocivil;

    @Override
    public void nuevo() {
        estadocivil = new EstadoCivil();

    }

    @Override
    public void grabar() {
        try {
            if (estadocivil.getCodigo() == null) {
                estadoFacadeLocal.create(estadocivil);
            } else {
                estadoFacadeLocal.edit(estadocivil);

            }
            estadocivil = null;
            listaEstado = estadoFacadeLocal.findAll();
            mostrarMensajeTry("INFORMACIÓN EXITOSA", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMensajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void seleccionar(EstadoCivil t) {
        estadocivil = t;
    }

    @Override
    public void eliminar(EstadoCivil t) {
        try {
            estadoFacadeLocal.remove(t);
            listaEstado = estadoFacadeLocal.findAll();
            mostrarMensajeTry("ELIMINADO EXITOSAMENTE", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMensajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void cancelar() {
        estadocivil = null;
        listaEstado = estadoFacadeLocal.findAll();
    }

    @Override
    public void mostrarMensajeTry(String mensaje, FacesMessage.Severity tipo) {
    }

    public EstadoCivilFacadeLocal getEstadoFacadeLocal() {
        return estadoFacadeLocal;
    }

    public void setEstadoFacadeLocal(EstadoCivilFacadeLocal estadoFacadeLocal) {
        this.estadoFacadeLocal = estadoFacadeLocal;
    }

    public List<EstadoCivil> getListaEstado() {
        return listaEstado;
    }

    public void setListaEstado(List<EstadoCivil> listaEstado) {
        this.listaEstado = listaEstado;
    }

    public EstadoCivil getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(EstadoCivil estadocivil) {
        this.estadocivil = estadocivil;
    }

    @Override
    public String iniciarSesion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

}

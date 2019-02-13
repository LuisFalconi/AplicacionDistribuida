/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.managebean;

import com.tienda.entidades.Cargo;
import com.tienda.entidades.EstadoCivil;
import com.tienda.entidades.Persona;
import com.tienda.session.CargoFacadeLocal;
import com.tienda.session.EstadoCivilFacadeLocal;
import com.tienda.session.PersonaFacadeLocal;
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
@Named(value = "personaManagedBean")
@ViewScoped
public class PersonaManagedBean implements Serializable, ManageBeanInterfaces<Persona> {
    
    @EJB
    private PersonaFacadeLocal personaFacadeLocal;
    @EJB
    private CargoFacadeLocal cargoFacadeLocal;
    @EJB
    private EstadoCivilFacadeLocal estadoCivilFacadeLocal;
    
    private List<Persona> listaPersona;
    private List<Cargo> listaCargo;
    private List<EstadoCivil> listaEstado;
    private Persona persona;

    public PersonaManagedBean() {
    }
    
    @PostConstruct
    public void init(){
        listaPersona = personaFacadeLocal.findAll();
        listaCargo = cargoFacadeLocal.findAll();
        listaEstado = estadoCivilFacadeLocal.findAll();
    }


    @Override
    public void nuevo() {
        persona = new Persona();
    }

    @Override
    public void grabar() {
          try {
            if (persona.getCodigo()==null) {
                personaFacadeLocal.create(persona);
            }else{
                personaFacadeLocal.edit(persona);
            }
            persona = null;
            listaPersona = personaFacadeLocal.findAll();
            mostrarMensajeTry("Informacion correcta", FacesMessage.SEVERITY_INFO);
            
        } catch (Exception e) {
            mostrarMensajeTry("Informacion erronea", FacesMessage.SEVERITY_INFO);        
        } 
    }

    @Override
    public void seleccionar(Persona t) {
        persona = t;
    }

    @Override
    public void eliminar(Persona t) {
        try {
            personaFacadeLocal.remove(t);
            listaPersona = personaFacadeLocal.findAll();
            mostrarMensajeTry("Informacion correcta", FacesMessage.SEVERITY_INFO);            
        } catch (Exception e) {
            mostrarMensajeTry("Informacion erronea", FacesMessage.SEVERITY_INFO);            
        }
    }

    @Override
    public void cancelar() {
        persona = null;
        listaPersona = personaFacadeLocal.findAll();
    }

    @Override
    public void mostrarMensajeTry(String mensaje, FacesMessage.Severity tipo) {
    }

    public PersonaFacadeLocal getPersonaFacadeLocal() {
        return personaFacadeLocal;
    }

    public void setPersonaFacadeLocal(PersonaFacadeLocal personaFacadeLocal) {
        this.personaFacadeLocal = personaFacadeLocal;
    }

    public CargoFacadeLocal getCargoFacadeLocal() {
        return cargoFacadeLocal;
    }

    public void setCargoFacadeLocal(CargoFacadeLocal cargoFacadeLocal) {
        this.cargoFacadeLocal = cargoFacadeLocal;
    }

    public EstadoCivilFacadeLocal getEstadoCivilFacadeLocal() {
        return estadoCivilFacadeLocal;
    }

    public void setEstadoCivilFacadeLocal(EstadoCivilFacadeLocal estadoCivilFacadeLocal) {
        this.estadoCivilFacadeLocal = estadoCivilFacadeLocal;
    }

    public List<Persona> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(List<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }

    public List<Cargo> getListaCargo() {
        return listaCargo;
    }

    public void setListaCargo(List<Cargo> listaCargo) {
        this.listaCargo = listaCargo;
    }

    public List<EstadoCivil> getListaEstado() {
        return listaEstado;
    }

    public void setListaEstado(List<EstadoCivil> listaEstado) {
        this.listaEstado = listaEstado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String iniciarSesion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    
}

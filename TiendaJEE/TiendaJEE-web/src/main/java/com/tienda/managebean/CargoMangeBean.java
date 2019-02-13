/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.managebean;

import com.tienda.entidades.Cargo;
import com.tienda.session.CargoFacadeLocal;
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
@Named(value = "cargoMangeBean")
@ViewScoped
public class CargoMangeBean implements Serializable, ManageBeanInterfaces<Cargo>{

       // INSTANCIAR SESSIONBEAN CARGO
    @EJB
    private CargoFacadeLocal cargoFacadeLocal;
    
    //UNA LISTA PARA TRAER EL LISTADO DE CARGOS
    private List<Cargo> listaCargo;
    
    
    public CargoMangeBean() {
        
        
    }
    
    //METODO ES PARA INICIALIZAR AL EmpleadoFacadeLocal abstracmethods
    @PostConstruct
    public void init(){
        listaCargo=cargoFacadeLocal.findAll();
    }

    //agregar o editar un cargo
    private Cargo cargo;
    

    @Override
    public void nuevo() {
         cargo=new Cargo();
    }

    @Override
    public void grabar() {
        try{
            //CON ESTO SE COMPRUEBA SI ES PARA CREAR UN NUEVO O EDITAR
            if(cargo.getCodigo()==null){
                cargoFacadeLocal.create(cargo);
            }else{
                cargoFacadeLocal.edit(cargo);
            }

            //SI YA SE DUARDO O EDITO
            //LA CLASE SE PONE EN NULL
            //LUEGO DEBE LISTAR

            cargo=null;
            listaCargo= cargoFacadeLocal.findAll();
            //LUEGO UN MENSAJE
            mostrarMensajeTry("Información exitosa", FacesMessage.SEVERITY_INFO);
        }catch(Exception e){
             mostrarMensajeTry("Ocurrio un error", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void seleccionar(Cargo t) {
         cargo =t;
    }

    @Override
    public void eliminar(Cargo t) {
            try{
            //ELIMINO EMPLEADO
            cargoFacadeLocal.remove(t);
            
            //LUEGO LISTAR EMPLEADOS
            
            listaCargo=cargoFacadeLocal.findAll();
          
            //LUEGO UN MENSAJE
            mostrarMensajeTry("Eliminado exitosamente", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMensajeTry("Ocurrio un error", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void cancelar() {
        cargo=null;
        listaCargo=cargoFacadeLocal.findAll();
    }

    public CargoFacadeLocal getCargoFacadeLocal() {
        return cargoFacadeLocal;
    }

    public void setCargoFacadeLocal(CargoFacadeLocal cargoFacadeLocal) {
        this.cargoFacadeLocal = cargoFacadeLocal;
    }

    public List<Cargo> getListaCargo() {
        return listaCargo;
    }

    public void setListaCargo(List<Cargo> listaCargo) {
        this.listaCargo = listaCargo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public String iniciarSesion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    
    
    
}

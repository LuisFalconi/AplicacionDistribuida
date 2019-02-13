/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.managebean;

import com.tienda.entidades.Proveedor;
import com.tienda.session.ProveedorFacadeLocal;
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
@Named(value = "proveedorManagedBean")
@ViewScoped
public class ProveedorManagedBean implements Serializable, ManageBeanInterfaces<Proveedor>{

    // INSTANCIAR SESSIONBEAN PROVEEDOR
    @EJB
    private ProveedorFacadeLocal proveedorFacadeLocal;
    
       //UNA LISTA PARA TRAER EL LISTADO DE PROVEEDORES
    private List<Proveedor> listaProveedor;
    
    public ProveedorManagedBean() {
    }
    
     //METODO ES PARA INICIALIZAR AL EmpleadoFacadeLocal abstracmethods
    @PostConstruct
    public void init(){
        listaProveedor=proveedorFacadeLocal.findAll();
    }

    //agregar o editar un cargo
    private Proveedor proveedor;

    @Override
    public void nuevo() {
          proveedor=new Proveedor();
    }

    @Override
    public void grabar() {
        try{
            //CON ESTO SE COMPRUEBA SI ES PARA CREAR UN NUEVO O EDITAR
            if(proveedor.getCodigo()==null){
                proveedorFacadeLocal.create(proveedor);
            }else{
                proveedorFacadeLocal.edit(proveedor);
            }

            //SI YA SE DUARDO O EDITO
            //LA CLASE SE PONE EN NULL
            //LUEGO DEBE LISTAR

            proveedor=null;
            listaProveedor= proveedorFacadeLocal.findAll();
            //LUEGO UN MENSAJE
            mostrarMensajeTry("Información exitosa", FacesMessage.SEVERITY_INFO);
        }catch(Exception e){
             mostrarMensajeTry("Ocurrio un error", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void seleccionar(Proveedor t) {
        proveedor= t;
    }

    @Override
    public void eliminar(Proveedor t) {
           try{
            //ELIMINO EMPLEADO
            proveedorFacadeLocal.remove(t);
            
            //LUEGO LISTAR EMPLEADOS
            
            listaProveedor=proveedorFacadeLocal.findAll();
          
            //LUEGO UN MENSAJE
            mostrarMensajeTry("Eliminado exitosamente", FacesMessage.SEVERITY_INFO);
        }catch(Exception e){
             mostrarMensajeTry("Ocurrio un error", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void cancelar() {
        proveedor= null;
        listaProveedor=proveedorFacadeLocal.findAll();
    }

    @Override
    public void mostrarMensajeTry(String mensaje, FacesMessage.Severity tipo) {

    }

    public ProveedorFacadeLocal getProveedorFacadeLocal() {
        return proveedorFacadeLocal;
    }

    public void setProveedorFacadeLocal(ProveedorFacadeLocal proveedorFacadeLocal) {
        this.proveedorFacadeLocal = proveedorFacadeLocal;
    }

    public List<Proveedor> getListaProveedor() {
        return listaProveedor;
    }

    public void setListaProveedor(List<Proveedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String iniciarSesion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}

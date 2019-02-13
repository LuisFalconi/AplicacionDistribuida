/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.session;

import com.tienda.entidades.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DELL
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "TIENDAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    @Override
    public Usuarios iniciarSesion(Usuarios user) {
        Usuarios usuario =null;
        try{
        
            Query query;
            query = em.createNamedQuery("SELECT u FROM usuarios u WHERE u.usuario = '"+ user.getUsuario()+"'"+"and u.password='"+user.getPassword()+"'");
            List<Usuarios> lista = query.getResultList();
            if(!lista.isEmpty()){
                usuario=lista.get(0);
            }
        }catch( Exception e){
            System.out.println(e);
            System.out.println("facade");
        }
        return usuario;
    }
    
}

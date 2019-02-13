package com.tienda.entidades;

import com.tienda.entidades.Cargo;
import com.tienda.entidades.EstadoCivil;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-05T13:03:22")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, Integer> codigo;
    public static volatile SingularAttribute<Persona, Date> fechaNacimiento;
    public static volatile SingularAttribute<Persona, String> cedula;
    public static volatile SingularAttribute<Persona, String> apellido;
    public static volatile SingularAttribute<Persona, EstadoCivil> codigoEstadoCivil;
    public static volatile SingularAttribute<Persona, Cargo> codigoCargo;
    public static volatile SingularAttribute<Persona, String> nombre;

}
package entidades;

import entidades.Produto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-05-31T11:16:05", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Marca.class)
public class Marca_ { 

    public static volatile ListAttribute<Marca, Produto> produto;
    public static volatile SingularAttribute<Marca, String> nome;
    public static volatile SingularAttribute<Marca, Integer> id;

}
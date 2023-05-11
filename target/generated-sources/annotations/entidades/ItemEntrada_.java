package entidades;

import entidades.EstoqueEntrada;
import entidades.Produto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-05-31T11:16:05", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(ItemEntrada.class)
public class ItemEntrada_ { 

    public static volatile ListAttribute<ItemEntrada, Produto> produto;
    public static volatile SingularAttribute<ItemEntrada, EstoqueEntrada> estoqueEntrada;
    public static volatile SingularAttribute<ItemEntrada, Integer> id;
    public static volatile SingularAttribute<ItemEntrada, Integer> quantidade;

}
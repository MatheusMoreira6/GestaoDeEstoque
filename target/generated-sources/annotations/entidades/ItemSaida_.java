package entidades;

import entidades.EstoqueSaida;
import entidades.Produto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-05-31T11:16:05", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(ItemSaida.class)
public class ItemSaida_ { 

    public static volatile ListAttribute<ItemSaida, Produto> produto;
    public static volatile SingularAttribute<ItemSaida, Integer> id;
    public static volatile SingularAttribute<ItemSaida, EstoqueSaida> estoqueSaida;
    public static volatile SingularAttribute<ItemSaida, Integer> quantidade;

}
package entidades;

import entidades.Cliente;
import entidades.Funcionario;
import entidades.ItemSaida;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-05-31T11:16:04", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(EstoqueSaida.class)
public class EstoqueSaida_ { 

    public static volatile SingularAttribute<EstoqueSaida, Cliente> cliente;
    public static volatile SingularAttribute<EstoqueSaida, String> justificativa;
    public static volatile SingularAttribute<EstoqueSaida, Integer> id;
    public static volatile ListAttribute<EstoqueSaida, ItemSaida> itemSaida;
    public static volatile SingularAttribute<EstoqueSaida, Date> dataSaida;
    public static volatile SingularAttribute<EstoqueSaida, Funcionario> funcionario;

}
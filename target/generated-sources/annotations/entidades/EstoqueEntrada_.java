package entidades;

import entidades.Fornecedor;
import entidades.Funcionario;
import entidades.ItemEntrada;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-05-31T11:16:04", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(EstoqueEntrada.class)
public class EstoqueEntrada_ { 

    public static volatile SingularAttribute<EstoqueEntrada, Date> dataEntrada;
    public static volatile SingularAttribute<EstoqueEntrada, String> justificativa;
    public static volatile SingularAttribute<EstoqueEntrada, Integer> id;
    public static volatile ListAttribute<EstoqueEntrada, ItemEntrada> itemEntrada;
    public static volatile SingularAttribute<EstoqueEntrada, Fornecedor> fornecedor;
    public static volatile SingularAttribute<EstoqueEntrada, Funcionario> funcionario;
    public static volatile SingularAttribute<EstoqueEntrada, String> notaFiscal;

}
package entidades;

import entidades.Endereco;
import entidades.EstoqueEntrada;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-05-31T11:16:04", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Fornecedor.class)
public class Fornecedor_ { 

    public static volatile SingularAttribute<Fornecedor, String> telefone;
    public static volatile SingularAttribute<Fornecedor, String> nomeFantasia;
    public static volatile ListAttribute<Fornecedor, Endereco> endereco;
    public static volatile SingularAttribute<Fornecedor, String> inscricaoEstadual;
    public static volatile ListAttribute<Fornecedor, EstoqueEntrada> estoqueEntrada;
    public static volatile SingularAttribute<Fornecedor, Integer> id;
    public static volatile SingularAttribute<Fornecedor, String> cnpj;
    public static volatile SingularAttribute<Fornecedor, String> razaoSocial;
    public static volatile SingularAttribute<Fornecedor, String> email;

}
package entidades;

import entidades.CargoFuncionario;
import entidades.EstoqueEntrada;
import entidades.EstoqueSaida;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-05-31T11:16:04", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Funcionario.class)
public class Funcionario_ { 

    public static volatile SingularAttribute<Funcionario, String> senha;
    public static volatile ListAttribute<Funcionario, EstoqueEntrada> estoqueEntrada;
    public static volatile SingularAttribute<Funcionario, String> cpf;
    public static volatile SingularAttribute<Funcionario, String> nome;
    public static volatile SingularAttribute<Funcionario, Integer> id;
    public static volatile SingularAttribute<Funcionario, CargoFuncionario> cargo;
    public static volatile SingularAttribute<Funcionario, String> login;
    public static volatile ListAttribute<Funcionario, EstoqueSaida> estoqueSaida;

}
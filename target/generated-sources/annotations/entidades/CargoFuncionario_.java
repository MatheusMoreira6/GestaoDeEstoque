package entidades;

import entidades.Funcionario;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-05-31T11:16:04", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(CargoFuncionario.class)
public class CargoFuncionario_ { 

    public static volatile SingularAttribute<CargoFuncionario, Integer> id;
    public static volatile ListAttribute<CargoFuncionario, Funcionario> funcionario;
    public static volatile SingularAttribute<CargoFuncionario, String> cargo;

}
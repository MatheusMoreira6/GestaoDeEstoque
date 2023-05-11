package construtores;

import entidades.Cliente;
import entidades.EstoqueSaida;
import entidades.Funcionario;
import java.util.Date;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ConstrutorEstoqueSaida {

    private final String justificativa;
    private final Date dataSaida;
    private final Cliente cliente;
    private final Funcionario funcionario;
    private final Integer id;

    public ConstrutorEstoqueSaida(String justificativa, Date dataSaida, Cliente cliente, Funcionario funcionario, Integer id) {
        this.justificativa = justificativa;
        this.dataSaida = dataSaida;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.id = id;
    }

    private ConstrutorEstoqueSaida validar() throws Exception {
        if (id == null) {
            throw new Exception();
        } else if (justificativa.isBlank() || justificativa.isEmpty()) {
            throw new Exception();
        } else if (dataSaida == null) {
            throw new Exception();
        } else if (cliente == null) {
            throw new Exception();
        } else if (funcionario == null) {
            throw new Exception();
        }

        return (this);
    }

    public EstoqueSaida construir() throws Exception {
        validar();

        EstoqueSaida estoqueSaida = new EstoqueSaida();
        estoqueSaida.setId(id);
        estoqueSaida.setJustificativa(justificativa);
        estoqueSaida.setDataSaida(dataSaida);
        estoqueSaida.setCliente(cliente);
        estoqueSaida.setFuncionario(funcionario);

        return (estoqueSaida);
    }
}

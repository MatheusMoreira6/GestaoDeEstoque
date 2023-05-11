package construtores;

import entidades.EstoqueEntrada;
import entidades.Fornecedor;
import entidades.Funcionario;
import java.util.Date;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ConstrutorEstoqueEntrada {

    private final String justificativa;
    private final Date dataEntrada;
    private final String notaFiscal;
    private final Fornecedor fornecedor;
    private final Funcionario funcionario;
    private final Integer id;

    public ConstrutorEstoqueEntrada(String justificativa, Date dataEntrada, String notaFiscal, Fornecedor fornecedor, Funcionario funcionario, Integer id) {
        this.justificativa = justificativa;
        this.dataEntrada = dataEntrada;
        this.notaFiscal = notaFiscal;
        this.fornecedor = fornecedor;
        this.funcionario = funcionario;
        this.id = id;
    }

    private ConstrutorEstoqueEntrada validar() throws Exception {
        if (id == null) {
            throw new Exception();
        } else if (justificativa.isBlank() || justificativa.isEmpty()) {
            throw new Exception();
        } else if (dataEntrada == null) {
            throw new Exception();
        } else if (notaFiscal.isBlank() || notaFiscal.isEmpty()) {
            throw new Exception();
        } else if (fornecedor == null) {
            throw new Exception();
        } else if (funcionario == null) {
            throw new Exception();
        }

        return (this);
    }

    public EstoqueEntrada construir() throws Exception {
        validar();

        EstoqueEntrada estoqueEntrada = new EstoqueEntrada();

        estoqueEntrada.setId(id);
        estoqueEntrada.setJustificativa(justificativa);
        estoqueEntrada.setDataEntrada(dataEntrada);
        estoqueEntrada.setNotaFiscal(notaFiscal);
        estoqueEntrada.setFornecedor(fornecedor);
        estoqueEntrada.setFuncionario(funcionario);

        return (estoqueEntrada);
    }
}

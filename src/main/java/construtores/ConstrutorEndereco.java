package construtores;

import entidades.Cidade;
import entidades.Endereco;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ConstrutorEndereco {

    private final String logradouro;
    private final Integer numero;
    private final String bairro;
    private final Cidade cidade;
    private final Integer id;

    public ConstrutorEndereco(String logradouro, Integer numero, String bairro, Cidade cidade, Integer id) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.id = id;
    }

    private ConstrutorEndereco validar() throws Exception {
        if (id == null) {
            throw new Exception();
        } else if (logradouro.isBlank() || logradouro.isEmpty()) {
            throw new Exception();
        } else if (numero == null) {
            throw new Exception();
        } else if (bairro.isBlank() || bairro.isEmpty()) {
            throw new Exception();
        } else if (cidade == null) {
            throw new Exception();
        }

        return (this);
    }

    public Endereco construir() throws Exception {
        validar();

        Endereco endereco = new Endereco();
        endereco.setId(id);
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);

        return (endereco);
    }

}

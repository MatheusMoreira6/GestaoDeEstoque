package construtores;

import entidades.Cidade;
import entidades.Estado;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ConstrutorCidade {

    private final String nome;
    private final Estado estado;
    private final Integer id;

    public ConstrutorCidade(String nome, Estado estado, Integer id) {
        this.nome = nome;
        this.estado = estado;
        this.id = id;
    }

    private ConstrutorCidade validar() throws Exception {
        if (id == null) {
            throw new Exception();
        } else if (nome.isBlank() || nome.isEmpty()) {
            throw new Exception();
        } else if (estado == null) {
            throw new Exception();
        }

        return (this);
    }

    public Cidade construir() throws Exception {
        validar();

        Cidade cidade = new Cidade();
        cidade.setId(id);
        cidade.setNome(nome);
        cidade.setEstado(estado);

        return (cidade);
    }
}

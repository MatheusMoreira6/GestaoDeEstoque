package construtores;

import entidades.Estado;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ConstrutorEstado {

    private final String nome;
    private final Integer id;

    public ConstrutorEstado(String nome, Integer id) {
        this.nome = nome;
        this.id = id;
    }

    private ConstrutorEstado validar() throws Exception {
        if (id == null) {
            throw new Exception();
        } else if (nome.isBlank() || nome.isEmpty()) {
            throw new Exception();
        }

        return (this);
    }

    public Estado construir() throws Exception {
        validar();

        Estado estado = new Estado();
        estado.setId(id);
        estado.setNome(nome);

        return (estado);
    }

}

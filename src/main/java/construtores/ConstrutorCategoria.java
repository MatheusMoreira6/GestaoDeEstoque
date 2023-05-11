package construtores;

import entidades.Categoria;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ConstrutorCategoria {

    private final String nome;
    private final Integer id;

    public ConstrutorCategoria(String nome, Integer id) {
        this.nome = nome;
        this.id = id;
    }

    private ConstrutorCategoria validar() throws Exception {
        if (id == null) {
            throw new Exception();
        } else if (nome.isBlank() || nome.isEmpty()) {
            throw new Exception();
        }

        return (this);
    }

    public Categoria construir() throws Exception {
        validar();

        Categoria categoria = new Categoria();
        categoria.setId(id);
        categoria.setNome(nome);

        return (categoria);
    }
}

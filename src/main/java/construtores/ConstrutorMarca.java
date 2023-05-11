package construtores;

import entidades.Marca;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ConstrutorMarca {

    private final String nome;
    private final Integer id;

    public ConstrutorMarca(String nome, Integer id) {
        this.nome = nome;
        this.id = id;
    }

    private ConstrutorMarca validar() throws Exception {
        if (id == null) {
            throw new Exception();
        } else if (nome.isBlank() || nome.isEmpty()) {
            throw new Exception();
        }

        return (this);
    }

    public Marca construir() throws Exception {
        validar();

        Marca marca = new Marca();
        marca.setId(id);
        marca.setNome(nome);

        return (marca);
    }
}

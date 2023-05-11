package construtores;

import entidades.Endereco;
import entidades.Fornecedor;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ConstrutorFornecedor {

    private final String razaoSocial;
    private final String nomeFantasia;
    private final String cnpj;
    private final String inscricaoEstadual;
    private final String telefone;
    private final String email;
    private final Endereco endereco;
    private final Integer id;

    public ConstrutorFornecedor(String razaoSocial, String nomeFantasia, String cnpj, String inscricaoEstadual, String telefone, String email, Endereco endereco, Integer id) {
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.id = id;
    }

    private ConstrutorFornecedor validar() throws Exception {
        if (id == null) {
            throw new Exception();
        } else if (razaoSocial.isBlank() || razaoSocial.isEmpty()) {
            throw new Exception();
        } else if (nomeFantasia.isBlank() || nomeFantasia.isEmpty()) {
            throw new Exception();
        } else if (cnpj.isBlank() || cnpj.isEmpty()) {
            throw new Exception();
        } else if (inscricaoEstadual.isBlank() || inscricaoEstadual.isEmpty()) {
            throw new Exception();
        } else if (telefone.isBlank() || telefone.isEmpty()) {
            throw new Exception();
        } else if (email.isBlank() || email.isEmpty()) {
            throw new Exception();
        } else if (endereco == null) {
            throw new Exception();
        }

        return (this);
    }

    public Fornecedor construir() throws Exception {
        validar();

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(id);
        fornecedor.setRazaoSocial(razaoSocial);
        fornecedor.setNomeFantasia(nomeFantasia);
        fornecedor.setCnpj(cnpj);
        fornecedor.setInscricaoEstadual(inscricaoEstadual);
        fornecedor.setTelefone(telefone);
        fornecedor.setEmail(email);
        fornecedor.setEndereco(endereco);

        return (fornecedor);
    }
}

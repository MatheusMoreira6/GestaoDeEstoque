package construtores;

import entidades.Cliente;
import entidades.Endereco;
import java.util.Date;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ConstrutorCliente {

    private final String nome;
    private final String cpf;
    private final String telefone;
    private final Date dataNascimento;
    private final String email;
    private final Endereco endereco;
    private final Integer id;

    public ConstrutorCliente(String nome, String cpf, String telefone, Date dataNascimento, String email, Endereco endereco, Integer id) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.endereco = endereco;
        this.id = id;
    }

    private ConstrutorCliente validar() throws Exception {
        if (id == null) {
            throw new Exception();
        } else if (nome.isBlank() || nome.isEmpty()) {
            throw new Exception();
        } else if (cpf.isBlank() || cpf.isEmpty()) {
            throw new Exception();
        } else if (telefone.isBlank() || telefone.isEmpty()) {
            throw new Exception();
        } else if (dataNascimento == null) {
            throw new Exception();
        } else if (email.isBlank() || email.isEmpty()) {
            throw new Exception();
        } else if (endereco == null) {
            throw new Exception();
        }

        return (this);
    }

    public Cliente construir() throws Exception {
        validar();

        Cliente cliente = new Cliente();

        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setDataNascimento(dataNascimento);
        cliente.setEmail(email);
        cliente.setEndereco(endereco);

        return (cliente);
    }
}

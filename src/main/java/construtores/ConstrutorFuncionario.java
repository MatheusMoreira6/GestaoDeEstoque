package construtores;

import entidades.CargoFuncionario;
import entidades.Funcionario;
import funcoes.GerarSenha;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ConstrutorFuncionario {

    private String nome;
    private String cpf;
    private CargoFuncionario cargo;
    private final String login;
    private String senha;
    private Integer id;

    private final Integer construtor;

    public ConstrutorFuncionario(String login) {
        this.login = login;

        construtor = 0;
    }

    public ConstrutorFuncionario(String login, String senha) {
        this.login = login;
        this.senha = senha;

        construtor = 1;
    }

    public ConstrutorFuncionario(String nome, String cpf, CargoFuncionario cargo, String login, String senha, Integer id) {
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
        this.id = id;

        construtor = 2;
    }

    private ConstrutorFuncionario validar() throws Exception {
        switch (construtor) {
            case 0 -> {
                if (login.isBlank() || login.isEmpty()) {
                    throw new Exception();
                }
            }
            case 1 -> {
                if (login.isBlank() || login.isEmpty()) {
                    throw new Exception();
                } else if (senha.isBlank() || senha.isEmpty()) {
                    throw new Exception();
                }
            }
            case 2 -> {
                if (id == null) {
                    throw new Exception();
                } else if (nome.isBlank() || nome.isEmpty()) {
                    throw new Exception();
                } else if (cpf.isBlank() || cpf.isEmpty()) {
                    throw new Exception();
                } else if (cargo == null) {
                    throw new Exception();
                } else if (login.isBlank() || login.isEmpty()) {
                    throw new Exception();
                } else if (senha.isBlank() || senha.isEmpty()) {
                    throw new Exception();
                }
            }
        }

        return (this);
    }

    public Funcionario construir() throws Exception {
        validar();

        Funcionario funcionario = new Funcionario();

        switch (construtor) {
            case 0 -> {
                funcionario.setLogin(login);
            }
            case 1 -> {
                funcionario.setLogin(login);
                funcionario.setSenha(GerarSenha.criptografar(senha));
            }
            case 2 -> {
                funcionario.setId(id);
                funcionario.setNome(nome);
                funcionario.setCpf(cpf);
                funcionario.setCargo(cargo);
                funcionario.setLogin(login);
                funcionario.setSenha(GerarSenha.criptografar(senha));
            }
        }

        return (funcionario);
    }
}

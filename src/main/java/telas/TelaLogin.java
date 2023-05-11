package telas;

import casoUso.FuncionarioUC;
import construtores.ConstrutorFuncionario;
import entidades.Funcionario;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author Matheus
 * @author Gabriel
 */
public class TelaLogin extends JFrame implements ActionListener {

    private JPanel painelLogin = new JPanel();
    private JPanel painelBotoes = new JPanel();

    private JLabel labelUsuario = new JLabel("Usuário:");
    private JLabel labelSenha = new JLabel("Senha:");

    private JTextField textUsuario = new JTextField();
    private JPasswordField textSenha = new JPasswordField();

    private JButton botaoLogin = new JButton("Login");
    private JButton botaoCancelar = new JButton("Cancelar");

    public TelaLogin() {
        setTitle("Login");
        setResizable(false);
        setLayout(new BorderLayout());
        setSize(new Dimension(400, 200));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        painelLogin.setLayout(new GridBagLayout());
        painelLogin.setPreferredSize(new Dimension(0, 320));
        painelLogin.setBorder(new EmptyBorder(10, 10, 10, 10));
        addComponentePainelLogin(labelUsuario, 0, 0, 0.1, 10);
        addComponentePainelLogin(textUsuario, 1, 0, 1.0, 10);
        addComponentePainelLogin(labelSenha, 0, 1, 0.1, 10);
        addComponentePainelLogin(textSenha, 1, 1, 1.0, 10);

        painelBotoes.setLayout(new GridBagLayout());
        painelBotoes.setPreferredSize(new Dimension(0, 60));
        painelBotoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        addComponentePainelBotoes(botaoLogin, 0, 0, 0, 5);
        addComponentePainelBotoes(botaoCancelar, 1, 0, 5, 0);

        this.add(painelLogin, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.PAGE_END);

        botaoLogin.addActionListener(this);
        botaoCancelar.addActionListener(this);
    }

    private void addComponentePainelLogin(Component componente, Integer posicaoX, Integer posicaoY, Double tamanhoX, Integer marginTop) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets = new Insets(marginTop, 0, 0, 0);

        gbc.weighty = 0.01;
        gbc.weightx = tamanhoX;

        gbc.gridy = posicaoY;
        gbc.gridx = posicaoX;

        painelLogin.add(componente, gbc);
    }

    private void addComponentePainelBotoes(Component componente, Integer posicaoX, Integer posicaoY, Integer marginEsquerda, Integer marginDireita) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets = new Insets(0, marginEsquerda, 0, marginDireita);

        gbc.weighty = 1.0;
        gbc.weightx = 1.0;

        gbc.gridy = posicaoY;
        gbc.gridx = posicaoX;

        painelBotoes.add(componente, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(botaoCancelar)) {
            dispose();
        }

        if (evento.getSource().equals(botaoLogin)) {
            try {
                List<Funcionario> buscaFuncionario;

                ConstrutorFuncionario funcionario;
                funcionario = new ConstrutorFuncionario(textUsuario.getText(), new String(textSenha.getPassword()));

                buscaFuncionario = FuncionarioUC.buscarFuncionario(funcionario);

                if (!buscaFuncionario.isEmpty() && buscaFuncionario.size() < 2) {
                    if (buscaFuncionario.get(0).getCargo().getFuncoesAdministrador()) {
                        new TelaPrincipalAdministrador(buscaFuncionario.get(0)).setVisible(true);
                    } else {
                        new TelaPrincipal(buscaFuncionario.get(0)).setVisible(true);
                    }

                    dispose();
                } else {
                    if (buscaFuncionario.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Usuário não encontrado!", "Erro de Busca", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário duplicado!\n\nEntre em contato com o administrador!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Verifique se todos os campos estão preechidos corretamente.", "Erro ao realizar o login", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new TelaLogin().setVisible(true);
    }
}

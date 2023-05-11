package telas;

import casoUso.CargoFuncionarioUC;
import casoUso.FuncionarioUC;
import construtores.ConstrutorFuncionario;
import entidades.CargoFuncionario;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import persistencia.BancoFuncionario;

/**
 * @author Matheus
 * @author Gabriel
 */
public class TelaCadastrarFuncionario extends JDialog implements ActionListener {

    private Icon iconAdicao = new ImageIcon(getClass().getResource("/icons/new.png"));

    private JPanel painelInformacoes = new JPanel(new GridBagLayout());
    private JPanel painelBotoes = new JPanel(new GridBagLayout());

    private JButton botaoSalvar = new JButton("Salvar");
    private JButton botaoCancelar = new JButton("Cancelar");

    private JLabel labelNome = new JLabel("Nome:");
    private JTextField textNome = new JTextField();

    private JLabel labelCPF = new JLabel("CPF:");
    private JTextField textCPF = new JTextField();

    private JLabel labelCargo = new JLabel("Cargo:");
    private JComboBox<CargoFuncionario> comboCargo = new JComboBox<>();
    private JButton botaoNovoCargo = new JButton("Adicionar Cargo", iconAdicao);

    private JLabel labelLogin = new JLabel("Login:");
    private JTextField textLogin = new JTextField();

    private JLabel labelSenha = new JLabel("Senha:");
    private JTextField textSenha = new JTextField();

    private Funcionario funcionario;

    public TelaCadastrarFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;

        if (funcionario.getId() == null) {
            atualizarComboCargo();

            try {
                this.funcionario = (Funcionario) BancoFuncionario.salvar(funcionario);
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao criar o funcionario no banco", "Erro de Gravação", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            textNome.setText(funcionario.getNome());
            textCPF.setText(funcionario.getCpf());
            textLogin.setText(funcionario.getLogin());
            comboCargo.removeAllItems();
            comboCargo.addItem(funcionario.getCargo());
        }

        configuracoesTela();
    }

    private void configuracoesTela() {
        setModal(true);
        setResizable(false);
        setTitle("Cadastrar Funcionário");
        setSize(new Dimension(815, 340));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        painelInformacoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setPreferredSize(new Dimension(0, 60));

        addComponentePainelInformacoes(labelNome, 0, 0, 70, 30, 0, 5);
        addComponentePainelInformacoes(textNome, 1, 0, 480, 30, 0, 20);

        addComponentePainelInformacoes(labelCPF, 0, 1, 70, 30, 15, 5);
        addComponentePainelInformacoes(textCPF, 1, 1, 480, 30, 15, 20);

        addComponentePainelInformacoes(labelCargo, 0, 2, 70, 30, 15, 5);
        addComponentePainelInformacoes(comboCargo, 1, 2, 480, 30, 15, 20);
        addComponentePainelInformacoes(botaoNovoCargo, 2, 2, 200, 40, 15, 0);

        addComponentePainelInformacoes(labelLogin, 0, 3, 70, 30, 15, 5);
        addComponentePainelInformacoes(textLogin, 1, 3, 480, 30, 15, 20);

        addComponentePainelInformacoes(labelSenha, 0, 4, 70, 30, 15, 5);
        addComponentePainelInformacoes(textSenha, 1, 4, 480, 30, 15, 20);

        addComponentePainelBotoes(botaoSalvar, 0, 0, 0, 5);
        addComponentePainelBotoes(botaoCancelar, 1, 0, 5, 0);

        this.add(painelInformacoes, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.PAGE_END);

        botaoSalvar.addActionListener(this);
        botaoCancelar.addActionListener(this);
        botaoNovoCargo.addActionListener(this);
    }

    private void addComponentePainelInformacoes(Component componente, Integer posicaoX, Integer posicaoY, Integer tamanhoX, Integer tamanhoY, Integer marginTop, Integer marginDireita) {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = posicaoX;
        gbc.gridy = posicaoY;

        gbc.insets = new Insets(marginTop, 0, 0, marginDireita);

        componente.setPreferredSize(new Dimension(tamanhoX, tamanhoY));

        painelInformacoes.add(componente, gbc);
    }

    private void addComponentePainelBotoes(Component componente, Integer posicaoX, Integer posicaoY, Integer marginEsquerda, Integer marginDireita) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.gridx = posicaoX;
        gbc.gridy = posicaoY;

        gbc.insets = new Insets(0, marginEsquerda, 0, marginDireita);

        painelBotoes.add(componente, gbc);
    }

    private void atualizarComboCargo() {
        comboCargo.removeAllItems();

        try {
            List<CargoFuncionario> cargos = CargoFuncionarioUC.buscarTodos();

            for (int i = 0; i < cargos.size(); i++) {
                comboCargo.addItem(cargos.get(i));
            }
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar a lista de cargos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(botaoCancelar)) {
            try {
                if (funcionario.getCargo() == null) {
                    FuncionarioUC.removerFuncionario(funcionario);
                }
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao remover o funcionario com cargo vazio!", "Erro de Remoção", JOptionPane.ERROR_MESSAGE);
            }

            dispose();
        }

        if (evento.getSource().equals(botaoNovoCargo)) {
            new TelaCadastrarCargoFuncionario(new CargoFuncionario()).setVisible(true);

            atualizarComboCargo();
        }

        if (evento.getSource().equals(botaoSalvar)) {
            try {
                ConstrutorFuncionario construtorFuncionario;

                construtorFuncionario = new ConstrutorFuncionario(textLogin.getText());

                List<Funcionario> buscaFuncionario = FuncionarioUC.buscarFuncionario(construtorFuncionario);

                if (buscaFuncionario.size() >= 0 && buscaFuncionario.size() < 2) {
                    construtorFuncionario = new ConstrutorFuncionario(
                            textNome.getText(),
                            textCPF.getText(),
                            (CargoFuncionario) comboCargo.getSelectedItem(),
                            textLogin.getText(),
                            textSenha.getText(),
                            funcionario.getId()
                    );

                    FuncionarioUC.salvarFuncionario(construtorFuncionario);

                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "O Login já existe!", "Erro de Duplicação de conta", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Verifique se todos os campos estão preechidos corretamente.", "Erro ao salvar o funcionario", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

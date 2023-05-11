package telas;

import casoUso.CargoFuncionarioUC;
import construtores.ConstrutorCargoFuncionario;
import entidades.CargoFuncionario;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import persistencia.BancoCargoFuncionario;

/**
 * @author Matheus
 * @author Gabriel
 */
public class TelaCadastrarCargoFuncionario extends JDialog implements ActionListener {

    private JPanel painelNome = new JPanel(new GridBagLayout());
    private JPanel painelBotoes = new JPanel(new GridBagLayout());

    private JButton botaoSalvar = new JButton("Salvar");
    private JButton botaoCancelar = new JButton("Cancelar");

    private JLabel labelNome = new JLabel("Nome:");
    private JTextField textNome = new JTextField();

    private JLabel labelAdministrador = new JLabel("Administrador:");
    private final String opcoes[] = {"Não", "Sim"};
    private JComboBox<String> comboAdministrador = new JComboBox<>(opcoes);

    private CargoFuncionario cargoFuncionario;

    public TelaCadastrarCargoFuncionario(CargoFuncionario cargoFuncionario) {
        this.cargoFuncionario = cargoFuncionario;

        if (cargoFuncionario.getId() == null) {
            try {
                this.cargoFuncionario = (CargoFuncionario) BancoCargoFuncionario.salvar(cargoFuncionario);
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao criar o cargo no banco", "Erro de Gravação", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            textNome.setText(cargoFuncionario.getCargo());

            if (cargoFuncionario.getFuncoesAdministrador()) {
                comboAdministrador.removeAllItems();
                String tempOpcoes[] = {"Sim", "Não"};
                comboAdministrador = new JComboBox<>(tempOpcoes);
            }
        }

        configuracoesTela();
    }

    private void configuracoesTela() {
        setModal(true);
        setResizable(false);
        setTitle("Cadastrar Cargo do Funcionario");
        setSize(new Dimension(618, 200));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        painelNome.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setPreferredSize(new Dimension(0, 60));

        addComponentePainelNome(labelNome, 0, 0, 50, 30, 0, 5);
        addComponentePainelNome(textNome, 1, 0, 480, 30, 0, 0);
        addComponentePainelNome(labelAdministrador, 0, 1, 95, 30, 15, 5);
        addComponentePainelNome(comboAdministrador, 1, 1, 60, 30, 15, 0);

        addComponentePainelBotoes(botaoSalvar, 0, 0, 0, 5);
        addComponentePainelBotoes(botaoCancelar, 1, 0, 5, 0);

        this.add(painelNome, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.PAGE_END);

        botaoSalvar.addActionListener(this);
        botaoCancelar.addActionListener(this);
    }

    private void addComponentePainelNome(Component componente, Integer posicaoX, Integer posicaoY, Integer tamanhoX, Integer tamanhoY, Integer marginTop, Integer marginDireita) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = posicaoX;
        gbc.gridy = posicaoY;

        gbc.insets = new Insets(marginTop, 0, 0, marginDireita);

        componente.setPreferredSize(new Dimension(tamanhoX, tamanhoY));

        painelNome.add(componente, gbc);
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

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(botaoCancelar)) {
            try {
                if (cargoFuncionario.getFuncoesAdministrador() == null) {
                    CargoFuncionarioUC.removerCargoFuncionario(cargoFuncionario);
                }
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao remover o cargo com funções adminstrador vazio!", "Erro de Remoção", JOptionPane.ERROR_MESSAGE);
            }

            dispose();
        }

        if (evento.getSource().equals(botaoSalvar)) {
            try {
                ConstrutorCargoFuncionario construtorCargoFuncionario;

                if (comboAdministrador.getSelectedItem().equals("Sim")) {
                    construtorCargoFuncionario = new ConstrutorCargoFuncionario(
                            textNome.getText(),
                            true,
                            cargoFuncionario.getId()
                    );
                } else {
                    construtorCargoFuncionario = new ConstrutorCargoFuncionario(
                            textNome.getText(),
                            false,
                            cargoFuncionario.getId()
                    );
                }

                CargoFuncionarioUC.salvarCargoFuncionario(construtorCargoFuncionario);

                dispose();
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar o cargo!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

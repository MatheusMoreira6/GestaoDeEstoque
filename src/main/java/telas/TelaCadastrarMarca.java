package telas;

import casoUso.MarcaUC;
import construtores.ConstrutorMarca;
import entidades.Marca;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import persistencia.BancoMarca;

/**
 * @author Matheus
 * @author Gabriel
 */
public class TelaCadastrarMarca extends JDialog implements ActionListener {

    private JPanel painelNome = new JPanel(new GridBagLayout());
    private JPanel painelBotoes = new JPanel(new GridBagLayout());

    private JButton botaoSalvar = new JButton("Salvar");
    private JButton botaoCancelar = new JButton("Cancelar");

    private JLabel labelNome = new JLabel("Nome:");
    private JTextField textNome = new JTextField();

    private Marca marca;

    public TelaCadastrarMarca(Marca marca) {
        this.marca = marca;

        if (marca.getId() == null) {
            try {
                this.marca = (Marca) BancoMarca.salvar(marca);
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao criar a marca no banco", "Erro de Gravação", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            textNome.setText(marca.getNome());
        }

        setModal(true);
        setResizable(false);
        setTitle("Cadastrar Marca");
        setSize(new Dimension(578, 150));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        painelNome.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setPreferredSize(new Dimension(0, 60));

        addComponentePainelNome(labelNome, 0, 0, 50, 30, 5);
        addComponentePainelNome(textNome, 1, 0, 480, 30, 0);

        addComponentePainelBotoes(botaoSalvar, 0, 0, 0, 5);
        addComponentePainelBotoes(botaoCancelar, 1, 0, 5, 0);

        this.add(painelNome, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.PAGE_END);

        botaoSalvar.addActionListener(this);
        botaoCancelar.addActionListener(this);
    }

    private void addComponentePainelNome(Component componente, Integer posicaoX, Integer posicaoY, Integer tamanhoX, Integer tamanhoY, Integer marginDireita) {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = posicaoX;
        gbc.gridy = posicaoY;

        gbc.insets = new Insets(0, 0, 0, marginDireita);

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
            dispose();
        }

        if (evento.getSource().equals(botaoSalvar)) {
            try {
                ConstrutorMarca construtorMarca;

                construtorMarca = new ConstrutorMarca(
                        textNome.getText(),
                        marca.getId()
                );

                MarcaUC.salvarMarca(construtorMarca);

                dispose();
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Verifique se todos os campos estão preechidos corretamente.", "Erro ao salvar o funcionario", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

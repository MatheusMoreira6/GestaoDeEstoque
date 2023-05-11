package telas;

import casoUso.CategoriaUC;
import construtores.ConstrutorCategoria;
import entidades.Categoria;

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
import persistencia.BancoCategoria;

/**
 * @author Matheus
 * @author Gabriel
 */
public class TelaCadastrarCategoria extends JDialog implements ActionListener {

    private JPanel painelNome = new JPanel(new GridBagLayout());
    private JPanel painelBotoes = new JPanel(new GridBagLayout());

    private JButton botaoSalvar = new JButton("Salvar");
    private JButton botaoCancelar = new JButton("Cancelar");

    private JLabel labelNome = new JLabel("Nome:");
    private JTextField textNome = new JTextField();

    private Categoria categoria;

    public TelaCadastrarCategoria(Categoria categoria) {
        this.categoria = categoria;

        if (categoria.getId() == null) {
            try {
                this.categoria = (Categoria) BancoCategoria.salvar(categoria);
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao criar a marca no banco", "Erro de Gravação", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            textNome.setText(categoria.getNome());
        }

        setModal(true);
        setResizable(false);
        setTitle("Cadastrar Categoria");
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
            if (textNome.getText().isBlank() || textNome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo nome está vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    ConstrutorCategoria construtorCategoria;

                    construtorCategoria = new ConstrutorCategoria(
                            textNome.getText(),
                            categoria.getId()
                    );

                    CategoriaUC.salvarCategoria(construtorCategoria);

                    dispose();
                } catch (Exception excecao) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar a categoria!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}

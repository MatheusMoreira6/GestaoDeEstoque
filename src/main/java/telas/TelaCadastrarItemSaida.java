package telas;

import casoUso.ItemSaidaUC;
import casoUso.ProdutoUC;
import construtores.ConstrutorItemSaida;
import entidades.EstoqueSaida;
import entidades.Produto;

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

/**
 * @author Matheus
 * @author Gabriel
 */
public class TelaCadastrarItemSaida extends JDialog implements ActionListener {

    private Icon iconAdicao = new ImageIcon(getClass().getResource("/icons/new.png"));

    private JPanel painelInformacoes = new JPanel(new GridBagLayout());
    private JPanel painelBotoes = new JPanel(new GridBagLayout());

    private JButton botaoSalvar = new JButton("Salvar");
    private JButton botaoCancelar = new JButton("Cancelar");

    private JLabel labelProduto = new JLabel("Produto:");
    private JComboBox<Produto> comboProduto = new JComboBox<>();
    private JButton botaoNovoProduto = new JButton("Adicionar Produto", iconAdicao);

    private JLabel labelQuantidade = new JLabel("Quantidade:");
    private JTextField textQuantidade = new JTextField();

    private final EstoqueSaida estoqueSaida;

    public TelaCadastrarItemSaida(EstoqueSaida estoqueSaida) {
        setModal(true);
        setResizable(false);
        setTitle("Cadastrar Item Saida");
        setSize(new Dimension(820, 205));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.estoqueSaida = estoqueSaida;

        painelInformacoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setPreferredSize(new Dimension(0, 60));

        addComponentePainelInformacoes(labelProduto, 0, 0, 70, 30, 15, 5);
        addComponentePainelInformacoes(comboProduto, 1, 0, 480, 30, 15, 20);
        addComponentePainelInformacoes(botaoNovoProduto, 2, 0, 200, 40, 15, 0);

        addComponentePainelInformacoes(labelQuantidade, 0, 1, 70, 30, 0, 5);
        addComponentePainelInformacoes(textQuantidade, 1, 1, 480, 30, 0, 20);

        addComponentePainelBotoes(botaoSalvar, 0, 0, 0, 5);
        addComponentePainelBotoes(botaoCancelar, 1, 0, 5, 0);

        this.add(painelInformacoes, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.PAGE_END);

        botaoSalvar.addActionListener(this);
        botaoCancelar.addActionListener(this);
        botaoNovoProduto.addActionListener(this);

        atualizarComboProduto();
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

    private void atualizarComboProduto() {
        comboProduto.removeAllItems();

        try {
            List<Produto> produtos = ProdutoUC.buscarTodos();

            for (int i = 0; i < produtos.size(); i++) {
                comboProduto.addItem(produtos.get(i));
            }
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar a lista de produtos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(botaoCancelar)) {
            dispose();
        }

        if (evento.getSource().equals(botaoNovoProduto)) {
            new TelaCadastrarProduto(new Produto()).setVisible(true);

            atualizarComboProduto();
        }

        if (evento.getSource().equals(botaoSalvar)) {
            try {
                ConstrutorItemSaida construtorItemSaida;

                construtorItemSaida = new ConstrutorItemSaida(
                        (Produto) comboProduto.getSelectedItem(),
                        Integer.parseInt(textQuantidade.getText()),
                        estoqueSaida
                );

                ItemSaidaUC.salvarItemSaida(construtorItemSaida);

                dispose();
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Verifique se todos os campos estão preechidos corretamente.", "Erro ao salvar a entrada do item", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

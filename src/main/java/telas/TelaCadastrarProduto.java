package telas;

import casoUso.CategoriaUC;
import casoUso.MarcaUC;
import casoUso.ProdutoUC;
import construtores.ConstrutorProduto;
import entidades.Categoria;
import entidades.Marca;
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
import persistencia.BancoProduto;

/**
 * @author Matheus
 * @author Gabriel
 */
public class TelaCadastrarProduto extends JDialog implements ActionListener {

    private Icon iconAdicao = new ImageIcon(getClass().getResource("/icons/new.png"));

    private JPanel painelInformacoes = new JPanel(new GridBagLayout());
    private JPanel painelBotoes = new JPanel(new GridBagLayout());

    private JButton botaoSalvar = new JButton("Salvar");
    private JButton botaoCancelar = new JButton("Cancelar");

    private JLabel labelNome = new JLabel("Nome:");
    private JTextField textNome = new JTextField();

    private JLabel labelMarca = new JLabel("Marca:");
    private JComboBox<Marca> comboMarca = new JComboBox<>();
    private JButton botaoNovaMarca = new JButton("Adicionar Marca", iconAdicao);

    private JLabel labelGarantia = new JLabel("Garantia:");
    private JTextField textGarantia = new JTextField();

    private JLabel labelQuantidade = new JLabel("Quantidade:");
    private JTextField textQuantidade = new JTextField();

    private JLabel labelCategoria = new JLabel("Categoria:");
    private JComboBox<Categoria> comboCategoria = new JComboBox<>();
    private JButton botaoNovaCategoria = new JButton("Adicionar Categoria", iconAdicao);

    private JLabel labelPreco = new JLabel("Preço:");
    private JTextField textPreco = new JTextField();

    private Produto produto;

    public TelaCadastrarProduto(Produto produto) {
        this.produto = produto;

        if (produto.getId() == null) {
            atualizarComboMarca();
            atualizarComboCategoria();

            try {
                this.produto = (Produto) BancoProduto.salvar(produto);
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao criar o produto no banco", "Erro de Gravação", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            textNome.setText(produto.getNome());
            textGarantia.setText(String.format("%s", produto.getGarantia()));
            textQuantidade.setText(String.format("%s", produto.getQuantidade()));
            textPreco.setText(String.format("%s", produto.getPreco()));
            comboMarca.removeAllItems();
            comboCategoria.removeAllItems();
            comboMarca.addItem(produto.getMarca());
            comboCategoria.addItem(produto.getCategoria());
        }

        configuracoesTela();
    }

    private void configuracoesTela() {
        setModal(true);
        setResizable(false);
        setTitle("Cadastrar Produto");
        setSize(new Dimension(815, 400));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        painelInformacoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setPreferredSize(new Dimension(0, 60));

        addComponentePainelInformacoes(labelNome, 0, 0, 70, 30, 0, 5);
        addComponentePainelInformacoes(textNome, 1, 0, 480, 30, 0, 20);

        addComponentePainelInformacoes(labelMarca, 0, 1, 70, 30, 15, 5);
        addComponentePainelInformacoes(comboMarca, 1, 1, 480, 30, 15, 20);
        addComponentePainelInformacoes(botaoNovaMarca, 2, 1, 200, 40, 15, 0);

        addComponentePainelInformacoes(labelGarantia, 0, 2, 70, 30, 15, 5);
        addComponentePainelInformacoes(textGarantia, 1, 2, 480, 30, 15, 20);

        addComponentePainelInformacoes(labelQuantidade, 0, 3, 70, 30, 15, 5);
        addComponentePainelInformacoes(textQuantidade, 1, 3, 480, 30, 15, 20);

        addComponentePainelInformacoes(labelCategoria, 0, 4, 70, 30, 15, 5);
        addComponentePainelInformacoes(comboCategoria, 1, 4, 480, 30, 15, 20);
        addComponentePainelInformacoes(botaoNovaCategoria, 2, 4, 200, 40, 15, 0);

        addComponentePainelInformacoes(labelPreco, 0, 5, 70, 30, 15, 5);
        addComponentePainelInformacoes(textPreco, 1, 5, 480, 30, 15, 20);

        addComponentePainelBotoes(botaoSalvar, 0, 0, 0, 5);
        addComponentePainelBotoes(botaoCancelar, 1, 0, 5, 0);

        this.add(painelInformacoes, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.PAGE_END);

        botaoSalvar.addActionListener(this);
        botaoCancelar.addActionListener(this);
        botaoNovaMarca.addActionListener(this);
        botaoNovaCategoria.addActionListener(this);
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

    private void atualizarComboMarca() {
        comboMarca.removeAllItems();

        try {
            List<Marca> marcas = MarcaUC.buscarTodos();

            for (int i = 0; i < marcas.size(); i++) {
                comboMarca.addItem(marcas.get(i));
            }
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar a lista de marcas!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarComboCategoria() {
        comboCategoria.removeAllItems();

        try {
            List<Categoria> categorias = CategoriaUC.buscarTodos();

            for (int i = 0; i < categorias.size(); i++) {
                comboCategoria.addItem(categorias.get(i));
            }
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar a lista de marcas!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(botaoCancelar)) {
            try {
                if (produto.getMarca() == null || produto.getCategoria() == null) {
                    ProdutoUC.removerProduto(produto);
                }
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao remover o produto com marca/categoria vazia!", "Erro de Remoção", JOptionPane.ERROR_MESSAGE);
            }

            dispose();
        }

        if (evento.getSource().equals(botaoNovaMarca)) {
            new TelaCadastrarMarca(new Marca()).setVisible(true);

            atualizarComboMarca();
        }

        if (evento.getSource().equals(botaoNovaCategoria)) {
            new TelaCadastrarCategoria(new Categoria()).setVisible(true);

            atualizarComboCategoria();
        }

        if (evento.getSource().equals(botaoSalvar)) {
            try {
                ConstrutorProduto construtorProduto;

                construtorProduto = new ConstrutorProduto(textNome.getText(),
                        (Marca) comboMarca.getSelectedItem(),
                        Integer.parseInt(textGarantia.getText()),
                        Integer.parseInt(textQuantidade.getText()),
                        (Categoria) comboCategoria.getSelectedItem(),
                        Float.parseFloat(textPreco.getText()),
                        produto.getId()
                );

                ProdutoUC.salvarProduto(construtorProduto);

                dispose();
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Verifique se todos os campos estão preechidos corretamente.", "Erro ao salvar o produto", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

package telas;

import casoUso.ProdutoUC;
import entidades.Funcionario;
import entidades.Produto;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * @author Matheus
 * @author Gabriel
 */
public class TelaPrincipalAdministrador extends JFrame implements ActionListener {

    private Icon iconEntrada = new ImageIcon(getClass().getResource("/icons/add.png"));
    private Icon iconSaida = new ImageIcon(getClass().getResource("/icons/out.png"));
    private Icon iconClientes = new ImageIcon(getClass().getResource("/icons/people.png"));
    private Icon iconFornecedores = new ImageIcon(getClass().getResource("/icons/shipping.png"));
    private Icon iconFuncionarios = new ImageIcon(getClass().getResource("/icons/badge.png"));
    private Icon iconLogout = new ImageIcon(getClass().getResource("/icons/logout.png"));

    private JPanel painelCentro = new JPanel(new BorderLayout());
    private JPanel painelTop = new JPanel(new GridBagLayout());

    private JButton botaoEntradas = new JButton("Entradas", iconEntrada);
    private JButton botaoSaidas = new JButton("Saídas", iconSaida);
    private JButton botaoClientes = new JButton("Clientes", iconClientes);
    private JButton botaoFornecedores = new JButton("Fornecedores", iconFornecedores);
    private JButton botaoFuncionarios = new JButton("Funcionários", iconFuncionarios);
    private JButton botaoLogout = new JButton("Logout", iconLogout);

    private JLabel labelProdutos = new JLabel("Produtos:");

    private JMenuBar menuBar = new JMenuBar();

    private JMenu menuProduto = new JMenu("Produtos");
    private JMenuItem editar = new JMenuItem("Editar Produto");
    private JMenuItem remover = new JMenuItem("Remover Produto");

    private JMenu menuMarca = new JMenu("Marcas");
    private JMenuItem listagemMarca = new JMenuItem("Listagem");

    private JMenu menuCategoria = new JMenu("Categorias");
    private JMenuItem listagemCategoria = new JMenuItem("Listagem");

    private JMenu menuTabela = new JMenu("Tabela");
    private JMenuItem atualizar = new JMenuItem("Atualizar");

    private JTable tabela = new JTable();
    private JScrollPane scrollTabela = new JScrollPane(tabela);

    private Funcionario funcionario;
    private List<Produto> produtos = new ArrayList<>();

    public TelaPrincipalAdministrador(Funcionario funcionario) {
        setTitle("Listagem de Produtos (Administrador)");
        setSize(new Dimension(1280, 720));
        setMinimumSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(menuBar);

        this.funcionario = funcionario;

        menuBar.add(menuProduto);
        menuProduto.add(editar);
        menuProduto.add(remover);

        menuBar.add(menuMarca);
        menuMarca.add(listagemMarca);

        menuBar.add(menuCategoria);
        menuCategoria.add(listagemCategoria);

        menuBar.add(menuTabela);
        menuTabela.add(atualizar);

        painelTop.setPreferredSize(new Dimension(0, 80));
        painelTop.setBorder(new EmptyBorder(10, 10, 10, 10));

        addComponentePainelTop(botaoEntradas, 0, 0, 10);
        addComponentePainelTop(botaoSaidas, 1, 10, 10);
        addComponentePainelTop(botaoClientes, 2, 10, 10);
        addComponentePainelTop(botaoFornecedores, 3, 10, 10);
        addComponentePainelTop(botaoFuncionarios, 4, 10, 10);
        addComponentePainelTop(botaoLogout, 5, 10, 0);

        painelCentro.setBorder(new EmptyBorder(10, 10, 10, 10));

        labelProdutos.setHorizontalAlignment(JLabel.CENTER);
        labelProdutos.setBorder(new EmptyBorder(10, 10, 10, 10));

        painelCentro.add(labelProdutos, BorderLayout.PAGE_START);
        painelCentro.add(scrollTabela, BorderLayout.CENTER);

        this.add(painelTop, BorderLayout.PAGE_START);
        this.add(painelCentro, BorderLayout.CENTER);

        botaoEntradas.addActionListener(this);
        botaoSaidas.addActionListener(this);
        botaoClientes.addActionListener(this);
        botaoFornecedores.addActionListener(this);
        botaoFuncionarios.addActionListener(this);
        botaoLogout.addActionListener(this);
        editar.addActionListener(this);
        remover.addActionListener(this);
        listagemMarca.addActionListener(this);
        listagemCategoria.addActionListener(this);
        atualizar.addActionListener(this);

        atualizarTabela();
    }

    private void addComponentePainelTop(Component componente, Integer posicaoX, Integer marginEsquerda, Integer marginDireita) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridy = 0;

        gbc.gridx = posicaoX;
        gbc.insets = new Insets(0, marginEsquerda, 0, marginDireita);

        painelTop.add(componente, gbc);
    }

    private void atualizarTabela() {
        try {
            produtos = ProdutoUC.buscarTodos();

            String[] titulos = {"ID", "Nome", "Marca", "Garantia", "Quantidade", "Categoria", "Preço"};
            Object[][] dados = new Object[produtos.size()][titulos.length];

            for (int i = 0; i < produtos.size(); i++) {
                dados[i][0] = produtos.get(i).getId();
                dados[i][1] = produtos.get(i).getNome();
                dados[i][2] = produtos.get(i).getMarca().getNome();
                dados[i][3] = produtos.get(i).getGarantia();
                dados[i][4] = produtos.get(i).getQuantidade();
                dados[i][5] = produtos.get(i).getCategoria().getNome();
                dados[i][6] = String.format("R$ %.2f", produtos.get(i).getPreco());
            }

            tabela.setModel(new DefaultTableModel(dados, titulos));
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar a lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(editar)) {
            if (tabela.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Nenhum Produto Selecionado!", "Erro de Seleção", JOptionPane.ERROR_MESSAGE);
            } else {
                new TelaCadastrarProduto(produtos.get(tabela.getSelectedRow())).setVisible(true);
            }

            atualizarTabela();
        }

        if (evento.getSource().equals(remover)) {
            if (tabela.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Nenhum Produto Selecionado!", "Erro de Seleção", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    ProdutoUC.removerProduto(produtos.get(tabela.getSelectedRow()));
                } catch (Exception excecao) {
                    JOptionPane.showMessageDialog(null, "Falha o remover o produto do banco de dados", "Erro de remoção", JOptionPane.ERROR_MESSAGE);
                }
            }

            atualizarTabela();
        }

        if (evento.getSource().equals(listagemMarca)) {
            new TelaListarMarca(funcionario).setVisible(true);

            dispose();
        }

        if (evento.getSource().equals(listagemCategoria)) {
            new TelaListarCategoria(funcionario).setVisible(true);

            dispose();
        }

        if (evento.getSource().equals(atualizar)) {
            atualizarTabela();
        }

        if (evento.getSource().equals(botaoEntradas)) {
            new TelaListarEstoqueEntrada(funcionario).setVisible(true);

            dispose();
        }

        if (evento.getSource().equals(botaoSaidas)) {
            new TelaListarEstoqueSaida(funcionario).setVisible(true);

            dispose();
        }

        if (evento.getSource().equals(botaoClientes)) {
            new TelaListarCliente(funcionario).setVisible(true);

            dispose();
        }

        if (evento.getSource().equals(botaoFornecedores)) {
            new TelaListarFornecedor(funcionario).setVisible(true);

            dispose();
        }

        if (evento.getSource().equals(botaoFuncionarios)) {
            new TelaListarFuncionario(funcionario).setVisible(true);

            dispose();
        }

        if (evento.getSource().equals(botaoLogout)) {
            new TelaLogin().setVisible(true);

            dispose();
        }
    }

}

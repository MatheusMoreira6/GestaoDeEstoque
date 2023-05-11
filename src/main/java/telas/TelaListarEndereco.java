package telas;

import casoUso.EnderecoUC;
import entidades.Endereco;
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
public class TelaListarEndereco extends JFrame implements ActionListener {

    private Icon iconAdd = new ImageIcon(getClass().getResource("/icons/post.png"));
    private Icon iconEdit = new ImageIcon(getClass().getResource("/icons/edit.png"));
    private Icon iconDelete = new ImageIcon(getClass().getResource("/icons/delete.png"));
    private Icon iconMore = new ImageIcon(getClass().getResource("/icons/more.png"));
    private Icon iconReturn = new ImageIcon(getClass().getResource("/icons/return.png"));

    private JPanel painelTop = new JPanel(new GridBagLayout());
    private JPanel painelCentro = new JPanel(new BorderLayout());

    private JButton botaoCadastrar = new JButton("Novo Endereço", iconAdd);
    private JButton botaoEditar = new JButton("Editar", iconEdit);
    private JButton botaoDeletar = new JButton("Deletar", iconDelete);
    private JButton botaoCidade = new JButton("Cidades", iconMore);
    private JButton botaoEstado = new JButton("Estados", iconMore);
    private JButton botaoVoltar = new JButton("Voltar", iconReturn);

    private JLabel labelEndereco = new JLabel("Endereços:");

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuTabela = new JMenu("Tabela");
    private JMenuItem atualizar = new JMenuItem("Atualizar");

    private JTable tabela = new JTable();
    private JScrollPane scrollTabela = new JScrollPane(tabela);

    private List<Endereco> listaEnderecos;
    private Funcionario funcionario;
    private Boolean tela;

    public TelaListarEndereco(Funcionario funcionario, Boolean tela) {
        setTitle("Listagem de Endereços");
        setSize(new Dimension(1280, 720));
        setMinimumSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(menuBar);

        this.funcionario = funcionario;
        this.tela = tela;

        menuBar.add(menuTabela);
        menuTabela.add(atualizar);

        painelTop.setPreferredSize(new Dimension(0, 80));
        painelTop.setBorder(new EmptyBorder(10, 10, 10, 10));

        addComponentePainelTop(botaoCadastrar, 0, 0, 10);
        addComponentePainelTop(botaoEditar, 1, 10, 10);
        addComponentePainelTop(botaoDeletar, 2, 10, 10);
        addComponentePainelTop(botaoCidade, 3, 10, 10);
        addComponentePainelTop(botaoEstado, 4, 10, 10);
        addComponentePainelTop(botaoVoltar, 5, 10, 0);

        painelCentro.setBorder(new EmptyBorder(10, 10, 10, 10));

        labelEndereco.setHorizontalAlignment(JLabel.CENTER);
        labelEndereco.setBorder(new EmptyBorder(10, 10, 10, 10));

        painelCentro.add(labelEndereco, BorderLayout.PAGE_START);
        painelCentro.add(scrollTabela, BorderLayout.CENTER);

        this.add(painelTop, BorderLayout.PAGE_START);
        this.add(painelCentro, BorderLayout.CENTER);

        botaoCadastrar.addActionListener(this);
        botaoEditar.addActionListener(this);
        botaoDeletar.addActionListener(this);
        botaoCidade.addActionListener(this);
        botaoEstado.addActionListener(this);
        botaoVoltar.addActionListener(this);
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
            listaEnderecos = EnderecoUC.buscarTodos();

            String[] titulos = {"ID", "Logradouro", "Número", "Bairro", "Cidade", "Estado"};
            Object[][] dados = new Object[listaEnderecos.size()][titulos.length];

            for (int i = 0; i < listaEnderecos.size(); i++) {
                dados[i][0] = listaEnderecos.get(i).getId();
                dados[i][1] = listaEnderecos.get(i).getLogradouro();
                dados[i][2] = listaEnderecos.get(i).getNumero();
                dados[i][3] = listaEnderecos.get(i).getBairro();
                dados[i][4] = listaEnderecos.get(i).getCidade();
                dados[i][5] = listaEnderecos.get(i).getCidade().getEstado();
            }

            tabela.setModel(new DefaultTableModel(dados, titulos));
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar a lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(atualizar)) {
            atualizarTabela();
        }

        if (evento.getSource().equals(botaoCadastrar)) {
            new TelaCadastrarEndereco(new Endereco()).setVisible(true);

            atualizarTabela();
        }

        if (evento.getSource().equals(botaoEditar)) {
            if (tabela.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Nenhum Endereço Selecionado!", "Erro de Seleção", JOptionPane.ERROR_MESSAGE);
            } else {
                new TelaCadastrarEndereco(listaEnderecos.get(tabela.getSelectedRow())).setVisible(true);
            }

            atualizarTabela();
        }

        if (evento.getSource().equals(botaoDeletar)) {
            if (tabela.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Nenhum Endereço Selecionado!", "Erro de Seleção", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    EnderecoUC.removerEndereco(listaEnderecos.get(tabela.getSelectedRow()));
                } catch (Exception excecao) {
                    JOptionPane.showMessageDialog(null, "Falha o remover o endereço do banco de dados", "Erro de remoção", JOptionPane.ERROR_MESSAGE);
                }
            }

            atualizarTabela();
        }

        if (evento.getSource().equals(botaoCidade)) {
            new TelaListarCidade(funcionario, tela).setVisible(true);

            dispose();
        }

        if (evento.getSource().equals(botaoEstado)) {
            new TelaListarEstado(funcionario, tela).setVisible(true);

            dispose();
        }

        if (evento.getSource().equals(botaoVoltar)) {
            if (tela) {
                new TelaListarCliente(funcionario).setVisible(true);
            } else {
                new TelaListarFornecedor(funcionario).setVisible(true);
            }

            dispose();
        }
    }

}

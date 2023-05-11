package telas;

import casoUso.CategoriaUC;
import entidades.Categoria;
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
public class TelaListarCategoria extends JFrame implements ActionListener {

    private Icon iconAdd = new ImageIcon(getClass().getResource("/icons/post.png"));
    private Icon iconEdit = new ImageIcon(getClass().getResource("/icons/edit.png"));
    private Icon iconDelete = new ImageIcon(getClass().getResource("/icons/delete.png"));
    private Icon iconMore = new ImageIcon(getClass().getResource("/icons/more.png"));
    private Icon iconReturn = new ImageIcon(getClass().getResource("/icons/return.png"));

    private JPanel painelTop = new JPanel(new GridBagLayout());
    private JPanel painelCentro = new JPanel(new BorderLayout());

    private JButton botaoCadastrar = new JButton("Nova Categoria", iconAdd);
    private JButton botaoEditar = new JButton("Editar", iconEdit);
    private JButton botaoDeletar = new JButton("Deletar", iconDelete);
    private JButton botaoVoltar = new JButton("Voltar", iconReturn);

    private JLabel labelCategoria = new JLabel("Categorias:");

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuTabela = new JMenu("Tabela");
    private JMenuItem atualizar = new JMenuItem("Atualizar");

    private JTable tabela = new JTable();
    private JScrollPane scrollTabela = new JScrollPane(tabela);

    private List<Categoria> listaCategorias;
    private Funcionario funcionario;

    public TelaListarCategoria(Funcionario funcionario) {
        setTitle("Listagem de Categorias");
        setSize(new Dimension(1280, 720));
        setMinimumSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(menuBar);

        this.funcionario = funcionario;

        menuBar.add(menuTabela);
        menuTabela.add(atualizar);

        painelTop.setPreferredSize(new Dimension(0, 80));
        painelTop.setBorder(new EmptyBorder(10, 10, 10, 10));

        addComponentePainelTop(botaoCadastrar, 0, 0, 10);
        addComponentePainelTop(botaoEditar, 1, 10, 10);
        addComponentePainelTop(botaoDeletar, 2, 10, 10);
        addComponentePainelTop(botaoVoltar, 3, 10, 0);

        painelCentro.setBorder(new EmptyBorder(10, 10, 10, 10));

        labelCategoria.setHorizontalAlignment(JLabel.CENTER);
        labelCategoria.setBorder(new EmptyBorder(10, 10, 10, 10));

        painelCentro.add(labelCategoria, BorderLayout.PAGE_START);
        painelCentro.add(scrollTabela, BorderLayout.CENTER);

        this.add(painelTop, BorderLayout.PAGE_START);
        this.add(painelCentro, BorderLayout.CENTER);

        botaoCadastrar.addActionListener(this);
        botaoEditar.addActionListener(this);
        botaoDeletar.addActionListener(this);
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
            listaCategorias = CategoriaUC.buscarTodos();

            String[] titulos = {"ID", "Nome"};
            Object[][] dados = new Object[listaCategorias.size()][titulos.length];

            for (int i = 0; i < listaCategorias.size(); i++) {
                dados[i][0] = listaCategorias.get(i).getId();
                dados[i][1] = listaCategorias.get(i).getNome();
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
            new TelaCadastrarCategoria(new Categoria()).setVisible(true);

            atualizarTabela();
        }

        if (evento.getSource().equals(botaoEditar)) {
            if (tabela.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Nenhuma Categoria Selecionada!", "Erro de Seleção", JOptionPane.ERROR_MESSAGE);
            } else {
                new TelaCadastrarCategoria(listaCategorias.get(tabela.getSelectedRow())).setVisible(true);
            }

            atualizarTabela();
        }

        if (evento.getSource().equals(botaoDeletar)) {
            if (tabela.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Nenhuma Categoria Selecionada!", "Erro de Seleção", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    CategoriaUC.removerCategoria(listaCategorias.get(tabela.getSelectedRow()));
                } catch (Exception excecao) {
                    JOptionPane.showMessageDialog(null, "Falha o remover a categoria do banco de dados", "Erro de remoção", JOptionPane.ERROR_MESSAGE);
                }
            }

            atualizarTabela();
        }

        if (evento.getSource().equals(botaoVoltar)) {
            if (funcionario.getCargo().getFuncoesAdministrador()) {
                new TelaPrincipalAdministrador(funcionario).setVisible(true);
            } else {
                new TelaPrincipal(funcionario).setVisible(true);
            }

            dispose();
        }
    }

}

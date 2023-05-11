package telas;

import casoUso.EnderecoUC;
import casoUso.FornecedorUC;
import entidades.Fornecedor;
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
public class TelaListarFornecedor extends JFrame implements ActionListener {

    private Icon iconAdd = new ImageIcon(getClass().getResource("/icons/business.png"));
    private Icon iconEdit = new ImageIcon(getClass().getResource("/icons/edit.png"));
    private Icon iconDelete = new ImageIcon(getClass().getResource("/icons/delete.png"));
    private Icon iconMore = new ImageIcon(getClass().getResource("/icons/more.png"));
    private Icon iconReturn = new ImageIcon(getClass().getResource("/icons/return.png"));

    private JPanel painelTop = new JPanel(new GridBagLayout());
    private JPanel painelCentro = new JPanel(new BorderLayout());

    private JButton botaoCadastrar = new JButton("Novo Fornecedor", iconAdd);
    private JButton botaoEditar = new JButton("Editar", iconEdit);
    private JButton botaoDeletar = new JButton("Deletar", iconDelete);
    private JButton botaoEnderecos = new JButton("Endereços", iconMore);
    private JButton botaoVoltar = new JButton("Voltar", iconReturn);

    private JLabel labelFornecedor = new JLabel("Fornecedores:");

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuTabela = new JMenu("Tabela");
    private JMenuItem atualizar = new JMenuItem("Atualizar");

    private JTable tabela = new JTable();
    private JScrollPane scrollTabela = new JScrollPane(tabela);

    private List<Fornecedor> listaFornecedores;
    private Funcionario funcionario;

    public TelaListarFornecedor(Funcionario funcionario) {
        setTitle("Listagem de Fornecedores");
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
        addComponentePainelTop(botaoEnderecos, 3, 10, 10);
        addComponentePainelTop(botaoVoltar, 4, 10, 0);

        painelCentro.setBorder(new EmptyBorder(10, 10, 10, 10));

        labelFornecedor.setHorizontalAlignment(JLabel.CENTER);
        labelFornecedor.setBorder(new EmptyBorder(10, 10, 10, 10));

        painelCentro.add(labelFornecedor, BorderLayout.PAGE_START);
        painelCentro.add(scrollTabela, BorderLayout.CENTER);

        this.add(painelTop, BorderLayout.PAGE_START);
        this.add(painelCentro, BorderLayout.CENTER);

        botaoCadastrar.addActionListener(this);
        botaoEditar.addActionListener(this);
        botaoDeletar.addActionListener(this);
        botaoEnderecos.addActionListener(this);
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
            listaFornecedores = FornecedorUC.buscarTodos();

            String[] titulos = {"ID", "Razão Social", "Nome Fantasia", "CNPJ", "Inscrição Estadual", "Telefone", "Email", "Endereço"};
            Object[][] dados = new Object[listaFornecedores.size()][titulos.length];

            for (int i = 0; i < listaFornecedores.size(); i++) {
                dados[i][0] = listaFornecedores.get(i).getId();
                dados[i][1] = listaFornecedores.get(i).getRazaoSocial();
                dados[i][2] = listaFornecedores.get(i).getNomeFantasia();
                dados[i][3] = listaFornecedores.get(i).getCnpj();
                dados[i][4] = listaFornecedores.get(i).getInscricaoEstadual();
                dados[i][5] = listaFornecedores.get(i).getTelefone();
                dados[i][6] = listaFornecedores.get(i).getEmail();
                dados[i][7] = listaFornecedores.get(i).getEndereco();
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
            new TelaCadastrarFornecedor(new Fornecedor()).setVisible(true);

            atualizarTabela();
        }

        if (evento.getSource().equals(botaoEditar)) {
            if (tabela.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Nenhum Fornecedor Selecionado!", "Erro de Seleção", JOptionPane.ERROR_MESSAGE);
            } else {
                new TelaCadastrarFornecedor(listaFornecedores.get(tabela.getSelectedRow())).setVisible(true);
            }

            atualizarTabela();
        }

        if (evento.getSource().equals(botaoDeletar)) {
            if (tabela.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Nenhum Fornecedor Selecionado!", "Erro de Seleção", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    FornecedorUC.removerFornecedor(listaFornecedores.get(tabela.getSelectedRow()));

                    if (listaFornecedores.get(tabela.getSelectedRow()).getEndereco().getCliente() == null) {
                        EnderecoUC.removerEndereco(listaFornecedores.get(tabela.getSelectedRow()).getEndereco());
                    }
                } catch (Exception excecao) {
                    JOptionPane.showMessageDialog(null, "Falha o remover o fornecedor do banco de dados", "Erro de remoção", JOptionPane.ERROR_MESSAGE);
                }
            }

            atualizarTabela();
        }

        if (evento.getSource().equals(botaoEnderecos)) {
            new TelaListarEndereco(funcionario, false).setVisible(true);

            dispose();
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

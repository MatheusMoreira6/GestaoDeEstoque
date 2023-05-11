package telas;

import casoUso.EstoqueSaidaUC;
import casoUso.ItemSaidaUC;
import entidades.EstoqueSaida;
import entidades.Funcionario;
import entidades.ItemSaida;

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
public class TelaListarEstoqueSaida extends JFrame implements ActionListener {

    private Icon iconAdd = new ImageIcon(getClass().getResource("/icons/add.png"));
    private Icon iconEdit = new ImageIcon(getClass().getResource("/icons/edit.png"));
    private Icon iconDelete = new ImageIcon(getClass().getResource("/icons/delete.png"));
    private Icon iconMore = new ImageIcon(getClass().getResource("/icons/more.png"));
    private Icon iconReturn = new ImageIcon(getClass().getResource("/icons/return.png"));

    private JPanel painelTop = new JPanel(new GridBagLayout());
    private JPanel painelCentro = new JPanel(new BorderLayout());

    private JButton botaoCadastrar = new JButton("Nova Saida", iconAdd);
    private JButton botaoEditar = new JButton("Editar", iconEdit);
    private JButton botaoDeletar = new JButton("Deletar", iconDelete);
    private JButton botaoItem = new JButton("Ver Itens", iconMore);
    private JButton botaoVoltar = new JButton("Voltar", iconReturn);

    private JLabel labelSaida = new JLabel("Saida de Produtos:");

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuTabela = new JMenu("Tabela");
    private JMenuItem atualizar = new JMenuItem("Atualizar");

    private JTable tabela = new JTable();
    private JScrollPane scrollTabela = new JScrollPane(tabela);

    private Funcionario funcionario;
    private List<EstoqueSaida> estoqueSaida;

    public TelaListarEstoqueSaida(Funcionario funcionario) {
        setTitle("Listagem de Saídas");
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
        addComponentePainelTop(botaoItem, 3, 10, 10);
        addComponentePainelTop(botaoVoltar, 4, 10, 0);

        painelCentro.setBorder(new EmptyBorder(10, 10, 10, 10));

        labelSaida.setHorizontalAlignment(JLabel.CENTER);
        labelSaida.setBorder(new EmptyBorder(10, 10, 10, 10));

        painelCentro.add(labelSaida, BorderLayout.PAGE_START);
        painelCentro.add(scrollTabela, BorderLayout.CENTER);

        this.add(painelTop, BorderLayout.PAGE_START);
        this.add(painelCentro, BorderLayout.CENTER);

        botaoCadastrar.addActionListener(this);
        botaoEditar.addActionListener(this);
        botaoDeletar.addActionListener(this);
        botaoItem.addActionListener(this);
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

    private List<ItemSaida> listaItensSaida(Integer idEstoqueSaida) {
        List<ItemSaida> itensSaida = new ArrayList<>();

        try {
            List<ItemSaida> auxItensSaida = ItemSaidaUC.buscarTodos();

            for (int i = 0; i < auxItensSaida.size(); i++) {
                if (auxItensSaida.get(i).getEstoqueSaida().getId().equals(idEstoqueSaida)) {
                    itensSaida.add(auxItensSaida.get(i));
                }
            }
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar a lista de itens da saida", "Erro de Retorno", JOptionPane.ERROR_MESSAGE);
        }

        return (itensSaida);
    }

    private void atualizarTabela() {
        try {
            estoqueSaida = EstoqueSaidaUC.buscarTodos();

            String[] titulos = {"ID", "Quantidade de Itens", "Justificativa", "Data", "Cliente", "Funcionario"};
            Object[][] dados = new Object[estoqueSaida.size()][titulos.length];

            for (int i = 0; i < estoqueSaida.size(); i++) {
                dados[i][0] = estoqueSaida.get(i).getId();
                dados[i][1] = listaItensSaida(estoqueSaida.get(i).getId()).size();
                dados[i][2] = estoqueSaida.get(i).getJustificativa();
                dados[i][3] = estoqueSaida.get(i).getDataSaida();
                dados[i][4] = estoqueSaida.get(i).getCliente().getNome();
                dados[i][5] = estoqueSaida.get(i).getFuncionario().getNome();
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
            new TelaCadastrarEstoqueSaida(funcionario, new EstoqueSaida()).setVisible(true);

            atualizarTabela();
        }

        if (evento.getSource().equals(botaoEditar)) {
            if (tabela.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Nenhuma Saida Selecionada!", "Erro de Seleção", JOptionPane.ERROR_MESSAGE);
            } else {
                new TelaCadastrarEstoqueSaida(funcionario, estoqueSaida.get(tabela.getSelectedRow())).setVisible(true);
            }

            atualizarTabela();
        }

        if (evento.getSource().equals(botaoDeletar)) {
            if (tabela.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Nenhuma Saida Selecionada!", "Erro de Seleção", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    List<ItemSaida> itensSaida = listaItensSaida(estoqueSaida.get(tabela.getSelectedRow()).getId());

                    for (int i = 0; i < itensSaida.size(); i++) {
                        ItemSaidaUC.removerItemSaida(itensSaida.get(i));
                    }

                    EstoqueSaidaUC.removerEstoqueSaida(estoqueSaida.get(tabela.getSelectedRow()));

                } catch (Exception excecao) {
                    JOptionPane.showMessageDialog(null, "Falha o remover a entrada de produtos do banco de dados", "Erro de remoção", JOptionPane.ERROR_MESSAGE);
                }
            }

            atualizarTabela();
        }

        if (evento.getSource().equals(botaoItem)) {
            if (tabela.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Nenhuma Saida Selecionada!", "Erro de Seleção", JOptionPane.ERROR_MESSAGE);
            } else {
                if (listaItensSaida(estoqueSaida.get(tabela.getSelectedRow()).getId()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum item saida cadastrado!", "Erro de Preenchimento", JOptionPane.ERROR_MESSAGE);
                } else {
                    new TelaListarItensSaida(listaItensSaida(estoqueSaida.get(tabela.getSelectedRow()).getId())).setVisible(true);
                }
            }
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

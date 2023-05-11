package telas;

import casoUso.ClienteUC;
import casoUso.EstoqueSaidaUC;
import casoUso.ItemSaidaUC;
import com.toedter.calendar.JDateChooser;
import construtores.ConstrutorEstoqueSaida;
import entidades.Cliente;
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
import java.util.Date;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import persistencia.BancoEstoqueSaida;

/**
 * @author Matheus
 * @author Gabriel
 */
public class TelaCadastrarEstoqueSaida extends JDialog implements ActionListener {

    private Icon iconAdicao = new ImageIcon(getClass().getResource("/icons/new.png"));
    private Icon iconDelete = new ImageIcon(getClass().getResource("/icons/delete.png"));

    private JPanel painelInformacoes = new JPanel(new GridBagLayout());
    private JPanel painelItemSaida = new JPanel(new BorderLayout());
    private JPanel painelBotoes = new JPanel(new GridBagLayout());

    private JLabel labelItemSaida = new JLabel("Item Saida:");
    private List<ItemSaida> itensSaida = new ArrayList<>();
    private JTable tabelaItemSaida = new JTable();
    private JScrollPane scrolTabela = new JScrollPane(tabelaItemSaida);

    private JPanel painelBotoesItemSaida = new JPanel(new BorderLayout());
    private JButton botaoAdicionarItemSaida = new JButton("Adicionar Item", iconAdicao);
    private JButton botaoRemoverItemSaida = new JButton("Remover Item", iconDelete);

    private JLabel labelData = new JLabel("Data:");
    private JDateChooser dataSaida = new JDateChooser(new Date(), "dd/MM/yyyy");

    private JLabel labelJustificativa = new JLabel("Justificativa:");
    private JTextField textJustificativa = new JTextField();

    private JLabel labelCliente = new JLabel("Cliente:");
    private JComboBox<Cliente> comboCliente = new JComboBox<>();
    private JButton botaoAdicionarCliente = new JButton("Adicionar Cliente", iconAdicao);

    private JLabel labelFuncionario = new JLabel("Funcionario:");
    private Funcionario funcionario = new Funcionario();
    private JLabel labelNomeFuncionario;

    private EstoqueSaida estoqueSaida;

    private JButton botaoSalvar = new JButton("Salvar");
    private JButton botaoCancelar = new JButton("Cancelar");

    public TelaCadastrarEstoqueSaida(Funcionario funcionario, EstoqueSaida estoqueSaida) {
        this.funcionario = funcionario;
        this.estoqueSaida = estoqueSaida;
        labelNomeFuncionario = new JLabel(String.format("%s", funcionario.getNome()));

        if (estoqueSaida.getId() == null) {
            atualizarComboCliente();

            try {
                this.estoqueSaida = (EstoqueSaida) BancoEstoqueSaida.salvar(estoqueSaida);
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao criar o estoque no banco", "Erro de Gravação", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            dataSaida.setDate(estoqueSaida.getDataSaida());
            textJustificativa.setText(estoqueSaida.getJustificativa());
            comboCliente.removeAllItems();
            comboCliente.addItem(estoqueSaida.getCliente());
        }

        configuracoesTela();
        atualizarTabela();
    }

    private void configuracoesTela() {
        setModal(true);
        setResizable(false);
        setTitle("Cadastrar Saida de Produtos");
        setSize(new Dimension(848, 820));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        painelInformacoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setPreferredSize(new Dimension(0, 60));

        labelItemSaida.setHorizontalAlignment(JLabel.CENTER);
        painelItemSaida.add(labelItemSaida, BorderLayout.PAGE_START);
        painelItemSaida.add(scrolTabela, BorderLayout.CENTER);
        botaoAdicionarItemSaida.setPreferredSize(new Dimension(0, 50));
        painelItemSaida.add(botaoAdicionarItemSaida, BorderLayout.PAGE_END);

        botaoAdicionarItemSaida.setPreferredSize(new Dimension(300, 50));
        botaoRemoverItemSaida.setPreferredSize(new Dimension(300, 50));
        painelBotoesItemSaida.add(botaoAdicionarItemSaida, BorderLayout.LINE_START);
        painelBotoesItemSaida.add(botaoRemoverItemSaida, BorderLayout.LINE_END);
        painelItemSaida.add(painelBotoesItemSaida, BorderLayout.PAGE_END);

        addComponentePainelInformacoes(labelData, 0, 0, 1, 1, 0, 0, 5, 75, 30, 0);
        addComponentePainelInformacoes(dataSaida, 1, 0, 1, 1, 0, 0, 0, 95, 30, 0);

        addComponentePainelInformacoes(labelCliente, 0, 1, 1, 1, 15, 0, 5, 75, 30, 0);
        addComponentePainelInformacoes(comboCliente, 1, 1, 1, 1, 15, 0, 20, 0, 30, 1);
        addComponentePainelInformacoes(botaoAdicionarCliente, 2, 1, 1, 1, 15, 0, 0, 250, 40, 0);

        addComponentePainelInformacoes(labelJustificativa, 0, 3, 1, 1, 15, 0, 5, 75, 30, 0);
        addComponentePainelInformacoes(textJustificativa, 1, 3, 2, 1, 15, 0, 0, 0, 30, 1);

        addComponentePainelInformacoes(painelItemSaida, 0, 4, 3, 1, 15, 0, 0, 300, 480, 1);

        addComponentePainelInformacoes(labelFuncionario, 0, 5, 1, 1, 30, 0, 5, 75, 30, 0);
        addComponentePainelInformacoes(labelNomeFuncionario, 1, 5, 1, 1, 30, 0, 0, 480, 30, 0);

        addComponentePainelBotao(botaoSalvar, 0, 0, 0, 5);
        addComponentePainelBotao(botaoCancelar, 1, 0, 5, 0);

        this.add(painelInformacoes, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.PAGE_END);

        botaoSalvar.addActionListener(this);
        botaoCancelar.addActionListener(this);
        botaoAdicionarCliente.addActionListener(this);
        botaoAdicionarItemSaida.addActionListener(this);
        botaoRemoverItemSaida.addActionListener(this);
    }

    private void addComponentePainelInformacoes(
            Component componente,
            Integer posicaoX,
            Integer posicaoY,
            Integer quantidadeCelulasX,
            Integer quantidadeCelulasY,
            Integer marginTop,
            Integer marginEsquerda,
            Integer marginDireita,
            Integer tamanhoX,
            Integer tamanhoY,
            Integer configuracaoResponsiva) {

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(marginTop, marginEsquerda, 0, marginDireita);

        if (configuracaoResponsiva == 1) {
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
        }

        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridwidth = quantidadeCelulasX;
        gbc.gridheight = quantidadeCelulasY;

        gbc.gridy = posicaoY;
        gbc.gridx = posicaoX;

        componente.setPreferredSize(new Dimension(tamanhoX, tamanhoY));

        painelInformacoes.add(componente, gbc);
    }

    private void addComponentePainelBotao(Component componente, Integer posicaoX, Integer posicaoY, Integer marginEsquerda, Integer marginDireita) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets = new Insets(0, marginEsquerda, 0, marginDireita);

        gbc.weighty = 1.0;
        gbc.weightx = 1.0;

        gbc.gridy = posicaoY;
        gbc.gridx = posicaoX;

        painelBotoes.add(componente, gbc);
    }

    private void atualizarComboCliente() {
        comboCliente.removeAllItems();

        try {
            List<Cliente> fornecedores = ClienteUC.buscarTodos();

            for (int i = 0; i < fornecedores.size(); i++) {
                comboCliente.addItem(fornecedores.get(i));
            }
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar a lista de clientes!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabela() {
        try {
            List<ItemSaida> auxItemSaidas = ItemSaidaUC.buscarTodos();

            itensSaida.removeAll(itensSaida);
            for (int i = 0; i < auxItemSaidas.size(); i++) {
                if (auxItemSaidas.get(i).getEstoqueSaida().getId().equals(estoqueSaida.getId())) {
                    itensSaida.add(auxItemSaidas.get(i));
                }
            }

            String[] titulos = {"Produto", "Quantidade"};
            Object[][] dados = new Object[itensSaida.size()][titulos.length];

            for (int i = 0; i < itensSaida.size(); i++) {
                dados[i][0] = itensSaida.get(i).getProduto();
                dados[i][1] = itensSaida.get(i).getQuantidade();
            }

            tabelaItemSaida.setModel(new DefaultTableModel(dados, titulos));
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar a tabela!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(botaoAdicionarCliente)) {
            new TelaCadastrarCliente(new Cliente()).setVisible(true);

            atualizarComboCliente();
        }

        if (evento.getSource().equals(botaoAdicionarItemSaida)) {
            new TelaCadastrarItemSaida(estoqueSaida).setVisible(true);

            atualizarTabela();
        }

        if (evento.getSource().equals(botaoRemoverItemSaida)) {
            if (tabelaItemSaida.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Nenhuma Saida Selecionada!", "Erro de Seleção", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    ItemSaidaUC.removerItemSaida(itensSaida.get(tabelaItemSaida.getSelectedRow()));
                } catch (Exception excecao) {
                    JOptionPane.showMessageDialog(null, "Falha o remover a entrada de produtos do banco de dados", "Erro de remoção", JOptionPane.ERROR_MESSAGE);
                }
            }

            atualizarTabela();
        }

        if (evento.getSource().equals(botaoCancelar)) {
            atualizarTabela();

            if (estoqueSaida.getCliente() == null || estoqueSaida.getFuncionario() == null) {
                try {
                    for (int i = 0; i < itensSaida.size(); i++) {
                        ItemSaidaUC.removerItemSaida(itensSaida.get(i));
                    }

                    EstoqueSaidaUC.removerEstoqueSaida(estoqueSaida);
                } catch (Exception excecao) {
                    JOptionPane.showMessageDialog(null, "Falha ao remover a saida do banco de dados", "Erro de Remoção", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if (itensSaida.isEmpty()) {
                    Integer opcao = JOptionPane.showConfirmDialog(null, "A lista de itens está vazia!\n\nDeseja deletar a saida correspondente?", "Alerta: Lista Vazia", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                    if (opcao == 0) {
                        try {
                            EstoqueSaidaUC.removerEstoqueSaida(estoqueSaida);
                        } catch (Exception excecao) {
                            JOptionPane.showMessageDialog(null, "Falha ao remover a saida do banco de dados", "Erro de Remoção", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }

            dispose();
        }

        if (evento.getSource().equals(botaoSalvar)) {
            atualizarTabela();

            if (itensSaida.isEmpty()) {
                JOptionPane.showMessageDialog(null, "A lista de itens está vazia!", "Erro de Preenchimento", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    ConstrutorEstoqueSaida construtorEstoqueSaida;

                    construtorEstoqueSaida = new ConstrutorEstoqueSaida(
                            textJustificativa.getText(),
                            dataSaida.getDate(),
                            (Cliente) comboCliente.getSelectedItem(),
                            funcionario,
                            estoqueSaida.getId()
                    );

                    EstoqueSaidaUC.salvarEstoqueSaida(construtorEstoqueSaida);

                    dispose();
                } catch (Exception excecao) {
                    JOptionPane.showMessageDialog(null, "Verifique se todos os campos estão preechidos corretamente.", "Erro ao salvar a entrada no estoque", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}

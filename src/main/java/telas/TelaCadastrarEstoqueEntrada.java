package telas;

import casoUso.EstoqueEntradaUC;
import casoUso.FornecedorUC;
import casoUso.ItemEntradaUC;
import com.toedter.calendar.JDateChooser;
import construtores.ConstrutorEstoqueEntrada;
import entidades.EstoqueEntrada;
import entidades.Fornecedor;
import entidades.Funcionario;
import entidades.ItemEntrada;

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
import persistencia.BancoEstoqueEntrada;

/**
 * @author Matheus
 * @author Gabriel
 */
public class TelaCadastrarEstoqueEntrada extends JDialog implements ActionListener {

    private Icon iconAdicao = new ImageIcon(getClass().getResource("/icons/new.png"));
    private Icon iconDelete = new ImageIcon(getClass().getResource("/icons/delete.png"));

    private JPanel painelInformacoes = new JPanel(new GridBagLayout());
    private JPanel painelItemEntrada = new JPanel(new BorderLayout());
    private JPanel painelBotoes = new JPanel(new GridBagLayout());

    private JLabel labelItemEntrada = new JLabel("Item Entrada:");
    private List<ItemEntrada> itensEntrada = new ArrayList<>();
    private JTable tabelaItemEntrada = new JTable();
    private JScrollPane scrolTabela = new JScrollPane(tabelaItemEntrada);

    private JPanel painelBotoesItemEntrada = new JPanel(new BorderLayout());
    private JButton botaoAdicionarItemEntrada = new JButton("Adicionar Item", iconAdicao);
    private JButton botaoRemoverItemEntrada = new JButton("Remover Item", iconDelete);

    private JLabel labelData = new JLabel("Data:");
    private JDateChooser dataEntrada = new JDateChooser(new Date(), "dd/MM/yyyy");

    private JLabel labelNotaFiscal = new JLabel("Nota Fiscal:");
    private JTextField textNotaFiscal = new JTextField();

    private JLabel labelJustificativa = new JLabel("Justificativa:");
    private JTextField textJustificativa = new JTextField();

    private JLabel labelFornecedor = new JLabel("Fornecedor:");
    private JComboBox<Fornecedor> comboFornecedor = new JComboBox<>();
    private JButton botaoAdicionarFornecedor = new JButton("Adicionar Fornecedor", iconAdicao);

    private JLabel labelFuncionario = new JLabel("Funcionario:");
    private Funcionario funcionario;
    private JLabel labelNomeFuncionario;

    private EstoqueEntrada estoqueEntrada;

    private JButton botaoSalvar = new JButton("Salvar");
    private JButton botaoCancelar = new JButton("Cancelar");

    public TelaCadastrarEstoqueEntrada(Funcionario funcionario, EstoqueEntrada estoqueEntrada) {
        this.funcionario = funcionario;
        this.estoqueEntrada = estoqueEntrada;
        labelNomeFuncionario = new JLabel(String.format("%s", funcionario.getNome()));

        if (estoqueEntrada.getId() == null) {
            atualizarComboFornecedor();

            try {
                this.estoqueEntrada = (EstoqueEntrada) BancoEstoqueEntrada.salvar(estoqueEntrada);
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao criar o estoque no banco", "Erro de Gravação", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            dataEntrada.setDate(estoqueEntrada.getDataEntrada());
            textNotaFiscal.setText(estoqueEntrada.getNotaFiscal());
            textJustificativa.setText(estoqueEntrada.getJustificativa());
            comboFornecedor.removeAllItems();
            comboFornecedor.addItem(estoqueEntrada.getFornecedor());
        }

        configuracoesTela();
        atualizarTabela();
    }

    private void configuracoesTela() {
        setModal(true);
        setResizable(false);
        setTitle("Cadastrar Entrada de Produtos");
        setSize(new Dimension(848, 850));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        painelInformacoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setPreferredSize(new Dimension(0, 60));

        labelItemEntrada.setHorizontalAlignment(JLabel.CENTER);
        painelItemEntrada.add(labelItemEntrada, BorderLayout.PAGE_START);
        painelItemEntrada.add(scrolTabela, BorderLayout.CENTER);

        botaoAdicionarItemEntrada.setPreferredSize(new Dimension(300, 50));
        botaoRemoverItemEntrada.setPreferredSize(new Dimension(300, 50));
        painelBotoesItemEntrada.add(botaoAdicionarItemEntrada, BorderLayout.LINE_START);
        painelBotoesItemEntrada.add(botaoRemoverItemEntrada, BorderLayout.LINE_END);
        painelItemEntrada.add(painelBotoesItemEntrada, BorderLayout.PAGE_END);

        addComponentePainelInformacoes(labelData, 0, 0, 1, 1, 0, 0, 5, 75, 30, 0);
        addComponentePainelInformacoes(dataEntrada, 1, 0, 1, 1, 0, 0, 0, 95, 30, 0);

        addComponentePainelInformacoes(labelFornecedor, 0, 1, 1, 1, 15, 0, 5, 75, 30, 0);
        addComponentePainelInformacoes(comboFornecedor, 1, 1, 1, 1, 15, 0, 20, 0, 30, 1);
        addComponentePainelInformacoes(botaoAdicionarFornecedor, 2, 1, 1, 1, 15, 0, 0, 250, 40, 0);

        addComponentePainelInformacoes(labelNotaFiscal, 0, 2, 1, 1, 15, 0, 5, 75, 30, 0);
        addComponentePainelInformacoes(textNotaFiscal, 1, 2, 2, 1, 15, 0, 0, 0, 30, 1);

        addComponentePainelInformacoes(labelJustificativa, 0, 3, 1, 1, 15, 0, 5, 75, 30, 0);
        addComponentePainelInformacoes(textJustificativa, 1, 3, 2, 1, 15, 0, 0, 0, 30, 1);

        addComponentePainelInformacoes(painelItemEntrada, 0, 4, 3, 1, 15, 0, 0, 300, 480, 1);

        addComponentePainelInformacoes(labelFuncionario, 0, 5, 1, 1, 25, 0, 5, 75, 30, 0);
        addComponentePainelInformacoes(labelNomeFuncionario, 1, 5, 1, 1, 25, 0, 0, 480, 30, 0);

        addComponentePainelBotao(botaoSalvar, 0, 0, 0, 5);
        addComponentePainelBotao(botaoCancelar, 1, 0, 5, 0);

        this.add(painelInformacoes, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.PAGE_END);

        botaoSalvar.addActionListener(this);
        botaoCancelar.addActionListener(this);
        botaoAdicionarFornecedor.addActionListener(this);
        botaoAdicionarItemEntrada.addActionListener(this);
        botaoRemoverItemEntrada.addActionListener(this);
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

    private void atualizarComboFornecedor() {
        comboFornecedor.removeAllItems();

        try {
            List<Fornecedor> fornecedores = FornecedorUC.buscarTodos();

            for (int i = 0; i < fornecedores.size(); i++) {
                comboFornecedor.addItem(fornecedores.get(i));
            }
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar a lista de fornecedores!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabela() {
        try {
            List<ItemEntrada> auxItemEntrada = ItemEntradaUC.buscarTodos();

            itensEntrada.removeAll(itensEntrada);
            for (int i = 0; i < auxItemEntrada.size(); i++) {
                if (auxItemEntrada.get(i).getEstoqueEntrada().getId().equals(estoqueEntrada.getId())) {
                    itensEntrada.add(auxItemEntrada.get(i));
                }
            }

            String[] titulos = {"Produto", "Quantidade"};
            Object[][] dados = new Object[itensEntrada.size()][titulos.length];

            for (int i = 0; i < itensEntrada.size(); i++) {
                dados[i][0] = itensEntrada.get(i).getProduto();
                dados[i][1] = itensEntrada.get(i).getQuantidade();
            }

            tabelaItemEntrada.setModel(new DefaultTableModel(dados, titulos));
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar a tabela!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(botaoAdicionarFornecedor)) {
            new TelaCadastrarFornecedor(new Fornecedor()).setVisible(true);

            atualizarComboFornecedor();
        }

        if (evento.getSource().equals(botaoAdicionarItemEntrada)) {
            new TelaCadastrarItemEntrada(estoqueEntrada).setVisible(true);

            atualizarTabela();
        }

        if (evento.getSource().equals(botaoRemoverItemEntrada)) {
            if (tabelaItemEntrada.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Nenhuma Entrada Selecionada!", "Erro de Seleção", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    ItemEntradaUC.removerItemEntrada(itensEntrada.get(tabelaItemEntrada.getSelectedRow()));
                } catch (Exception excecao) {
                    JOptionPane.showMessageDialog(null, "Falha o remover a entrada de produtos do banco de dados", "Erro de remoção", JOptionPane.ERROR_MESSAGE);
                }
            }

            atualizarTabela();
        }

        if (evento.getSource().equals(botaoCancelar)) {
            atualizarTabela();

            if (estoqueEntrada.getFornecedor() == null || estoqueEntrada.getFuncionario() == null) {
                try {
                    for (int i = 0; i < itensEntrada.size(); i++) {
                        ItemEntradaUC.removerItemEntrada(itensEntrada.get(i));
                    }

                    EstoqueEntradaUC.removerEstoqueEntrada(estoqueEntrada);
                } catch (Exception excecao) {
                    JOptionPane.showMessageDialog(null, "Falha ao remover a entrada do banco de dados", "Erro de Remoção", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if (itensEntrada.isEmpty()) {
                    Integer opcao = JOptionPane.showConfirmDialog(null, "A lista de itens está vazia!\n\nDeseja deletar a entrada correspondente?", "Alerta: Lista Vazia", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                    if (opcao == 0) {
                        try {
                            EstoqueEntradaUC.removerEstoqueEntrada(estoqueEntrada);
                        } catch (Exception excecao) {
                            JOptionPane.showMessageDialog(null, "Falha ao remover a entrada do banco de dados", "Erro de Remoção", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }

            dispose();
        }

        if (evento.getSource().equals(botaoSalvar)) {
            atualizarTabela();

            if (itensEntrada.isEmpty()) {
                JOptionPane.showMessageDialog(null, "A lista de itens está vazia!", "Erro de Preenchimento", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    ConstrutorEstoqueEntrada construtorEstoqueEntrada;

                    construtorEstoqueEntrada = new ConstrutorEstoqueEntrada(
                            textJustificativa.getText(),
                            dataEntrada.getDate(),
                            textNotaFiscal.getText(),
                            (Fornecedor) comboFornecedor.getSelectedItem(),
                            funcionario,
                            estoqueEntrada.getId()
                    );

                    EstoqueEntradaUC.salvarEstoqueEntrada(construtorEstoqueEntrada);

                    dispose();
                } catch (Exception excecao) {
                    JOptionPane.showMessageDialog(null, "Verifique se todos os campos estão preechidos corretamente.", "Erro ao salvar a entrada no estoque", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}

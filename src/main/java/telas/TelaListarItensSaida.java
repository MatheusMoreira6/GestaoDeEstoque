package telas;

import entidades.ItemSaida;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * @author Matheus
 * @author Gabriel
 */
public class TelaListarItensSaida extends JDialog implements ActionListener {

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuTabela = new JMenu("Tabela");
    private JMenuItem atualizar = new JMenuItem("Atualizar");

    private JPanel painelInformacoes = new JPanel(new BorderLayout());

    private List<ItemSaida> listaItensSaida;

    private JLabel labelItemSaida = new JLabel("Itens Saida:");

    private JTable tabelaItemSaida = new JTable();
    private JScrollPane scrollTabela = new JScrollPane(tabelaItemSaida);

    private JButton botaoOK = new JButton("OK");

    public TelaListarItensSaida(List<ItemSaida> listaItensSaida) {
        setModal(true);
        setResizable(false);
        setTitle("Lista de Itens Saida");
        setSize(new Dimension(450, 700));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setJMenuBar(menuBar);

        this.listaItensSaida = listaItensSaida;

        menuBar.add(menuTabela);
        menuTabela.add(atualizar);

        painelInformacoes.setBorder(new EmptyBorder(10, 10, 10, 10));

        labelItemSaida.setHorizontalAlignment(JLabel.CENTER);
        painelInformacoes.add(labelItemSaida, BorderLayout.PAGE_START);
        painelInformacoes.add(scrollTabela, BorderLayout.CENTER);

        botaoOK.setPreferredSize(new Dimension(0, 40));

        add(painelInformacoes, BorderLayout.CENTER);
        add(botaoOK, BorderLayout.PAGE_END);

        atualizar.addActionListener(this);
        botaoOK.addActionListener(this);

        atualizarTabela();
    }

    private void atualizarTabela() {
        String[] titulos = {"ID", "Produto", "Quantidade"};
        Object[][] dados = new Object[listaItensSaida.size()][titulos.length];

        for (int i = 0; i < listaItensSaida.size(); i++) {
            dados[i][0] = listaItensSaida.get(i).getId();
            dados[i][1] = listaItensSaida.get(i).getProduto().getNome();
            dados[i][2] = listaItensSaida.get(i).getQuantidade();
        }

        tabelaItemSaida.setModel(new DefaultTableModel(dados, titulos));
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(atualizar)) {
            atualizarTabela();
        }

        if (evento.getSource().equals(botaoOK)) {
            dispose();
        }
    }
}

package telas;

import casoUso.CidadeUC;
import casoUso.EnderecoUC;
import construtores.ConstrutorEndereco;
import entidades.Cidade;
import entidades.Endereco;

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
import persistencia.BancoEndereco;

/**
 * @author Matheus
 * @author Gabriel
 */
public class TelaCadastrarEndereco extends JDialog implements ActionListener {

    private Icon iconAdicao = new ImageIcon(getClass().getResource("/icons/new.png"));

    private JPanel painelInformacoes = new JPanel(new GridBagLayout());
    private JPanel painelBotoes = new JPanel(new GridBagLayout());

    private JButton botaoSalvar = new JButton("Salvar");
    private JButton botaoCancelar = new JButton("Cancelar");

    private JLabel labelLogradouro = new JLabel("Logradouro:");
    private JTextField textLogradouro = new JTextField();

    private JLabel labelNumero = new JLabel("Número:");
    private JTextField textNumero = new JTextField();

    private JLabel labelBairro = new JLabel("Bairro:");
    private JTextField textBairro = new JTextField();

    private JLabel labelCidade = new JLabel("Cidade:");
    private JComboBox<Cidade> comboCidade = new JComboBox<>();
    private JButton botaoNovaCidade = new JButton("Adicionar Cidade", iconAdicao);

    private Endereco endereco;

    public TelaCadastrarEndereco(Endereco endereco) {
        this.endereco = endereco;

        if (endereco.getId() == null) {
            atualizarComboCidade();

            try {
                this.endereco = (Endereco) BancoEndereco.salvar(endereco);
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao criar o endereço no banco", "Erro de Gravação", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            textLogradouro.setText(endereco.getLogradouro());
            textNumero.setText(String.format("%s", endereco.getNumero()));
            textBairro.setText(endereco.getBairro());
            comboCidade.removeAllItems();
            comboCidade.addItem(endereco.getCidade());
        }

        configuracoesTela();
    }

    private void configuracoesTela() {
        setModal(true);
        setResizable(false);
        setTitle("Cadastrar Endereço");
        setSize(new Dimension(815, 300));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        painelInformacoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setPreferredSize(new Dimension(0, 60));

        addComponentePainelInformacoes(labelLogradouro, 0, 0, 70, 30, 0, 5);
        addComponentePainelInformacoes(textLogradouro, 1, 0, 480, 30, 0, 20);

        addComponentePainelInformacoes(labelNumero, 0, 1, 70, 30, 15, 5);
        addComponentePainelInformacoes(textNumero, 1, 1, 480, 30, 15, 20);

        addComponentePainelInformacoes(labelBairro, 0, 2, 70, 30, 15, 5);
        addComponentePainelInformacoes(textBairro, 1, 2, 480, 30, 15, 20);

        addComponentePainelInformacoes(labelCidade, 0, 3, 70, 30, 15, 5);
        addComponentePainelInformacoes(comboCidade, 1, 3, 480, 30, 15, 20);
        addComponentePainelInformacoes(botaoNovaCidade, 2, 3, 200, 40, 15, 0);

        addComponentePainelBotoes(botaoSalvar, 0, 0, 0, 5);
        addComponentePainelBotoes(botaoCancelar, 1, 0, 5, 0);

        this.add(painelInformacoes, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.PAGE_END);

        botaoSalvar.addActionListener(this);
        botaoCancelar.addActionListener(this);
        botaoNovaCidade.addActionListener(this);
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

    private void atualizarComboCidade() {
        comboCidade.removeAllItems();

        try {
            List<Cidade> cidades = CidadeUC.buscarTodos();

            for (int i = 0; i < cidades.size(); i++) {
                comboCidade.addItem(cidades.get(i));
            }
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar a lista de cidades!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(botaoCancelar)) {
            try {
                if (endereco.getCidade() == null) {
                    EnderecoUC.removerEndereco(endereco);
                }
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao remover o endereço com cidade vazio!", "Erro de Remoção", JOptionPane.ERROR_MESSAGE);
            }

            dispose();
        }

        if (evento.getSource().equals(botaoNovaCidade)) {
            new TelaCadastrarCidade(new Cidade()).setVisible(true);

            atualizarComboCidade();
        }

        if (evento.getSource().equals(botaoSalvar)) {
            try {
                ConstrutorEndereco construtorEndereco;

                construtorEndereco = new ConstrutorEndereco(
                        textLogradouro.getText(),
                        Integer.parseInt(textNumero.getText()),
                        textBairro.getText(),
                        (Cidade) comboCidade.getSelectedItem(),
                        endereco.getId()
                );

                EnderecoUC.salvarEndereco(construtorEndereco);

                dispose();
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Verifique se todos os campos estão preechidos corretamente.", "Erro ao salvar o endereço", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

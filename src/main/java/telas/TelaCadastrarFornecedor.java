package telas;

import casoUso.EnderecoUC;
import casoUso.FornecedorUC;
import construtores.ConstrutorFornecedor;
import entidades.Endereco;
import entidades.Fornecedor;

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
import persistencia.BancoFornecedor;

/**
 * @author Matheus
 * @author Gabriel
 */
public class TelaCadastrarFornecedor extends JDialog implements ActionListener {

    private Icon iconAdicao = new ImageIcon(getClass().getResource("/icons/new.png"));

    private JPanel painelInformacoes = new JPanel(new GridBagLayout());
    private JPanel painelBotoes = new JPanel(new GridBagLayout());

    private JButton botaoSalvar = new JButton("Salvar");
    private JButton botaoCancelar = new JButton("Cancelar");

    private JLabel labelRazaoSocial = new JLabel("Razão Social:");
    private JTextField textRazaoSocial = new JTextField();

    private JLabel labelNomeFantasia = new JLabel("Nome Fantasia:");
    private JTextField textNomeFantasia = new JTextField();

    private JLabel labelCNPJ = new JLabel("CNPJ:");
    private JTextField textCNPJ = new JTextField();

    private JLabel labelInscricaoEstadual = new JLabel("Inscrição Estadual:");
    private JTextField textInscricaoEstadual = new JTextField();

    private JLabel labelTelefone = new JLabel("Telefone:");
    private JTextField textTelefone = new JTextField();

    private JLabel labelEmail = new JLabel("Email:");
    private JTextField textEmail = new JTextField();

    private JLabel labelEndereco = new JLabel("Endereço:");
    private JComboBox<Endereco> comboEndereco = new JComboBox<>();
    private JButton botaoNovoEndereco = new JButton("Adicionar Endereço", iconAdicao);

    private Fornecedor fornecedor;

    public TelaCadastrarFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;

        if (fornecedor.getId() == null) {
            atualizarComboEndereco();

            try {
                this.fornecedor = (Fornecedor) BancoFornecedor.salvar(fornecedor);
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao criar o fornecedor no banco", "Erro de Gravação", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            textRazaoSocial.setText(fornecedor.getRazaoSocial());
            textNomeFantasia.setText(fornecedor.getNomeFantasia());
            textCNPJ.setText(fornecedor.getCnpj());
            textInscricaoEstadual.setText(fornecedor.getInscricaoEstadual());
            textTelefone.setText(fornecedor.getTelefone());
            textEmail.setText(fornecedor.getEmail());
            comboEndereco.removeAllItems();
            comboEndereco.addItem(fornecedor.getEndereco());
        }

        configuracoesTela();
    }

    private void configuracoesTela() {
        setModal(true);
        setResizable(false);
        setTitle("Cadastrar Fornecedor");
        setSize(new Dimension(865, 440));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        painelInformacoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setPreferredSize(new Dimension(0, 60));

        addComponentePainelInformacoes(labelRazaoSocial, 0, 0, 120, 30, 0, 5);
        addComponentePainelInformacoes(textRazaoSocial, 1, 0, 480, 30, 0, 20);

        addComponentePainelInformacoes(labelNomeFantasia, 0, 1, 120, 30, 15, 5);
        addComponentePainelInformacoes(textNomeFantasia, 1, 1, 480, 30, 15, 20);

        addComponentePainelInformacoes(labelCNPJ, 0, 2, 70, 30, 15, 5);
        addComponentePainelInformacoes(textCNPJ, 1, 2, 480, 30, 15, 20);

        addComponentePainelInformacoes(labelInscricaoEstadual, 0, 3, 120, 30, 15, 5);
        addComponentePainelInformacoes(textInscricaoEstadual, 1, 3, 480, 30, 15, 0);

        addComponentePainelInformacoes(labelTelefone, 0, 4, 70, 30, 15, 5);
        addComponentePainelInformacoes(textTelefone, 1, 4, 480, 30, 15, 20);

        addComponentePainelInformacoes(labelEmail, 0, 5, 70, 30, 15, 5);
        addComponentePainelInformacoes(textEmail, 1, 5, 480, 30, 15, 20);

        addComponentePainelInformacoes(labelEndereco, 0, 6, 70, 30, 15, 5);
        addComponentePainelInformacoes(comboEndereco, 1, 6, 480, 30, 15, 20);
        addComponentePainelInformacoes(botaoNovoEndereco, 2, 6, 200, 40, 15, 0);

        addComponentePainelBotoes(botaoSalvar, 0, 0, 0, 5);
        addComponentePainelBotoes(botaoCancelar, 1, 0, 5, 0);

        this.add(painelInformacoes, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.PAGE_END);

        botaoSalvar.addActionListener(this);
        botaoCancelar.addActionListener(this);
        botaoNovoEndereco.addActionListener(this);
    }

    private void addComponentePainelInformacoes(Component componente, Integer posicaoX, Integer posicaoY, Integer tamanhoX, Integer tamanhoY, Integer marginTop, Integer marginDireita) {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.WEST;

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

    private void atualizarComboEndereco() {
        comboEndereco.removeAllItems();

        try {
            List<Endereco> enderecos = EnderecoUC.buscarTodos();

            for (int i = 0; i < enderecos.size(); i++) {
                comboEndereco.addItem(enderecos.get(i));
            }
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar a lista de endereços!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(botaoCancelar)) {
            try {
                if (fornecedor.getEndereco() == null) {
                    FornecedorUC.removerFornecedor(fornecedor);
                }
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao remover o fornecedor com endereço vazio!", "Erro de Remoção", JOptionPane.ERROR_MESSAGE);
            }

            dispose();
        }

        if (evento.getSource().equals(botaoNovoEndereco)) {
            new TelaCadastrarEndereco(new Endereco()).setVisible(true);

            atualizarComboEndereco();
        }

        if (evento.getSource().equals(botaoSalvar)) {
            try {
                ConstrutorFornecedor construtorFornecedor;

                construtorFornecedor = new ConstrutorFornecedor(
                        textRazaoSocial.getText(),
                        textNomeFantasia.getText(),
                        textCNPJ.getText(),
                        textInscricaoEstadual.getText(),
                        textTelefone.getText(),
                        textEmail.getText(),
                        (Endereco) comboEndereco.getSelectedItem(),
                        fornecedor.getId()
                );

                FornecedorUC.salvarFornecedor(construtorFornecedor);

                dispose();
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Verifique se todos os campos estão preechidos corretamente.", "Erro ao salvar o fornecedor", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

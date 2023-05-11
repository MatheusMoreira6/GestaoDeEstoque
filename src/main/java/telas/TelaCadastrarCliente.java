package telas;

import casoUso.ClienteUC;
import casoUso.EnderecoUC;
import com.toedter.calendar.JDateChooser;
import construtores.ConstrutorCliente;
import entidades.Cliente;
import entidades.Endereco;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import persistencia.BancoCliente;

/**
 * @author Matheus
 * @author Gabriel
 */
public class TelaCadastrarCliente extends JDialog implements ActionListener {

    private Icon iconAdicao = new ImageIcon(getClass().getResource("/icons/new.png"));

    private JPanel painelInformacoes = new JPanel(new GridBagLayout());
    private JPanel painelBotoes = new JPanel(new GridBagLayout());

    private JButton botaoSalvar = new JButton("Salvar");
    private JButton botaoCancelar = new JButton("Cancelar");

    private JLabel labelNome = new JLabel("Nome:");
    private JTextField textNome = new JTextField();

    private JLabel labelCPF = new JLabel("CPF:");
    private JTextField textCPF = new JTextField();

    private JLabel labelTelefone = new JLabel("Telefone:");
    private JTextField textTelefone = new JTextField();

    private JLabel labelDataNascimento = new JLabel("Data de Nascimento:");
    private JDateChooser dataNascimento = new JDateChooser(new Date(), "dd/MM/yyyy");

    private JLabel labelEmail = new JLabel("Email:");
    private JTextField textEmail = new JTextField();

    private JLabel labelEndereco = new JLabel("Endereço:");
    private JComboBox<Endereco> comboEndereco = new JComboBox<>();
    private JButton botaoNovoEndereco = new JButton("Adicionar Endereço", iconAdicao);

    private Cliente cliente;

    public TelaCadastrarCliente(Cliente cliente) {
        this.cliente = cliente;

        if (cliente.getId() == null) {
            atualizarComboEndereco();

            try {
                this.cliente = (Cliente) BancoCliente.salvar(cliente);
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao criar o cliente no banco", "Erro de Gravação", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            textNome.setText(cliente.getNome());
            textCPF.setText(cliente.getCpf());
            dataNascimento.setDate(cliente.getDataNascimento());
            textTelefone.setText(cliente.getTelefone());
            textEmail.setText(cliente.getEmail());
            comboEndereco.removeAllItems();
            comboEndereco.addItem(cliente.getEndereco());
        }

        configuracoesTela();
    }

    private void configuracoesTela() {
        setModal(true);
        setResizable(false);
        setTitle("Cadastrar Cliente");
        setSize(new Dimension(865, 385));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        painelInformacoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setPreferredSize(new Dimension(0, 60));

        addComponentePainelInformacoes(labelNome, 0, 0, 70, 30, 0, 5);
        addComponentePainelInformacoes(textNome, 1, 0, 480, 30, 0, 20);

        addComponentePainelInformacoes(labelCPF, 0, 1, 70, 30, 15, 5);
        addComponentePainelInformacoes(textCPF, 1, 1, 480, 30, 15, 20);

        addComponentePainelInformacoes(labelTelefone, 0, 2, 70, 30, 15, 5);
        addComponentePainelInformacoes(textTelefone, 1, 2, 480, 30, 15, 20);

        addComponentePainelInformacoes(labelDataNascimento, 0, 3, 120, 30, 15, 5);
        addComponentePainelInformacoes(dataNascimento, 1, 3, 95, 30, 15, 0);

        addComponentePainelInformacoes(labelEmail, 0, 4, 70, 30, 15, 5);
        addComponentePainelInformacoes(textEmail, 1, 4, 480, 30, 15, 20);

        addComponentePainelInformacoes(labelEndereco, 0, 5, 70, 30, 15, 5);
        addComponentePainelInformacoes(comboEndereco, 1, 5, 480, 30, 15, 20);
        addComponentePainelInformacoes(botaoNovoEndereco, 2, 5, 200, 40, 15, 0);

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
                if (cliente.getEndereco() == null) {
                    ClienteUC.removerCliente(cliente);
                }
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao remover o cliente com endereço vazio!", "Erro de Remoção", JOptionPane.ERROR_MESSAGE);
            }

            dispose();
        }

        if (evento.getSource().equals(botaoNovoEndereco)) {
            new TelaCadastrarEndereco(new Endereco()).setVisible(true);

            atualizarComboEndereco();
        }

        if (evento.getSource().equals(botaoSalvar)) {
            try {
                ConstrutorCliente construtorCliente;

                construtorCliente = new ConstrutorCliente(
                        textNome.getText(),
                        textCPF.getText(),
                        textTelefone.getText(),
                        dataNascimento.getDate(),
                        textEmail.getText(),
                        (Endereco) comboEndereco.getSelectedItem(),
                        cliente.getId()
                );

                ClienteUC.salvarCliente(construtorCliente);

                dispose();
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Verifique se todos os campos estão preechidos corretamente.", "Erro ao salvar o cliente", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

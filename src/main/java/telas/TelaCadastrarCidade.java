package telas;

import casoUso.CidadeUC;
import casoUso.EstadoUC;
import construtores.ConstrutorCidade;
import entidades.Cidade;
import entidades.Estado;

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
import persistencia.BancoCidade;

/**
 * @author Matheus
 * @author Gabriel
 */
public class TelaCadastrarCidade extends JDialog implements ActionListener {

    private Icon iconAdicao = new ImageIcon(getClass().getResource("/icons/new.png"));

    private JPanel painelInformacoes = new JPanel(new GridBagLayout());
    private JPanel painelBotoes = new JPanel(new GridBagLayout());

    private JButton botaoSalvar = new JButton("Salvar");
    private JButton botaoCancelar = new JButton("Cancelar");

    private JLabel labelNome = new JLabel("Nome:");
    private JTextField textNome = new JTextField();

    private JLabel labelEstado = new JLabel("Estado:");
    private JComboBox<Estado> comboEstado = new JComboBox<>();
    private JButton botaoNovoEstado = new JButton("Adicionar Estado", iconAdicao);

    private Cidade cidade;

    public TelaCadastrarCidade(Cidade cidade) {
        this.cidade = cidade;

        if (cidade.getId() == null) {
            atualizarComboEstado();

            try {
                this.cidade = (Cidade) BancoCidade.salvar(cidade);
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao criar a cidade no banco", "Erro de Gravação", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            textNome.setText(cidade.getNome());
            comboEstado.removeAllItems();
            comboEstado.addItem(cidade.getEstado());
        }

        configuracoesTela();
    }

    private void configuracoesTela() {
        setModal(true);
        setResizable(false);
        setTitle("Cadastrar Cidade");
        setSize(new Dimension(795, 220));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        painelInformacoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelBotoes.setPreferredSize(new Dimension(0, 60));

        addComponentePainelInformacoes(labelNome, 0, 0, 50, 30, 0, 5);
        addComponentePainelInformacoes(textNome, 1, 0, 480, 30, 0, 20);

        addComponentePainelInformacoes(labelEstado, 0, 1, 50, 30, 15, 5);
        addComponentePainelInformacoes(comboEstado, 1, 1, 480, 30, 15, 20);
        addComponentePainelInformacoes(botaoNovoEstado, 2, 1, 200, 40, 15, 0);

        addComponentePainelBotoes(botaoSalvar, 0, 0, 0, 5);
        addComponentePainelBotoes(botaoCancelar, 1, 0, 5, 0);

        this.add(painelInformacoes, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.PAGE_END);

        botaoSalvar.addActionListener(this);
        botaoCancelar.addActionListener(this);
        botaoNovoEstado.addActionListener(this);
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

    private void atualizarComboEstado() {
        comboEstado.removeAllItems();

        try {
            List<Estado> estados = EstadoUC.buscarTodos();

            for (int i = 0; i < estados.size(); i++) {
                comboEstado.addItem(estados.get(i));
            }
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar a lista de estados!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource().equals(botaoCancelar)) {
            try {
                if (cidade.getEstado() == null) {
                    CidadeUC.removerCidade(cidade);
                }
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Falha ao remover a cidade com estado vazio!", "Erro de Remoção", JOptionPane.ERROR_MESSAGE);
            }

            dispose();
        }

        if (evento.getSource().equals(botaoNovoEstado)) {
            new TelaCadastrarEstado(new Estado()).setVisible(true);

            atualizarComboEstado();
        }

        if (evento.getSource().equals(botaoSalvar)) {
            try {
                ConstrutorCidade construtorCidade;

                construtorCidade = new ConstrutorCidade(
                        textNome.getText(),
                        (Estado) comboEstado.getSelectedItem(),
                        cidade.getId()
                );

                CidadeUC.salvarCidade(construtorCidade);

                dispose();
            } catch (Exception excecao) {
                JOptionPane.showMessageDialog(null, "Verifique se todos os campos estão preechidos corretamente.", "Erro ao salvar a cidade", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

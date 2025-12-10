package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

// Importe seus Models e Controls aqui
import model.Aluno;
import model.Plano;
import model.Pagamento;
import control.MatriculaControl;
import control.AlunoControl;    // Assumindo que existe
import control.PlanoControl;    // Assumindo que existe
import control.PagamentoControl;// Assumindo que existe

public class MatriculaView extends JFrame {

    // Componentes da tela alterados para JComboBox
    private JComboBox<Aluno> cbAluno;
    private JComboBox<Plano> cbPlano;
    private JComboBox<Pagamento> cbPagamento;
    private JButton btnAdicionar;

    // Referências aos Controladores
    private MatriculaControl matriculaControl;
    private AlunoControl alunoControl;
    private PlanoControl planoControl;
    private PagamentoControl pagamentoControl;

    public MatriculaView() {
        // Inicializa todos os controladores necessários
        this.matriculaControl = new MatriculaControl();
        this.alunoControl = new AlunoControl();
        this.planoControl = new PlanoControl();
        this.pagamentoControl = new PagamentoControl();

        configurarJanela();
        inicializarComponentes();
        carregarDadosNasCombos(); // Método novo para popular as listas no comboBOX
    }

    private void configurarJanela() {
        setTitle("Realizar Matrícula");
        setSize(450, 300); //?
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        // --- COMBOBOX DO ALUNO ---
        JLabel lblAluno = new JLabel("Selecione o Aluno:");
        lblAluno.setBounds(30, 32, 140, 25);
        getContentPane().add(lblAluno);

        cbAluno = new JComboBox<>();
        cbAluno.setBounds(180, 32, 200, 25); //+Largura
        getContentPane().add(cbAluno);

        // --- COMBOBOX DO PLANO ---
        JLabel lblPlano = new JLabel("Selecione o Plano:");
        lblPlano.setBounds(30, 68, 140, 25);
        getContentPane().add(lblPlano);

        cbPlano = new JComboBox<>();
        cbPlano.setBounds(180, 68, 200, 25);
        getContentPane().add(cbPlano);

        // --- COMBOBOX DO PAGAMENTO ---
        JLabel lblPagamento = new JLabel("Pagamento:");
        lblPagamento.setBounds(30, 104, 150, 25);
        getContentPane().add(lblPagamento);

        cbPagamento = new JComboBox<>();
        cbPagamento.setBounds(180, 104, 200, 25);
        getContentPane().add(cbPagamento);

        // --- BOTÃO ---
        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(160, 160, 100, 30);
        getContentPane().add(btnAdicionar);

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarMatricula();
            }
        });
    }

    /**
     * Busca os dados no banco através dos controllers e preenche as ComboBoxes.
     */
    private void carregarDadosNasCombos() {
        try {
            // 1. Carregar Alunos
            List<Aluno> listaAlunos = alunoControl.listarTodos(); 
            cbAluno.removeAllItems();
            for (Aluno a : listaAlunos) {
                cbAluno.addItem(a);
            }

            // 2. Carregar Planos
            List<Plano> listaPlanos = planoControl.listarTodos();
            cbPlano.removeAllItems();
            for (Plano p : listaPlanos) {
                cbPlano.addItem(p);
            }

            // 3. Carregar Pagamentos 
            List<Pagamento> listaPagamentos = pagamentoControl.listarTodos();
            cbPagamento.removeAllItems();
            for (Pagamento pg : listaPagamentos) {
                cbPagamento.addItem(pg);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage());
        }
    }

    private void realizarMatricula() {
        try {
            // 1. Captura os OBJETOS selecionados nas combos
            Aluno alunoSelecionado = (Aluno) cbAluno.getSelectedItem();
            Plano planoSelecionado = (Plano) cbPlano.getSelectedItem();
            Pagamento pagamentoSelecionado = (Pagamento) cbPagamento.getSelectedItem();

            // Validação simples evitando erro GRAVES!!!
            if (alunoSelecionado == null || planoSelecionado == null || pagamentoSelecionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, selecione todos os campos.");
                return;
            }


            LocalDate dataInicio = LocalDate.now();

            // 2. Chama o Controller
            boolean sucesso = matriculaControl.adicionarMatricula(alunoSelecionado,planoSelecionado, dataInicio, pagamentoSelecionado);

            // 3. Feedback
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Matrícula realizada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao realizar matrícula.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro no sistema: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MatriculaView().setVisible(true);
    }
}
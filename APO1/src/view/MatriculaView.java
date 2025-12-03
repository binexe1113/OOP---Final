package view;

import javax.swing.*;
import control.MatriculaControl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate; 

public class MatriculaView extends JFrame {

    // Componentes da tela
    private JTextField txtCpfAluno; // Mudei o nome da variável para ficar claro
    private JTextField txtIdPlano;
    private JButton btnAdicionar;
    private JTextField txtIdPagamento;

    
    // Referência ao Controlador
    private MatriculaControl matriculaControl;
    private JTextField textField;

    public MatriculaView() {
        this.matriculaControl = new MatriculaControl();
        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setTitle("Realizar Matrícula");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        // --- CAMPO DO ALUNO (AGORA É CPF) ---
        JLabel lblAluno = new JLabel("Insira o CPF do Aluno:");
        lblAluno.setBounds(30, 32, 140, 25);
        getContentPane().add(lblAluno);

        txtCpfAluno = new JTextField(); // Variável renomeada
        txtCpfAluno.setBounds(180, 32, 150, 25);
        getContentPane().add(txtCpfAluno);
        
        

        // --- CAMPO DO PLANO (CONTINUA ID POR ENQUANTO) ---
        // Notei que seu label dizia "nome", mas a lógica usava ID. 
        // Mantive ID para não quebrar a lógica do plano agora.
        JLabel lblPlano = new JLabel("Insira o ID do Plano:"); 
        lblPlano.setBounds(30, 68, 140, 25);
        getContentPane().add(lblPlano);

        txtIdPlano = new JTextField();
        txtIdPlano.setBounds(180, 68, 150, 25);
        getContentPane().add(txtIdPlano);

        // --- BOTÃO ---
        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(140, 160, 100, 30);
        getContentPane().add(btnAdicionar);
        
        JLabel lblInsiraOId = new JLabel("Insira o ID do Pagamento:");
        lblInsiraOId.setBounds(20, 104, 150, 25);
        getContentPane().add(lblInsiraOId);
        
        txtIdPagamento = new JTextField();
        txtIdPagamento.setBounds(180, 104, 150, 25);
        getContentPane().add(txtIdPagamento);

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarMatricula();
            }
        });
    }

    private void realizarMatricula() {
        try {
            // 1. Captura os dados da tela
            
            // MUDANÇA PRINCIPAL AQUI:
            // Pegamos o texto direto (String), sem converter para Integer
            String cpfAluno = txtCpfAluno.getText(); 
            
            // O Plano continua sendo int (ID)
            int idPlano = Integer.parseInt(txtIdPlano.getText());
            
            int idPagamento = Integer.parseInt(txtIdPagamento.getText());

            // 2. Data atual
            LocalDate dataInicio = LocalDate.now(); 


            // 3. Validação simples de campo vazio
            if(cpfAluno.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, digite o CPF.");
                return;
            }

            // 4. Chama o Controller 
            // O Controller DEVE esperar (String, int, LocalDate) agora
            boolean sucesso = matriculaControl.adicionarMatricula(cpfAluno, idPlano, dataInicio, idPagamento);

            // 5. Feedback
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Matrícula realizada com sucesso!");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro: Aluno não encontrado, erro ao salvar ou Aluno já possui matricula ativa", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O ID do Plano deve ser um número válido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro no sistema: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    private void limparCampos() {
        txtCpfAluno.setText("");
        txtIdPlano.setText("");
    }

    public static void main(String[] args) {
        new MatriculaView().setVisible(true);
    }
}
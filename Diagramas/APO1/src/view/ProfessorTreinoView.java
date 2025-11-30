package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel; // Importei Label para identificar o campo
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane; // Importei para barra de rolagem

import java.util.List;
import control.TreinoControl;
import model.Aluno;

public class ProfessorTreinoView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdAluno; // Campo novo para digitar o ID
    private JTextArea textAreaLista; // O de cima (Lista)
    private JTextArea textAreaDetalhe; // O de baixo (Treino)
    
    private TreinoControl control;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Teste com ID 999 (fictício)
                    ProfessorTreinoView frame = new ProfessorTreinoView(999);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    // CORREÇÃO 1: Recebe o ID do professor no construtor
    public ProfessorTreinoView(int idProfessor) {
        
        // Inicializa o controller
        control = new TreinoControl();
        
        setTitle("Painel do Professor - ID Logado: " + idProfessor);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 450); // Aumentei um pouco a altura
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // --- BOTÃO 1: LISTAR ALUNOS ---
        JButton btnListar = new JButton("Listar Meus Alunos");
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica: Busca a lista e escreve linha por linha na área de cima
                List<Aluno> lista = control.listarAlunosParaProfessor();
                
                textAreaLista.setText(""); // Limpa antes de listar
                textAreaLista.append("--- LISTA DE ALUNOS ---\n");
                
                for(Aluno a : lista) {
                    textAreaLista.append("ID: " + a.getId() + " - Nome: " + a.getNome() + "\n");
                }
            }
        });
        btnListar.setBounds(51, 12, 428, 23);
        contentPane.add(btnListar);
        
        // --- TEXT AREA 1 (LISTA) ---
        textAreaLista = new JTextArea();
        textAreaLista.setEditable(false);
        // Adicionei ScrollPane para caso a lista seja grande
        JScrollPane scrollLista = new JScrollPane(textAreaLista);
        scrollLista.setBounds(51, 41, 428, 114);
        contentPane.add(scrollLista);
        
        // --- ÁREA DE SELEÇÃO (MEIO DA TELA) ---
        
        // Label explicativo
        JLabel lblDigiteId = new JLabel("Digite o ID do Aluno:");
        lblDigiteId.setBounds(51, 162, 120, 14);
        contentPane.add(lblDigiteId);
        
        // CORREÇÃO 2: Campo para digitar o ID
        txtIdAluno = new JTextField();
        txtIdAluno.setBounds(165, 159, 50, 20);
        contentPane.add(txtIdAluno);
        txtIdAluno.setColumns(10);
        
        // --- BOTÃO 2: SELECIONAR ---
        JButton btnSelecionar = new JButton("Ver Treino");
        btnSelecionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Lógica: Pega o número digitado e busca o treino
                    String idTexto = txtIdAluno.getText();
                    if(!idTexto.isEmpty()) {
                        int idBuscado = Integer.parseInt(idTexto);
                        
                        String treino = control.consultarTreino(idBuscado);
                        textAreaDetalhe.setText(treino);
                    } else {
                        JOptionPane.showMessageDialog(null, "Digite um ID na caixinha!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "O ID deve ser numérico.");
                }
            }
        });
        btnSelecionar.setBounds(225, 158, 120, 23);
        contentPane.add(btnSelecionar);
        
        // --- TEXT AREA 2 (DETALHE DO TREINO) ---
        textAreaDetalhe = new JTextArea();
        textAreaDetalhe.setEditable(false);
        JScrollPane scrollDetalhe = new JScrollPane(textAreaDetalhe);
        scrollDetalhe.setBounds(51, 192, 428, 150);
        contentPane.add(scrollDetalhe);
    }
}
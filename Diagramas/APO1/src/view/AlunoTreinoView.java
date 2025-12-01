package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

// Importar o Controller e o Model
import control.TreinoControl;
import model.Treino;

public class AlunoTreinoView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    private JTextArea textArea; 
    private TreinoControl control;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Teste com ID 1 (Naruto)
                    AlunoTreinoView frame = new AlunoTreinoView(1);
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
    public AlunoTreinoView(int idAluno) {
        
        // Inicializa o controlador
        control = new TreinoControl();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        setTitle("Painel do Aluno - ID: " + idAluno);
        
        JButton btnConsultar = new JButton("Consultar próprio treino");
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // --- MUDANÇA AQUI ---
                
                // 1. Chamamos o método novo que faz o Loop (conforme seu diagrama)
                // Ele retorna um OBJETO, não uma String
                Treino treinoEncontrado = control.consultarTreinoPorLoop(idAluno);
                
                textArea.setText(""); // Limpa o texto anterior
                
                // 2. Verificamos se achou algo
                if (treinoEncontrado != null) {
                    // 3. Montamos o texto pegando os dados de dentro do objeto
                    textArea.append("--- FICHA DE TREINO ---\n\n");
                    textArea.append("Descrição: " + treinoEncontrado.getDescricao() + "\n");
                    textArea.append("Início: " + treinoEncontrado.getDataInicio() + "\n");
                    textArea.append("Fim: " + treinoEncontrado.getDataFim() + "\n");
                    
                    // Se quiser mostrar que o objeto Aluno veio junto:
                    // textArea.append("Aluno (Objeto): " + treinoEncontrado.getAluno().getId());
                } else {
                    textArea.setText("Nenhum treino encontrado para o seu ID.");
                }
            }
        });
        btnConsultar.setBounds(120, 11, 200, 23);
        contentPane.add(btnConsultar);
        
        textArea = new JTextArea();
        textArea.setBounds(45, 68, 354, 168);
        textArea.setEditable(false); 
        contentPane.add(textArea);
    }
}
package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import control.TreinoControl;
import model.Treino;

public class AlunoTreinoView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    private JTextArea textArea; 
    private TreinoControl control;

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

    public AlunoTreinoView(int idAluno) {
        // Inicializa o controlador
        control = new TreinoControl();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400); // Aumentei um pouco a altura para caber a lista
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        setTitle("Painel do Aluno - ID: " + idAluno);
        
        JButton btnConsultar = new JButton("Consultar próprio treino");
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // 1. Busca o treino usando o método de loop
                Treino treinoEncontrado = control.consultarTreinoPorLoop(idAluno);
                
                textArea.setText(""); // Limpa o texto anterior
                
                if (treinoEncontrado != null) {
                    textArea.append("--- FICHA DE TREINO ---\n\n");
                    
                    // 2. CHAMA A FORMATAÇÃO DA CONTROL
                    // Isso vai transformar "Supino, Rosca" em:
                    // - Supino
                    // - Rosca
                    String listaExercicios = control.gerarDescricaoFormatada(treinoEncontrado);
                    
                    textArea.append("ATIVIDADES:\n");
                    textArea.append(listaExercicios + "\n\n"); // Adiciona a lista formatada
                    
                    textArea.append("---------------------------\n");
                    textArea.append("Vigência: " + treinoEncontrado.getDataInicio() + " até " + treinoEncontrado.getDataFim());
                    
                } else {
                    textArea.setText("Nenhum treino encontrado para o seu ID.");
                }
            }
        });
        btnConsultar.setBounds(120, 11, 200, 23);
        contentPane.add(btnConsultar);
        
        textArea = new JTextArea();
        textArea.setBounds(45, 68, 354, 250); // Aumentei a altura da caixa de texto
        textArea.setEditable(false); 
        
        // DICA EXTRA: Isso garante que se a linha for muito longa, ela quebra automaticamente
        textArea.setLineWrap(true); 
        textArea.setWrapStyleWord(true);
        
        contentPane.add(textArea);
    }
}
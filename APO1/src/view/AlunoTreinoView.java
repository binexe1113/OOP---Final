package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane; // Import necessário para a barra de rolagem

import control.TreinoControl;
import model.Aluno;
import model.Treino;

public class AlunoTreinoView extends JFrame {

    private JPanel contentPane;
    
    private JTextArea textArea; 
    private TreinoControl control;
    
    private Aluno alunoLogado;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Teste simulando o aluno com ID 1
                	Aluno alunoTeste = new Aluno();
                	alunoTeste.setId(1);
                    alunoTeste.setNome("Usuário de Teste");
                 // 2. Passamos esse objeto criado para a janela
                    AlunoTreinoView frame = new AlunoTreinoView(alunoTeste);
                    frame.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();            }
            }
        });
    }

    public AlunoTreinoView(Aluno alunoLogado) {
        // Inicializa o controlador para acessar os dados
        control = new TreinoControl();
        
        // Configurações básicas da Janela (JFrame)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400); 
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null); // Layout absoluto (posicionamento manual X,Y)
        
        setTitle("Painel do Aluno - Nome: " + alunoLogado.getNome());
        
        // Configuração do Botão de Consulta
        JButton btnConsultar = new JButton("Consultar próprio treino");
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	textArea.setText(""); // Limpa o campo de texto antes de mostrar novos dados

                // 1. Chama a Control para buscar o treino pelo ID
                String treinoEncontrado = control.consultarTreino(alunoLogado);
                
                if(treinoEncontrado != null) {//checker para debug
                	textArea.setText(treinoEncontrado);
                } 
                	
                
                
                
                    
                    textArea.setCaretPosition(0); 
                    
            }
        });
        btnConsultar.setBounds(120, 11, 200, 23);
        contentPane.add(btnConsultar);
        
        // --- INÍCIO DA CORREÇÃO DO SCROLL //
        
        textArea = new JTextArea();
        
        textArea.setEditable(false); 
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        scrollPane.setBounds(45, 68, 340, 250);
        
        contentPane.add(scrollPane);
        
    }

}
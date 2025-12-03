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
                    // Teste simulando o aluno com ID 1
                    AlunoTreinoView frame = new AlunoTreinoView(1);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AlunoTreinoView(int idAluno) {
        // Inicializa o controlador para acessar os dados
        control = new TreinoControl();
        
        // Configurações básicas da Janela (JFrame)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400); 
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null); // Layout absoluto (posicionamento manual X,Y)
        
        setTitle("Painel do Aluno - ID: " + idAluno);
        
        // Configuração do Botão de Consulta
        JButton btnConsultar = new JButton("Consultar próprio treino");
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // 1. Chama a Control para buscar o treino pelo ID
                Treino treinoEncontrado = control.consultarTreinoPorLoop(idAluno);
                
                textArea.setText(""); // Limpa o campo de texto antes de mostrar novos dados
                
                if (treinoEncontrado != null) {
                    textArea.append("--- FICHA DE TREINO ---\n\n");
                    
                    // 2. Formata a lista de exercícios (ex: transforma String única em lista visual)
                    String listaExercicios = control.gerarDescricaoFormatada(treinoEncontrado);
                    
                    textArea.append("ATIVIDADES:\n");
                    textArea.append(listaExercicios + "\n\n"); 
                    
                    textArea.append("---------------------------\n");
                    textArea.append("Vigência: " + treinoEncontrado.getDataInicio() + " até " + treinoEncontrado.getDataFim());
                    
                    // IMPORTANTE: Reseta a barra de rolagem para o topo após carregar o texto.
                    // Sem isso, se o texto for longo, ele pode aparecer rolado lá para baixo.
                    textArea.setCaretPosition(0); 
                    
                } else {
                    textArea.setText("Nenhum treino encontrado para o seu ID.");
                }
            }
        });
        btnConsultar.setBounds(120, 11, 200, 23);
        contentPane.add(btnConsultar);
        
        // --- INÍCIO DA CORREÇÃO DO SCROLL (BARRA DE ROLAGEM) ---
        
        textArea = new JTextArea();
        
        // Quem define o tamanho na tela agora é o painel de rolagem (JScrollPane).
        
        textArea.setEditable(false); // Impede que o usuário digite no campo
        textArea.setLineWrap(true);  // Quebra a linha automaticamente se o texto for longo
        textArea.setWrapStyleWord(true); // Garante que a quebra de linha não corte palavras no meio
        
        // Cria o JScrollPane e "embala" o textArea dentro dele.
        // O textArea agora é o conteúdo (viewport) do scrollPane.
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        // Define a posição (X, Y) e o tamanho (Largura, Altura) do SCROLLPANE
        scrollPane.setBounds(45, 68, 340, 250);
        
        // Adiciona o scrollPane ao painel principal da janela.
        // Nota: Não adicionamos o textArea diretamente ao contentPane, apenas o scrollPane.
        contentPane.add(scrollPane);
        
        // --- FIM DA CORREÇÃO ---
    }
}
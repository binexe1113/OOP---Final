package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.TreinoControl;
import model.Aluno;
import model.Treino; // <--- IMPORTANTE: Importar o modelo Treino

public class ProfessorTreinoView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdAluno;
    private JTextArea textAreaLista;
    private JTextArea textAreaDetalhe;
    
    private TreinoControl control;
    private int idProfessorLogado; // Variável para guardar quem está logado

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Teste com ID 1 (Prof. Jiraya do nosso banco)
                    ProfessorTreinoView frame = new ProfessorTreinoView(1);
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
    public ProfessorTreinoView(int idProfessor) {
        //Guardamos o ID recebido na variável da classe
        this.idProfessorLogado = idProfessor;
        
        control = new TreinoControl();
        
        setTitle("Painel do Professor - ID Logado: " + idProfessor);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // --- BOTÃO 1: LISTAR MEUS ALUNOS ---
        JButton btnListar = new JButton("Listar Meus Alunos");
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarAlunos();
            }
        });
        btnListar.setBounds(51, 12, 428, 23);
        contentPane.add(btnListar);
        
        // --- TEXT AREA 1 (LISTA) ---
        textAreaLista = new JTextArea();
        textAreaLista.setEditable(false);
        JScrollPane scrollLista = new JScrollPane(textAreaLista);
        scrollLista.setBounds(51, 41, 428, 114);
        contentPane.add(scrollLista);
        
        // --- ÁREA DE SELEÇÃO ---
        JLabel lblDigiteId = new JLabel("Digite o ID do Aluno:");
        lblDigiteId.setBounds(51, 162, 120, 14);
        contentPane.add(lblDigiteId);
        
        txtIdAluno = new JTextField();
        txtIdAluno.setBounds(165, 159, 50, 20);
        contentPane.add(txtIdAluno);
        txtIdAluno.setColumns(10);
        
        // --- BOTÃO 2: VER TREINO ---
        JButton btnSelecionar = new JButton("Ver Treino");
        btnSelecionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarTreino();
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
    

    private void listarAlunos() {
    	// Agora chama o método que popula o cache
    	List<Aluno> lista = control.listarAlunosComTreino(this.idProfessorLogado);
    	// ... código de preencher o JTextArea igual antes ...        
        textAreaLista.setText(""); 
        textAreaLista.append("--- ALUNOS VINCULADOS A MIM ---\n");
        
        if (lista.isEmpty()) {
            textAreaLista.append("Nenhum aluno encontrado.");
        } else {
            for(Aluno a : lista) {
                // Exibe ID, Nome e CPF que vieram do banco
                textAreaLista.append("ID: " + a.getId() + " | Nome: " + a.getNome() + " | CPF: " + a.getCpf() + "\n");
            }
        }
    }
    
    private void buscarTreino() {
        try {
            String idTexto = txtIdAluno.getText();
            if(!idTexto.isEmpty()) {
            	int idBuscado = Integer.parseInt(txtIdAluno.getText());
            	
            	Treino treino = control.consultarTreinoNaMemoria(idBuscado);

            	if (treino != null) {
            	    textAreaDetalhe.setText(treino.getDescricao() + "...");
            	}                
                textAreaDetalhe.setText(""); // Limpa anterior
                
                if (treino != null) {
                    // Montamos o texto bonito usando os Getters do objeto
                    StringBuilder sb = new StringBuilder();
                    sb.append("--- FICHA DE TREINO ATUAL ---\n");
                    sb.append("Descrição: ").append(treino.getDescricao()).append("\n");
                    sb.append("Data Início: ").append(treino.getDataInicio()).append("\n");
                    sb.append("Data Fim: ").append(treino.getDataFim()).append("\n");
                    
                    textAreaDetalhe.setText(sb.toString());
                } else {
                    textAreaDetalhe.setText("Nenhum treino ativo encontrado para este aluno.");
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "Digite um ID na caixinha!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O ID deve ser numérico.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
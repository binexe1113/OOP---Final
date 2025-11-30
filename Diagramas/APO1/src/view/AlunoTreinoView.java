package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

// Importar o Controller para buscar os dados
import control.TreinoControl; 

public class AlunoTreinoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// Transformamos em variável global para o botão conseguir acessar
	private JTextArea textArea; 
	private TreinoControl control;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Passamos um ID falso (1) apenas para testar essa tela sozinha
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
	// AQUI ESTÁ A MUDANÇA PRINCIPAL: Recebemos o idAluno
	public AlunoTreinoView(int idAluno) {
		
		// Inicializa o controlador
		control = new TreinoControl();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Título da janela com o ID (opcional, ajuda a saber quem logou)
		setTitle("Painel do Aluno - ID: " + idAluno);
		
		JButton btnNewButton = new JButton("Consultar próprio treino");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// AÇÃO DO BOTÃO:
				// 1. Chama o controller passando o ID que recebemos no construtor
				String treino = control.consultarTreino(idAluno);
				
				// 2. Escreve o resultado na caixa de texto
				textArea.setText(treino);
			}
		});
		btnNewButton.setBounds(141, 11, 154, 23);
		contentPane.add(btnNewButton);
		
		// Inicializa a variável global textArea
		textArea = new JTextArea();
		textArea.setBounds(45, 68, 354, 168);
		textArea.setEditable(false); // O aluno não deve editar o treino, só ler
		contentPane.add(textArea);
		
		// (Opcional) Se você quiser que o treino apareça automático sem clicar no botão,
		// descomente a linha abaixo:
		// btnNewButton.doClick(); 
	}
}
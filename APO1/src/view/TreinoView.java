package view;

import javax.swing.*;

import dao.AlunoDAO;
import dao.ProfessorDAO;
import model.Aluno;
import model.Professor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TreinoView extends JFrame {
    
    // Variáveis globais da classe
    private JTextField txtTreinos;
    private JTextField txtInputId; 
    private JTextField txtLabelId; 
    private JComboBox<String> comboBox; 

    public TreinoView() {
        // Configurações da Janela
        setTitle("Sistema de Treino");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza na tela
        getContentPane().setLayout(null);
        
        // --- Título "TREINOS" ---
        txtTreinos = new JTextField();
        txtTreinos.setHorizontalAlignment(SwingConstants.CENTER);
        txtTreinos.setText("TREINOS");
        txtTreinos.setEditable(false);
        txtTreinos.setBounds(174, 11, 86, 20);
        getContentPane().add(txtTreinos);
        txtTreinos.setColumns(10);
        
        // --- ComboBox (Escolha de Perfil) ---
        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Aluno", "Professor"}));
        comboBox.setBounds(174, 72, 120, 22); // MAIOR?
        getContentPane().add(comboBox);
        
        // --- Label "CPF:" ---
        txtLabelId = new JTextField();
        txtLabelId.setEditable(false);
        txtLabelId.setText("CPF:");
        txtLabelId.setColumns(10);
        txtLabelId.setBounds(174, 122, 30, 20);
        getContentPane().add(txtLabelId);

        // --- Campo de Texto para digitar o CPF ---
        txtInputId = new JTextField();
        txtInputId.setBounds(210, 122, 120, 20);
        getContentPane().add(txtInputId);
        txtInputId.setColumns(10);
        
        // --- Botão ENTRAR ---
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(174, 167, 89, 23);
        getContentPane().add(btnEntrar);

        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // 1. Pega o CPF digitado
                    String cpf = txtInputId.getText();
                    
                    if (cpf.isEmpty()) {//DENOVO EVITANDO GRANDE ERROS!
                        JOptionPane.showMessageDialog(null, "Por favor, digite o CPF.");
                        return;
                    }
                    
                    
                    // 2. Verifica o que está selecionado no ComboBox : ALUNO ? PROFESSOR
                    String tipoUsuario = comboBox.getSelectedItem().toString();
                    
                    

                    // 3. Decide qual tela abrir
                    if (tipoUsuario.equals("Aluno")) { 
                    	// 1. Chama a classe que controla o banco de dados do Aluno
                        AlunoDAO dao = new AlunoDAO(); 
                        
                        // 2. Recupera o objeto Aluno completo pelo ID digitado
                        Aluno alunoLogado = dao.buscarPorCpf(cpf); 
                        
                        // Validação: verifica se achou Oo Aluno
                        if (alunoLogado == null) {
                            JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
                            return;
                        }

                        // 3. Abre a tela passando o OBJETO 'alunoLogado' recuperado
                        AlunoTreinoView telaAluno = new AlunoTreinoView(alunoLogado);
                        telaAluno.setVisible(true);
                        
                    } else {
                        // --- LÓGICA NOVA PARA PROFESSOR ---
                        
                        ProfessorDAO dao = new ProfessorDAO();
                        Professor professorLogado = dao.buscarPorCpf(cpf);
                        
                        if (professorLogado == null) {
                            JOptionPane.showMessageDialog(null, "Professor não encontrado!");
                            return;
                        }

                        // Abre a tela passando o OBJETO 'professorLogado'
                        ProfessorTreinoView telaProf = new ProfessorTreinoView(professorLogado);
                        telaProf.setVisible(true);
                    }                    // 4. Fecha a tela de login atual
                    dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "O ID deve ser apenas números!");
                } catch (Exception ex) {
                	
                	//DEBUG:
                    JOptionPane.showMessageDialog(null, "Erro ao abrir tela: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    // Método main para testar essa tela isoladamente
    public static void main(String[] args) {
        new TreinoView().setVisible(true);
    }
}
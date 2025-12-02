package view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TreinoView extends JFrame {
    
    // Variáveis globais da classe
    private JTextField txtTreinos;
    private JTextField txtInputId; // Renomeei o 'textField' para ficar mais claro
    private JTextField txtLabelId; // Renomeei o 'txtId' para ficar mais claro
    private JComboBox<String> comboBox; // Agora é acessível em toda a classe

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
        comboBox.setBounds(174, 72, 120, 22); // Aumentei um pouco a largura
        getContentPane().add(comboBox);
        
        // --- Label "ID:" ---
        txtLabelId = new JTextField();
        txtLabelId.setEditable(false);
        txtLabelId.setText("ID:");
        txtLabelId.setColumns(10);
        txtLabelId.setBounds(174, 122, 30, 20);
        getContentPane().add(txtLabelId);

        // --- Campo de Texto para digitar o ID ---
        txtInputId = new JTextField();
        txtInputId.setBounds(210, 122, 83, 20);
        getContentPane().add(txtInputId);
        txtInputId.setColumns(10);
        
        // --- Botão ENTRAR ---
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(174, 167, 89, 23);
        getContentPane().add(btnEntrar);

        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // 1. Pega o ID digitado
                    String idTexto = txtInputId.getText();
                    
                    if (idTexto.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, digite o ID.");
                        return;
                    }
                    
                    int id = Integer.parseInt(idTexto); // Converte para número
                    
                    // 2. Verifica o que está selecionado no ComboBox
                    String tipoUsuario = comboBox.getSelectedItem().toString();

                    // 3. Decide qual tela abrir
                    if (tipoUsuario.equals("Aluno")) {
                        // Abre a tela do ALUNO passando o ID
                        AlunoTreinoView telaAluno = new AlunoTreinoView(id);
                        telaAluno.setVisible(true);
                        
                    } else {
                        // Abre a tela do PROFESSOR passando o ID
                        ProfessorTreinoView telaProf = new ProfessorTreinoView(id);
                        telaProf.setVisible(true);
                    }

                    // 4. Fecha a tela de login atual
                    dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "O ID deve ser apenas números!");
                } catch (Exception ex) {
                    // Caso as outras telas ainda não estejam prontas ou dê erro
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
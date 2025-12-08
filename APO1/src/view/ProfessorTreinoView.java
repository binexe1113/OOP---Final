package view; // Define que esta classe pertence ao pacote de visualização (View)

// Importações necessárias para componentes de interface gráfica (Swing/AWT) e manipulação de listas
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

// Importações das classes do projeto (Model e Control)
import control.TreinoControl;
import model.Aluno;
import model.Professor;
import model.Treino; // <--- IMPORTANTE: Importar o modelo Treino

public class ProfessorTreinoView extends JFrame {

    // Identificador único para serialização da classe (padrão do Java Swing)
    private static final long serialVersionUID = 1L;
    
    // Declaração dos componentes da interface gráfica
    private JPanel contentPane;
    private JTextField txtIdAluno;
    private JTextArea textAreaLista;
    private JTextArea textAreaDetalhe;

    
    // Referência ao controlador para realizar operações lógicas
    private TreinoControl control;
    
    private Professor professorLogado;
    

    /**
     * Launch the application.
     * Método principal para testar a tela individualmente.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Teste simulando o aluno com ID 1
                	Professor proTeste = new Professor();
                	proTeste.setIdProf(1);
                    proTeste.setNome("Usuário de Teste");
                 // 2. Passamos esse objeto criado para a janela
                    ProfessorTreinoView frame = new ProfessorTreinoView(proTeste);
                    frame.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();            }
            }
        });
    }

    /**
     * Create the frame.
     * Construtor da classe: Configura a janela e seus componentes.
     */
    public ProfessorTreinoView(Professor professorLogado) {        
        // Instancia o controlador para poder acessar os métodos de busca e listagem
        control = new TreinoControl();
        
        // Configurações iniciais da Janela (Título, ação de fechar, tamanho)
        setTitle("Painel do Professor - Nome: " + professorLogado.getNome());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Encerra o programa ao fechar a janela
        setBounds(100, 100, 550, 450); // Define posição (x, y) e tamanho (largura, altura)
        
        // Configuração do painel principal
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null); // Layout nulo permite posicionamento absoluto (setBounds)
        
        // --- BOTÃO 1: LISTAR MEUS ALUNOS ---
        JButton btnListar = new JButton("Listar Meus Alunos");
        // Adiciona um ouvinte de evento para o clique do botão
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarAlunos(professorLogado); // Chama o método interno que busca e exibe os alunos
            }
        });
        btnListar.setBounds(51, 12, 428, 23); // Posicionamento manual
        contentPane.add(btnListar); // Adiciona o botão ao painel
        
        // --- TEXT AREA 1 (LISTA DE ALUNOS) ---
        textAreaLista = new JTextArea();
        textAreaLista.setEditable(false); // Impede que o usuário digite nesta área, apenas leitura
        
        // Adiciona barra de rolagem (Scroll) ao redor da área de texto
        JScrollPane scrollLista = new JScrollPane(textAreaLista);
        scrollLista.setBounds(51, 41, 428, 114);
        contentPane.add(scrollLista);
        
        // --- ÁREA DE SELEÇÃO (LABEL E INPUT) ---
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
                buscarTreino(); // Chama o método interno que busca o treino específico
            }
        });
        btnSelecionar.setBounds(225, 158, 120, 23);
        contentPane.add(btnSelecionar);
        
        // --- TEXT AREA 2 (DETALHE DO TREINO) ---
        textAreaDetalhe = new JTextArea();
        textAreaDetalhe.setEditable(false); // Apenas leitura
        
        // Adiciona barra de rolagem para os detalhes do treino
        JScrollPane scrollDetalhe = new JScrollPane(textAreaDetalhe);
        scrollDetalhe.setBounds(51, 192, 428, 150);
        contentPane.add(scrollDetalhe);
    }
    

    // Método auxiliar para buscar alunos no banco/memória e exibir na tela
    private void listarAlunos(Professor professorLogado) {
        // Solicita ao control a lista de alunos vinculados a este professor
        List<Aluno> lista = control.listarAlunosComTreino(professorLogado);
        
        // Limpa a área de texto antes de escrever os novos dados
        textAreaLista.setText(""); 
        textAreaLista.append("--- ALUNOS VINCULADOS A MIM ---\n");
        
        // Verifica se a lista retornada está vazia
        if (lista.isEmpty()) {
            textAreaLista.append("Nenhum aluno encontrado.");
        } else {
            // Percorre a lista de alunos (Foreach)
            for(Aluno a : lista) {
                // Formata uma string com os dados do aluno e adiciona no TextArea
                textAreaLista.append("ID: " + a.getId() + " | Nome: " + a.getNome() + " | CPF: " + a.getCpf() + "\n");
            }
        }
    }
    
    // Método auxiliar para buscar o treino de um aluno específico pelo ID digitado
    private void buscarTreino() {
        try {
            // Pega o texto digitado no campo de ID
            String idTexto = txtIdAluno.getText();
            
            Aluno aluno = new Aluno();
            textAreaDetalhe.setText("");
            // Verifica se o campo não está vazio
            if(!idTexto.isEmpty()) {
                // Converte o texto (String) para número inteiro (int)
                int idBuscado = Integer.parseInt(txtIdAluno.getText());
                
                //pASSA O ATRIBUTO ID PARA OBJETO ALUNO
                aluno.setId(idBuscado);
                
                // Busca o treino na memória (lista cacheada) através do control
                String treino = control.consultarTreinoNaMemoria(aluno);

                // Define um texto inicial se encontrar o treino (Nota: este trecho é visualmente sobrescrito logo abaixo)
                if (treino != null) {
                	textAreaDetalhe.setText(treino);
                }                
                
            } else {
                    // Caso o objeto treino seja nulo (não encontrado)
                    textAreaDetalhe.setText("Nenhum treino ativo encontrado para este aluno.");
                }
                

        } catch (NumberFormatException ex) {
            // Tratamento de erro: executado se o usuário digitar letras em vez de números
            JOptionPane.showMessageDialog(this, "O ID deve ser numérico.");
        } catch (Exception ex) {
            // Tratamento genérico para outros erros inesperados
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
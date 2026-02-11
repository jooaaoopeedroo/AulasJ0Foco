import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CadastroAlunos {

    // Map para amazenar a matrícula e o índice do alun no ArrayList
    static Map<String, Integer> matriculaMap = new HashMap<>();
    // Lista para armazenar os dados dos alunos
    static ArrayList<Map<String, Object>> alunos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            ExibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            switch (opcão) {
                case 1:
                    cadastrarAluno(scanner);
                    break;
                case 2:
                    buscarPorMatricula(scanner);
                    break;
                case 3:
                    listarTodosAlunos();
                    break;
                case 4:
                    removerPorMatricula(scanner);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // Exibe o menu de opções
    public static void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Cadastrar aluno");
        System.out.println("2 - Buscar por matrícula");
        System.out.println("3 - Listar todos");
        System.out.println("4 - Remover por matrícula");
        System.out.println("5 - Sair");
    }

    // Cadastrar um novo aluno
    public static void cadastrarAluno(Scanner scanner) {
        System.out.println("Digite o nome do aluno:");
        String nome = scanner.nextLine();

        System.out.println("Digite a idade do aluno:");
        int idade = scanner.nextInt();
        scanner.nextLine();  // Limpar o buffer

        // Validando a idade
        if (idade < 0) {
            System.out.println("Idade inválida! A idade não pode ser negativa.");
            return;
        }

        System.out.println("Digite a matrícula do aluno:");
        String matricula = scanner.nextLine();

        // Validando matrícula única
        if (matriculaMap.containsKey(matricula)) {
            System.out.println("Matrícula já cadastrada. Tente novamente.");
            return;
        }

        // Criando o registro do aluno
        Map<String, Object> aluno = new HashMap<>();
        aluno.put("nome", nome);
        aluno.put("idade", idade);
        aluno.put("matricula", matricula);

        // Adicionando o aluno na lista
        alunos.add(aluno);
        // Atualizando o Map com a matrícula e o índice do aluno
        matriculaMap.put(matricula, alunos.size() - 1);

        System.out.println("Aluno cadastrado com sucesso!");
    }

    // Buscar um aluno pela matrícula
    public static void buscarPorMatricula(Scanner scanner) {
        System.out.println("Digite a matrícula do aluno que deseja buscar:");
        String matricula = scanner.nextLine();

        if (matriculaMap.containsKey(matricula)) {
            int indice = matriculaMap.get(matricula);
            Map<String, Object> aluno = alunos.get(indice);
            System.out.println("Aluno encontrado: " + aluno.get("nome") + ", Idade: " + aluno.get("idade"));
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    // Listar todos os alunos cadastrados
    public static void listarTodosAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("\nLista de alunos cadastrados:");
        for (Map<String, Object> aluno : alunos) {
            System.out.println("Nome: " + aluno.get("nome") + ", Idade: " + aluno.get("idade") + ", Matrícula: " + aluno.get("matricula"));
        }
    }

    // Remover um aluno pela matrícula
    public static void removerPorMatricula(Scanner scanner) {
        System.out.println("Digite a matrícula do aluno que deseja remover:");
        String matricula = scanner.nextLine();

        if (matriculaMap.containsKey(matricula)) {
            int indice = matriculaMap.get(matricula);
            alunos.remove(indice);  // Remove o aluno da lista
            matriculaMap.remove(matricula);  // Remove a matrícula do Map

            // Atualiza os índices do Map após a remoção
            for (int i = indice; i < alunos.size(); i++) {
                String matriculaAtualizada = (String) alunos.get(i).get("matricula");
                matriculaMap.put(matriculaAtualizada, i);
            }

            System.out.println("Aluno removido com sucesso!");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }
}


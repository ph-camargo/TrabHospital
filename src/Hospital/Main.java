package Hospital;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Main {
    private ArrayList<Paciente> pacientes;
    private Scanner scanner;

    public Main() {
        pacientes = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void incluirPaciente() {
        System.out.println("Insira o nome do paciente:");
        String nome = scanner.nextLine();
        System.out.println("Insira o sobrenome do paciente:");
        String sobrenome = scanner.nextLine();
        System.out.println("Insira a data de nascimento (dd/MM/yyyy):");
        String dataNascimento = scanner.nextLine();
        LocalDate data = LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setSobrenome(sobrenome);
        paciente.setDataNascimento(data);
        pacientes.add(paciente);

        System.out.println("Paciente incluído com sucesso.");
    }

    public void alterarPaciente() {
        System.out.println("Insira o nome do paciente que deseja alterar:");
        String nome = scanner.nextLine();
        Paciente paciente = buscarPaciente(nome);

        if (paciente != null) {
            System.out.println("Insira o novo nome:");
            paciente.setNome(scanner.nextLine());
            System.out.println("Insira o novo sobrenome:");
            paciente.setSobrenome(scanner.nextLine());
            System.out.println("Dados alterados com sucesso.");
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    public void realizarAtendimento() {
        System.out.println("Insira o nome do paciente que será atendido:");
        String nome = scanner.nextLine();
        Paciente paciente = buscarPaciente(nome);

        if (paciente != null) {
            System.out.println("Descreva o atendimento:");
            String descricao = scanner.nextLine();
            Atendimento atendimento = new Atendimento();
            atendimento.setDescricao(descricao);
            atendimento.setData(LocalDate.now());

            paciente.adicionarConsulta(atendimento);
            System.out.println("Atendimento realizado com sucesso.");
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    public void listarPacientes() {
        System.out.println("Lista de pacientes ativos:");
        for (Paciente paciente : pacientes) {
            if (paciente.isAtivo()) {
                System.out.println(paciente.getNome() + " " + paciente.getSobrenome());
            }
        }
    }

    public void mostrarPaciente() {
        System.out.println("Insira o nome do paciente:");
        String nome = scanner.nextLine();
        Paciente paciente = buscarPaciente(nome);

        if (paciente != null) {
            System.out.println(paciente.toString());
            ArrayList<Atendimento> atendimentos = paciente.getAtendimentos();
            for (int i = 0; i < atendimentos.size(); i += 5) {
                for (int j = i; j < i + 5 && j < atendimentos.size(); j++) {
                    System.out.println(atendimentos.get(j));
                }
                if (i + 5 < atendimentos.size()) {
                    System.out.println("Pressione Enter para continuar...");
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    public void apagarPaciente() {
        System.out.println("Insira o nome do paciente que deseja apagar:");
        String nome = scanner.nextLine();
        Paciente paciente = buscarPaciente(nome);

        if (paciente != null) {
            paciente.desativar();
            System.out.println("Paciente removido com sucesso.");
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    private Paciente buscarPaciente(String nome) {
        for (Paciente paciente : pacientes) {
            if (paciente.getNome().equalsIgnoreCase(nome)) {
                return paciente;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Main clinica = new Main();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Menu:");
            System.out.println("1. Incluir Paciente");
            System.out.println("2. Alterar Paciente");
            System.out.println("3. Realizar Atendimento");
            System.out.println("4. Listar Pacientes");
            System.out.println("5. Mostrar Paciente");
            System.out.println("6. Apagar Paciente");
            System.out.println("0. Sair");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a quebra de linha

            switch (opcao) {
                case 1:
                    clinica.incluirPaciente();
                    break;
                case 2:
                    clinica.alterarPaciente();
                    break;
                case 3:
                    clinica.realizarAtendimento();
                    break;
                case 4:
                    clinica.listarPacientes();
                    break;
                case 5:
                    clinica.mostrarPaciente();
                    break;
                case 6:
                    clinica.apagarPaciente();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
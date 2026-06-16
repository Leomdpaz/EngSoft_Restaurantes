import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    static Scanner entrada = new Scanner(System.in);

    static ReservaDAO reservaDAO = new ReservaDAO();
    static EspacoDAO espacoDAO = new EspacoDAO();

    static String nomeClienteLogado = "";

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== SISTEMA DE RESERVAS =====");
            System.out.println("1 - Cliente");
            System.out.println("2 - Funcionário");
            System.out.println("0 - Sair");

            int opcao =
                    EntradaUtil.lerInteiro(
                            entrada,
                            "Opção: ");

            switch (opcao) {

                case 1:
                    menuCliente();
                    break;

                case 2:
                    loginFuncionario();
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void menuCliente() {

        nomeClienteLogado =
                EntradaUtil.lerTextoObrigatorio(
                        entrada,
                        "Informe seu nome: ");

        while (true) {

            System.out.println("\n===== CLIENTE =====");
            System.out.println("1 - Criar reserva");
            System.out.println("2 - Alterar minha reserva");
            System.out.println("3 - Cancelar minha reserva");
            System.out.println("4 - Minhas reservas");
            System.out.println("0 - Voltar");

            int opcao =
                    EntradaUtil.lerInteiro(
                            entrada,
                            "Opção: ");

            switch (opcao) {

                case 1:
                    criarReserva();
                    break;

                case 2:
                    alterarReserva();
                    break;

                case 3:
                    cancelarReserva();
                    break;

                case 4:
                    reservaDAO.listarReservasCliente(
                            nomeClienteLogado);
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void loginFuncionario() {

        System.out.println("\n1 - Administrador");
        System.out.println("2 - Recepção");
        System.out.println("3 - Cozinha");

        int perfil =
                EntradaUtil.lerInteiro(
                        entrada,
                        "Perfil: ");

        String senha =
                EntradaUtil.lerTextoObrigatorio(
                        entrada,
                        "Senha: ");

        if (!senha.equals("123")) {

            System.out.println("Senha incorreta.");
            return;
        }

        switch (perfil) {

            case 1:
                menuAdministrador();
                break;

            case 2:
                menuRecepcao();
                break;

            case 3:
                menuCozinha();
                break;

            default:
                System.out.println("Perfil inválido.");
        }
    }

    private static void menuRecepcao() {

        while (true) {

            System.out.println("\n===== RECEPÇÃO =====");
            System.out.println("1 - Criar reserva");
            System.out.println("2 - Alterar reserva");
            System.out.println("3 - Cancelar reserva");
            System.out.println("4 - Ver reservas");
            System.out.println("0 - Voltar");

            int opcao =
                    EntradaUtil.lerInteiro(
                            entrada,
                            "Opção: ");

            switch (opcao) {

                case 1:
                    criarReserva();
                    break;

                case 2:
                    alterarReserva();
                    break;

                case 3:
                    cancelarReserva();
                    break;

                case 4:
                    reservaDAO.listarReservas();
                    break;

                case 0:
                    return;
            }
        }
    }

    private static void menuCozinha() {

        while (true) {

            System.out.println("\n===== COZINHA =====");
            System.out.println("1 - Ver requerimentos especiais");
            System.out.println("0 - Voltar");

            int opcao =
                    EntradaUtil.lerInteiro(
                            entrada,
                            "Opção: ");

            switch (opcao) {

                case 1:
                    reservaDAO.listarRequerimentos();
                    break;

                case 0:
                    return;
            }
        }
    }

    private static void criarReserva() {

        String cliente =
                EntradaUtil.lerTextoObrigatorio(
                        entrada,
                        "Cliente: ");

        int quantidade =
                EntradaUtil.lerInteiroPositivo(
                        entrada,
                        "Quantidade de pessoas: ");

        System.out.println("\nTipo:");
        System.out.println("1 - Mesa");
        System.out.println("2 - Sala");

        int tipoEscolha =
                EntradaUtil.lerInteiro(
                        entrada,
                        "Opção: ");

        String tipoEspaco =
                (tipoEscolha == 1)
                        ? "MESA"
                        : "SALA";

        System.out.println("\nEspaços disponíveis:");

        Espaco espaco =
                espacoDAO.buscarMenorEspaco(
                        tipoEspaco,
                        quantidade);

        if (espaco == null) {

            System.out.println(
                    "\nNenhum espaço possui capacidade suficiente.");

            return;
        }

        System.out.println(
                "\nEspaço atribuído automaticamente:");

        System.out.println(
                "Nome: " +
                        espaco.getNome());

        System.out.println(
                "Capacidade: " +
                        espaco.getCapacidade());

        int espacoId =
                espaco.getId();

        String data =
                EntradaUtil.lerData(
                        entrada,
                        "Data (DD-MM-AAAA): ");

        String horarioInicio =
                EntradaUtil.lerHorario(
                        entrada,
                        "Horário (HH:mm): ");

        LocalTime fim =
                LocalTime.parse(
                                horarioInicio)
                        .plusHours(4);

        String horarioFim =
                fim.toString();

        String requerimentos =
                EntradaUtil.lerTextoObrigatorio(
                        entrada,
                        "Requerimentos especiais (ou Nenhum): ");

        Reserva reserva =
                new Reserva(
                        cliente,
                        quantidade,
                        tipoEspaco,
                        espacoId,
                        data,
                        horarioInicio,
                        horarioFim,
                        requerimentos
                );

        reservaDAO.inserir(reserva);
    }

    private static void alterarReserva() {

        int id =
                EntradaUtil.lerInteiro(
                        entrada,
                        "ID da reserva: ");

        String cliente =
                EntradaUtil.lerTextoObrigatorio(
                        entrada,
                        "Cliente: ");

        int quantidade =
                EntradaUtil.lerInteiroPositivo(
                        entrada,
                        "Quantidade de pessoas: ");

        System.out.println("\nTipo:");
        System.out.println("1 - Mesa");
        System.out.println("2 - Sala");

        int tipoEscolha =
                EntradaUtil.lerInteiro(
                        entrada,
                        "Opção: ");

        String tipoEspaco =
                (tipoEscolha == 1)
                        ? "MESA"
                        : "SALA";

        espacoDAO.listarPorTipo(tipoEspaco)
                .forEach(System.out::println);

        int espacoId =
                EntradaUtil.lerInteiro(
                        entrada,
                        "ID do espaço: ");

        String data =
                EntradaUtil.lerData(
                        entrada,
                        "Data (AAAA-MM-DD): ");

        String horarioInicio =
                EntradaUtil.lerHorario(
                        entrada,
                        "Horário (HH:mm): ");

        String horarioFim =
                LocalTime.parse(horarioInicio)
                        .plusHours(4)
                        .toString();

        String requerimentos =
                EntradaUtil.lerTextoObrigatorio(
                        entrada,
                        "Requerimentos: ");

        Reserva reserva =
                new Reserva(
                        cliente,
                        quantidade,
                        tipoEspaco,
                        espacoId,
                        data,
                        horarioInicio,
                        horarioFim,
                        requerimentos
                );

        reservaDAO.alterarReserva(
                id,
                reserva);
    }

    private static void cancelarReserva() {

        int id =
                EntradaUtil.lerInteiro(
                        entrada,
                        "ID da reserva: ");

        reservaDAO.cancelarReserva(id);
    }

    private static void menuAdministrador() {

        while (true) {

            System.out.println(
                    "\n===== ADMINISTRADOR =====");

            System.out.println(
                    "1 - Criar reserva");

            System.out.println(
                    "2 - Alterar reserva");

            System.out.println(
                    "3 - Cancelar reserva");

            System.out.println(
                    "4 - Ver reservas");

            System.out.println(
                    "5 - Ver espaços");

            System.out.println(
                    "6 - Criar espaço");

            System.out.println(
                    "7 - Alterar capacidade");

            System.out.println(
                    "8 - Remover espaço");

            System.out.println(
                    "9 - Relatório");

            System.out.println(
                    "0 - Voltar");

            int opcao =
                    EntradaUtil.lerInteiro(
                            entrada,
                            "Opção: ");

            switch (opcao) {

                case 1:

                    criarReserva();
                    break;

                case 2:

                    alterarReserva();
                    break;

                case 3:

                    cancelarReserva();
                    break;

                case 4:

                    reservaDAO.listarReservas();
                    break;

                case 5:

                    espacoDAO.listarEspacos();
                    break;

                case 6:

                    criarEspaco();
                    break;

                case 7:

                    alterarCapacidade();
                    break;

                case 8:

                    removerEspaco();
                    break;

                case 9:

                    gerarRelatorio();
                    break;

                case 0:

                    return;

                default:

                    System.out.println(
                            "Opção inválida.");
            }
        }
    }

    private static void criarEspaco() {

        String nome =
                EntradaUtil.lerTextoObrigatorio(
                        entrada,
                        "Nome do espaço: ");

        System.out.println(
                "\n1 - Mesa");

        System.out.println(
                "2 - Sala");

        int opcao =
                EntradaUtil.lerInteiro(
                        entrada,
                        "Tipo: ");

        String tipo =
                (opcao == 1)
                        ? "MESA"
                        : "SALA";

        int capacidade =
                EntradaUtil.lerInteiroPositivo(
                        entrada,
                        "Capacidade: ");

        Espaco espaco =
                new Espaco(
                        nome,
                        tipo,
                        capacidade
                );

        espacoDAO.adicionarEspaco(
                espaco);
    }

    private static void alterarCapacidade() {

        espacoDAO.listarEspacos();

        int id =
                EntradaUtil.lerInteiro(
                        entrada,
                        "ID do espaço: ");

        int capacidade =
                EntradaUtil.lerInteiroPositivo(
                        entrada,
                        "Nova capacidade: ");

        espacoDAO.alterarCapacidade(
                id,
                capacidade);
    }

    private static void removerEspaco() {

        espacoDAO.listarEspacos();

        int id =
                EntradaUtil.lerInteiro(
                        entrada,
                        "ID do espaço: ");

        espacoDAO.removerEspaco(id);
    }

    private static void gerarRelatorio() {

        String inicio =
                EntradaUtil.lerData(
                        entrada,
                        "Data inicial (AAAA-MM-DD): ");

        String fim =
                EntradaUtil.lerData(
                        entrada,
                        "Data final (AAAA-MM-DD): ");

        reservaDAO.relatorioPeriodo(
                inicio,
                fim);
    }
}
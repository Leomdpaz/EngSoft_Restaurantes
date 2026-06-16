import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class EntradaUtil {

    private static final DateTimeFormatter DATA_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static int lerInteiro(
            Scanner scanner,
            String mensagem) {

        while (true) {

            try {

                System.out.print(mensagem);

                return Integer.parseInt(
                        scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println(
                        "Erro: Digite apenas números.");
            }
        }
    }

    public static int lerInteiroPositivo(
            Scanner scanner,
            String mensagem) {

        while (true) {

            try {

                System.out.print(mensagem);

                int valor =
                        Integer.parseInt(
                                scanner.nextLine());

                if (valor <= 0) {

                    System.out.println(
                            "Erro: Digite um valor maior que zero.");

                    continue;
                }

                return valor;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Erro: Digite apenas números.");
            }
        }
    }

    public static String lerTextoObrigatorio(
            Scanner scanner,
            String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String texto =
                    scanner.nextLine().trim();

            if (!texto.isEmpty()) {

                return texto;
            }

            System.out.println(
                    "Erro: Campo obrigatório.");
        }
    }

    public static String lerData(
            Scanner scanner,
            String mensagem) {

        while (true) {

            try {

                System.out.print(mensagem);

                String data =
                        scanner.nextLine().trim();

                LocalDate.parse(
                        data,
                        DATA_FORMATTER);

                return data;

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Data inválida. Use DD-MM-AAAA.");
            }
        }
    }

    public static String lerHorario(
            Scanner scanner,
            String mensagem) {

        while (true) {

            try {

                System.out.print(mensagem);

                String horario =
                        scanner.nextLine().trim();

                LocalTime.parse(horario);

                return horario;

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Horário inválido. Use HH:mm.");
            }
        }
    }

    public static LocalDate converterData(
            String data) {

        return LocalDate.parse(
                data,
                DATA_FORMATTER);
    }
}
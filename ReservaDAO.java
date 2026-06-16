import java.sql.*;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservaDAO {

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern(
                    "dd-MM-yyyy");

    public boolean verificarDisponibilidade(
            int espacoId,
            String data,
            String horarioInicio) {

        try {

            LocalTime inicio =
                    LocalTime.parse(horarioInicio);

            LocalTime fim =
                    inicio.plusHours(4);

            String sql =
                    """
                    SELECT *
                    FROM reservas
                    WHERE espaco_id = ?
                    AND data_reserva = ?
                    AND (
                        horario_inicio < ?
                        AND horario_fim > ?
                    )
                    """;

            Connection con =
                    Conexao.conectar();

            PreparedStatement stmt =
                    con.prepareStatement(sql);

            stmt.setInt(1, espacoId);
            LocalDate dataReserva =
                    LocalDate.parse(
                            data,
                            formatter);

            stmt.setDate(
                    2,
                    Date.valueOf(dataReserva));
            stmt.setTime(3, Time.valueOf(fim));
            stmt.setTime(4, Time.valueOf(inicio));

            ResultSet rs =
                    stmt.executeQuery();

            boolean livre =
                    !rs.next();

            rs.close();
            stmt.close();
            con.close();

            return livre;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public boolean verificarDisponibilidadeAlteracao(
            int reservaId,
            int espacoId,
            String data,
            String horarioInicio) {

        try {

            LocalTime inicio =
                    LocalTime.parse(horarioInicio);

            LocalTime fim =
                    inicio.plusHours(4);

            String sql =
                    """
                    SELECT *
                    FROM reservas
                    WHERE espaco_id = ?
                    AND data_reserva = ?
                    AND id <> ?
                    AND (
                        horario_inicio < ?
                        AND horario_fim > ?
                    )
                    """;

            Connection con =
                    Conexao.conectar();

            PreparedStatement stmt =
                    con.prepareStatement(sql);

            stmt.setInt(1, espacoId);
            LocalDate dataReserva =
                    LocalDate.parse(
                            data,
                            formatter);

            stmt.setDate(
                    2,
                    Date.valueOf(dataReserva));
            stmt.setInt(3, reservaId);
            stmt.setTime(4, Time.valueOf(fim));
            stmt.setTime(5, Time.valueOf(inicio));

            ResultSet rs =
                    stmt.executeQuery();

            boolean livre =
                    !rs.next();

            rs.close();
            stmt.close();
            con.close();

            return livre;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public void inserir(Reserva reserva) {

        String sql =
                """
                INSERT INTO reservas(
                cliente,
                quantidade_pessoas,
                tipo_espaco,
                espaco_id,
                data_reserva,
                horario_inicio,
                horario_fim,
                requerimentos)
                VALUES(?,?,?,?,?,?,?,?)
                """;

        try {

            if (!verificarDisponibilidade(
                    reserva.getEspacoId(),
                    reserva.getDataReserva(),
                    reserva.getHorarioInicio())) {

                System.out.println(
                        "\nERRO: Espaço já reservado nesse horário.");

                return;
            }

            Connection con =
                    Conexao.conectar();

            PreparedStatement stmt =
                    con.prepareStatement(sql);

            stmt.setString(1,
                    reserva.getCliente());

            stmt.setInt(2,
                    reserva.getQuantidadePessoas());

            stmt.setString(3,
                    reserva.getTipoEspaco());

            stmt.setInt(4,
                    reserva.getEspacoId());

            LocalDate data =
                    LocalDate.parse(
                            reserva.getDataReserva(),
                            formatter);

            stmt.setDate(
                    5,
                    Date.valueOf(data));

            stmt.setTime(
                    6,
                    Time.valueOf(
                            reserva.getHorarioInicio() + ":00"));

            stmt.setTime(
                    7,
                    Time.valueOf(
                            reserva.getHorarioFim() + ":00"));

            stmt.setString(
                    8,
                    reserva.getRequerimentos());

            stmt.executeUpdate();

            stmt.close();
            con.close();

            System.out.println(
                    "\nReserva criada com sucesso!");

        } catch (Exception e) {

            System.out.println(
                    "\nErro ao criar reserva.");

            e.printStackTrace();
        }
    }

    public void alterarReserva(
            int id,
            Reserva reserva) {

        try {

            if (!verificarDisponibilidadeAlteracao(
                    id,
                    reserva.getEspacoId(),
                    reserva.getDataReserva(),
                    reserva.getHorarioInicio())) {

                System.out.println(
                        "\nERRO: Espaço já reservado nesse horário.");

                return;
            }

            String sql =
                    """
                    UPDATE reservas
                    SET cliente=?,
                        quantidade_pessoas=?,
                        tipo_espaco=?,
                        espaco_id=?,
                        data_reserva=?,
                        horario_inicio=?,
                        horario_fim=?,
                        requerimentos=?
                    WHERE id=?
                    """;

            Connection con =
                    Conexao.conectar();

            PreparedStatement stmt =
                    con.prepareStatement(sql);

            stmt.setString(1,
                    reserva.getCliente());

            stmt.setInt(2,
                    reserva.getQuantidadePessoas());

            stmt.setString(3,
                    reserva.getTipoEspaco());

            stmt.setInt(4,
                    reserva.getEspacoId());

            LocalDate data =
                    LocalDate.parse(
                            reserva.getDataReserva(),
                            formatter);

            stmt.setDate(
                    5,
                    Date.valueOf(data));

            stmt.setTime(
                    6,
                    Time.valueOf(
                            reserva.getHorarioInicio() + ":00"));

            stmt.setTime(
                    7,
                    Time.valueOf(
                            reserva.getHorarioFim() + ":00"));

            stmt.setString(
                    8,
                    reserva.getRequerimentos());

            stmt.setInt(9, id);

            int linhas =
                    stmt.executeUpdate();

            if (linhas > 0) {

                System.out.println(
                        "\nReserva alterada com sucesso!");

            } else {

                System.out.println(
                        "\nReserva não encontrada.");
            }

            stmt.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void cancelarReserva(int id) {

        String sql =
                "DELETE FROM reservas WHERE id=?";

        try {

            Connection con =
                    Conexao.conectar();

            PreparedStatement stmt =
                    con.prepareStatement(sql);

            stmt.setInt(1, id);

            int linhas =
                    stmt.executeUpdate();

            if (linhas > 0) {

                System.out.println(
                        "\nReserva cancelada com sucesso!");

            } else {

                System.out.println(
                        "\nReserva não encontrada.");
            }

            stmt.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void listarReservas() {

        String sql =
                """
                SELECT r.*,
                       e.nome
                FROM reservas r
                INNER JOIN espacos e
                ON r.espaco_id = e.id
                ORDER BY data_reserva,
                         horario_inicio
                """;

        try {

            Connection con =
                    Conexao.conectar();

            PreparedStatement stmt =
                    con.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            System.out.println(
                    "\n===== RESERVAS =====");

            while (rs.next()) {

                System.out.println(
                        "\nID: " + rs.getInt("id") +
                                "\nCliente: " + rs.getString("cliente") +
                                "\nEspaço: " + rs.getString("nome") +
                                "\nData: " + rs.getDate("data_reserva") +
                                "\nHorário Início: " + rs.getTime("horario_inicio") +
                                "\nHorário Fim: " + rs.getTime("horario_fim") +
                                "\nPessoas: " + rs.getInt("quantidade_pessoas") +
                                "\nRequerimentos: " + rs.getString("requerimentos") +
                                "\n--------------------------------"
                );
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void listarReservasCliente(
            String cliente) {

        String sql =
                """
                SELECT r.*,
                       e.nome
                FROM reservas r
                INNER JOIN espacos e
                ON r.espaco_id = e.id
                WHERE r.cliente = ?
                ORDER BY data_reserva,
                         horario_inicio
                """;

        try {

            Connection con =
                    Conexao.conectar();

            PreparedStatement stmt =
                    con.prepareStatement(sql);

            stmt.setString(1, cliente);

            ResultSet rs =
                    stmt.executeQuery();

            System.out.println(
                    "\n===== MINHAS RESERVAS =====");

            while (rs.next()) {

                System.out.println(
                        "\nID: " + rs.getInt("id") +
                                "\nEspaço: " + rs.getString("nome") +
                                "\nData: " + rs.getDate("data_reserva") +
                                "\nHorário: " + rs.getTime("horario_inicio") +
                                "\n--------------------------"
                );
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void listarRequerimentos() {

        String sql =
                """
                SELECT r.id,
                       r.cliente,
                       e.nome,
                       r.requerimentos
                FROM reservas r
                INNER JOIN espacos e
                ON r.espaco_id = e.id
                ORDER BY r.data_reserva
                """;

        try {

            Connection con =
                    Conexao.conectar();

            PreparedStatement stmt =
                    con.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            System.out.println(
                    "\n===== REQUERIMENTOS ESPECIAIS =====");

            while (rs.next()) {

                System.out.println(
                        "\nReserva: " + rs.getInt("id") +
                                "\nCliente: " + rs.getString("cliente") +
                                "\nEspaço: " + rs.getString("nome") +
                                "\nExigências: " + rs.getString("requerimentos") +
                                "\n--------------------------------"
                );
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void relatorioPeriodo(
            String dataInicio,
            String dataFim) {

        String sql =
                """
                SELECT COUNT(*) total
                FROM reservas
                WHERE data_reserva
                BETWEEN ? AND ?
                """;

        try {

            Connection con =
                    Conexao.conectar();

            PreparedStatement stmt =
                    con.prepareStatement(sql);

            stmt.setDate(
                    1,
                    Date.valueOf(dataInicio));

            stmt.setDate(
                    2,
                    Date.valueOf(dataFim));

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {

                System.out.println(
                        "\n===== RELATÓRIO =====");

                System.out.println(
                        "Total de reservas: "
                                + rs.getInt("total"));
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
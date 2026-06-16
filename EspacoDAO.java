import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspacoDAO {

    public void listarEspacos() {

        String sql =
                "SELECT * FROM espacos ORDER BY tipo, capacidade";

        try (
                Connection con = Conexao.conectar();
                PreparedStatement stmt =
                        con.prepareStatement(sql);
                ResultSet rs =
                        stmt.executeQuery()
        ) {

            System.out.println(
                    "\n===== ESPAÇOS =====");

            while (rs.next()) {

                System.out.println(
                        "\nID: " + rs.getInt("id") +
                                "\nNome: " + rs.getString("nome") +
                                "\nTipo: " + rs.getString("tipo") +
                                "\nCapacidade: " + rs.getInt("capacidade") +
                                "\n------------------------"
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void adicionarEspaco(
            Espaco espaco) {

        String sql =
                """
                INSERT INTO espacos
                (nome,tipo,capacidade)
                VALUES(?,?,?)
                """;

        try (
                Connection con =
                        Conexao.conectar();

                PreparedStatement stmt =
                        con.prepareStatement(sql)
        ) {

            stmt.setString(
                    1,
                    espaco.getNome());

            stmt.setString(
                    2,
                    espaco.getTipo());

            stmt.setInt(
                    3,
                    espaco.getCapacidade());

            stmt.executeUpdate();

            System.out.println(
                    "\nEspaço criado com sucesso!");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void alterarCapacidade(
            int id,
            int novaCapacidade) {

        String sql =
                """
                UPDATE espacos
                SET capacidade=?
                WHERE id=?
                """;

        try (
                Connection con =
                        Conexao.conectar();

                PreparedStatement stmt =
                        con.prepareStatement(sql)
        ) {

            stmt.setInt(
                    1,
                    novaCapacidade);

            stmt.setInt(
                    2,
                    id);

            int linhas =
                    stmt.executeUpdate();

            if (linhas > 0) {

                System.out.println(
                        "\nCapacidade alterada!");

            } else {

                System.out.println(
                        "\nEspaço não encontrado.");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void removerEspaco(
            int id) {

        String sql =
                "DELETE FROM espacos WHERE id=?";

        try (
                Connection con =
                        Conexao.conectar();

                PreparedStatement stmt =
                        con.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            int linhas =
                    stmt.executeUpdate();

            if (linhas > 0) {

                System.out.println(
                        "\nEspaço removido.");

            } else {

                System.out.println(
                        "\nEspaço não encontrado.");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public Espaco buscarPorId(
            int id) {

        String sql =
                """
                SELECT *
                FROM espacos
                WHERE id=?
                """;

        try (
                Connection con =
                        Conexao.conectar();

                PreparedStatement stmt =
                        con.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {

                return new Espaco(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("tipo"),
                        rs.getInt("capacidade")
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    public List<Espaco> listarPorTipo(
            String tipo) {

        List<Espaco> lista =
                new ArrayList<>();

        String sql =
                """
                SELECT *
                FROM espacos
                WHERE tipo=?
                ORDER BY capacidade
                """;

        try (
                Connection con =
                        Conexao.conectar();

                PreparedStatement stmt =
                        con.prepareStatement(sql)
        ) {

            stmt.setString(
                    1,
                    tipo);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                lista.add(
                        new Espaco(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("tipo"),
                                rs.getInt("capacidade")
                        )
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return lista;
    }

    /*
       Procura o MENOR espaço capaz
       de acomodar a quantidade
       de pessoas.
    */
    public Espaco buscarMenorEspaco(
            String tipo,
            int quantidadePessoas) {

        String sql =
                """
                SELECT *
                FROM espacos
                WHERE tipo=?
                AND capacidade>=?
                ORDER BY capacidade ASC
                LIMIT 1
                """;

        try (
                Connection con =
                        Conexao.conectar();

                PreparedStatement stmt =
                        con.prepareStatement(sql)
        ) {

            stmt.setString(
                    1,
                    tipo);

            stmt.setInt(
                    2,
                    quantidadePessoas);

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {

                return new Espaco(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("tipo"),
                        rs.getInt("capacidade")
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    public boolean capacidadeValida(
            int espacoId,
            int quantidadePessoas) {

        Espaco espaco =
                buscarPorId(
                        espacoId);

        if (espaco == null) {

            return false;
        }

        return quantidadePessoas
                <= espaco.getCapacidade();
    }
}
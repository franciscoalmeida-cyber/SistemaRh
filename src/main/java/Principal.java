import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Principal {

    public static void main(String[] args) {
        // 1. Testando conexão e listando funcionários
        listarFuncionarios();
       // inserirFuncionario(8, "Carlos Eduardo", "1992-04-18", 3, 3, 8500.00);

        // 2. Exemplo de inserção de um novo registro
        // inserirFuncionario(7, "Carlos Eduardo", "1990-08-15", 3, 3, 8500.00);
    }

    public static void listarFuncionarios() {
        String sql = """
            SELECT f.matricula, f.funcionario, f.dt_nascimento, 
                   s.setor, c.cargo, f.salario
            FROM tb_funcionario f
            INNER JOIN tb_setor s ON f.cd_setor = s.cd_setor
            INNER JOIN tb_cargo c ON f.cd_cargo = c.cd_cargo
            ORDER BY f.matricula
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("=== LISTA DE FUNCIONÁRIOS ===");
            while (rs.next()) {
                int matricula = rs.getInt("matricula");
                String nome = rs.getString("funcionario").trim();
                Date nascimento = rs.getDate("dt_nascimento");
                String setor = rs.getString("setor").trim();
                String cargo = rs.getString("cargo").trim();
                double salario = rs.getDouble("salario");

                System.out.printf("Matrícula: %d | Nome: %s | Nasc: %s | Setor: %s | Cargo: %s | Salário: R$ %.2f%n",
                        matricula, nome, nascimento, setor, cargo, salario);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao consultar o banco: " + e.getMessage());
        }
    }

    public static void inserirFuncionario(int matricula, String nome, String dataNasc, int cdSetor, int cdCargo, double salario) {
        String sql = "INSERT INTO tb_funcionario (matricula, funcionario, dt_nascimento, cd_setor, cd_cargo, salario) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matricula);
            stmt.setString(2, nome);
            stmt.setDate(3, Date.valueOf(dataNasc)); // Formato esperado: YYYY-MM-DD
            stmt.setInt(4, cdSetor);
            stmt.setInt(5, cdCargo);
            stmt.setDouble(6, salario);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Funcionário inserido com sucesso!");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir funcionário: " + e.getMessage());
        }
    }
}
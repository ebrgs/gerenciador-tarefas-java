import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TarefaDAO {

    public void adicionarTarefa(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas (descricao, concluida) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tarefa.getDescricao());
            stmt.setBoolean(2, tarefa.isConcluida());

            stmt.execute();
            System.out.println("Tarefa salva no banco de dados!");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar tarefa: " + e.getMessage());
        }
    }

    public ArrayList<Tarefa> listarTarefas() {
        ArrayList<Tarefa> lista = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                boolean concluida = rs.getBoolean("concluida");

                Tarefa t = new Tarefa(descricao, concluida);
                t.setId(id);

                lista.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar tarefas: " + e.getMessage());
        }

        return lista;
    }

    public void concluirTarefa(int id) {
        String sql = "UPDATE tarefas SET concluida = true WHERE id = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Tarefa numero: " + id + " marcada como concluida");
            } else {
                System.out.println("Nenhuma tarefa encontrada com o ID -> " + id);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar tarefa: " + e.getMessage());
        }
    }

    public void removerTarefa(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Tarefa numero: " + id + " excluida");
            } else {
                System.out.println("Nenhuma tarefa encontrada com o ID -> " + id);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir tarefa: " + e.getMessage());
        }
    }
}

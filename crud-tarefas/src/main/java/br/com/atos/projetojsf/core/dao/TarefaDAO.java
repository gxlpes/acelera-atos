package br.com.atos.projetojsf.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.atos.projetojsf.core.dao.conexao.ConexaoJDBC;
import br.com.atos.projetojsf.core.entity.Tarefa;

public class TarefaDAO {
    
    public void cadastrarTarefa(Tarefa tarefa) {
        try (Connection conn = ConexaoJDBC.getConnection()) {
            String sql = "INSERT INTO tarefas (titulo, descricao) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tarefa> listarTodasTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        try (Connection conn = ConexaoJDBC.getConnection()) {
            String sql = "SELECT * FROM tarefas";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getLong("id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarefas;
    }

    public void excluirTarefa(Long id) {
        try (Connection conn = ConexaoJDBC.getConnection()) {
            String sql = "DELETE FROM tarefas WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterarTarefa(Tarefa tarefa) {
        try (Connection conn = ConexaoJDBC.getConnection()) {
            String sql = "UPDATE tarefas SET titulo = ?, descricao = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setLong(3, tarefa.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

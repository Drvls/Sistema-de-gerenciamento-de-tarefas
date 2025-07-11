package org.alexvsi.dao;

import org.alexvsi.enums.Coluna;
import org.alexvsi.enums.Prioridade;
import org.alexvsi.enums.StatusTarefa;
import org.alexvsi.model.Tarefa;
import org.alexvsi.db.DB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {
    public static void adicionarTarefa(Tarefa tarefa){
        String sql = "INSERT INTO tarefas "
                + "(TarefaId, Titulo, Descrição, Prioridade, DataLimite, Status) "
                + "VALUES "
                + "(?, ?, ?, ?, ?, ?)";
        try(
                Connection connection = DB.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                ){
            st.setInt(1, tarefa.getId());
            st.setString(2, tarefa.getTitulo());
            st.setString(3, tarefa.getDescricao());
            st.setString(4, tarefa.getPrioridade().name());
            st.setDate(5, Date.valueOf(tarefa.getDataLimite()));
            st.setString(6, tarefa.getStatus().name());

            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static List<Tarefa> listarTarefas(){
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";

        try(
                Connection connection = DB.getConnection();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                ){
            while(rs.next()){
                tarefas.add(new Tarefa(rs.getInt("TarefaID"),
                        rs.getString("Titulo"),
                        rs.getString("Descrição"),
                        rs.getString("Prioridade"),
                        rs.getDate("DataLimite").toLocalDate(),
                        rs.getString("status")
                ));
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return tarefas;
    }

    public static void removerTarefa(int id){
        String sql = "DELETE FROM tarefas WHERE TarefaId = ?";

        try(
                Connection connection = DB.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                ){
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void concluirTarefa(int id){
        String sql = "UPDATE tarefas SET status = ? WHERE TarefaID = ?";

        try(
                Connection connection = DB.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                ){
            st.setString(1, StatusTarefa.CONCLUIDO.name());
            st.setInt(2, id);
            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static int[] mostrarEstatisticas(){
        String sql = "SELECT " +
                "COUNT(status) as Total, " +
                "COUNT(CASE WHEN status = 'concluido' THEN 1 END) as Completo, " +
                "COUNT(CASE WHEN status = 'pendente' THEN 1 END) as Incompleto, " +
                "COUNT(CASE WHEN Prioridade = 'ALTA' THEN 1 END) as AltaPrioridade " +
                "FROM tarefas";

        int[] dados = new int[4];
        try(
                Connection connection = DB.getConnection();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql);
                ){
            while(rs.next()){
                dados[0] = rs.getInt("Total");
                dados[1] = rs.getInt("Completo");
                dados[2] = rs.getInt("Incompleto");
                dados[3] = rs.getInt("AltaPrioridade");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return dados;
    }

    public static boolean constaNoBD(int id){
        String sql = "SELECT 1 FROM tarefas WHERE TarefaID = ?";
        try(
                Connection connection = DB.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)
                ){
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            return rs.next();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static void atualizarTitulo(String titulo, int id) {
        String sql = "UPDATE tarefas SET Titulo = ? WHERE TarefaID = ?";

        try (
                Connection connection = DB.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                ){
            st.setString(1, titulo);
            st.setInt(2, id);

            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void atualizarDescricao(String descricao, int id) {
        String sql = "UPDATE tarefas SET Descrição = ? WHERE TarefaID = ?";

        try (
                Connection connection = DB.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                ){
            st.setString(1, descricao);
            st.setInt(2, id);

            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void atualizarPrioridade(Prioridade prioridade, int id) {
        String sql = "UPDATE tarefas SET Prioridade = ? WHERE TarefaID = ?";
        try (
                Connection connection = DB.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                ){
            st.setString(1, prioridade.name());
            st.setInt(2, id);

            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void atualizarDataLimite(LocalDate data, int id) {
        String sql = "UPDATE tarefas SET DataLimite = ? WHERE TarefaID = ?";

        try (
                Connection connection = DB.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                ){
            st.setDate(1, Date.valueOf(data));
            st.setInt(2, id);

            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void atualizar(Coluna coluna, Object valor, int id) {
        String sql = "UPDATE tarefas SET " + coluna.getDescricao() + " = ? WHERE TarefaID = ?";

        try (
                Connection connection = DB.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
        ) {
            if (valor instanceof String) {
                st.setString(1, (String) valor);
            }
            else if(valor instanceof Integer){
                st.setInt(1, (int) valor);
            }
            else if(valor instanceof LocalDate){
                st.setDate(1, Date.valueOf((LocalDate) valor));
            }
            else if(valor instanceof Enum<?>){
                st.setString(1, valor.toString());
            }

            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

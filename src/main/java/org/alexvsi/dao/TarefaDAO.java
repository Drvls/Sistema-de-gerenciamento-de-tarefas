package org.alexvsi.dao;

import org.alexvsi.enums.Coluna;
import org.alexvsi.enums.Status;
import org.alexvsi.model.Tarefa;
import org.alexvsi.db.DB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {
    public static void adicionarTarefa(Tarefa tarefa){
        String sql = "INSERT INTO tarefas "
                + "( Titulo, Descrição, Prioridade, DataLimite, Status) "
                + "VALUES "
                + "(?, ?, ?, ?, ?)";
        try(
                Connection connection = DB.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)
                ){
            st.setString(1, tarefa.getTitulo());
            st.setString(2, tarefa.getDescricao());
            st.setString(3, tarefa.getPrioridade().getDescricao());
            st.setDate(4, Date.valueOf(tarefa.getDataLimite()));
            st.setString(5, tarefa.getStatus().getDescricao());

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
                ResultSet rs = st.executeQuery(sql)
                ){
            while(rs.next()){
                tarefas.add(new Tarefa(rs.getInt("TarefaID"),
                        rs.getString("Titulo"),
                        rs.getString("Descrição"),
                        rs.getString("Prioridade"),
                        rs.getDate("DataLimite").toLocalDate(),
                        rs.getString("Status")
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
                PreparedStatement st = connection.prepareStatement(sql)
                ){
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void concluirTarefa(int id){
        String sql = "UPDATE tarefas SET Status = ? WHERE TarefaID = ?";

        try(
                Connection connection = DB.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)
                ){
            st.setString(1, Status.CONCLUIDO.getDescricao());
            st.setInt(2, id);
            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static int[] mostrarEstatisticas(){
        String sql = "SELECT " +
                "COUNT(Status) as Total, " +
                "COUNT(CASE WHEN Status = 'Concluído' THEN 1 END) as Completo, " +
                "COUNT(CASE WHEN Status = 'Pendente' THEN 1 END) as Incompleto, " +
                "COUNT(CASE WHEN Prioridade = 'Alta' THEN 1 END) as AltaPrioridade " +
                "FROM tarefas";

        int[] dados = new int[4];
        try(
                Connection connection = DB.getConnection();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(sql)
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

    // DRY
    public static void atualizar(Coluna coluna, Object valor, int id) {
        String sql = "UPDATE tarefas SET " + coluna.getDescricao() + " = ? WHERE TarefaID = ?";

        try (
                Connection connection = DB.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)
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

package org.alexvsi.dao;

import org.alexvsi.enums.StatusTarefa;
import org.alexvsi.model.Tarefa;
import org.alexvsi.db.DB;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {
    public static void adicionarTarefa(Tarefa tarefa){
        Connection connection = null;
        PreparedStatement st = null;
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
        String sql = "INSERT INTO tarefas "
                + "(TarefaId, Titulo, Descrição, Prioridade, DataLimite, Status) "
                + "VALUES "
                + "(?, ?, ?, ?, ?, ?)";
        try{
            connection = DB.getConnection();
            st = connection.prepareStatement(sql);

            st.setInt(1, tarefa.getId());
            st.setString(2, tarefa.getTitulo());
            st.setString(3, tarefa.getDescricao());
            st.setString(4, tarefa.getPrioridade().name());
            st.setDate(5, java.sql.Date.valueOf(tarefa.getDataLimite()));
            st.setString(6, tarefa.getStatus().name());

            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static List<Tarefa> listarTarefas(){
        List<Tarefa> tarefas = new ArrayList<>();

        Connection connection = null;
        Statement st = null;
        String sql = "SELECT * FROM tarefas";

        try{
            connection = DB.getConnection();
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
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
        Connection connection = null;
        PreparedStatement st = null;
        String sql = "DELETE FROM tarefas WHERE TarefaId = ?";

        try{
            connection = DB.getConnection();
            st = connection.prepareStatement(sql);

            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void concluirTarefa(int id){
        Connection connection = null;
        PreparedStatement st = null;
        String sql = "UPDATE tarefas SET status = ? WHERE TarefaID = ?";

        try{
            connection = DB.getConnection();
            st = connection.prepareStatement(sql);

            st.setString(1, StatusTarefa.CONCLUIDO.name());
            st.setInt(2, id);
            st.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static int[] mostrarEstatisticas(){
        Connection connection = null;
        Statement st = null;
        String sql = "SELECT " +
                "COUNT(status) as Total, " +
                "COUNT(CASE WHEN status = 'concluido' THEN 1 END) as Completo, " +
                "COUNT(CASE WHEN status = 'pendente' THEN 1 END) as Incompleto, " +
                "COUNT(CASE WHEN Prioridade = 'ALTA' THEN 1 END) as AltaPrioridade " +
                "FROM tarefas";

        int[] dados = new int[4];
        try{
            connection = DB.getConnection();
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

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
}

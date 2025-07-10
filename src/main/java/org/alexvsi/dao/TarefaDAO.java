package org.alexvsi.dao;

import org.alexvsi.model.Tarefa;
import org.alexvsi.db.DB;

import java.sql.*;
import java.text.SimpleDateFormat;

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
            st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, tarefa.getId()-1);
            st.setString(2, tarefa.getTitulo());
            st.setString(3, tarefa.getDescricao());
            st.setString(4, tarefa.getPrioridade().name());
            st.setDate(5, java.sql.Date.valueOf(tarefa.getDataLimite()));
            st.setString(6, tarefa.getStatus().name());

            int infoUpdated = st.executeUpdate();

            if(infoUpdated > 0){
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()){
                    int i = rs.getInt(1);
                    System.out.println("Tarefa adicionada. ID da tarefa: " + i);
                }
            }
            else{
                System.out.println("Nenhuma info atualizada");
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeConnection();
        }
    }
}

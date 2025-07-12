package org.alexvsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DbSetup {
    public static void criarDb(){
        String sql = "CREATE DATABASE IF NOT EXISTS GerenciadorDeTarefas";
        try(
                Connection connection = DB.getRootConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                ){
            st.executeUpdate();
            System.out.println("DATABASE OK");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void criarTb(){
        String sql1 = "USE GerenciadorDeTarefas";
        String sql2 = "CREATE TABLE IF NOT EXISTS Tarefas(" +
                "TarefaID INT PRIMARY KEY AUTO_INCREMENT," +
                "Titulo VARCHAR(50) NOT NULL," +
                "Descrição VARCHAR(150)," +
                "Prioridade VARCHAR(5) NOT NULL," +
                "DataLimite DATE NOT NULL," +
                "Status VARCHAR(10) NOT NULL" +
                ")";
        try(
                Connection connection = DB.getRootConnection();
                PreparedStatement st = connection.prepareStatement(sql1);
                ){
            st.execute();
            st.execute(sql2);
            System.out.println("TABLE OK\n");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

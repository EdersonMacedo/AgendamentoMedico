package br.com.model.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
    private static final String STR_CONEXAO = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE = "agendamento_medico";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection getConnection()throws SQLException{
        try{
            return DriverManager.getConnection(STR_CONEXAO + DATABASE, USER, PASSWORD);
        }catch(Exception ex){
            throw new SQLException(ex.getMessage());
        }
    }

    public static void closeConnection (Connection con) throws SQLException{
        if(con!=null){
            con.close();
        }
    }
    
    public static void closeConnection(Connection con, Statement stmt)
    throws SQLException{
        if(stmt != null){
            stmt.close();
        }
        closeConnection(con);
    }
    public static void closeConnection(Connection con,Statement stmt,ResultSet rs)
            throws SQLException{
        if(rs != null){
            rs.close();
        }
        closeConnection(con, stmt);
    }
}
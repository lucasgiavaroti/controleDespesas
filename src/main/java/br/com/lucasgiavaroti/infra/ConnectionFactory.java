package br.com.lucasgiavaroti.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private ConnectionFactory() {
    };

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost/DespesasDB", "postgres", "postgres");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar-se ao Banco de Dados: "+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

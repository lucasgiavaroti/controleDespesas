package br.com.lucasgiavaroti.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private ConnectionFactory() {
    };

    public static Connection getConnection() {
        try {
//            System.out.println("Conectado ao banco de dados com sucesso!");
            System.out.println();
            return DriverManager.getConnection("jdbc:postgresql://localhost/DespesasDB", "postgres", "18072004");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar-se ao Banco de Dados: "+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

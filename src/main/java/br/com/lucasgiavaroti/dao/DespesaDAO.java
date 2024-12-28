package br.com.lucasgiavaroti.dao;

import br.com.lucasgiavaroti.infra.ConnectionFactory;
import br.com.lucasgiavaroti.model.Categoria;
import br.com.lucasgiavaroti.model.Despesa;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DespesaDAO implements IDespesaDAO{

    @Override
    public Despesa save(Despesa despesa) {
        try (Connection connection =  ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO Despesas (descricao, valor, data, categoria) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, despesa.getDescricao());
            ps.setDouble(2, despesa.getValor());
            ps.setDate(3, java.sql.Date.valueOf(despesa.getData()));
            ps.setString(4, despesa.getCategoria().toString());

            ps.executeUpdate();

            //recuperando o id gerado pelo postgresql
            ResultSet resultSet = ps.getGeneratedKeys();
            resultSet.next();

            Long generatedId = resultSet.getLong("id");
            despesa.setId(generatedId);

            System.out.print("Despesa salva com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
        return despesa;
    }

    @Override
    public Despesa update(Despesa despesa) {
        try(Connection connection =  ConnectionFactory.getConnection()){
            String sql = """
                    UPDATE Despesas 
                    SET descricao = ?, valor = ?, data = ?, categoria = ?
                    WHERE id = ?
                    """;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, despesa.getDescricao());
            ps.setDouble(2, despesa.getValor());
            ps.setDate(3, java.sql.Date.valueOf(despesa.getData()));
            ps.setString(4, despesa.getCategoria().toString());
            ps.setLong(5, despesa.getId());
            ps.executeUpdate();

            System.out.print("Despesa alterada com sucesso!");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return despesa;
    }

    @Override
    public void delete(Long id) {
        try(Connection connection =  ConnectionFactory.getConnection()){
            String sql = "DELETE FROM Despesas WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            System.out.print("Despesa excluída com sucesso!");
        }catch(SQLException e ){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Despesa> findAll() {
        String sql = "SELECT id, descricao, data, valor, categoria FROM Despesas";

        List<Despesa> despesas = new ArrayList<>();

        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                 Long id = rs.getLong("id");
                 String descricao = rs.getString("descricao");
                 LocalDate data = rs.getDate("data").toLocalDate();
                 Double valor = rs.getDouble("valor");
                 Categoria categoria = Categoria.valueOf(rs.getString("categoria"));

                 Despesa despesa = new Despesa(descricao, valor, data, categoria);
                 despesa.setId(id);

                 despesas.add(despesa);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return despesas;
    }

    @Override
    public Optional<Despesa> findById(Long id) {
        String sql = "SELECT id, descricao, data, valor, categoria FROM Despesas WHERE id = ?";

        Despesa despesa = null;

        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Long pKey = rs.getLong("id");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                Double valor = rs.getDouble("valor");
                Categoria categoria = Categoria.valueOf(rs.getString("categoria"));

                despesa = new Despesa(descricao, valor, data, categoria);
                despesa.setId(pKey);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(despesa);
    }

    @Override
    public List<Despesa> findByCategoria(Categoria categoria) {
        String sql = "SELECT id, descricao, data, valor, categoria FROM Despesas where CATEGORIA = ?";

        List<Despesa> despesas = new ArrayList<>();

        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, categoria.toString());

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Long id = rs.getLong("id");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                Double valor = rs.getDouble("valor");
                Categoria categoriaDb = Categoria.valueOf(rs.getString("categoria"));

                Despesa despesa = new Despesa(descricao, valor, data, categoriaDb);
                despesa.setId(id);

                despesas.add(despesa);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return despesas;
    }
}

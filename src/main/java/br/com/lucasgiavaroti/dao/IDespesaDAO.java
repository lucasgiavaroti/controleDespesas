package br.com.lucasgiavaroti.dao;

import br.com.lucasgiavaroti.model.Categoria;
import br.com.lucasgiavaroti.model.Despesa;

import java.util.List;
import java.util.Optional;

public interface IDespesaDAO {
    Despesa save (Despesa despesa);
    Despesa update  (Despesa despesa);
    void delete (Long id);
    List<Despesa> findAll();

    double valorTotal();

    Optional<Despesa> findById(Long id);
    List<Despesa> findByCategoria(Categoria categoria);
}

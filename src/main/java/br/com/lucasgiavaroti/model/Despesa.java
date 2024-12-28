package br.com.lucasgiavaroti.model;

import java.time.LocalDate;

public class Despesa {
    private long id;
    private String descricao;
    private LocalDate data;
    private double valor;
    private Categoria categoria;

    public Despesa() {}

    public Despesa(String descricao, double valor, LocalDate data, Categoria categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return String.format("""
                ID: %d
                Descrição: %s
                Data: %s
                Valor: R$%.2f
                Categoria: %s
                """, id, descricao, data, valor, categoria);
    }
}

package br.ucb.centralstore.models;

import br.ucb.centralstore.exceptions.NomeException;
import br.ucb.centralstore.exceptions.QuantidadeException;

public class Produto {
    private String nome;
    private int quantidade;
    private int id;

    public Produto(String nome, int quantidade, int id) throws NomeException, QuantidadeException {
        setNome(nome);
        setQuantidade(quantidade);
        setId(id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws NomeException {
        if(nome.length() == 0){
            throw new NomeException("Nome inválido! O nome não pode ser vazio!");
        }else{
            this.nome = nome;
        }
        
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) throws QuantidadeException {
        if(quantidade <= 0){
            throw new QuantidadeException("Quantidade inválida! A quantidade não pode ser menor ou igual a zero!");
        }
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return  "   Nome do produto: " + this.getNome() + "\n" + 
                "   Quantidade disponível em estoque: " + this.getQuantidade() + "\n" + 
                "   ID do produto: " + getId() + "\n";
    }
}
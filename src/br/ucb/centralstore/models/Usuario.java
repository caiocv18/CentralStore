package br.ucb.centralstore.models;

import java.util.ArrayList;

import br.ucb.centralstore.exceptions.IdException;
import br.ucb.centralstore.exceptions.NomeException;

public class Usuario {
    private String nome;
    private int id;
    private ArrayList<Produto> produtos;

    
    public Usuario(String nome, int id) throws NomeException {
        setNome(nome);
        setId(id);
        produtos = new ArrayList<Produto>();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto(int id) throws IdException {
        // for (int i = 0; i < produtos.size(); i++) {
        //     if(produtos.get(i).getId() == id){
        //         return produtos.get(i);
        //     }
        // }
        for (Produto produto : produtos) {
            if(produto.getId() == id){
                return produto;
            }
        }
        throw new IdException("Produto não encontrado. ID não existente");
    }

    public void adicionaProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public void listaProdutos(){
        System.out.println("Produtos anunciados pelo(a) Sr(a). " + this.getNome());
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }
    
    @Override
    public String toString(){
        return "Nome do(a) cliente: " + this.getNome() + "\n" +
        "ID do(a) cliente: " + this.getId();
    }

}
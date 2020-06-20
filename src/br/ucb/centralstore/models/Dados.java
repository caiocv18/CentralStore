package br.ucb.centralstore.models;

import java.util.ArrayList;

public class Dados {
    private ArrayList<Usuario> usuariosCadastrados;
    private ArrayList<Produto> produtosCadastrados;

    public Dados(){
        this.usuariosCadastrados = new ArrayList<Usuario>();
        this.produtosCadastrados = new ArrayList<Produto>();
    }

    public ArrayList<Usuario> getUsuariosCadastrados() {
        return usuariosCadastrados;
    }

    public void adicionaUsuario(Usuario usuario) {
        this.usuariosCadastrados.add(usuario);
    }

    public ArrayList<Produto> getProdutosCadastrados() {
        return produtosCadastrados;
    }

    public void adicionaProduto(Produto produto) {
        this.produtosCadastrados.add(produto);
    }
    
}
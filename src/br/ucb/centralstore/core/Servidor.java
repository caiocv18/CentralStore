package br.ucb.centralstore.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import br.ucb.centralstore.exceptions.IdException;
import br.ucb.centralstore.exceptions.NomeException;
import br.ucb.centralstore.exceptions.QuantidadeException;
import br.ucb.centralstore.jobs.ExitJob;
import br.ucb.centralstore.models.Dados;
import br.ucb.centralstore.models.Produto;
import br.ucb.centralstore.models.Usuario;
import br.ucb.centralstore.tools.View;

public class Servidor {
    static final int ID_MAX = 1000;
    static final int ID_MIN = 1;
    static Dados dados = new Dados();
    static String[] opcoes = { "Cadastro de produtos para venda", "Remoção de um produto à venda",
            "Listagem de todos os Produtos que estão à venda", "Chat de conversa" };

    public static void main(String[] args) throws IOException {

        System.out.println("Aguardando conexão com clientes...");
        ServerSocket ss = new ServerSocket(9806);

        ExitJob exitJob = new ExitJob(ss);
        exitJob.start();

        Socket socket = ss.accept();
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        exitJob.sockets.add(socket);

        String userName = input.readLine();
        String resposta = "END";

        if (nomeExistente(userName)) {
            resposta = "INVALID";
        } else {
            resposta = "VALID";
            System.out.println("Banco conectado com o usuário");
            try {
                cadastraUsuario(userName);

                menuInicial();
            } catch (Exception e) {
                View.mensagemDeErro(e.getMessage());
            }

        }
        output.println(resposta);

        System.out.println("Servidor desconectado...");
        socket.close();
        input.close();
        output.close();
        ss.close();

    }

    protected static void menuInicial() {
        while (true) {
            int opcao = 0;
            opcao = View.menuOpcoesInt("Selecione uma das opções", "Menu inicial da Central Store", opcoes);
            switch (opcao) {
                // Cadastro de produto
                case 0:
                    try {
                        cadastraProduto();
                    } catch (Exception e) {
                        View.mensagemDeErro(e.getMessage());
                    }
                    break;
                // Remoção de produto
                case 1:
                    try {
                        removeProduto();
                    } catch (Exception e) {
                        View.mensagemDeErro(e.getMessage());
                    }
                    break;
                // Listagem de produtos (todos e os anunciados pelo usuario)
                case 2:
                    listagemDeProdutos();
                    break;
                // Chat
                case 3:
                    break;
                default:
                    break;
            }
            if (opcao < 0) {
                break;
            }
        }
    }

    protected static boolean nomeExistente(String nome) {
        for (Usuario usuario : dados.getUsuariosCadastrados()) {
            if (usuario.getNome() == nome) {
                return true;
            }
        }
        return false;
    }

    protected static boolean idUsuarioExistente(int id) {
        for (Usuario usuario : dados.getUsuariosCadastrados()) {
            if (usuario.getId() == id) {
                return true;
            }
        }
        return false;
    }

    protected static boolean idProdutoExistente(int id) {
        for (Produto produto : dados.getProdutosCadastrados()) {
            if (produto.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static int inteiroAleatorio(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    protected static int geradorDeIdUsuario() {
        int id = 0;
        do {
            id = inteiroAleatorio(ID_MIN, ID_MAX);
        } while (idUsuarioExistente(id));
        return id;
    }

    protected static int geradorDeIdProduto() {
        int id = 0;
        do {
            id = inteiroAleatorio(ID_MIN, ID_MAX);
        } while (idProdutoExistente(id));
        return id;
    }

    protected static void cadastraUsuario(String nome) throws NomeException {
        Usuario usuario;
        usuario = new Usuario(nome, geradorDeIdUsuario());
        dados.adicionaUsuario(usuario);
        View.mensagem("Sr(a). " + usuario.getNome()
                + ".\nBem vindo ao programa de cadastro de produtos da CentralStore.\nSeu ID de usuário é: "
                + usuario.getId(), "Bem vindo! A CentralStore agradece! :)");
    }

    protected static void cadastraProduto() throws NomeException, QuantidadeException {
        String nomeDoProduto = "";
        int quantidade = 0;
        nomeDoProduto = View.inputString("Informe o nome do produto: ", "Cadastro de produto");
        quantidade = View.inputInt("Informe a quantidade disponível em estoque: ",
                "Cadastro de produto - " + nomeDoProduto);
        Produto produto;
        produto = new Produto(nomeDoProduto, quantidade, geradorDeIdProduto());
        dados.adicionaProduto(produto);
        View.mensagem(produto.toString(), "Produto Cadastrado com sucesso");
    }

    protected static void removeProduto() throws IdException {
        int id = 0;
        id = View.inputInt("Informe o ID do produto que deseja remover: ", "Remoção de produto");
        for (Produto produto : dados.getProdutosCadastrados()) {
            if (produto.getId() == id) {
                dados.getProdutosCadastrados().remove(produto);
                return;
            }
        }
        throw new IdException("ID inválido! Não há nenhum produto cadastrado com esse ID");
    }

    protected static void listagemDeProdutos() {
        String listagem = "";
        for (Produto produto : dados.getProdutosCadastrados()) {
            listagem = listagem + "\n\n" + produto.toString();
        }
        View.mensagem("Lista de produtos cadastrados: " + listagem, "Listagem de produtos");
    }
}
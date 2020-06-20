package br.ucb.centralstore.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import br.ucb.centralstore.tools.View;

public class Cliente {

    public static BufferedReader input;
    public static PrintWriter output;

    public static void main(String[] args) {
        Cliente cliente =  new Cliente();
        try {
            cliente.conectaComServidor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void conectaComServidor() throws UnknownHostException, IOException {
        Socket soc;
        soc = new Socket("localhost", 9806);

        input = new BufferedReader(new InputStreamReader(soc.getInputStream()));

        output = new PrintWriter(soc.getOutputStream(), true);

        String nomeDeUsuario = "";
        nomeDeUsuario = View.inputString("Digite um nome de usuário:", "Campo obrigatório");

        output.println(nomeDeUsuario);
        String resposta = input.readLine();

        while (true) {
            if (resposta.contains("INVALID")) {
                View.mensagemDeErro("Nome inválido, esse nome já existe no servidor");
                break;
            } else if (resposta.contains("VALID")) {
                continue;
            } else if (resposta.contains("END")) {
                break;
            }
        }

        soc.close();
    }
    
}
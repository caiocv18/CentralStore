package br.ucb.centralstore.jobs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ucb.centralstore.models.Chat;
public class ChatListener implements ActionListener{

    // Esse metodo ira ser acionado toda vez que eu clicar em enviar ou no meu teclado ENTER
    @Override
    public void actionPerformed(ActionEvent e) {
        // Aqui, eu envio o texto que esta na minha textField, digitada pelo usuário
        Chat.out.println(Chat.textField.getText());

        // Limpa o textField, ja que ja enviamos o texto acima
        Chat.textField.setText("");
    }
    
}
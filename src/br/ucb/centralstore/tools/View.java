package br.ucb.centralstore.tools;
import javax.swing.JOptionPane;

/**
 * View
 */
public class View {
    /*
     * metodo para utilizacao de caixa de dialogo com opcoes para serem selecionadas
     * e com retorno de valores inteiros, cada valor de retorno corresponde a
     * posicao da opcao selecionada (considerando uma array)
     */
    public static int menuOpcoesInt(String mensagem, String titulo, Object[] opcoes) {
        return JOptionPane.showOptionDialog(null, mensagem, titulo, JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, (Object[]) opcoes, opcoes);

    }

    // metodo para utilizacao de caixa de dialogo para visualização de mensagem
    // simples
    public static void mensagem(String mensagem, String titulo) {
        JOptionPane.showInternalMessageDialog(null, mensagem, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    // metodo para utilizacao de caixa de dialogo para visualização de mensagem de
    // erro
    public static void mensagemDeErro(String mensagem) {
        JOptionPane.showInternalMessageDialog(null, mensagem, "ERRO", JOptionPane.ERROR_MESSAGE);
    }

    // metodo para utilizacao de caixa de dialogo com retorno de String
    public static String inputString(String mensagem, String titulo) {
        return JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);
    }

    // metodo para utilizacao de caixa de dialogo com retorno de int
    public static Integer inputInt(String mensagem, String titulo) {
        return Integer.parseInt(JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE));
    }

    // metodo para utilizacao de caixa de dialogo com retorno de double
    public static Double inputDouble(String mensagem, String titulo) {
        return Double.parseDouble(JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE));
    }
}
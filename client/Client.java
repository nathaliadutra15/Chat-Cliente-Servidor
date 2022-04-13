package client;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String IP = "127.0.0.1";
        final int PORTA = 12345;
        Socket socket;
        PrintStream output = null;
        Scanner input = null;
        Scanner teclado;

        //Criar socket e pedir conexão
        try {
            socket = new Socket(IP, PORTA);
        } catch (Exception e) {
            System.out.println("Não foi possível conectar ao servidor.");
            return;
        }

        //Comunicação
        try {
            input = new Scanner(socket.getInputStream());
            output = new PrintStream(socket.getOutputStream());
            teclado = new Scanner(System.in);

            String mensagemRecebida;
            do {
                System.out.println("Digite a mensagem: ");
                mensagemRecebida = teclado.nextLine();
                output.println(mensagemRecebida);
            } while (!mensagemRecebida.equalsIgnoreCase("exit"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Encerrar conexão
        try {
            output.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

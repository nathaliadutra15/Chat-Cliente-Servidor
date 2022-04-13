package server;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        final int PORTA = 12345; // Constante com o número da porta
        ServerSocket serverSocket;
        Socket clientSocket = null;
        Scanner input = null;
        PrintStream output = null;

        // Criar o socket e fazer o bind

        // try/catch para o tratamento da exceção
        try {
            serverSocket = new ServerSocket(PORTA);
        } catch (Exception e) {
            System.out.println("Porta " + PORTA + " já está em uso.");
            return;
        }

        // Aguardar pedido de conexão
        try {
            System.out.println("Aguardando pedido de conexão...");
            clientSocket = serverSocket.accept(); // Atribuindo o retorno do socket do cliente
            System.out.println("Conectado com " + clientSocket.getInetAddress().getHostAddress());
        } catch (Exception e) {
            System.out.println("Erro na conexão.");
            System.out.println(e.getMessage());
        }

        // Comunicação
        try {
            // Ler as mensagens dentro deste canal de comunicação
            input = new Scanner(clientSocket.getInputStream());
            output = new PrintStream(clientSocket.getOutputStream());

            // Enviar a mensagem dentro deste canal de comunicação
            String mensagemRecebida;
            do {
                mensagemRecebida = input.nextLine();
                System.out.println("Recebido: " + mensagemRecebida);
            } while (!mensagemRecebida.equalsIgnoreCase("exit"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Encerrar conexão
        try {
            input.close();
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
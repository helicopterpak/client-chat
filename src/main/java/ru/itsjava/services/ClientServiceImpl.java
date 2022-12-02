package ru.itsjava.services;

import lombok.SneakyThrows;
import ru.itsjava.services.ClientService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientServiceImpl implements ClientService {
    public final static int PORT = 8081;
    public final static String HOST = "localhost";

    @SneakyThrows
    @Override
    public void start() {
        Socket socket = new Socket(HOST, PORT);

        if (socket.isConnected()) {
            new Thread(new SocketRunnable(socket)).start();

            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());

            MessageInputService messageInputService = new MessageInputServiceImpl(System.in);

            System.out.println("Print message");
            String consoleMessage = messageInputService.getMessage();

            serverWriter.println(consoleMessage);
            serverWriter.flush();


        }
    }
}

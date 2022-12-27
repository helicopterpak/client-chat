package ru.itsjava.services;

import lombok.SneakyThrows;
import ru.itsjava.domain.User;
import ru.itsjava.services.ClientService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientServiceImpl implements ClientService {
    public final static int PORT = 8081;
    public final static String HOST = "localhost";
    private User user;

    @SneakyThrows
    @Override
    public void start() {
        Socket socket = new Socket(HOST, PORT);

        if (socket.isConnected()) {
            new Thread(new SocketRunnable(socket, user)).start();

            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());

            MessageInputService messageInputService = new MessageInputServiceImpl(System.in);

            System.out.println("Введите свой логин:");
            String login = messageInputService.getMessage();

            System.out.println("Введите свой пароль:");
            String password = messageInputService.getMessage();

            serverWriter.println("!autho!" + login + ":" + password);
            serverWriter.flush();

            while (true) {
                System.out.println("Print message");
                String consoleMessage = messageInputService.getMessage();
//                if (consoleMessage.startsWith(login)) {
//                    String memoryLogin = consoleMessage.split(":")[0];
//                    String memoryMessage = consoleMessage.split(":")[1];
//                    user = new User(memoryLogin, memoryMessage);
//
//                    if (!memoryLogin.equals(user.getName())) {
                        serverWriter.println(consoleMessage);
                        serverWriter.flush();
                        if (consoleMessage.equals("exit")) {
                            System.exit(0);
                        }
                    }
//                }
//
//            }
        }
    }
}

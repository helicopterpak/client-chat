package ru.itsjava.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.itsjava.domain.User;

import java.net.Socket;

@RequiredArgsConstructor
@AllArgsConstructor
public class SocketRunnable implements Runnable{
    private final Socket socket;
    private User user;

    @SneakyThrows
    @Override
    public void run() {
        MessageInputServiceImpl serverReader = new MessageInputServiceImpl(socket.getInputStream());
        while(true) {
            System.out.println(serverReader.getMessage());
        }
    }
}

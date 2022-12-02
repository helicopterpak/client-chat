package ru.itsjava;

import ru.itsjava.services.ClientService;
import ru.itsjava.services.ClientServiceImpl;

import java.io.IOException;


public class Main  {

    public static void main(String[] args) throws IOException {
        ClientService clientService = new ClientServiceImpl();
        clientService.start();
    }
}

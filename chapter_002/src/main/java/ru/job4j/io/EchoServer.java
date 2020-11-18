package ru.job4j.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                String answer = "";
                String[] word = new String[2];
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        if (str.contains("/?")) {
                            String[] line = str.split("=");
                            word = line[1].split(" ");
                        }
                        System.out.println(str);

                    }
                    if (Objects.equals(word[0], null)) {
                        answer = "Hello World";
                    } else {
                        answer = word[0];
                    }
                    if ("Hello".equals(word[0])) {
                        answer = "Hello";
                    }
                    if ("Bye".equals(word[0])) {
                        break;
                    }

                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(answer.getBytes());
                }
            }
        } catch (IOException e) {
            LOG.error("Chatching IOException", e);
        }
    }
}

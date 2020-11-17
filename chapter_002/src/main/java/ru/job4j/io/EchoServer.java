package ru.job4j.io;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EchoServer {
    public static void main(String[] args) throws IOException {

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
                    answer = word[0];
                    if (word[0].equals("Hello")) {
                        answer = "Hello";
                    }
                    if (word[0].equals("Bye")) {
                        break;
                    }

                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(answer.getBytes());
                }
            }
        }
    }
}

package exercice9;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 2222);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Client connecté au serveur");

            String userInput;
            while (true) {
                System.out.print("Entrez un mot (ou 'print' pour afficher la chaîne, 'exit' pour terminer) : ");
                userInput = consoleReader.readLine();
                writer.write(userInput + "\n");
                writer.flush();

                if (userInput.equals("exit")) {
                    System.out.println("Connexion terminée par le client");
                    break;
                }

                String response = reader.readLine();
                System.out.println("Réponse du serveur : " + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

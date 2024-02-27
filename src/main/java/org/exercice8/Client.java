package org.exercice8;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5555);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Client connecté au serveur");

            String userInput;
            while (true) {
                System.out.print("Entrez une instruction (ou 'exit' pour terminer) : ");
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
            writer.write("+3\n");
            writer.flush();
            System.out.println(reader.readLine()); // Réponse attendue: "Instruction '+3' traitée. Nouvelle valeur de nb : 4"

            writer.write("-2\n");
            writer.flush();
            System.out.println(reader.readLine()); // Réponse attendue: "Instruction '-2' traitée. Nouvelle valeur de nb : 2"

            writer.write("*10\n");
            writer.flush();
            System.out.println(reader.readLine()); // Réponse attendue: "Instruction '*10' traitée. Nouvelle valeur de nb : 20"

            writer.write("/5\n");
            writer.flush();
            System.out.println(reader.readLine()); // Réponse attendue: "Instruction '/5' traitée. Nouvelle valeur de nb : 4"

            writer.write("print\n");
            writer.flush();
            System.out.println(reader.readLine()); // Réponse attendue: "La valeur actuelle de nb est : 4"

            writer.write("exit\n");
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

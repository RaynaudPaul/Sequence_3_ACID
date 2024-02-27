package exercice9;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    private static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(2222)) {
            System.out.println("Serveur en attente de connexion...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connecté");

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String userInput;
                while ((userInput = reader.readLine()) != null) {
                    if (userInput.equals("exit")) {
                        System.out.println("Connexion terminée par le client");
                        break;
                    } else if (userInput.equals("print")) {
                        System.out.println("Contenu actuel de la chaîne : " + stringBuilder.toString());
                        stringBuilder.setLength(0); // Réinitialiser la chaîne
                        writer.write("Chaîne affichée et réinitialisée.\n");
                        writer.flush();
                    } else {
                        if (MiddlewareValidationMot.isValidWord(userInput)) {
                            stringBuilder.append(userInput.toLowerCase()).append(" ");
                            writer.write("Mot '" + userInput + "' ajouté à la chaîne.\n");
                            writer.flush();
                        } else {
                            writer.write("Mot non conforme. Veuillez envoyer un mot contenant uniquement des lettres.\n");
                            writer.flush();
                        }
                    }
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

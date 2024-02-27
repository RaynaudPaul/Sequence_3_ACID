package org.exercice8;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    private static int nb = 1;
    private static final String FILE_PATH = "server_log.txt";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5555)) {
            System.out.println("Serveur en attente de connexion...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connecté");

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String instruction;
                while ((instruction = reader.readLine()) != null) {
                    if (instruction.equals("exit")) {
                        System.out.println("Connexion terminée par le client");
                        break;
                    } else if (instruction.equals("print")) {
                        writer.write("La valeur actuelle de nb est : " + nb + "\n");
                        writer.flush();
                    } else {
                        processInstruction(instruction);
                        writer.write("Instruction '" + instruction + "' traitée. Nouvelle valeur de nb : " + nb + "\n");
                        writer.flush();
                    }
                }

                // Enregistrement de l'instruction dans le fichier
                saveToLogFile(instruction);

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processInstruction(String instruction) {
        try {
            char operator = instruction.charAt(0);
            int operand = Integer.parseInt(instruction.substring(1));

            switch (operator) {
                case '+':
                    nb += operand;
                    break;
                case '-':
                    nb -= operand;
                    break;
                case '*':
                    nb *= operand;
                    break;
                case '/':
                    nb /= operand;
                    break;
                default:
                    System.out.println("Opérateur non pris en charge : " + operator);
            }
        } catch (NumberFormatException | ArithmeticException e) {
            System.out.println("Erreur de traitement de l'instruction : " + instruction);
        }
    }

    private static void saveToLogFile(String instruction) {
        try (BufferedWriter logWriter = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            logWriter.write(instruction + "\n");
            logWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package org.exercice8;



public class Main {
    public static void main(String[] args) {
        Thread serverThread = new Thread(() -> Serveur.main(null));
        serverThread.start();

        // Attendre un moment pour s'assurer que le serveur est démarré
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Démarrer le client
        Client.main(null);

        // Attendre que le serveur termine son exécution (ce qui ne se produira pas dans cet exemple, car il est en boucle infinie)
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }
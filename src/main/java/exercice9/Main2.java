package exercice9;

public class Main2 {
    public static void main(String[] args) {
        Thread middlewareThread = new Thread(() -> MiddlewareValidationMot.main(null));
        middlewareThread.start();

        // Attendre un moment pour s'assurer que le middleware est démarré
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Démarrer le serveur dans un thread séparé
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

        // Attendre que le client termine son exécution
        try {
            middlewareThread.join();
            serverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }


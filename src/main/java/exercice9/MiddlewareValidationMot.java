package exercice9;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;


public class MiddlewareValidationMot {
        public static boolean isValidWord(String word) {
            // Vérifier si le mot ne contient que des lettres
            return Pattern.matches("[a-zA-Z]+", word);
        }

    public static void main(Object o) {
    }
}



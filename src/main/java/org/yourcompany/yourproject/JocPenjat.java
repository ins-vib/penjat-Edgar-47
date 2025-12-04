/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.yourcompany.yourproject;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author alumne
 */
public class JocPenjat {

    public static void main(String[] args) {
        // Obtenim una paraula aleatòria
        String paraula = obtenirParaula();

        // Inicialitzem l'estat amb guions baixos
        char[] estat = inicialitzarEstat(paraula);

        int errors = 0;
        final int MAX_ERRORS = 6;

        System.out.println("Benvingut al joc del Penjat!");
        System.out.println("-----------------------------");

        // Bucle principal del joc
        while (!paraulaCompletada(estat) && errors < MAX_ERRORS) {
            System.out.println();
            System.out.println("PARAULA:");
            mostrarEstat(estat);

            System.out.println();
            System.out.println("Errors: " + errors + "/" + MAX_ERRORS);
            mostrarNinot(errors);

            char lletra = demanarLletra();

            boolean encert = actualitzarEstat(estat, paraula, lletra);

            if (encert) {
                System.out.println("Molt bé! La lletra '" + lletra + "' hi és!");
            } else {
                System.out.println("Llàstima! La lletra '" + lletra + "' no hi és!");
                errors++;
            }
        }

        System.out.println();
        System.out.println("PARAULA FINAL:");
        mostrarEstat(estat);
        System.out.println();

        if (paraulaCompletada(estat)) {
            System.out.println("Enhorabona! Has encertat la paraula (" + paraula+")");
        } else {
            mostrarNinot(6);
            System.out.println("Has fallat! La paraula era (" + paraula+")");
        }
    }

    // Retorna una paraula seleccionada aleatòriament.
    public static String obtenirParaula() {
        String[] PARAULES = {
            "hola","cadira","tisores","riu","fotografia","escala",
            "llibre","ordinador","ratoli","armari","pati","programa","columna"
        };

        Random rnd = new Random();
        int index = rnd.nextInt(PARAULES.length);
        return PARAULES[index];
    }

    // Mostra el dibuix del penjat segons el nombre d’errors.
    public static void mostrarNinot(int errors) {
        String[] DIBUIXOS = {
            """
               +---+
                   |
                   |
                   |
                  ===
            """,
            """
               +---+
               O   |
                   |
                   |
                  ===
            """,
            """
               +---+
               O   |
               |   |
                   |
                  ===
            """,
            """
               +---+
               O   |
              /|   |
                   |
                  ===
            """,
            """
               +---+
               O   |
              /|\\  |
                   |
                  ===
            """,
            """
               +---+
               O   |
              /|\\  |
              /    |
                  ===
            """,
            """
               +---+
               O   |
              /|\\  |
              / \\  |
                  ===
            """
        };

        if (errors < 0) {
            errors = 0;
        } else if (errors >= DIBUIXOS.length) {
            errors = DIBUIXOS.length - 1;
        }

        System.out.println(DIBUIXOS[errors]);
    }

    // Demana una lletra vàlida (entre ‘a’ i ‘z’) i la retorna.
    public static char demanarLletra() {
        Scanner teclat = new Scanner(System.in);

        while (true) {
            System.out.print("Entra una lletra: ");
            String entrada = teclat.nextLine().trim().toLowerCase();

            if (entrada.length() == 1) {
                char c = entrada.charAt(0);
                if (c >= 'a' && c <= 'z') {
                    return c;
                }
            }
            System.out.println("Error! Introdueix un valor correcte");
        }
    }

    // Crea l’estat inicial ple de '_'.
    public static char[] inicialitzarEstat(String paraula) {
        char[] estat = new char[paraula.length()];
        for (int i = 0; i < estat.length; i++) {
            estat[i] = '_';
        }
        return estat;
    }

    // Actualitza l’estat i indica si la lletra hi és.
    public static boolean actualitzarEstat(char[] estat, String paraula, char lletra) {
        boolean trobat = false;

        for (int i = 0; i < paraula.length(); i++) {
            if (paraula.charAt(i) == lletra && estat[i] == '_') {
                estat[i] = lletra;
                trobat = true;
            }
        }

        return trobat;
    }

    // Mostra les lletres descobertes i les ocultes.
    public static void mostrarEstat(char[] estat) {
        for (int i = 0; i < estat.length; i++) {
            System.out.print(estat[i] + " ");
        }
        System.out.println();
    }

    // Comprova si ja no queden '_'.
    public static boolean paraulaCompletada(char[] estat) {
        for (int i = 0; i < estat.length; i++) {
            if (estat[i] == '_') {
                return false;
            }
        }
        return true;
    }
}
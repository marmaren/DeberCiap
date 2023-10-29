package com.mycompany.aprendizajearitmetico;

import java.util.Random;
import java.util.Scanner;

public class AprendizajeAritmetico {

    private static final Random generadorNumerosAleatorios = new Random();
    private static final Scanner entradaUsuario = new Scanner(System.in);

    public static void main(String[] args) {
        String respuesta;

        do {
            ejecutarProgramaAritmetico();
            System.out.println("¿Quieres permitir que otro estudiante pruebe el programa? (si/no)");
            respuesta = entradaUsuario.next();
            entradaUsuario.nextLine(); // Consumir la nueva línea
        } while (respuesta.toLowerCase().equals("si"));
    }

    private static void ejecutarProgramaAritmetico() {
        int nivelDificultad;
        int tipoOperacion;

        System.out.println("Bienvenido al programa de Aprendizaje Aritmético!");
        System.out.println("Elige el nivel de dificultad (1-3): ");
        nivelDificultad = entradaUsuario.nextInt();

        System.out.println("Elige el tipo de operación aritmética (1-suma, 2-resta, 3-multiplicación, 4-división, 5-mezcla): ");
        tipoOperacion = entradaUsuario.nextInt();

        int preguntasCorrectas = 0;

        for (int i = 0; i < 10; i++) {
            if (realizarPreguntaAritmetica(nivelDificultad, tipoOperacion)) {
                preguntasCorrectas++;
                System.out.println(obtenerRespuestaPositivaAleatoria());
            } else {
                System.out.println(obtenerRespuestaNegativaAleatoria());
                i--; // Repetir la misma pregunta si la respuesta es incorrecta
            }
        }

        double porcentajeCorrectas = (double) preguntasCorrectas / 10 * 100;

        System.out.println("Rendimiento del estudiante: " + porcentajeCorrectas + "%");

        if (porcentajeCorrectas < 75) {
            System.out.println("Por favor, pide ayuda adicional a tu instructor.");
        } else {
            System.out.println("¡Felicidades, estás listo para pasar al siguiente nivel!");
        }
    }

    private static boolean realizarPreguntaAritmetica(int nivelDificultad, int tipoOperacion) {
        int num1 = generarNumeroAleatorio(nivelDificultad);
        int num2 = generarNumeroAleatorio(nivelDificultad);
        char signo = obtenerSignoOperacion(tipoOperacion);
        int respuestaCorrecta = obtenerRespuestaCorrecta(num1, num2, tipoOperacion);

        System.out.print("¿Cuánto es " + num1 + " " + signo + " " + num2 + "? ");
        int respuestaUsuario = entradaUsuario.nextInt();
        entradaUsuario.nextLine(); // Consumir esta línea para evitar problemas con el buffer

        return respuestaUsuario == respuestaCorrecta;
    }

    private static char obtenerSignoOperacion(int tipoOperacion) {
        switch (tipoOperacion) {
            case 1:
                return '+';
            case 2:
                return '-';
            case 3:
                return '*';
            case 4:
                return '/';
            case 5:
                return obtenerSignoOperacion(generadorNumerosAleatorios.nextInt(4) + 1);
            default:
                return '?';
        }
    }

    private static int generarNumeroAleatorio(int nivelDificultad) {
        int rango = (int) Math.pow(10, nivelDificultad);
        return generadorNumerosAleatorios.nextInt(rango);
    }

    private static int obtenerRespuestaCorrecta(int num1, int num2, int tipoOperacion) {
        switch (tipoOperacion) {
            case 1:
                return num1 + num2;
            case 2:
                return num1 - num2;
            case 3:
                return num1 * num2;
            case 4:
                return num1 / num2;
            case 5:
                return obtenerRespuestaCorrecta(num1, num2, generadorNumerosAleatorios.nextInt(4) + 1);
            default:
                return 0;
        }
    }

    private static String obtenerRespuestaPositivaAleatoria() {
        String[] respuestasPositivas = {"¡Muy bien!", "¡Excelente!", "¡Buen trabajo!", "¡Sigue así!"};
        int indice = generadorNumerosAleatorios.nextInt(respuestasPositivas.length);
        return respuestasPositivas[indice];
    }

    private static String obtenerRespuestaNegativaAleatoria() {
        String[] respuestasNegativas = {"No. Por favor intenta de nuevo.", "Incorrecto. Intenta una vez más.",
                "¡No te rindas!", "No. Sigue intentando."};
        int indice = generadorNumerosAleatorios.nextInt(respuestasNegativas.length);
        return respuestasNegativas[indice];
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programa;

import java.security.SecureRandom;
import java.util.Scanner;
import java.util.Random;
public class ProgramaCAI {

    private static final int NUMERO_PREGUNTAS = 10;

    private static final String[] RESPUESTAS_POSITIVAS = {
            "¡Muy bien!",
            "¡Excelente!",
            "¡Buen trabajo!",
            "¡Sigue así!"
    };

    private static final String[] RESPUESTAS_NEGATIVAS = {
            "No. Por favor intenta de nuevo.",
            "Incorrecto. Intenta una vez más.",
            "¡No te rindas!",
            "No. Sigue intentando."
    };

    private static final SecureRandom random = new SecureRandom();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int nivelDificultad = seleccionarNivelDificultad();
        int tipoProblema = seleccionarTipoProblema();

        int preguntasCorrectas = 0;
        for (int i = 0; i < NUMERO_PREGUNTAS; i++) {
            if (realizarPregunta(nivelDificultad, tipoProblema)) {
                preguntasCorrectas++;
                System.out.println(obtenerRespuestaPositivaAleatoria());
            } else {
                System.out.println(obtenerRespuestaNegativaAleatoria());
            }
        }

        double porcentajeCorrectas = (preguntasCorrectas / (double) NUMERO_PREGUNTAS) * 100;
        System.out.println("Porcentaje de respuestas correctas: " + porcentajeCorrectas + "%");

        if (porcentajeCorrectas < 75) {
            System.out.println("Por favor pide ayuda adicional a tu instructor.");
        } else {
            System.out.println("¡Felicidades, estás listo para pasar al siguiente nivel!");
        }
    }

    private static int seleccionarNivelDificultad() {
        System.out.println("Elige el nivel de dificultad:");
        System.out.println("1. Números de un dígito");
        System.out.println("2. Números de dos dígitos");
        // Puedes agregar más niveles de dificultad aquí si lo deseas

        return scanner.nextInt();
    }

    private static int seleccionarTipoProblema() {
        System.out.println("Elige el tipo de problema aritmético:");
        System.out.println("1. Suma");
        System.out.println("2. Resta");
        System.out.println("3. Multiplicación");
        System.out.println("4. División");
        System.out.println("5. Mezcla aleatoria");

        return scanner.nextInt();
    }

    private static boolean realizarPregunta(int nivelDificultad, int tipoProblema) {
        int num1 = generarNumeroAleatorio(nivelDificultad);
        int num2 = generarNumeroAleatorio(nivelDificultad);
        String operador = obtenerOperador(tipoProblema);

        int resultadoEsperado = obtenerResultadoEsperado(num1, num2, operador);
        int respuestaUsuario;

        do {
            System.out.print("¿Cuánto es " + num1 + " " + operador + " " + num2 + "? ");
            respuestaUsuario = scanner.nextInt();

            if (respuestaUsuario == resultadoEsperado) {
                return true;
            } else {
                System.out.println("Respuesta incorrecta. Intenta nuevamente.");
            }
        } while (true);
    }

    private static int generarNumeroAleatorio(int nivelDificultad) {
        int maximo;

        switch (nivelDificultad) {
            case 1:
                maximo = 9;
                break;
            case 2:
                maximo = 99;
                break;
            default:
                maximo = 9;
                break;
        }

        return random.nextInt(maximo) + 1;
    }

    private static String obtenerOperador(int tipoProblema) {
        switch (tipoProblema) {
            case 1:
                return "+";
            case 2:
                return "-";
            case 3:
                return "*";
            case 4:
                return "/";
            case 5:
                return obtenerOperadorAleatorio();
            default:
                return "+";
        }
    }

    private static String obtenerOperadorAleatorio() {
        String[] operadores = {"+", "-", "*", "/"};
        int indice = random.nextInt(operadores.length);
        return operadores[indice];
    }

    private static int obtenerResultadoEsperado(int num1, int num2, String operador) {
        int resultado;

        switch (operador) {
            case "+":
                resultado = num1 + num2;
                break;
            case "-":
                resultado = num1 - num2;
                break;
            case "*":
                resultado = num1 * num2;
                break;
            case "/":
                resultado = num1 / num2;
                break;
            default:
                resultado = num1 + num2;
                break;
        }

        return resultado;
    }

    private static String obtenerRespuestaPositivaAleatoria() {
        int indice = random.nextInt(RESPUESTAS_POSITIVAS.length);
        return RESPUESTAS_POSITIVAS[indice];
    }

    private static String obtenerRespuestaNegativaAleatoria() {
        int indice = random.nextInt(RESPUESTAS_NEGATIVAS.length);
        return RESPUESTAS_NEGATIVAS[indice];
    }
}

// -->>>>>>>>>>> Resposta = 91.

public class Primeiro {
    public static void main(String[] args) {
        int INDICE = 13, SOMA = 0, K = 0;

        while (K < INDICE) {
            K = K + 1;
            SOMA = SOMA + K;
            //System.out.println("Somando: " + SOMA);
        }

        System.out.println("O final da SOMA é -->>> " + SOMA);
    }
}
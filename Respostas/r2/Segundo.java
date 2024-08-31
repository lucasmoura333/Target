package Respostas.r2;

import java.util.Scanner;

public class Segundo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite número para se validar: ");
        int numero = scanner.nextInt();

        if (ehFibonacci(numero)){
            System.out.println("O numero " + numero + " pertence à sequênca Fibonacci.");
        }else {
            System.out.println("O numero " + numero + " está fora da sequência.");
        }

        scanner.close();
    }

    public static boolean ehFibonacci(int numero) {
        if (numero == 0 || numero == 1){
            return true;
        }

        //segundo as regras, 0 e 1 sempre serão

        int var1 = 0;
        int var2 = 1;
        int sequencia = var1 + var2; // i - Começa com 0+1

        while(sequencia <= numero){
            if (sequencia == numero){
                return true;
            }

            var1 = var2; // ii - Passa para  1+1+...
            var2 = sequencia; // iii - Pega a sequencia declarada e atualiza para o padrao >[n +  (n-1)]
            sequencia = var1 + var2; // iv - O loop fecha na soma em torno da sequencia Fibonacci até fazer parte da mesma, OU NAO...

        }
        return false;
    }



    
}

package Respostas.r5;

import java.util.Scanner;

public class LeitorDeEntrada {

    public String capturaString(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a string que deseja inverter : ");

        String entrada = scanner.nextLine();
        scanner.close();

        return entrada;
    }
    
}

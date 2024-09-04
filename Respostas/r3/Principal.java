package Respostas.r3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        List<FaturamentoDiario> faturamentos = leitorDJason("faturamento.json");

        //Validação para caso o arquivo esteja vazio
        if (faturamentos.isEmpty()){
            System.out.println("Nenhum faturamento encontrado");
            return;
        }

        
        //Terminalzinho para "usuario" , já que optei separar os métodos por classes
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha um relatório:");
        System.out.println(" 1 - Calcular Menor Faturamento");
        System.out.println(" 2 - Calcular Maior Faturamento");
        System.out.println(" 3 - Calcular dias com Faturamento Acima da Média");
        int relatorio = scanner.nextInt();


        //Pro relatório 1 e 2 optei por fazer um loop pelos lados opostos, o problema está obviamente em valores muito altos ou jsons muito grandes
        switch (relatorio){
            case 1:
                 classePMenorFatuarmento calculaMenor = new classePMenorFatuarmento();
                 double menor = calculaMenor.calcularMenor(faturamentos);
                 System.out.println("Menor faturamento do mês: " + menor);
                 break;

            case 2:
                classePMaiorFatuarmento calculaMaior = new classePMaiorFatuarmento();
                double maior = calculaMaior.calcularMaior(faturamentos);
                System.out.println("Menor faturamento do mês: " + maior);
                break;

            case 3:
                classePCalculaDiaBom calculadiasbons = new classePCalculaDiaBom();
                int diasAcimaMedia = calculadiasbons.calculardiabom(faturamentos);
                System.out.println("Número de dias com faturamento acima da média: " + diasAcimaMedia);
                break;

            default:
                System.out.println("Opção inválida.");
        }
        scanner.close();

    }

    //Em nenhum momento do desafio deixa explicito se precisa ou não dem método para ler o arquivo em Json, apenas presupõe-se que ele esteja sendo recebido mas dei uma pesquisada para fins didáticos rsrsrsrs


private static List<FaturamentoDiario> leitorDJason(String caminhoArquivo) {
        List<FaturamentoDiario> faturamentos = new ArrayList<>();

        //Gambiarra para leitura de arquivos que encontrei para evitar uso de dependencias
        //Encontrei outros métodos mais avançados mas por hora, este ficou compreensivel para mim rsrsrsrs
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                
                // Removendo caracteres indesejados e separando por chaves e vírgulas
                linha = linha.replaceAll("[\\[\\]{}]", "");
                String[] elementos = linha.split(",");

                // Extrai os valores de dia e valor
                for (String elemento : elementos) {
                    if (elemento.contains("\"dia\"")) {
                        int dia = Integer.parseInt(elemento.split(":")[1].trim());
                        double valor = Double.parseDouble(elementos[1].split(":")[1].trim());
                        faturamentos.add(new FaturamentoDiario(dia, valor));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return faturamentos;
    }





    
}

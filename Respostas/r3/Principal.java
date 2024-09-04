package Respostas.r3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        //Lista os arquivos na pasta "/Ref/" - Tive que garimpar umas coisas
        File pasta = new File("Ref");
        File[] arquivos = pasta.listFiles();

        //Só tive a ideia, nem testei.
        if (arquivos == null || arquivos.length == 0){
            System.out.println("Nenhum arquivo encontrado na pasta /Ref/");
            return;
        }

        //Exibe terminalzinho dos arquivos disponiveis na pasta.
        System.out.println("Arquivos disponiveis para leitura do Faturamento:");
        for(int i = 0; i < arquivos.length; i++){
            System.out.println((i + 1) + " - " + arquivos[i].getName());
        }

        //Abre o terminalzinho pro usuario escolher o arquivo correspondente na pasta.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha um arquivo pelo número: ");
        int escolhaArquivo = scanner.nextInt();

        if (escolhaArquivo < 1 || escolhaArquivo > arquivos.length){
            System.out.println("Escolha inválida.");
            return;
        }
        

        // Validação da extensão do arquivo > Garimpei.
        String nomeArquivo = arquivos[escolhaArquivo - 1].getName();
        if (!nomeArquivo.endsWith(".json") && !nomeArquivo.endsWith(".xml")) {
            System.out.println("Arquivo inválido. Apenas arquivos JSON ou XML são permitidos.");
            return;
        }

        // Ajusta o caminho do arquivo escolhido
        String caminhoArquivo = arquivos[escolhaArquivo - 1].getPath();
        List<FaturamentoDiario> faturamentos = leitorDJason(caminhoArquivo);

        // Validação para caso o arquivo esteja vazio
        if (faturamentos.isEmpty()) {
            System.out.println("Nenhum faturamento encontrado.");
            return;
        }

        
        //Escolha dos relatórios, lembrando que o terminalzinho foi aberto anteriormente para escolha do arquivo.        
        System.out.println("Escolha um relatório:");
        System.out.println(" 1 - Calcular Menor Faturamento");
        System.out.println(" 2 - Calcular Maior Faturamento");
        System.out.println(" 3 - Calcular dias com Faturamento Acima da Média");
        int relatorio = scanner.nextInt();

        System.out.println("");
        System.out.println("|____________________________________________|");
        System.out.println("");


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

   /* 
     Atualização provisória, presumindo que o Arquivo JSON fornecido sera referente a esse problema.
        Tomarei a liberdade poética de fazer em três partes com uma possivel quarta...
            1 - Esta atualização impotando o arquivo enviado.
            2 - Criação de um terminal para escolha dos arquivos.
            3 - Adaptando ao XML tambem, e, separando maisos objetos.  
            Bonus - Transformar em API, recebendo qualquer links compativeis e usando o minimo de spring possivel.
           
    */

    private static List<FaturamentoDiario> leitorDJason(String caminhoArquivo) {
        List<FaturamentoDiario> faturamentos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {

                //Remove os caracteres especiais e tira o espaço final
                linha = linha.replaceAll("[\\[\\]{}]", "").trim();
                //Pasa a tratar cada linha como elemento unico separado por virgula
                String[] elementos = linha.split(",");

                //Variáveis temporárias para armazenar dia e valor, para garantir que não se perdessem valores em dias com valor = 0, tinha virado roleta-russa.
                int dia = 0;
                double valor = 0.0;

                // Extrai os valores de dia e valor, se existirem
                for (String elemento : elementos) {
                    String[] keyValue = elemento.split(":");

                    // Verifica se há um par chave-valor e verifica se realmente foi repatido entre dois dados, ex: '[dia:valor]'
                    if (keyValue.length == 2) {
                        String chave = keyValue[0].trim().replace("\"", "");
                        String valorStr = keyValue[1].trim();

                        // Verifica se o elemento atual é "dia" ou "valor"
                        if (chave.equals("dia")) {
                            dia = Integer.parseInt(valorStr);
                        } else if (chave.equals("valor")) {
                            valor = Double.parseDouble(valorStr);
                        }
                    }
                }

                // Adiciona um novo registro de faturamento se ambos os valores forem válidos
                faturamentos.add(new FaturamentoDiario(dia, valor));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter os valores numéricos: " + e.getMessage());
        }
        return faturamentos;
    }    
    
}

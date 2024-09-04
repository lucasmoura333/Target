package Respostas.r3;


import java.io.File;
import java.util.List;
import java.util.Scanner;


public class Principal {
    public static void main(String[] args) {
        //Lista os arquivos na pasta "/Ref/" - Tive que garimpar umas coisas
        File pasta = new File("Ref");
        File[] arquivos = pasta.listFiles();

        
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
        

        //Refatorei e já fvalidei no mesmo bloco.
        String nomeArquivo = arquivos[escolhaArquivo - 1].getName();
        String caminhoArquivo = arquivos[escolhaArquivo - 1].getPath();
        List<FaturamentoDiario> faturamentos;

        if (nomeArquivo.endsWith(".json")) {
            faturamentos = LeitorDJson.lerFaturamento(caminhoArquivo);
        } else if (nomeArquivo.endsWith(".xml")) {
            faturamentos = LeitorDXml.lerFaturamento(caminhoArquivo);
        } else {
            System.out.println("Arquivo inválido. Apenas arquivos JSON ou XML são permitidos.");
            return;
        }

        //Validação para caso a Lista esteja vazia, ou seja, ainda é possivel abrir um arquivo Json ou Xml mas ele está
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
        System.out.println("");
        System.out.println("");


        //Pro relatório 1 e 2 optei por fazer um loop pelos lados opostos, o problema está obviamente em valores muito altos ou jsons muito grandes
        switch (relatorio){
            case 1:
                 ClassePMenorFatuarmento calculaMenor = new ClassePMenorFatuarmento();
                 FaturamentoDiario resultadoMenor = calculaMenor.calcularMenor(faturamentos);                              
                 System.out.println("Menor faturamento do mês: " + resultadoMenor.getValorDia() + " no dia: " + resultadoMenor.getDia());
                 break;

            case 2:
                ClassePMaiorFatuarmento calculaMaior = new ClassePMaiorFatuarmento();
                FaturamentoDiario resultadoMaior = calculaMaior.calcularMaior(faturamentos);
                System.out.println("Maior faturamento do mês: " + resultadoMaior.getValorDia() + " no dia: " + resultadoMaior.getDia());
                break;

            case 3:
                ClassePCalculaDiaBom calculadiasbons = new ClassePCalculaDiaBom();
                int diasAcimaMedia = calculadiasbons.calculardiabom(faturamentos);
                System.out.println("Número de dias com faturamento acima da média: " + diasAcimaMedia);
                break;

            default:
                System.out.println("Opção inválida.");
        }
        scanner.close();

    }
}

   /* 
     Atualização provisória, presumindo que o Arquivo JSON fornecido sera referente a esse problema.
        Tomarei a liberdade poética de fazer em três partes com uma possivel quarta...
            1 - Esta atualização impotando o arquivo enviado.
            2 - Criação de um terminal para escolha dos arquivos.
            3 - Adaptando ao XML tambem, e, separando maisos objetos.  
            Bonus - Transformar em API, recebendo qualquer links compativeis e usando o minimo de spring possivel.
           
    */

    

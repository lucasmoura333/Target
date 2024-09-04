package Respostas.r3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorDJason {

    public static List<FaturamentoDiario> lerFaturamento(String caminhoArquivo){
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

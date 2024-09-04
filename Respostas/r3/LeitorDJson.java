package Respostas.r3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorDJson {

    public static List<FaturamentoDiario> lerFaturamento(String caminhoArquivo) {
        List<FaturamentoDiario> faturamentos = new ArrayList<>();
        StringBuilder conteudoArquivo = new StringBuilder();

        // Leitura do arquivo inteiro de uma vez
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                conteudoArquivo.append(linha.trim());
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return faturamentos;
        }

        // Quebra o conteúdo do arquivo em registros baseados na separação por chaves
        String[] registros = conteudoArquivo.toString().split("},\\s*\\{");

        // Ajuste para lidar com possíveis casos de ausência de colchetes externos
        for (String registro : registros) {
            // Limpa possíveis colchetes, chaves e ajusta a formatação
            registro = registro.replaceAll("[\\[\\]{}]", "").trim();

            //Variáveis temporárias para armazenar dia e valor, para garantir que não se perdessem valores em dias com valor = 0, tinha virado roleta-russa.
            int dia = 0;
            double valor = 0.0;

            // Divide o registro atual em pares chave-valor
            String[] elementos = registro.split(",");

            for (String elemento : elementos) {
                String[] keyValue = elemento.split(":");

                // Verifica se há um par chave-valor
                if (keyValue.length == 2) {
                    String chave = keyValue[0].trim().replace("\"", "");
                    String valorStr = keyValue[1].trim();

                    try {
                        // Define os valores de 'dia' e 'valor' conforme as chaves
                        if (chave.equals("dia")) {
                            dia = Integer.parseInt(valorStr);
                        } else if (chave.equals("valor")) {
                            valor = Double.parseDouble(valorStr);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter os valores numéricos: " + e.getMessage());
                    }
                }
            }

            // Adiciona um novo registro de faturamento se o dia e o valor forem válidos
            if (dia > 0) { // Ignora registros onde o dia é inválido
                faturamentos.add(new FaturamentoDiario(dia, valor));
            }
        }

        return faturamentos;
    }
}

package Respostas.r3;

import java.io.File;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


/*
 * Nesse em especial tive que pesquisar e ainda pedir help do GPT com força visando a possibilidade de em multiplas camadas o xml estar quebrado/defeituoso.
 * Saberia fazer novamente sem cola? Muito pouco provavel.
 * Conseguiria expicar? Pouco provavel tambem. Esse tipo de conteúdo é altamente nichado mas agradeço a oortunidade de me deparar com esse tipo de situações.
 
 */


public class LeitorDXml {
    public static List<FaturamentoDiario> lerFaturamento(String caminhoArquivo) {
        List<FaturamentoDiario> faturamentos = new ArrayList<>();

        // Leitura do arquivo XML como string
        File file = new File(caminhoArquivo);
        StringBuilder xmlContent = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                xmlContent.append(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return faturamentos;
        }

        // Adiciona uma raiz temporária ao XML
        String xmlComRaiz = "<root>" + xmlContent.toString() + "</root>";

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlComRaiz)));
            document.getDocumentElement().normalize();

            // Coletar todos os elementos <dia> e <valor> independentemente da tag externa
            NodeList dias = document.getElementsByTagName("dia");
            NodeList valores = document.getElementsByTagName("valor");

            // Verifica se há um número correspondente de <dia> e <valor>
            int minLength = Math.min(dias.getLength(), valores.getLength());

            for (int i = 0; i < minLength; i++) {
                // Extrai o valor do dia e do valor correspondentes
                int dia = Integer.parseInt(dias.item(i).getTextContent().trim());
                double valor = Double.parseDouble(valores.item(i).getTextContent().trim());

                // Adiciona à lista de faturamentos
                faturamentos.add(new FaturamentoDiario(dia, valor));
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar o arquivo XML: " + e.getMessage());
        }

        return faturamentos;
    }
}
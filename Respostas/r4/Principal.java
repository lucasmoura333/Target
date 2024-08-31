package quarto;

import java.util.ArrayList;
import java.util.List;

public class Principal {

    public static void main(String[] args) {

        //Optei por facilitar a minha vida e utilizar e utilizar apenas os valores presentes no requisito.

        List <FaturamentoEstado> faturamentos = new ArrayList<>();
        faturamentos.add(new FaturamentoEstado("SP", 67836.43));
        faturamentos.add(new FaturamentoEstado("RJ", 36678.66));
        faturamentos.add(new FaturamentoEstado("MG", 29229.88));
        faturamentos.add(new FaturamentoEstado("ES", 27165.48));
        faturamentos.add(new FaturamentoEstado("Outros", 19849.53));

        CalculaPercentual calculadoraPorEstado = new CalculaPercentual(faturamentos);
        calculadoraPorEstado.calcularPerecntual(faturamentos);


    }
    
}

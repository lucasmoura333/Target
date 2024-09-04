package Respostas.r3;

import java.util.List;

public class ClassePMaiorFatuarmento {
    
    public FaturamentoDiario calcularMaior(List <FaturamentoDiario> faturamentos){
        double maiorFaturamento = Double.MIN_VALUE;
        int diaMaiorFaturamento = -1;


        for (FaturamentoDiario fatura : faturamentos){

            // System.out.println("Dia: " + fatura.getDia() + ", Valor: " + fatura.getValorDia());

            if (fatura.getValorDia() > 0 && fatura.getValorDia() > maiorFaturamento){
                maiorFaturamento = fatura.getValorDia();
                diaMaiorFaturamento = fatura.getDia();
            }
        }

        if (diaMaiorFaturamento != -1) {
            return new FaturamentoDiario(diaMaiorFaturamento, maiorFaturamento);
        } else {
            return new FaturamentoDiario(0, 0.0);
        }
    }    
}

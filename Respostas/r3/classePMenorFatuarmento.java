package Respostas.r3;

import java.util.List;

public class ClassePMenorFatuarmento {

    public FaturamentoDiario calcularMenor(List <FaturamentoDiario> faturamentos){
        double menorFaturamento = Double.MAX_VALUE;
        int diaMenorFaturamento = -1;

        for (FaturamentoDiario fatura : faturamentos){

            // System.out.println("Dia: " + fatura.getDia() + ", Valor: " + fatura.getValorDia());
            if (fatura.getValorDia() > 0 && fatura.getValorDia() < menorFaturamento){
                menorFaturamento = fatura.getValorDia();
                diaMenorFaturamento = fatura.getDia();
            }
        }

        if (diaMenorFaturamento != -1){
            return new FaturamentoDiario(diaMenorFaturamento, menorFaturamento);
        } else {
            return new FaturamentoDiario(0,0.0);
        }        
    }    
}

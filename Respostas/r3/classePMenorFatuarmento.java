package Respostas.r3;

import java.util.List;

public class classePMenorFatuarmento {
    public double calcularMenor(List <FaturamentoDiario> faturamentos){
        double menorFaturamento = Double.MAX_VALUE;

        for (FaturamentoDiario fatura : faturamentos){
            if (fatura.getValorDia() > 0 && fatura.getValorDia() < menorFaturamento){
                menorFaturamento = fatura.getValorDia();
            }
        }

        return menorFaturamento;
    }
    
}

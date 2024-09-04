package Respostas.r4;

import java.util.List;

public class CalculaPercentual {
    private double totalFaturamento;

    public CalculaPercentual(List<FaturamentoEstado> faturamentos){
        this.totalFaturamento = calcularTotal(faturamentos);
    }
    
    //Soma o valor bruto
    private double calcularTotal(List<FaturamentoEstado> faturamentos){
        double total = 0;
        for (FaturamentoEstado faturamento : faturamentos){
            total += faturamento.getVlorEstado();
        }        
        return total;
    }


    public void calcularPerecntual(List<FaturamentoEstado> faturamentos){
        for (FaturamentoEstado faturamento : faturamentos) {

            double percentual = (faturamento.getVlorEstado() / totalFaturamento) * 100;
            System.out.printf("Estado: %s - Percentual: %.2f%%\n", faturamento.getEstadoRef(), percentual);
            
        }
    }

    


    
}

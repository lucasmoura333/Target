package Respostas.r3;
import java.util.List;

public class classePCalculaDiaBom {

    public int calculardiabom(List <FaturamentoDiario> faturamentos){
        double somaFaturamento = 0;
        int diasComFaturamento = 0;

        //Contador de dias com faturamentos !> 0
        for (FaturamentoDiario faturamento : faturamentos) {
            if (faturamento.getValorDia() > 0){
                somaFaturamento += faturamento.getValorDia();
                diasComFaturamento++;
            }
        }

        double mediaMensal = somaFaturamento / diasComFaturamento;
        int diasAcimaMedia = 0;


        //Loop pra procurar os dias bons de faturamento
        for (FaturamentoDiario faturamento2 : faturamentos){
            if (faturamento2.getValorDia() > mediaMensal){
                diasAcimaMedia++;
            }
        }
        return diasAcimaMedia;
    }    
}

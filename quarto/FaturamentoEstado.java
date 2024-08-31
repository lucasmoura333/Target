package quarto;

public class FaturamentoEstado {

    private String estadoRef;
    private double valorEstado;

    public FaturamentoEstado(String estadoRef, double valorEstado ){
        this.estadoRef = estadoRef;
        this.valorEstado = valorEstado;
    }

    public String getEstadoRef(){
        return estadoRef;
    }

    public double getVlorEstado(){
        return valorEstado;
    }
    

  
}

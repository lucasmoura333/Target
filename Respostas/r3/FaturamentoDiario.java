package Respostas.r3;

class FaturamentoDiario {
    int dia;
    double valorDia;

    //Segundo requisito essa separação sera crucial para os Relatórios, então optei por fazer fora.

    public FaturamentoDiario(int dia, double valorDia) {
        this.dia = dia;
        this.valorDia = valorDia;
    }

    public int getDia() {
        return dia;
    }

    public double getValorDia() {
        return valorDia;
    }
}
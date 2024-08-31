package quinto;

public class Principal {
    public static void main(String[] args) {
        // Instancia o leitor de entrada
        LeitorDeEntrada leitor = new LeitorDeEntrada();
        String stringOriginal = leitor.capturaString();

        // Instancia o inversor de string e inverte a entrada
        InversorDeString inversor = new InversorDeString();
        String stringInvertida = inversor.inverter(stringOriginal);

        // Exibe o resultado
        System.out.println("String original: " + stringOriginal);
        System.out.println("String invertida: " + stringInvertida);
    }
}

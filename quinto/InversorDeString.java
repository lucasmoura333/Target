package quinto;

public class InversorDeString {

    public String inverter(String original){
        
        //Transforma a String original em um array de caracteres
        char[] caracteres = original.toCharArray();
        
        //Cria indices, partindo do pressuposto que o tamanhoTotal -1 será a posição final a ser trabalhada no array  > Tendo em vista que indices em arrays começam do 0 e o .length sempre teria um valor MENOR ex:  esquerda -> 8 caracteres  mas a letra A no final tem posição [7]      
        int esquerda = 0, direita = caracteres.length - 1;

        while (esquerda < direita ){
            char temp = caracteres[esquerda];
            caracteres[esquerda] = caracteres[direita];
            caracteres[direita] = temp;

            esquerda++;
            direita--;
        }

        return new String(caracteres);

    }
    
}

package Cripto;
/*
Autor Gabriel Leite
 */
class MD5 {

    // Inicialização dos 4 Buffers
    private static final int Buffer_A = 0x67452301;
    private static final int Buffer_B = (int) 0xEFCDAB89L;
    private static final int Buffer_C = (int) 0x98BADCFEL;
    private static final int Buffer_D = 0x10325476;

    //16 números
    private static final int[] Números = {
            7, 12, 17, 22,
            5, 9, 14, 20,
            4, 11, 16, 23,
            6, 10, 15, 21
    };

    // Armazena os valores de deslocamento usados no algoritmo MD5, determina quantos bits são deslocados em certas operações.
    private static final int[] Tabela = new int[64];
    static {
        for (int i = 0; i < 64; i++) {
            Tabela[i] = (int) (long) ((1L << 32) * Math.abs(Math.sin(i + 1)));
        }
    }

    public static byte[] CriptoMD5(byte[] mensagem) {

        int Tamanho = mensagem.length; // Armazena o tamanho da mensagem em bytes, usando o método length() do array mensagem.
        int Número_Blocos = ((Tamanho + 8) >>> 6) + 1; //Essa variável calcula o número de blocos de 64 bytes necessários para armazenar a mensagem. É feito um deslocamento bitwise (>>>) de 6 bits para a direita para dividir o tamanho total por 64 e é adicionado 1 para garantir que haja espaço suficiente para o preenchimento.
        int Tamanho_Total = Número_Blocos << 6; // Essa variável calcula o tamanho total necessário para armazenar a mensagem e o preenchimento. É feito um deslocamento bitwise (<<) de 6 bits para a esquerda para multiplicar o número de blocos por 64.
        byte[] Bytes = new byte[Tamanho_Total - Tamanho]; //Essa variável é um array de bytes usado para armazenar os bytes de preenchimento. O tamanho do array é calculado subtraindo o tamanho original da mensagem (Tamanho) do tamanho total necessário (Tamanho_Total).
        Bytes[0] = (byte) 0x80; //O primeiro byte do array Bytes é definido como 0x80. Isso é feito para adicionar o byte de preenchimento inicial, conforme necessário pelo algoritmo MD5.
        long Tamanho_Mensagem_Bits = (long) Tamanho << 3; //Essa variável calcula o tamanho total da mensagem em bits, multiplicando o tamanho da mensagem (Tamanho) por 8 usando o operador de deslocamento bitwise (<<).

        for (int i = 0; i < 8; i++) {
            Bytes[Bytes.length - 8 + i] = (byte) Tamanho_Mensagem_Bits;
            Tamanho_Mensagem_Bits >>>= 8;
        } // É responsável por atribuir os últimos 8 bytes do array Bytes com os valores do tamanho total da mensagem em bits, armazenado na variável Tamanho_Mensagem_Bits.

        int a = Buffer_A;
        int b = Buffer_B;
        int c = Buffer_C;
        int d = Buffer_D;
        int[] buffer = new int[16];

        for (int i = 0; i < Número_Blocos; i++) {
            int indice = i << 6;
            for (int j = 0; j < 64; j++, indice++) {
                buffer[j >>> 2] = ((int) ((indice < Tamanho) ? mensagem[indice] : Bytes[indice - Tamanho]) << 24) | (buffer[j >>> 2] >>> 8);
            } // Iteração sobre os blocos da mensagem que serão processados pelo algoritmo MD5.

            int Original_A = a;
            int Original_B = b;
            int Original_C = c;
            int Original_D = d;

            for (int j = 0; j < 64; j++) {

                int D16 = j >>> 4; // Realiza um deslocamento lógico para a direita de j em 4 bits.
                                    // O resultado desse deslocamento é armazenado na variável D16.
                                    // Esse deslocamento é usado para dividir o valor de j por 16 (2^4), obtendo assim o valor inteiro da divisão inteira (0,1,2,3).
                int f = 0;
                int indiceBuffer = j;

                switch (D16) {
                    case 0:
                        f = (b & c) | (~b & d);
                        // É calculado o valor de f usando uma combinação de operadores bit a bit (&, |, ~).
                        break;
                    case 1:
                        f = (b & d) | (c & ~d);
                        indiceBuffer = (indiceBuffer * 5 + 1) & 0x0F;
                        // É calculado o valor de f de forma semelhante ao caso 0,
                        // Mas há também uma linha adicional que atualiza o valor da variável indiceBuffer usando uma fórmula de multiplicação e adição.
                        break;
                    case 2:
                        f = b ^ c ^ d;
                        indiceBuffer = (indiceBuffer * 3 + 5) & 0x0F;
                        // É calculado o valor de f usando o operador bit a bit ^ (ou exclusivo).
                        // Também há uma linha que atualiza o valor de indiceBuffer usando uma fórmula de multiplicação e adição.
                        break;
                    case 3:
                        f = c ^ (b | ~d);
                        indiceBuffer = (indiceBuffer * 7) & 0x0F;
                        // É calculado o valor de f usando uma combinação de operadores bit a bit e operador de negação (~).
                        // Também há uma linha que atualiza o valor de indiceBuffer usando uma fórmula de multiplicação
                        break;
                }
                int Aux = b + Integer.rotateLeft(a + f + buffer[indiceBuffer] + Tabela[j], Números[(D16 << 2) | (j & 3)]);
                a = d;
                d = c;
                c = b;
                b = Aux;
            }
            a += Original_A;
            b += Original_B;
            c += Original_C;
            d += Original_D;
        }

        byte[] md5 = new byte[16];
        int Contador = 0;

        for (int i = 0; i < 4; i++) {
            int n = (i == 0) ? a : ((i == 1) ? b : ((i == 2) ? c : d));
            // O loop externo percorre quatro iterações, controlado pela variável i.
            // A variável n é inicializada com o valor de a, b, c ou d dependendo do valor de i.
            // Isso é feito usando operadores ternários para selecionar a variável correta com base no valor de i.
            for (int j = 0; j < 4; j++) {
                md5[Contador++] = (byte) n;
                n >>>= 8;
                // O loop interno percorre quatro iterações, controlado pela variável j.
                // Dentro desse loop, o valor de n é convertido para o tipo byte e atribuído ao array md5.
                // Em seguida, o valor de n é deslocado lógicamente para a direita em 8 bits usando o operador >>>= 8.
                // Isso é feito para obter o próximo byte do valor original de n e armazená-lo no array md5 nas próximas iterações do loop interno.
            }
        }
        return md5;
    }

    public static String Texto_Hexadecimal(byte[] b) {
        StringBuilder Construtor = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            Construtor.append(String.format("%02x", b[i] & 0xFF));
        }
        return Construtor.toString();
        // Itera sobre os elementos do array de bytes b usando um loop for.
        // Dentro do loop, você usa o método append() do StringBuilder para adicionar a representação hexadecimal de cada byte à sequência.
        // A expressão String.format("%02x", b[i] & 0xFF) é usada para formatar cada byte como uma sequência hexadecimal de dois caracteres.
        // O "%02x" especifica que o valor será formatado como um inteiro hexadecimal com dois caracteres, sendo o 0 utilizado para preencher com zeros à esquerda, se necessário.
        // O b[i] & 0xFF garante que apenas os 8 bits menos significativos do byte sejam considerados, ignorando qualquer extensão de sinal.
    }

   /* public static void main(String[] args) {
        String teste = "1Daniel";
        System.out.println("0x" + toHexString(CriptoMD5(teste.getBytes())));
        //1Daniel - 9d358c0d28ee3a6f5a35078a511f25ed
        //Fonte: https://www.md5hashgenerator.com
    }*/
}
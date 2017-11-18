/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplinas: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 * 
 * Cálculo da Mediana com complexidade linear utilizando o select aleatório
 *
 * Atenção:
 * Vetor em java inicia em 0, os algoritmos consideram início em 1.
 * A subtraçào de -1 ocorre somente no local de acesso ao vetor ou matriz 
 * para manter a compatibilidade entre os algoritmos.
 *
 */

/**
 * @author Osmar de Oliveira Braz Junior
 */
public class Principal {

    /**
     * Seleciona um numero aleatorio no intervalo de inicio a fim.
     *
     * @param inicio Inicio do intervalo do número aleatório
     * @param fim Fim do intervalo do número aleatório
     */
    public static int aleatorio(int inicio, int fim) {
        return (int) Math.ceil(Math.random() * (fim - inicio + 1)) - 1 + inicio;
    }

    /**
     * Realiza a troca de posição de dois elementos do vetor.
     *
     * @param A Vetor que contem os dados
     * @param i Primeira posição de troca
     * @param j Segunda posição de troca
     */
    public static void troca(int[] A, int i, int j) {
        int aux = A[i - 1];
        A[i - 1] = A[j - 1];
        A[j - 1] = aux;
    }

    /**
     * Particione encontra o pivo.
     *
     * Complexidade de tempo Theta(n).
     * T(n) = Theta(2n + 4) + O(2n) = Theta(n).
     *
     * Slide 68 da aula 01/09/2017.
     *
     * @param A Vetor com os dados
     * @param p Início do vetor
     * @param r Fim do vetor
     * @return o pivo da troca
     */
    public static int particione(int A[], int p, int r) {
        //x é o "pivô"
        int x = A[r - 1];                     //Theta(1)
        int i = p - 1;                      //Theta(1)
        for (int j = p; j <= r - 1; j++) {  //Theta(n)
            if (A[j - 1] <= x) {              //Theta(n)
                i = i + 1;                  //O(n)
                troca(A, i, j);             //O(n)
            }
        }
        troca(A, i + 1, r);                 //Theta(1)
        return i + 1;                       //Theta(1)
    }

    /**
     * Particione aleatório encontra o pivo de forma aleatória.
     *
     * Slide 91 da aula 01/09/2017 .
     *
     * @param A Vetor com os dados
     * @param p Início do vetor
     * @param r Fim do vetor
     * @return o pivo da troca
     */
    public static int particioneAleatorio(int A[], int p, int r) {
        //i é o "pivô"
        int j = aleatorio(p, r);              //Theta(1)          
        troca(A, j, r);                       //Theta(1)
        return particione(A, p, r);           //Theta(n)
    }

    /**
     * Recebe um vetor A[1...n] e devolve o valor do i-ésimo menor elemento de
     * A. A complexidade de tempo no pior caso n = r - p + 1 T(n) = max{T(k-1),
     * T(n-k)} + Theta(n) T(n) = Theta(n^2)
     *
     * A complexidade para o caso médio é Theta(n)
     *
     * @param A Vetor com os valores
     * @param p Posição inicial do vetor
     * @param r Posição final do vetor
     * @param i Posição desejada do vetor
     * @return Um valor do elemento da posição i do vetor
     */
    public static int selectAleatorio(int A[], int p, int r, int i) {
        if (p == r) {                                       //Theta(1)
            return A[p - 1];                                //O(1)
        }
        int q = particioneAleatorio(A, p, r);               //Theta(n)                
        int k = q - p + 1;                                  //Theta(n)
        if (i == k) { //O valor do pivô é a resposta        //Theta(n)
            return A[q - 1];                                //O(1)   
        } else {
            if (i < k) {                                    //O(1)   
                return selectAleatorio(A, p, q - 1, i);     //T(k-1)  
            } else {
                return selectAleatorio(A, q + 1, r, i - k); //T(n-k)
            }
        }
    }

    /**
     * Cálculo da Mediana com complexidade Linear. 
     * 
     * Utilizando o select aleatório com complexidade Theta(n)
     *
     * @param A Vetor com os dados
     * @param p Início do Vetor
     * @param r Quantidade de elementos do vetor
     * @return A posição da mediana em A
     */
    private static int medianaN(int A[], int p, int r) {
        int n = r;
        int m = n / 2;
        selectAleatorio(A, p, n, m); //Particiona todo o vetor coloca a media na posição m
        if (n % 2 == 1) {
            return (p + m);
        } else {
            //Particiona todo o vetor para encontrar a mediana
            return ((p + m) + (p + m - 1)) / 2;
        }
    }

    public static void main(String args[]) {

        //Vetor de dados
        int A[] = {99, 33, 55, 77, 11, 22, 88, 66, 44};
        
        //Quantidade de elementos
        int r = A.length;

        System.out.println(">>> Cálculo da Mediana com complexidade linear<<<");
        System.out.println("Vetor A antes: ");
        for (int i = 0; i < r; i++) {
            System.out.println((i + 1) + " - " + A[i]);
        }

        //Localiza a mediana
        int q = medianaN(A, 1, r);
                
        System.out.println("A mediana está na posição: " + q);        
        System.out.println("O Valor é da Mediana: " + A[q - 1]);

        System.out.println("Vetor A após: ");
        for (int i = 0; i < r; i++) {
            System.out.println((i + 1) + " - " + A[i]);
        }
    }
}

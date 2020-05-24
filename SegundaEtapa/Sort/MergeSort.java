package Sort;

import java.util.ArrayList;

public class MergeSort {
    Contrato max = new Contrato();
    long time_ms;

    public MergeSort() {
        max.setFornecedor(Integer.MAX_VALUE);
        max.setMesIni(Integer.MAX_VALUE);
        max.setMesFim(Integer.MAX_VALUE);
        max.setValorTotal(Integer.MAX_VALUE);

    }
    public void run(ArrayList<Contrato> contratos, int inicio, int fim) {

        long start = System.currentTimeMillis();
        RUN(contratos,inicio,fim);
        time_ms = System.currentTimeMillis() - start;
        DAO.WriteTxt(contratos,"Output\\OutputMergeSort.txt");
    }
    public long get_execution_time(){
        return time_ms;
    }

    private void RUN(ArrayList<Contrato> contratos, int inicio, int fim) {
        // faz a divisão do array ate chegar em um problema 1x1 e aplica o merge
        if (inicio < fim) {                                                 //
            int meio = (inicio + fim) / 2;                                  //
            RUN(contratos, inicio, meio);                                   //                                    
            RUN(contratos, meio + 1, fim);                                  //  AT(N/B)+f(N)
            merge(contratos, inicio, meio, fim);                            //  2T(N/2)+Theta(N)
                                                                            //  N=N caso 2 
                                                                            // Theta(N*lg(N))
        }
       
    }

    public void merge(ArrayList<Contrato> contratos, int inicio, int meio, int fim) {
        //
        int i = 0, j = 0;                                            //     1
        int n1 = meio - inicio + 1;                                  //     1
        int n2 = fim - meio;                                         //     1
        Contrato[] L = new Contrato[n1 + 1];                         //     1
        Contrato[] R = new Contrato[n2 + 1];                         //     1
        // separa metade do array para cada um dos auxiliares
        for (i = 0; i < n1; i++) {                                   //     (n/2)+1
            L[i] = contratos.get(inicio + i);                        //     (n/2)
        }
        for (j = 0; j < n2; j++) {                                   //     (n/2)+1
            R[j] = contratos.get(meio + j + 1);                      //     (n/2)
        }
        L[n1] = max;                                                 //     1
        R[n2] = max;                                                 //     1
        i = 0;                                                       //     1
        j = 0;                                                       //     1
        // pega o menor elemento entre as mesmas possições do dos vetores e os adiciona ao array list
        for (int k = inicio; k <= fim; k++) {                        //     n+1
            if (L[i].getValorTotal() <= R[j].getValorTotal()) {      //     n
                contratos.set(k, L[i++]);                            //     >=1 e <=n
            } else {                                                 //     >=1 e <=n
                contratos.set(k, R[j++]);                            //     >=1 e <=n
            }                                                        //  O(N)
        }
    }

}
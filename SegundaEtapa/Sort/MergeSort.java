

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
        ArrayList<Contrato> c = RUN(contratos,inicio,fim);
        time_ms = System.currentTimeMillis() - start;
        DAO.WriteTxt(c,"Output\\OutputMergeSort.txt");
       

    }
    public long get_execution_time(){
        return time_ms;
    }

    private ArrayList<Contrato> RUN(ArrayList<Contrato> contratos, int inicio, int fim) {

        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            RUN(contratos, inicio, meio);
            RUN(contratos, meio + 1, fim);
            merge(contratos, inicio, meio, fim);
            
        }
        return contratos;

    }

    public void merge(ArrayList<Contrato> contratos, int inicio, int meio, int fim) {
       
        int i = 0, j = 0;
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;
        Contrato[] L = new Contrato[n1 + 1];
        Contrato[] R = new Contrato[n2 + 1];
        for (i = 0; i < n1; i++) {
            L[i] = contratos.get(inicio + i);
        }
        for (j = 0; j < n2; j++) {
            R[j] = contratos.get(meio + j + 1);
        }

        L[n1] = max;
        R[n2] = max;
        i = 0;
        j = 0;
        for (int k = inicio; k <= fim; k++) {

            if (L[i].getValorTotal() <= R[j].getValorTotal()) {
                contratos.set(k, L[i++]);
            } else {
                contratos.set(k, R[j++]);
            }
        }
    }

}
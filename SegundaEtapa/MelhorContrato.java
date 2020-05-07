import Sort.HeapSort;
import Sort.InsertionSort;
import Sort.MergeSort;
import Sort.QuickSort;
import Sort.DAO;
import Sort.Contrato;
import java.util.ArrayList;
public class MelhorContrato {
    public static void main(String[] args) {

        DAO tratamento = new DAO("entrada.txt");
        ArrayList<Contrato> lContratos = tratamento.getLista();
        float[][][] MContratos = tratamento.CreateGetMatrix();
        int head[] = tratamento.head();
        System.out.println("######## Informações da entrada ########");        
        System.out.println("Quantidade de meses: " + head[0]);
        System.out.println("Quantidade de Fornecedores: " + head[1]);
        tratamento.variablesInformation();

        /// Fazendo Ordenações
        MergeSort mergesort = new MergeSort();
        mergesort.run(lContratos, 0, lContratos.size()-1);
        System.out.println("Tempo de execução MergeSort: "+mergesort.get_execution_time()+" ms");
        /*
        HeapSort heapsort=  new HeapSort();
        heapsort.run(add entradas nescessarias);
        System.out.println("Tempo de execução HeapSort: "+heapsort.get_execution_time()+" ms");
        
        InsertionSort insertionsort =  new InsertionSort();
        insertionsort.run(add entradas nescessarias);
        System.out.println("Tempo de execução InsertionSort: "+insertionsort.get_execution_time()+" ms");

        QuickSort quicksort = new QuickSort();
        quicksort.run(add entradas nescessarias);
        System.out.println("Tempo de execução QuickSort: "+insertionsort.get_execution_time()+" ms");
        */
        
        
    }
}
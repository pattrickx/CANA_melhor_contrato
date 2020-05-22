import Sort.HeapSort; //davi
import Sort.InsertionSort; //eliude
import Sort.MergeSort; //Patrick
import Sort.QuickSort;// Lucas
import Sort.MinHeapSort;//Patrick
import Sort.DAO;
import Sort.Contrato;
import java.util.ArrayList;
public class MelhorContrato {
    public static void head(ArrayList<Contrato>  lista){
        for (int i = 0;i<5;i++ )
            System.out.println(lista.get(i).getFornecedor() + " " + lista.get(i).getMesIni() + " " + lista.get(i).getMesFim() + " " +lista.get(i).getValorTotal());
    }

    public static void main(String[] args) {

        DAO tratamento = new DAO("entrada.txt");
        ArrayList<Contrato> lContratos = tratamento.getLista();
        float[][][] MContratos = tratamento.CreateGetMatrix();
        int head[] = tratamento.head();
        System.out.println("######## Informações da entrada ########");
        System.out.println("Quantidade de meses: " + head[0]);
        System.out.println("Quantidade de Fornecedores: " + head[1]);
        tratamento.variablesInformation();
        // for(int i=0;i<10;i++){
        //     for(int j=0;j<10;j++){
        //         System.out.print(MContratos[0][i][j]+"  ");
        //     }    System.out.println();
        // } 
        // System.out.println();
        // System.out.println();
        // Fazendo Ordenações

        head(lContratos);
        MinHeapSort min= new MinHeapSort();
        System.out.println("Iniciando MinHeapSort");
        min.buildminheap(MContratos);
        System.out.println("Tempo de execução MinHeapSort: "+min.get_execution_time()+" ms\n");
        
       
        
        head(lContratos);
        MergeSort mergesort = new MergeSort();
        System.out.println("Iniciando MergeSort");
        mergesort.run((ArrayList<Contrato>) lContratos.clone() ,  0, lContratos.size()-1);
        System.out.println("Tempo de execução MergeSort: "+mergesort.get_execution_time()+" ms\n");
        
        head(lContratos);
        QuickSort quicksort = new QuickSort();
        System.out.println("Iniciando QuickSort");
        quicksort.run((ArrayList<Contrato>) lContratos.clone(),0,lContratos.size()-1);
        System.out.println("Tempo de execução QuickSort: "+quicksort.get_execution_time()+" ms\n");
        
        head(lContratos);
        HeapSort heapsort = new HeapSort();
        System.out.println("Iniciando HeapSort");
        heapsort.run((ArrayList<Contrato>) lContratos.clone());
        System.out.println("Tempo de execução HeapSort: "+heapsort.get_execution_time()+" ms\n");
        
        head(lContratos);
        InsertionSort insertionsort = new InsertionSort();
        System.out.println("Iniciando InsertionSort");
        insertionsort.InsertionSort((ArrayList<Contrato>) lContratos.clone());
        System.out.println("Tempo de execução insertionsort: "+insertionsort.get_execution_time()/1000+" s\n");
        
        
    }
}
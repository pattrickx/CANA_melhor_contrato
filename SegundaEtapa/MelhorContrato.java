import Sort.HeapSort;
import Sort.InsertionSort;
import Sort.MergeSort;
import Sort.QuickSort;
import Sort.DAO;
import Sort.Contrato;
import java.util.ArrayList;
public class MelhorContrato {
    
    public static void main(String[] args) {

        DAO tratamento= new DAO("entrada.txt");
        ArrayList<Contrato> lContratos= tratamento.getLista();
        float[][][] MContratos= tratamento.CreateGetMatrix();
        int head[] = tratamento.head();
        System.out.println("Quantidade de meses: "+head[0]);
        System.out.println("Quantidade de Fornecedores: "+head[1]);
        //MergeSort mergesort = new MergeSort();

        //mergesort.run();
        
    }
}
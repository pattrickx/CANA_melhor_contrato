package Sort;
import java.util.ArrayList;

public class HeapSort {
    static long time_ms;

    public static void run(ArrayList<Contrato> lista) {
        long start = System.currentTimeMillis();

        montarArvoreHeap(lista);
        int n = lista.size();
        for (int i = lista.size() - 1; i > 0; i--) {
			//System.out.println("Heap: "+i);
            Contrato aux = lista.get(i);
            lista.add(i, lista.get(0));
            lista.add(0, aux);
            ordenarArvore(lista, 0, --n);
        }
        time_ms = System.currentTimeMillis() - start;
			System.out.println("Heap: "+time_ms);
        DAO.WriteTxt(lista,"Output\\OutputHeapSort.txt");

    }
    public long get_execution_time(){
        return time_ms;
    }

    public static void montarArvoreHeap(ArrayList<Contrato> lista) {
        for(int posicao = (lista.size() - 1) /2; posicao >= 0; posicao--) {
            ordenarArvore(lista, posicao, lista.size());
        }
    }

    public static void ordenarArvore(ArrayList<Contrato> lista, int posicao, int tamanhoDoVetor) {
        
        // O indice de um no filho é duas vezes a posição do nó pai + 1

        int indiceFilhoUm = 2 * posicao + 1;
        int indiceFilhoDois = indiceFilhoUm + 1;

        if(indiceFilhoUm < tamanhoDoVetor) {
            if(indiceFilhoDois < tamanhoDoVetor) {
                if(lista.get(indiceFilhoUm).getValorTotal() < lista.get(indiceFilhoDois).getValorTotal()) {
                    indiceFilhoUm = indiceFilhoDois;
                }
            }
            if(lista.get(indiceFilhoUm).getValorTotal() > lista.get(posicao).getValorTotal()) {
                Contrato aux = lista.get(indiceFilhoUm);
                lista.set(indiceFilhoUm, lista.get(posicao));
                lista.set(posicao, aux);
    
                ordenarArvore(lista, indiceFilhoUm, tamanhoDoVetor);
            }
        }
    } 
}
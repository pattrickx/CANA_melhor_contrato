package Sort;
import java.util.ArrayList;

public class HeapSort {
    static long time_ms;

    public static void run(ArrayList<Contrato> lista) {
        long start = System.currentTimeMillis();

        montarArvoreHeap(lista);
        int n = lista.size();
        for (int i = lista.size() - 1; i >= 0; i--) {
			//System.out.println("Heap: "+i);
            Contrato aux = lista.get(i);
            lista.set(i, lista.get(0));
            lista.set(0, aux);
            ordenarArvore(lista, 0, --n);
        }
        time_ms = System.currentTimeMillis() - start;
			System.out.println("Heap: "+time_ms);
        DAO.WriteTxt(lista,"Output\\OutputHeapSort.txt");

    }
    public long get_execution_time(){
        return time_ms;
    }


    // Montar Árvore
    public static void montarArvoreHeap(ArrayList<Contrato> lista) {
        for(int posicao = (lista.size() - 1) /2; posicao >= 0; posicao--) {
            ordenarArvore(lista, posicao, lista.size());
        }
    }

    public static void ordenarArvore(ArrayList<Contrato> lista, int posicao, int tamanhoDoVetor) {
        
        // Regra: indice do filho 1 é igual a 2x o indice do pai + 1
        int indiceFilhoUm = 2 * posicao + 1;

        // Regra: o indice do filho 2 é igual ao indice do filho 1 + 1
        int indiceFilhoDois = indiceFilhoUm + 1;

        // Conferir se o indice dos dois filhos são menores que o tamanho do vetor
        if(indiceFilhoUm < tamanhoDoVetor) {
            if(indiceFilhoDois < tamanhoDoVetor) {

                // Atribuir o maior valor ao indice do filho 1 para posteriormente compararmos ele com o pai
                if(lista.get(indiceFilhoUm).getValorTotal() < lista.get(indiceFilhoDois).getValorTotal()) {
                    indiceFilhoUm = indiceFilhoDois;
                }
            }

            // Ver se o maior filho é maior que o pai
            if(lista.get(indiceFilhoUm).getValorTotal() > lista.get(posicao).getValorTotal()) {

                // Caso seja maior trocaremos ele de posição com seu pai
                Contrato aux = lista.get(indiceFilhoUm);
                lista.set(indiceFilhoUm, lista.get(posicao));
                lista.set(posicao, aux);
    
                // Repetiremos o processo porém agora começando pelo filho
                ordenarArvore(lista, indiceFilhoUm, tamanhoDoVetor);
            }
        }
    } 
}
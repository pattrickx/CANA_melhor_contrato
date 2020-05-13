package Sort;

import java.util.ArrayList;

public class InsertionSort {
	// iniciando a escrita do código do InsertionSort
	// Eliude Soares!
	long time_ms;

	public void InsertionSort(ArrayList<Contrato> contratos) {

		long inicio = System.currentTimeMillis();
		int j;
		int n = contratos.size();
		System.out.println(n);
		Contrato aux;

		for (int i = 1; i < n; i++) {
			//System.out.println("Insertion: "+i);
			aux = contratos.get(i);

			j = i - 1;

			while (j >= 0 && contratos.get(j).valorTotal > aux.valorTotal) {

				contratos.set(j + 1, contratos.get(j));

				j = j - 1;

			}
			contratos.set(j + 1, aux);

		}
		time_ms = System.currentTimeMillis() - inicio;
			System.out.println("Insertion: "+time_ms);
		DAO.WriteTxt(contratos,"Output\\OutputInsertionSort.txt");
		
	}
	public long get_execution_time(){
        return time_ms;
    }

}

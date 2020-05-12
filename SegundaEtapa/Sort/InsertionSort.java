import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class InsertionSort {
	// iniciando a escrita do código do InsertionSort
	// Eliude Soares!
	long fim;

	public void InsertionSort(ArrayList<Contrato> contratos) {

		ArrayList<Contrato> aAux = (ArrayList<Contrato>) contratos.clone();

		int j;
		long inicio = System.currentTimeMillis();
		int n = aAux.size();
		System.out.println(n);
		Contrato aux;

		for (int i = 0; i < n; i++) {
			System.out.println("i(" + i + "): " + aAux.get(i).valorTotal);
			aux = contratos.get(i);

			j = i - 1;

			while (j >= 0 && aAux.get(j).valorTotal > aux.valorTotal) {

				aAux.set(j + 1, aAux.get(j));

				j = j - 1;

			}
			aAux.set(j + 1, aux);

		}
		fim = System.currentTimeMillis() - inicio;
		exportarDados(aAux);
		
	}

	public void exportarDados(ArrayList<Contrato> saida) {

		String fileName = "saida_ordenada.txt";

		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			for (Contrato cc : saida) {

				writer.write(cc.fornecedor + "|" + cc.getValorTotal() + "\n");

			}

			writer.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}

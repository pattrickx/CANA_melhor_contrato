package Sort;

import java.util.ArrayList;

public class InsertionSort {
	// iniciando a escrita do código do InsertionSort
	// Eliude Soares!
	long time_ms;

	public void InsertionSort(ArrayList<Contrato> contratos) {


		long inicio = System.currentTimeMillis(); //inicio = tempo do sistema nesse momento										
		int j; // o marcador de posições anteriores ao i(índice posição da minha chave)
		int n = contratos.size(); // n é o tamanho no vetor de contratos, onde estão todos os contratos de todos os fornecedores
		//System.out.println(n);//----------------------------------------------------------C1-------------(1)
		Contrato aux;


		// nesse for, vamos percorrer do segundo elemento do vetor até a última posição do vetor
		for (int i = 1; i < n; i++) {//---------------------------------------------------C2-------------(N)
			//System.out.println("Insertion: "+i);

			// aux recebe o contrato na posição i, pois ele será nossa chave de comparação
			aux = contratos.get(i);//-----------------------------------------------------C3-------------(N-1)
			// j recebe o contrato na posição imediatamente anterior ao contrato da posição i
			j = i - 1;//------------------------------------------------------------------C4-------------(N-1)


			// verificamos se o valor de j é maior ou igual a 0 e se o o valor do contrato na posição j é maior
			// que o valor do contrato na posição i(seu posterior)
			// se atender a essas condições, então haverá o switch de posições, onde substituiremos 
			// o contrato que estava na posição i, pelo contrato na posição j.
			while (j >= 0 && contratos.get(j).valorTotal > aux.valorTotal) {/*------------C5-------------melhor caso: 1*(N-1) 

																									   pior caso:		
																									   i=1: (2)
																									   i=2: (3)
																										.
																										.
																										.
																										i=N-1: (N) // faz 1 teste a mais
																										p.a = {(2+N)*(N-1)}/2 = (N^2 + N -2)/2*/
				// contratos.set(posicao j + 1 = 'i', pelo contrato na posição j)			
				contratos.set(j + 1, contratos.get(j));/*---------------------------------C6-------------melhor caso: 0

				 																				       pior caso:		
																									   i=1: (1)
																									   i=2: (2)
																										.
																										.
																										.
																										i=N-1: (N-1)
																										p.a = {[1+(n-1)]*(N-1)}/2 = (N^2 - N)/21*/
				// j será decrementado em uma posição e um novo teste de while é feito
				j = j - 1;/*--------------------------------------------------------------C7-------------melhor caso: 0

				 																					   pior caso:		
																									   i=1: (1)
																									   i=2: (2)
																										.
																										.
																										.
																										i=N-1: (N-1)
																										p.a = {[1+(n-1)]*(N-1)}/2 = (N^2 - N)/2*/

				}

																										/*Complexidade: O(N^2)
																										Notação: N = n^2*m, n = número de meses 
																														  m = número de fornecedores*/
			// como o j já foi decrementado dentro do while, então o aux agora será posicionado 
			// na posição correta, ordenada.
			contratos.set(j + 1, aux);//--------------------------------------------------C8-------------(N-1)

		}
		time_ms = System.currentTimeMillis() - inicio; // calculo do tempo do sistema, subtraído do valor inicial.
			System.out.println("Insertion: "+time_ms);
		DAO.WriteTxt(contratos,"Output\\OutputInsertionSort.txt");
	
	}
	public long get_execution_time(){
        return time_ms;
    }

}

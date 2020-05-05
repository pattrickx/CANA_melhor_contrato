public class InsertionSort {
    //iniciando a escrita do código do InsertionSort
    //Eliude Soares!
	
	ArrayList<Contrato> aAux = contratos;
	int i, j;
	
	int n = aAux.size();
	
	float aux;
	for(i = 1; i < n; i++) {
	
		aux = aAux.get(i).valorTotal;
		
		for(j = i; (j>0)&&(aux < aAux.get(j-1).getValorTotal()); j--) {
			aAux.get(j).valorTotal = aAux.get(j-1).getValorTotal();
			contratos.get(j).setValorTotal(aux);
		}
	
	}
	
	return contratos;
}

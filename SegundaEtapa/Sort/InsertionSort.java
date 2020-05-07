import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class InsertionSort {
    //iniciando a escrita do código do InsertionSort
    //Eliude Soares!
	
	
	public ArrayList<Contrato> InsertionSort(ArrayList<Contrato> contratos) {
	
		
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
		exportarDados(contratos);
		return contratos;
	}
	
	
	public void exportarDados(ArrayList<Contrato> saida) {
		
		String fileName = "saida_ordenada.txt";
		
	
			
			
			BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter(fileName));
				for(Contrato cc : saida) {

					
					writer.write(cc.fornecedor + "|" +cc.getValorTotal()+"\n");	
					
				}
				
				writer.close();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		
		
		
	}
	
	
	
}

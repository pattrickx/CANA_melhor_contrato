import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Controle {

	public ArrayList<Contrato> lista = new ArrayList<>();

	String path = "C:\\Users\\eliud\\Desktop\\entrada.txt";

	FileReader arq;
	BufferedReader br;

	public Contrato c;
	int meses, forn = 0;

	public Controle() {

	}

	public ArrayList<Contrato> importarDados() {

	
		try {

			arq = new FileReader(path);
			br = new BufferedReader(arq);

			String linha;
		//	int k = 100;
			String firstLineSplit[] = br.readLine().split(" ");
			meses = Integer.parseInt(firstLineSplit[0]);
			forn = Integer.parseInt(firstLineSplit[1]);

			linha = br.readLine();

			while (linha != null) {

				String linhaSplit[] = linha.split(" ");

				c = new Contrato();
				c.setFornecedor(Integer.parseInt(linhaSplit[0]));
				c.setMesIni(Integer.parseInt(linhaSplit[1]));
				c.setMesFim(Integer.parseInt(linhaSplit[2]));
				c.setValorTotal(Float.parseFloat(linhaSplit[3]));

				lista.add(c);

				linha = br.readLine();

			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(lista.size());
		// System.out.println(lista.get(0).valorTotal);
		System.out.println("Quantidade de Meses: " + meses);
		System.out.println("Quantidade de Fornecedores: " + forn);
		return lista;
	}

	public void contratoMenorValor(ArrayList<Contrato> lista, int forn) {

		double menorValor = 999999;
		double timeIni = System.currentTimeMillis();
		Contrato c = new Contrato();
		System.out.println("contrato menor valor");

		for (Contrato cc : lista) {

			if (cc.getFornecedor() == forn) {

				if (cc.valorTotal < menorValor) {
					menorValor = cc.valorTotal;
					c = cc;
				}
			}

		}
		Contrato d = new Contrato();
		d = lista.get(7260 * forn - 1);
		System.out.println("tempo de execucao: " + (System.currentTimeMillis() - timeIni) / 1000);
		System.out.println("\n*****menor contrato mensal***** -> \nFornecedor: " + c.getFornecedor() + "\nmes inicial: "
				+ c.getMesIni() + "\nmes final: " + c.getMesFim() + "\nvalor contrato: " + c.getValorTotal());

		System.out.println("\n*****menor contrato periodo de 120 meses***** -> \nFornecedor: " + d.getFornecedor()
				+ "\nmes inicial: " + d.getMesIni() + "\nmes final: " + d.getMesFim() + "\nvalor contrato: "
				+ d.getValorTotal());
	}

	public Contrato[][] matrizTotal(ArrayList<Contrato> lista) {

		Contrato[][] mContrato = new Contrato[meses + 1][meses + 1];

		double timeIni = System.currentTimeMillis();
		Contrato c = new Contrato();

		for (Contrato cc : lista) {
			if (mContrato[cc.mesIni][cc.mesFim] == null) {
				mContrato[cc.mesIni][cc.mesFim] = cc;
			} else if (mContrato[cc.mesIni][cc.mesFim].valorTotal > cc.valorTotal) {
				mContrato[cc.mesIni][cc.mesFim] = cc;

			}

		}

		System.out.println("tempo de execucao: " + (System.currentTimeMillis() - timeIni) / 1000);
		System.out.println("menor contrato mercado -> \nFornecedor: " + c.getFornecedor() + "\nmes inicial: "
				+ c.getMesIni() + "\nmes final: " + c.getMesFim() + "\nvalor contrato: " + c.getValorTotal());
		return mContrato;

	}

	public float menorContratoMeses(Contrato[][] matriz, int mesIni, int mesFim) {
		float menorValor = 0;

		if (mesIni == mesFim) {
			menorValor = matriz[mesIni][mesFim].valorTotal;

		}

		if ((matriz[mesIni][mesIni].valorTotal
				+ matriz[mesFim][mesFim].valorTotal) < matriz[mesIni][mesFim].valorTotal) {
			menorValor = (matriz[mesIni][mesIni].valorTotal + matriz[mesFim][mesFim].valorTotal);

		} else {
			menorValor = matriz[mesIni][mesFim].valorTotal;

		}

		menorValor = matriz[mesIni][mesIni].valorTotal + menorContratoMeses(matriz, mesIni + 1, mesFim);

		return menorValor;

	}

	public void contratoMenorIntervaloMes(ArrayList<Contrato> lista, int mesIni, int mesFim) {

		double menorValor = 999999;
		double timeIni = System.currentTimeMillis();
		Contrato c = new Contrato();
		System.out.println("contrato menor valor");

		for (Contrato cc : lista) {

			if (cc.valorTotal < menorValor) {
				menorValor = cc.valorTotal;
				c = cc;

			}

		}
		System.out.println("tempo de execucao: " + (System.currentTimeMillis() - timeIni) / 1000);
		System.out.println("menor contrato mercado -> \nFornecedor: " + c.getFornecedor() + "\nmes inicial: "
				+ c.getMesIni() + "\nmes final: " + c.getMesFim() + "\nvalor contrato: " + c.getValorTotal());
	}

	public ArrayList<Contrato> insertionSort(ArrayList<Contrato> contratos) {
		
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
			//System.out.println("encerrou...");
		}
		//System.out.println("encerrou...2");
		return contratos;
	}

	public void exportarDados(ArrayList<Contrato> saida) {
		
		String fileName = "C:\\Users\\eliud\\Desktop\\saida_ordenada.txt";
		
	
			
			
			BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter(fileName));
				for(Contrato cc : saida) {

					
					writer.write(cc.fornecedor + "|" +cc.getValorTotal()+"\n");	
					
				}
				
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	}
}

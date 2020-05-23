package Sort;

import java.util.ArrayList;
public class MinHeapSort {
    static long time_ms;
	public int left (int i) {
		return 2*(i)+1;
	}

	public int right (int i) {
		return 2*(i)+2;
	}

	public void minheapfy (Contrato[] V, int i, int size) {
		int l = 2*(i)+1;
		int r = l+1;

        if(l < size) {
            if(r < size) {
                if(V[l].getValorTotal() > V[r].getValorTotal()) {
                    l = r;
                }
            }
            if(V[l].getValorTotal() < V[i].getValorTotal()) {
                Contrato aux = V[l];
                V[l]= V[i];
                V[i]=aux;
    
                minheapfy(V, l, size);
            }
        }
	}

	public void buildminheap (float[][][] m) {
        long start = System.currentTimeMillis();                                  //
        // for(int i=0;i<10;i++){
            ArrayList<Contrato> organizado= new ArrayList<Contrato>();            // 1
            
            Contrato[] V= new Contrato[m.length*m[0].length];                      //1
            int vp=0;                                                              //1
            // pega contratos das diagonais principais de todos os fornecedores e adiciona a um array
            for(int i = 0;i<m.length;i++){                                         // M+1
            for(int j=0;j<m[0].length;j++){                                        // M*N+1
                V[vp]=new Contrato();                                              // M*N
                V[vp].fornecedor=i;                                                // M*N
                V[vp].mesIni=j;                                                    // M*N
                V[vp].mesFim=j;                                                    // M*N
                V[vp].valorTotal=m[i][j][j];                                       // M*N
                vp++;                                                              // M*N
            }    
            }
            /// criar  arvore heap minimo
            for (int a = (V.length/2)-1; a >=0 ; a--) {                            // M/2
                minheapfy(V, a,V.length);                                          // M/2 -1
               }
            
            int k = V.length - 1;                                                  // 1
            // remove menor elemento da arvore e adiciona o proximo de sua fila se houver, 
            // se n houver realisa a execução convencional do heap sorte removendo o ultimo elemento da fila e adicionando no topo no lugar do removido
           while(k >= 0 ) {                                                        // M*N+1
                if(V[0].mesFim+1<m[0].length){                                     // M*N
                    organizado.add(V[0]);                                          //<=M*N
                    Contrato x=new Contrato();                                     //<=M*N
                    x.fornecedor=V[0].fornecedor;                                  //<=M*N
                    x.mesIni=V[0].mesIni;                                          //<=M*N
                    x.mesFim=V[0].mesFim+1;                                        //<=M*N
                    x.valorTotal=m[V[0].fornecedor][V[0].mesIni][V[0].mesFim+1];   //<=M*N
                    V[0]=x;                                                        //<=M*N
                    minheapfy(V, 0, k+1);                                          //<=M*N
                }
                else{                                                              //<=M*N
                organizado.add(V[0]);                                              //<=M*N
                V[0] =V[k];                                                        //<=M*N
                minheapfy(V, 0, k--);                                              //<=M*N
                }
            }

            time_ms = System.currentTimeMillis() - start;
            DAO.WriteTxt(organizado,"Output\\OutputMinHeapSort.txt");

    }
    public long get_execution_time(){
        return time_ms;
    }

	// public int[] criaVetorAleatorio (int n) {
	// 	Random randomGenerator = new Random();
	// 	int[] A = new int[n];
	// 	for (int i = 0; i < n; i++) {
	// 		A[i] = randomGenerator.nextInt(10 * n);
	// 	}
	// 	return A;
	// }

	public void imprimeVetori (ArrayList<Contrato> A) {
		for (int i = 0; i < A.size(); i++) {
			System.out.printf("%3.2f  ", A.get(i).valorTotal);
		}
		System.out.print("\n\n\n");
    }
    public void imprimeVetor (Contrato[] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.printf("%322f ", A[i].valorTotal);
		}
		System.out.print("\n\n\n");
	}
	
	public void imprimeHeap (Contrato[] A) {
		int h = (int) (Math.log(A.length) / Math.log(2));
		int espacos = calculaEspacos(h);
		for (int i = 0; i <= h; i++) {
			for (int j = 1; j <= Math.pow(2, i); j++) {
				if ((int) (Math.pow(2, i)) - 1 + (j-1) >= A.length) break;
				imprimeEspacos(espacos);
				System.out.printf("%3.2f", A[(int) (Math.pow(2, i)) - 1 + (j-1)].valorTotal);
				imprimeEspacos(espacos);
				if (j < Math.pow(2, i)) {
					System.out.printf("%3s", "");
				}
			}
			espacos = (espacos - 3) / 2;
			System.out.println();
		}
	}
	
	public int calculaEspacos (int h) {
		int espacos = 3;
		for (int i = 1; i <= h; i++) {
			espacos = 2 * espacos + 3;
		}
		return espacos;
	}
	
	public void imprimeEspacos (int n) {
		for (int i = 1; i <= n; i++) {
			System.out.print(" ");
		}
	}
}
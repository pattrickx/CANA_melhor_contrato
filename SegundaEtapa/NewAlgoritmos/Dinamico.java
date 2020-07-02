package NewAlgoritmos;

public class Dinamico {
    
    public void MinDinamico(float[][] values,int[][] provedores ,int inicio, int fim){
		// long time = System.currentTimeMillis();

		float m[][] = values; // pesos 
		int s[][] = new int[fim+1][fim+1]; // ponto de ultima divisão
		for(int i = 0; i < fim ; i++) 
			for (int j = i+1; j <= fim ; j++) 
				s[i][j]=-1;
		long time = System.currentTimeMillis();
        for(int i = 0; i < fim ; i++) {
			for (int j = i+1; j <= fim ; j++) {
				for (int k = i; k < j ; k++) {
                    if ( m[i][k]+m[k+1][j]<m[i][j]){
						m[i][j]=m[i][k]+m[k+1][j];
                        s[i][j]=k;
                    }
				}
			}
		}

		time=(System.currentTimeMillis() - time);
        imprime(s,values,provedores, inicio, fim);
		System.out.println("\nValor total: "+m[0][fim]);
		System.out.println("Tempo: " + time+" ms");
		
	}
	static void imprime(int[][]s,float[][] values,int[][] provedores, int i, int j) {
		if(i<j)
			if (s[i][j]==-1) {
				System.out.println((provedores[i][j]+1)+" "+(i+1)+" "+(j+1)+" "+(values[i][j]+1));
			} else {
				imprime(s,values,provedores, i, s[i][j]);
				imprime(s,values,provedores, s[i][j]+1, j);
			}
	}

}
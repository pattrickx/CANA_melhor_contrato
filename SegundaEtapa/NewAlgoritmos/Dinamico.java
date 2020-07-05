package NewAlgoritmos;

public class Dinamico {
    
    public void MinDinamico(float[][] values,int[][] provedores ,int inicio, int fim){
		// long time = System.currentTimeMillis();

		float[][] m=new float[fim+1][fim+1]; // pesos 
		System.arraycopy(values,0,m,0,fim+1);
		int[][] s = new int[fim+1][fim+1]; // ponto de ultima divisão
		for(int i = 0; i < fim ; i++) 
			for (int j = i+1; j <= fim ; j++) 
				s[i][j]=-1;
		long time = System.currentTimeMillis();
        for(int i = 0; i < fim ; i++) {                   //(N+1)
			for (int j = i+1; j <= fim ; j++) {			  //(N+1)N/2
				for (int k = i; k < j ; k++) {			  //(N+1)N^2/2
                    if ( m[i][k]+m[k+1][j]<m[i][j]){   
						m[i][j]=m[i][k]+m[k+1][j];     
                        s[i][j]=k;                     
                    }    							   
				}									   
			}
		}												//THETA(N^3)
		
		time=(System.currentTimeMillis() - time);
        imprime(s,values,provedores, inicio, fim);
		System.out.println("\nValor total: "+m[0][fim]);
		System.out.println("Tempo: " + time+" ms");
		
	}
	static void imprime(int[][]s,float[][] values,int[][] provedores, int i, int j) {
		if(i<j)
			if (s[i][j]==-1) {
				System.out.println((provedores[i][j]+1)+" "+(i+1)+" "+(j+1)+" "+values[i][j]);
			} else {
				imprime(s,values,provedores, i, s[i][j]);
				imprime(s,values,provedores, s[i][j]+1, j);
			}
	}
	//melhor caso:2T(n/2)+θ(1)   => θ(n)
	//pior caso:T(1)+T(n-1)+θ(1)
}
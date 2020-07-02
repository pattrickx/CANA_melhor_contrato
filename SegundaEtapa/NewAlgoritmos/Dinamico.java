package NewAlgoritmos;

public class Dinamico {
    
    public void MinDinamico(float[][] values,int inicio, int fim){
		long time = System.currentTimeMillis();

		float m[][] = values; // pesos 
        int s[][] = new int[fim+1][fim+1]; // ponto de ultima divisão 
        for (int i = 0; i <= fim ; i++) {
			for (int j = 1; j <= fim ; j++) {
				for (int k = i; k < j ; k++) {
                    if ( m[i][k]+m[k+1][j]<m[i][j]){
                        m[i][j]=m[i][k]+m[k+1][j];
                        s[i][j]=k;
                    }
				}

			}
		}
        
		System.out.println("Valor total: "+m[0][fim]);
		System.out.println("Tempo: " + (System.currentTimeMillis() - time)+" ms");
		
    }

}
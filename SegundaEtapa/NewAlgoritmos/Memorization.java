package NewAlgoritmos;
import java.util.ArrayList;

import Sort.Contrato;
// import Sort.DAO;

public class Memorization {
    float[][] best ;
    int[] pai;

	long time_ms;
	ArrayList<Contrato> Contratos = new ArrayList<>();

    public void MinMemorization(float[][] values, int[][] provedores, int i, int j,float taxa){
        long inicio = System.currentTimeMillis();
        best = new float[j+1][j+1];
        pai = new int[j+1];
        float MinValue=minMemorization(values, provedores,i,j,taxa);

        time_ms = System.currentTimeMillis() - inicio;
        System.out.println("Valor total: "+MinValue);
        System.out.println("Tempo: " + time_ms+" ms");
        int atual=j;
        while (atual>=0) {
            MinValue-=(values[pai[atual]][atual]+taxa);
            System.out.println(provedores[pai[atual]][atual]+" "+pai[atual]+" "+atual+" "+values[pai[atual]][atual]+" "+ MinValue);
            atual=pai[atual]-1;
        }
    }
	private float minMemorization(float[][] values, int[][] provedores, int i, int j,float taxa) {

        float qMin = values[i][j];
        pai[j]=i;
        if(i<j){
            
            for (int k = i; k <j; k++) {
                float q;

                if(best[i][k]!=0){
                    q = best[i][k];
                    pai[k]=i;
                }
                else
                    q = minMemorization(values,provedores, i, k,taxa);
                    
                if( best[k+1][j]!=0){
                    q += best[k+1][j];
                    pai[j]=k+1;
                }
                else                
                    q +=minMemorization(values,provedores, k+1, j,taxa);
                // q+=taxa;
                if(qMin>q)
                    qMin = q;


                if(best[i][j]==0|| best[i][j]>qMin){
                    best[i][j]=qMin;
                }
                else
                    qMin =best[i][j];
            }
            
            
        }
            best[i][j] = qMin;
            return qMin;
        

	}


}

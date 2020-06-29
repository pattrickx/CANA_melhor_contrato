package NewAlgoritmos;
import java.util.ArrayList;

import Sort.Contrato;
// import Sort.DAO;

public class Guloso {
    float[][] best = new float[120][120];

	long time_ms;
	ArrayList<Contrato> Contratos = new ArrayList<>();

    public void MinGuloso(float[][] values, int[][] provedores, int i, int j){
        long inicio = System.currentTimeMillis();
        float MinValue=minGuloso(values, provedores,i,j);

        time_ms = System.currentTimeMillis() - inicio;
        System.out.println("Best Value: "+MinValue);
        System.out.println("Insertion: " + time_ms+" ms");
    }
	private float minGuloso(float[][] values, int[][] provedores, int i, int j) {

        float qMin = values[i][j];
        if(i<=j){
            
            for (int k = i; k <j; k++) {
                float q;

                if(best[i][k]!=0)
                    q = best[i][k];
                else
                    q = minGuloso(values,provedores, i, k);
                    
                if( best[k+1][j]!=0)
                    q += best[k+1][j];
                else                
                    q +=minGuloso(values,provedores, k+1, j);
                
                if(qMin>q)
                    qMin = q;

                if(best[i][j]==0|| best[i][j]>qMin)
                    best[i][j]=qMin;
                else
                    qMin =best[i][j];
            }
        }
        best[i][j] = qMin;
        return qMin;

	}


}

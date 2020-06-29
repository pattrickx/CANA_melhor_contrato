package NewAlgoritmos;
import java.util.ArrayList;

import Sort.Contrato;
// import Sort.DAO;

public class Guloso {
    float[][] best = new float[120][120];

	long time_ms;
	ArrayList<Contrato> Contratos = new ArrayList<>();

    public void MinGuloso(float[][] values, int[][] provedores, int i, int j,float taxa){
        long inicio = System.currentTimeMillis();
        float MinValue=minGuloso(values, provedores,i,j,taxa);

        time_ms = System.currentTimeMillis() - inicio;
        System.out.println("Best Value: "+MinValue);
        System.out.println("Insertion: " + time_ms+" ms");
    }
	private float minGuloso(float[][] values, int[][] provedores, int i, int j,float taxa) {

        float qMin = values[i][j];
        int aux=j;
        if(i<=j){
            
            for (int k = i; k <j; k++) {
                
                if(qMin>values[i][k]+values[k+1][j]+taxa){
                    qMin=values[i][k]+values[k+1][j]+taxa;
                    aux=k;
                }
            
            }

            float q = minGuloso(values,provedores, i, aux,taxa)+minGuloso(values,provedores, aux+1, j,taxa);

        }
        best[i][j] = qMin;
        return qMin;

	}


}

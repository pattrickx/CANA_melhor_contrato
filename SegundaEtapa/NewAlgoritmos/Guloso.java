package NewAlgoritmos;
import java.util.ArrayList;

import Sort.Contrato;
// import Sort.DAO;

public class Guloso {
    float[][] best = new float[120][120];
    String contratos="";
	long time_ms;
	ArrayList<Contrato> Contratos = new ArrayList<>();

    public void MinGuloso(float[][] values, int[][] provedores, int i, int j,float taxa){
        long inicio = System.currentTimeMillis();
        float MinValue=minGuloso(values, provedores,i,j,taxa);

        time_ms = System.currentTimeMillis() - inicio;

        System.out.println(contratos);
        System.out.println("Valor total: "+MinValue);
        System.out.println("Tempo: " + (System.currentTimeMillis() - inicio)+" ms");
    }
	private float minGuloso(float[][] values, int[][] provedores, int i, int j,float taxa) {

        float qMin = values[i][j];
        int aux=j;
        if(i<j){
            for (int k = i; k <j; k++) {
                if(qMin>values[i][k]+values[k+1][j]){
                    qMin=values[i][k]+values[k+1][j];
                    aux=k;
                }
            }
            if (aux<j)
                qMin = minGuloso(values,provedores, i, aux,taxa)+minGuloso(values,provedores, aux+1, j,taxa);
            else
                contratos+=(provedores[i][j]+1)+" "+(i+1)+" "+(j+1)+" "+ (values[i][j]+1)+"\n";
            return qMin;
        }
        else{
            contratos+=(provedores[i][j]+1)+" "+(i+1)+" "+(j+1)+" "+ (values[i][j]+1)+"\n";
            return qMin;
        }
	}


}

package NewAlgoritmos;
import java.util.ArrayList;

import NewAlgoritmos.aresta;
import NewAlgoritmos.grafo;
import Sort.Contrato;
public class dijkstra {
    long time_ms;
    public int extract_min(float Q[],boolean S[]){
        int min=-1;
        for(int v=0; v<Q.length;v++){     //Theta(N)
            if(S[v]==false){
                if(min==-1)
                    min=v;
                else if(Q[v]<Q[min])
                    min=v;
            }
        }
        return min;
    }
    public void dijkstra(grafo g[],int inicio, int destino){
        long start = System.currentTimeMillis();
        // inicialisando variaveis 
        int[] fornecedores= new int[g.length];
        float[] distancia = new float[g.length];
        int[] pai = new int[g.length];
        boolean[] S = new boolean[g.length];
        for( int v=0;v< g.length;v++){                              //   n
            distancia[v] = Float.POSITIVE_INFINITY;
            S[v]=false;
            pai[v] = -1;    
        }               
        distancia[inicio] = 0;              
        
        
        float[] Q= distancia.clone();
        //arvore
        for(int i=0;i<g.length;i++){                                  // n+1
            int v_min = extract_min(Q,S);                             // n^2
            S[v_min]=true;                                            // n
            
            if(g[v_min]!=null)                                       //  n
            for(aresta a : g[v_min].arestas){                        // n*<=n 
                if(distancia[a.destino]>distancia[v_min]+a.peso){    // n*<=n
                    distancia[a.destino]=distancia[v_min]+a.peso;    // n*<=n
                    pai[a.destino]=v_min;                            // n*<=n
                    fornecedores[a.destino]=a.fornecedor;            // n*<=n
                }
            }
        
        }
        time_ms = System.currentTimeMillis() - start;
        // ArrayList<Contrato> caminho = new ArrayList<>();
        int vertice_atual= destino;
        String show="";
        while(vertice_atual!=inicio){ 
            if(pai[vertice_atual]==0 || vertice_atual-1==pai[vertice_atual])
                show=(fornecedores[vertice_atual]+" "+(pai[vertice_atual]+1)+" "+(vertice_atual)+" "+(distancia[vertice_atual]-distancia[pai[vertice_atual]])+"\n")+show;
            else
                show=(fornecedores[vertice_atual]+" "+pai[vertice_atual]+" "+vertice_atual+" "+(distancia[vertice_atual]-distancia[pai[vertice_atual]])+"\n")+show;

            vertice_atual=pai[vertice_atual];
        }
        System.out.println(show);
        System.out.println("valor total: "+distancia[destino]);
        System.out.println("tempo: "+ time_ms+" ms");


    }
}
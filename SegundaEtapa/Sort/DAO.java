package Sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import NewAlgoritmos.aresta;
import NewAlgoritmos.grafo;
public class DAO {
    private int head[];
    private float taxa;
    private ArrayList<Contrato> lista = new ArrayList<>();
    private float matrix[][][];
    private float bestValue[][];
    private int bestProvedor[][];
    private grafo g[];
    public DAO(String path) {

        try {
            FileReader arq = new FileReader(path);
            BufferedReader readArq = new BufferedReader(arq);

            String aux[] = readArq.readLine().split(" ");
            head = new int[aux.length];
            for (int i = 0; i < aux.length-1; i++)
                head[i] = Integer.parseInt(aux[i]);
            taxa = Float.parseFloat(aux[aux.length-1]);
            // head = meses, provedores
            g = new grafo[head[0]+1];
            String line = readArq.readLine();
            Contrato c;
            while (line != null) {

                String lineSplit[] = line.split(" ");
                
                c = new Contrato();
                c.setFornecedor(Integer.parseInt(lineSplit[0]) - 1);
                c.setMesIni(Integer.parseInt(lineSplit[1]) - 1);
                c.setMesFim(Integer.parseInt(lineSplit[2]) - 1);
                c.setValorTotal(Float.parseFloat(lineSplit[3]));
                lista.add(c);
                int fornecedor=Integer.parseInt(lineSplit[0]);
                int origem=Integer.parseInt(lineSplit[1]);
                int destino=Integer.parseInt(lineSplit[2]);
                float peso=Float.parseFloat(lineSplit[3]);
                if ( origem==1  ){
                    origem-=1;
                }else {
                    origem-=1;
                    peso+=taxa;
                }

                if(g[origem]==null){
                    g[origem]= new grafo();
                    g[origem].arestas.add(new aresta(peso,destino,fornecedor));
                }
                else{
                    boolean achou=false;
                    for (aresta a : g[origem].arestas) {
                        if (a.destino==destino && peso<a.peso){
                            achou =true;
                            g[origem].arestas.remove(a);
                            g[origem].arestas.add(new aresta(peso,destino,fornecedor));
                            break;
                        }
                    }
                    if(achou==false)
                        g[origem].arestas.add(new aresta(peso,destino,fornecedor));
                }
                
                
                line = readArq.readLine();

            }

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

    }
    public float[][] getBestMatrixValue(){
        return bestValue;
    }
    public int[][] getBestMatrixProvedor(){
        return bestProvedor;
    }
    public grafo[] getGrafo(){
        
        return g;
    }

    public float[][][] CreateGetMatrix() {
        matrix = new float[head[1]][head[0]][head[0]];
        bestProvedor= new int [head[0]][head[0]];
        bestValue= new float [head[0]][head[0]];
        Contrato c;
        for (int i = 0; i < lista.size(); i++) {
            c = lista.get(i);
            matrix[c.getFornecedor()][c.getMesIni()][c.getMesFim()] = c.getValorTotal();

            // float value=0;
            // if(c.getMesIni()==0)
            //     value=c.getValorTotal();
            // else
            //     value=c.getValorTotal()+taxa;

            if(bestValue[c.getMesIni()][c.getMesFim()]== 0 || bestValue[c.getMesIni()][c.getMesFim()]>c.getValorTotal()){
                bestValue[c.getMesIni()][c.getMesFim()] =c.getValorTotal();
                bestProvedor[c.getMesIni()][c.getMesFim()]=c.getFornecedor();
            }
        }
        return matrix;

    }

    public ArrayList<Contrato> getLista() {
        return lista;
    }

    public int[] head() {
        return head;

    }

    public static void WriteTxt(ArrayList<Contrato> list, String path) {
        FileWriter arq;
        try {
            arq = new FileWriter(path);
            PrintWriter gravarArq = new PrintWriter(arq);
            for (int i = 0; i < list.size(); i++) {
                gravarArq.printf("%d %d %d %.2f\n", list.get(i).getFornecedor(), list.get(i).getMesIni(),
                        list.get(i).getMesFim(), list.get(i).getValorTotal());
            }
            arq.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void variablesInformation() {
        int aux = objSize(lista.get(0).getFornecedor()) + objSize(lista.get(0).getMesIni())
                + objSize(lista.get(0).getMesFim()) + objSize(lista.get(0).getValorTotal());
        
        System.out.println("Tamano da entrada em memoria: " + (lista.size()*(aux)+head.length*objSize(head[0]))/1000000 + " MBytes");
        System.out.println("######## Informações das Variaveis ########");        
        System.out.println("Tamano da lista em memoria: " + (lista.size() * (aux))/1000000 + " MBytes");
        System.out.println("Tamano da matriz em memoria: " + objSize(matrix)/1000000 + " MBytes");

        System.out.println("notação asintotica matriz: Theta( M*N² )");
        System.out.println("notação asintotica lista: Theta( N²*M )");
        System.out.println("###########################################");        

    }

    public static int objSize(Serializable obj) {
        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
                java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
            oos.writeObject(obj);
            oos.flush();
            return baos.toByteArray().length;
        } catch (java.io.IOException ex) {
            // ignore a IOException que é levantada quando se fecha o oos
        }
        return 0;
    }

}
package Sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class DAO {
    private int[] head;
    private ArrayList<Contrato> lista = new ArrayList<>();
    private float matrix[][][];

    public DAO(String path) {

        try {
            FileReader arq = new FileReader(path);
            BufferedReader readArq = new BufferedReader(arq);

            String aux[] = readArq.readLine().split(" ");
            head = new int[aux.length];
            for (int i = 0; i < aux.length; i++)
                head[i] = Integer.parseInt(aux[i]);

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

                line = readArq.readLine();

            }

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

    }

    public float[][][] CreateGetMatrix() {
        matrix = new float[head[1]][head[0]][head[0]];
        Contrato c;
        for (int i = 0; i < lista.size(); i++) {
            c = lista.get(i);
            matrix[c.getFornecedor()][c.getMesIni()][c.getMesFim()] = c.getValorTotal();

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
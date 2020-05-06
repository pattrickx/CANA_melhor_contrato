package Sort;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class DAO {
    private int[] head;
    private ArrayList<Contrato> lista = new ArrayList<>();
   
	public DAO(String path){
        Scanner read= new Scanner(System.in);
        try{
            FileReader arq= new FileReader(path);
            BufferedReader readArq= new BufferedReader(arq);
            
            String aux[]=readArq.readLine().split(" ");
            head= new int[aux.length];
            for(int i=0; i<aux.length; i++)
                head[i]=Integer.parseInt(aux[i]);

            String line= readArq.readLine();
            Contrato c;
            while(line!=null){
                
                String lineSplit[]=line.split(" ");
                
                c= new Contrato();
                c.setFornecedor(Integer.parseInt(lineSplit[0])-1);
                c.setMesIni(Integer.parseInt(lineSplit[1])-1);
                c.setMesFim(Integer.parseInt(lineSplit[2])-1);
                c.setValorTotal(Float.parseFloat(lineSplit[3]));

                lista.add(c);
                
                line= readArq.readLine();
                
            }

        }catch(IOException e){
            System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
        }
        
    
    }
    public float[][][] CreateGetMatrix(){
        float matrix[][][] = new float[head[1]][head[0]][head[0]];
        Contrato c;
        for(int i = 0; i < lista.size() ; i++ ){
            c = lista.get(i);
            matrix[c.getFornecedor()][c.getMesIni()][c.getMesFim()]=c.getValorTotal();
            
        }
        return matrix;

    }
    public ArrayList<Contrato> getLista(){
        return lista;
    }
    public int[] head(){
        return head;

    }
    
}
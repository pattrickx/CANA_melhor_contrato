/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Lucas Alves
 */
public class QuickSort {
    long time_ms;
    
    
    public void run(ArrayList<Contrato> v,int esq, int dir){
        long start = System.currentTimeMillis();
        ArrayList<Contrato> c = quicksort(v,esq,dir);
        time_ms = System.currentTimeMillis() - start;
        DAO.WriteTxt(c,"Output\\OutputQuickSort.txt");
        
        
    }
    
    

    private static ArrayList<Contrato> quicksort(ArrayList<Contrato> v, int esq, int dir) {
        if(esq < dir){   //recusividade continua enquanto direito for maior que esquerdo
            int j = separar(v,esq,dir);
            quicksort(v,esq,j-1);  //fazendo chamada recusiva até j-1//menores esquerda
            quicksort(v,j+1,dir);  //fazendo chamada recusiva até j+1//maiores direita
    }
    return v;
    }

    private static int separar(ArrayList<Contrato> v, int esq, int dir) {
        int i = esq + 1;
        int j = dir;
        float pivo = v.get(esq).getValorTotal();
        while(i <= j){
            if(v.get(i).getValorTotal() <= pivo) i++;  //vai avançando com i até achar um elemento maior que o pivo para jogar pro lado direito
            
            else if (v.get(j).getValorTotal() > pivo) j--;   //vai decrementando com j até achar um element menor que o pivo para jogar pro lado esquerdo
            
            else if(i <= j ){   //verificando possibilidades do i ser menor ou igual a j(caso nao entre nem pra direita e nem pra equerda
                trocar(v,i,j);  //trocando posições de i(esq) e j(dir)
                i++;
                j--;
            }
        }
            
        
        trocar(v,esq,j);
        return j;
    }

    private static void trocar(ArrayList<Contrato> v, int i, int j) {
        Contrato aux = v.get(i);
        v.set(i,v.get(j));
        v.set(j,aux);
        
    }
    
}




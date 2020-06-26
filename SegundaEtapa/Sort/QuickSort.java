/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sort;

import java.util.ArrayList;

/**
 *
 * @author Lucas Alves
 */
public class QuickSort {
    long time_ms;
    
    
    public void run(ArrayList<Contrato> v,int esq, int dir){

        long start = System.currentTimeMillis();
        quicksort(v,esq,dir);
        time_ms = System.currentTimeMillis() - start;
        DAO.WriteTxt(v,"Output\\OutputQuickSort.txt");
        
        
    }
    public long get_execution_time(){
        return time_ms;
    }
    
    

    private static void quicksort(ArrayList<Contrato> v, int esq, int dir) {              //|   Melhor Caso  |    Pior Caso    |
        if(esq < dir){   //recusividade continua enquanto direito for maior que esquerdo  //|	    M*N      |      M*N        |
            int j = separar(v,esq,dir);							  //|	    M*N      |      M*N        |
            quicksort(v,esq,j-1);  //fazendo chamada recusiva até j-1//menores esquerda	  //|       M-1      |      M-1        |
            quicksort(v,j+1,dir);  //fazendo chamada recusiva até j+1//maiores direita	  //|       N-1      |      N-1        |		
    }                                                                                     //|F(N,M)=Teta(M*N)| F(N,M)=Teta(M*N)| *notação assintotica

    											 //|F(N,M)=O(M*N) *Notação Assintotica do metodo																						
    }

    private static int separar(ArrayList<Contrato> v, int esq, int dir) {
        int i = esq + 1;							         //|       M         |        M        |  
        int j = dir;                                                                     //|       N         |        N        |
        float pivo = v.get(esq).getValorTotal();                                         //|       M         |        M        |
        while(i <= j){                                                                   //|    (N²* M)/2    |     (N²* M)/2   |
            if(v.get(i).getValorTotal() <= pivo) i++;                                    //|     M * M+1     |      M * M+1    |                                             //vai avançando com i até achar um elemento maior que o pivo para jogar pro lado direito
            
            else if (v.get(j).getValorTotal() > pivo) j--;                               //|     N * N+1     |      N * N+1    |                                                                               //vai decrementando com j até achar um element menor que o pivo para jogar pro lado esquerdo
            
            else if(i <= j ){                                                            //|     (N* M)/2    |     (N* M)/2    |                                                                                                                                           //verificando possibilidades do i ser menor ou igual a j(caso nao entre nem pra direita e nem pra equerda
                trocar(v,i,j);                                                           //|       M*N       |      M*N        |                  //trocando posições de i(esq) e j(dir)
                i++;                                                                     //|        1        |       0         |
                j--;                                                                     //|        1        |       0         |
            }
        }
        trocar(v,esq,j);                                                                 //|        1        |        0        |
        return j;                                                                        //|        1        |        0        |
    }

										         //|F(N,M)=(N²* M)/2 | F(N,M)=(N²* M)/2| *Notação Assintotica 

										         //|F(N,M)= (N²* M)/2|   Notação Assintotica do metodo

    private static void trocar(ArrayList<Contrato> v, int i, int j) {
        Contrato aux = v.get(i);                                                         //|        M        |        M        |
        v.set(i,v.get(j));                                                               //|        N        |        N        |
        v.set(j,aux);									 //|        1        |        1        |
        
    }
    
}

//ANALISE DE COMPLEXIDADE DO QUICKSORT:

//T(n)=2T(n/2)+Θ(n)(melhor caso) | Teorema Mestre:  T(n)=Θ(nlogn) Caso 2
//T(n)=T(n−1)+Θ(n)(pior caso)   | T(n)=O(n2) Caso 1

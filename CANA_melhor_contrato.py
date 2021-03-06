import copy as cp
from datetime import datetime
# entrada de dado
def entrada(nome_do_arquivo): # Questão A e B                                                    
    arquivo=open(nome_do_arquivo,'r') # recebe arquivo em modo de leitura                          |      1       |
    meses_provedores=list(map(int,arquivo.readline().strip().split(" "))) #Ler primeira linha      |      1       |
    dado_bruto=arquivo.readlines() # cria um array com todas as linas do arquivo                 # |      1       |
    ## tratamento de dado
    dado=[cp.deepcopy([0]*meses_provedores[0]) for i in range(meses_provedores[0])]              # |    N+1       |
    dado=[cp.deepcopy(dado)for i in range(meses_provedores[1])]                                  # |    N+1       |
    for i in range(len(dado_bruto)):                                                             # | M(N(N+1)/2)+1|
        x=dado_bruto[i].strip().split(" ")                                                       # | M(N(N+1)/2)  |
        dado[int(x[0])-1][int(x[1])-1][int(x[2])-1]=float(x[3])                                  # | M(N(N+1)/2)  |

    #dado[i]= [int(x) for x in (list(map(float,dado[i].strip().split(" "))))] # converte de float para int pois como ultimo valor é uma string representando float converter direto pra int não é possivel
    ## strip remove ultima posoção revendo o /n
    ## split separa a string onde tem " " espaço
    ## map(float, vetor) transforma o vetor de string para float
    ## list transforma o tipo da ariavel de volta para lista pois o map altera para tipo map
################################################################################################
                
                #   ESTRUTURA USADA FOI UMA MATRIZ TRIDIMENCIONAL
                #   SUA COMPLEXIDADE É DE   TETA (N*M^2)

    return dado,meses_provedores # dado apos tratamentos                                        # |       1      |
                                                                                                # | F(N,M)=Teta(N*M^2)|

                                                                                              

#-------------------------------------------------------------------------------------------------------------------------------------------------------

def menor_contrato_vendedor(vendedor,dado,meses): # Questão C e D                            Melhor         Pior
    valor=dado[vendedor][0][0]                                                          # |       1      |     1      |
    contrato=[vendedor,0,0,valor]                                                       # |       1      |     1      |
    for i in range(meses):                                                              # |      N+1     |    N+1     |
        if valor>dado[vendedor][i][i]:                                                  # |       N      |     N      |
            valor=dado[vendedor][i][i]                                                  # |       0      |     N      |
            contrato=[vendedor+1,i+1,i+1,valor]                                         # |       0      |     N      |
    return contrato                                                                     # |       1      |     1      |
                                                                                        # |F(N)=Teta(N)  |F(N)=Teta(N)|*notação assintotica

                                                                                        # |   F(N)=Teta(N)  |  *notação assintotica do metodo

#-------------------------------------------------------------------------------------------------------------------------------------------------------
def menor_contrato(dado,meses,provedores): # Questão E e F
    valor=dado[0][0][0]                                                                 #|        1      |        1      |
    contrato=[0,0,valor]                                                                #|        1      |        1      |
    for i in range(provedores):                                                         #|       M+1     |       M+1     |
        for j in range(meses):                                                          #|       M*N+1   |       M*N+1   |
            if valor>dado[i][j][j]:                                                     #|       M*N     |       M*N     |
                valor=dado[i][j][j]                                                     #|        0      |       M*N     |
                contrato=[i+1,j+1,j+1,valor]                                            #|        0      |       M*N     |
    return contrato                                                                     #|        1      |       1       |
                                                                                        #|F(N,M)=Teta(M*N)|F(N,M)=Teta(M*N)| *notação assintotica

                                                                                       # | F(N,M)=O(M*N)|  *notação assintotica do metodo

#-----------------------------------------[G,H]----------------------------------------------------
#-------------------------------------------------------------------------------------------------------------------------------------------------------

def menor_contrato_1_M(fim,vendedor,dado,meses): # Questão G e H
    valor=dado[0][0][fim]                                                                #|       1       |        1      |
    contrato=[0,0,fim,valor]                                                             #|       1       |        1      |
    for i in range(vendedor):                                                            #|       M+1     |        M+1    |
        if valor>dado[i][0][fim]:                                                        #|       M       |        M      |
            valor=dado[i][0][fim]                                                        #|       1       |        M      |  
            contrato=[i+1,1,fim+1,valor]                                                 #|       1       |        M      |
    return contrato                                                                      #|       1       |        1      |   
                                                                                         #|   F(M)=Teta(M)|F(M)=Teta(M) | *notação assintotica
    
                                                                                         #| F(M)=Teta(M)|  *notação assintotica do metodo

#------------------------------------------[I,J]----------------------------------------------------"
#-------------------------------------------------------------------------------------------------------------------------------------------------------
def melhores(meses,provedores,dado):
    M_melhor = [cp.deepcopy([0]*meses) for i in range(meses)]              #|        N       |     N      |       #cria matriz de meses por meses
    M_provedores = cp.deepcopy(M_melhor)                                   #|        1       |      1     |
    #M_provedores = cp.deepcopy(M_melhor)
    # ele cria uma copia do arquivo separando do indereço de memoria
    # inicial do copiado , caso não seja usado as duas variaveia usaram o mesmo endereço de moria prejudicando
    #  o funcionamento do codigo
    

    for i in range(meses):                                                 #|        N+1      |    N+1          |
        for j in  range(i,meses):                                          #|   [(N+1)]*N/2   | [(N+1)]*N/2     |
            valor=0                                                        #|   [(N+1)]*N/2   | [(N+1)]*N/2     |
            for k in range(provedores):                                    #|[[(N+1)]*N/2]*(M+1)|[[(N+1)]*N/2]*(M+1)  |
                if valor==0 or valor>dado[k][i][j]:                        #|[[(N+1)]*N/2]*M  |[[(N+1)]*N/2]*M  |
                    valor=dado[k][i][j]                                    #|        1        |[[(N+1)]*N/2]*M  |
                    M_melhor[i][j]=valor                                   #|        1        |[[(N+1)]*N/2]*M  |
                    M_provedores[i][j]=k                                   #|        1        |[[(N+1)]*N/2]*M  |

    return M_melhor,M_provedores                                           #|        1        |      1          |
                                                                           #| F(N,M)=Teta((N^2)*M)| F(N,M)=Teta((N^2)*M) *notação assintotica

                                                                           #| F(N,M)=Teta((N^2)*M)   *notação assintotica do Metodo


#-------------------------------------------------------------------------------------------------------------------------------------------------------
def menor(meses,M_melhor,M_provedores):
    soma = 0 # variavel para armazenar o valor do contrato completo mais barato   
    s_contratos=[] # variavel para armazenar os os contratos e seus provedores do total mais barato

    # os de baixo são variaveis auxiliares para descobrir os valores de cada teste de contrato
    soma2 =0                                                         
    s2_contratos=[]                                                                    
    #meses=len(M_melhor)
    #meses=5
    soma=0
    n=0
    for i in range(meses):                                                                #|     N+1     |     N+1      |
        soma2=M_melhor[0][i]                                                              #|     N       |      N       |
        s2_contratos.append([M_provedores[0][i]+1,1,i+1,soma2])                           #|     N       |      N       |
        for j in range(i+1 ,meses):                                                       #| [(N+1)]*N/2 |  [(N+1)]*N/2 |
               # recebe o primeiro valor do teste 
            #for k in range(j,meses):
            soma2 += M_melhor[j][j]                                                       #| [(N+1)]*N/2  |  [(N+1)]*N/2 |
            s2_contratos.append([M_provedores[j][j]+1,j+1,j+1,M_melhor[j][j]])            #| [(N+1)]*N/2  |  [(N+1)]*N/2 |
                #break
                
        if soma>soma2 or soma==0:                                                         #| [(N+1)]*N/2 | [(N+1)]*N/2  |
            soma=soma2                                                                    #|     1       |  [(N+1)]*N/2 |
            s_contratos.clear()                                                           #|     1       |  [(N+1)]*N/2 |
            s_contratos=cp.deepcopy(s2_contratos)                                         #|     1       |  [(N+1)]*N/2 |
            soma2=0                                                                       #|     1       |  [(N+1)]*N/2 |
            s2_contratos.clear()                                                          #|     1       |  [(N+1)]*N/2 |
        else:                                                                             #|([(N+1)]*N/2)-1|      0       |
            soma2=0                                                                       #|([(N+1)]*N/2)-1|      0       |
            s2_contratos.clear()                                                          #|([(N+1)]*N/2)-1|      0       |
    
    return s_contratos,soma                                                               #|      1     |     1        |

                                                                                          #| F(N)=Teta(N) | F(N)=Teta(N^2)  | *notação assintotica

                                                                                          #| F(N)=Teta(N^2) |   *notação assintotica do Metodo



#-------------------------------------------------------------------------------------------------------------------------------------------------------
 ## pega cabeçalho

print("----------------------------------------[A,B]-------------------------------------------------------")
print("------------------------------- Entrada e tipo de dado ---------------------------------------------")
ini = datetime.now()
dado,meses_provedores= entrada('entrada.txt')
fim = datetime.now()
print("Complexidade da Extrutura: TETA (N*M^2)")
print("Tempo de execução:",(fim-ini).total_seconds()*1000,'ms')

print("----------------------------------------[C,D]-------------------------------------------------------")
print("------------------------------ Menor valor de um dado fornecedor -----------------------------------")
fornecedor = int(input("digite qual o fornecedor deseja saber qual o menor contrato: "))-1
ini = datetime.now()
print('Mednor contrato do fornecedor: ',menor_contrato_vendedor(fornecedor,dado,meses_provedores[0]))
fim = datetime.now()
print("Tempo de execução:",(fim-ini).total_seconds()*1000,'ms')

print("------------------------------------------[E,F]-----------------------------------------------------")
print("---------------------------------- menor_contrato total --------------------------------------------")
ini = datetime.now()
print('menor_contrato_total: ',menor_contrato(dado,meses_provedores[0],meses_provedores[1]))
fim = datetime.now()
print("Tempo de execução:",(fim-ini).total_seconds()*1000,'ms')

print("------------------------------------------[G,H]----------------------------------------------------")
print("------------------menor valor referente ao período completo do mês 1 ao mês n----------------------")
mes_final=int(input("digite o mes final do periodo: "))-1
ini = datetime.now()
print("Contrato: ",menor_contrato_1_M(mes_final,meses_provedores[1],dado,meses_provedores[0]))
fim = datetime.now()
print("Tempo de execução: ",(fim-ini).total_seconds()*1000,'ms')
# melhor contrato para periodo total

print("------------------------------------------[I,J]----------------------------------------------------")
print("------- Sugere quais contratos de energia devem ser contratados para os próximos n meses-----------")
ini = datetime.now()
M_melhor,M_provedores=melhores(meses_provedores[0],meses_provedores[1],dado)
s_contratos,soma=menor(meses_provedores[0],M_melhor,M_provedores)
fim = datetime.now()
print("contratos: ",s_contratos)
print("preço: ",soma)
print("Tempo de execução: ",(fim-ini).total_seconds()*1000,'ms')


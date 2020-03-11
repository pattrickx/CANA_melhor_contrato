import copy as cp
from datetime import datetime
# entrada de dado
def entrada(nome_do_arquivo): # Questão A e B                                           # | MELHOR CASO | PIOR CASO |
    arquivo=open(nome_do_arquivo,'r') # recebe arquivo em modo de leitura                 |      1      |      1      |
    meses_provedores=list(map(int,arquivo.readline().strip().split(" ")))               # |      1      |      1      |
    dado_bruto=arquivo.readlines() # cria um array com todas as linas do arquivo        # |      1      |      1      |
    ## tratamento de dado
    # print(dado_bruto[0])
    dado=[cp.deepcopy([0]*meses_provedores[0]) for i in range(meses_provedores[0])]     # |      1      |     M      |
    dado=[cp.deepcopy(dado)for i in range(meses_provedores[1])]                         # |    M + 1    |      M     |
    for i in range(len(dado_bruto)):                                                    # |      M      |    M+1     |
        x=dado_bruto[i].strip().split(" ")                                              # |      1      |     1      |
        dado[int(x[0])-1][int(x[1])-1][int(x[2])-1]=float(x[3])                         # | (N-1)*2 + 1 |  (N^2)*2/2 |

    #dado[i]= [int(x) for x in (list(map(float,dado[i].strip().split(" "))))] # converte de float para int pois como ultimo valor é uma string representando float converter direto pra int não é possivel
    ## strip remove ultima posoção revendo o /n
    ## split separa a string onde tem " " espaço
    ## map(float, vetor) transforma o vetor de string para float
    ## list transforma o tipo da ariavel de volta para lista pois o map altera para tipo map
################################################################################################
                
                #   ESTRUTURA USADA FOI UMA MATRIZ TRIDIMENCIONAL
                #   SUA COMPLEXIDADE É DE N*M*M

    return dado,meses_provedores # dado apos tratamentos                                # |       1      |     1      |
                                                                                        # | F(N)=(M(N))  |F(N)=(M(N^2)| *notação assintotica

                                                                                        # |F(N)=O(M(N^2)| *notação assintotica do metodo

#-------------------------------------------------------------------------------------------------------------------------------------------------------

def menor_contrato_vendedor(vendedor,dado,meses): # Questão C e D
    valor=dado[vendedor][0][0]                                                          # |       1      |     1      |
    contrato=[vendedor,0,0,valor]                                                       # |       1      |     1      |
    for i in range(meses):                                                              # |      N+1     |    N+1     |
        if valor>dado[vendedor][i][i]:                                                  # |       N      |     N      |
            valor=dado[vendedor][i][i]                                                  # |       0      |     N      |
            contrato=[vendedor+1,i+1,i+1,valor]                                         # |       0      |     N      |
    return contrato                                                                     # |       1      |     1      |
                                                                                        # |   F(N)= 1    |   F(N)=N   |*notação assintotica

                                                                                        # |   F(N)=O(N)  |  *notação assintotica do metodo

#-------------------------------------------------------------------------------------------------------------------------------------------------------
def menor_contrato(dado,meses,provedores): # Questão E e F
    valor=dado[0][0][0]                                                                 #|        1      |        1      |
    contrato=[0,0,valor]                                                                #|        1      |        1      |
    for i in range(provedores):                                                         #|        N      |        N      |
        for j in range(meses):                                                          #|       N*M     |       N*M     |
            if valor>dado[i][j][j]:                                                     #|       N*M     |       N*M     |
                valor=dado[i][j][j]                                                     #|        0      |       N*M     |
                contrato=[i+1,j+1,j+1,valor]                                            #|        0      |       N*M     |
    return contrato                                                                     #|        1      |       1       |
                                                                                        #| F(N)=(M(N))   |  F(N)=(M^2(N))| *notação assintotica

                                                                                       # | F(N)=O(M^2(N))|  *notação assintotica do metodo

#-------------------------------------------------------------------------------------------------------------------------------------------------------
def melhores(meses,provedores,dado):
    M_melhor = [cp.deepcopy([0]*meses) for i in range(meses)]                          #|        N       |     N      |       #cria matriz de meses por meses
    M_provedores = cp.deepcopy(M_melhor)                                               #|        1       |      1     |
    #M_provedores = cp.deepcopy(M_melhor)
    #    ## ele cria uma copia do arquivo separando do indereço de memoria
    #       inicial do copiado , caso não seja usado as duas variaveia usaram o mesmo endereço de moria prejudicando
    #        o funcionamento do codigo
    

    for i in range(meses):                                                             #|        N        |    N       |
        for j in  range(i,meses):                                                      #|   [(N+1)]*N/2   | [(N+1)]*N/2|
            valor=0                                                                    #|        1        |     1      |
            for k in range(provedores):                                                #|        M+1      |     M      |
                if valor==0 or valor>dado[k][i][j]:                                    #|       N*M*M     |    N*M*M   |
                    valor=dado[k][i][j]                                                #|        0        |     1      |
                    M_melhor[i][j]=valor                                               #|        0        |     1      |
                    M_provedores[i][j]=k                                               #|        0        |     1      |

    return M_melhor,M_provedores                                                       #|        1        |      M     |
                                                                                       #|  F(N)=(N(M+1)   | F(N)=(N+1) | *notação assintotica


def menor(meses,M_melhor,M_provedores):
    soma = 0 # variavel para armazenar o valor do contrato completo mais barato
    s_contratos=[] # variavel para armazenar os os contratos e seus provedores do total mais barato

    # os de baixo são variaveis auxiliares para descobrir os valores de cada teste de contrato
    soma2 =0                                                                           #|        1        |     1      |
    s2_contratos=[]                                                                    #|        1        |     1      |
    #meses=len(M_melhor)
    #meses=5
    soma=0
    n=0
    for i in range(meses):
        soma2=M_melhor[0][i]
        s2_contratos.append([M_provedores[0][i],0,i])
        for j in range(i+1 ,meses):
               # recebe o primeiro valor do teste 
            for k in range(j,meses):
                soma2 += M_melhor[j][k]  
                s2_contratos.append([M_provedores[j][k],j,k])
                break
                
            # print(s2_contratos)
            # print(soma2)
            
        if soma>soma2 or soma==0:
            soma=soma2
            s_contratos.clear()
            s_contratos=cp.deepcopy(s2_contratos)
            soma2=0
            s2_contratos.clear()
        else:
            soma2=0
            s2_contratos.clear()
    # print("quantidade de operaçoes: ",n)
    return s_contratos,soma                                                            #|       1         |     1      |


dado,meses_provedores= entrada('entrada.txt')
 ## pega cabeçalho

ini = datetime.now()
print('menor_contrato_vendedor 2 : ',menor_contrato_vendedor(1,dado,meses_provedores[0]))
fim = datetime.now()
print("Tempo de execução:",(fim-ini).total_seconds()*1000,'ms')
ini = datetime.now()
print('menor_contrato total: ',menor_contrato(dado,meses_provedores[0],meses_provedores[1]))
fim = datetime.now()
print("Tempo de execução:",(fim-ini).total_seconds()*1000,'ms')

ini = datetime.now()
M_melhor,M_provedores=melhores(meses_provedores[0],meses_provedores[1],dado)
fim = datetime.now()
print("Tempo de execução para criação da matriz de melhores contratos:",(fim-ini).total_seconds()*1000,'ms')

#contratos de 1 a N periodos
for i in range(121):
    # contrato para 50 meses
    ini = datetime.now()
    s_contratos,soma=menor(i,M_melhor,M_provedores)
    fim = datetime.now()
    print("contrato: ",s_contratos)
    print("preço: ",soma)
    print("Tempo de execução: ",(fim-ini).total_seconds()*1000,'ms')


# melhor contrato para periodo total

ini = datetime.now()
s_contratos,soma=menor(meses_provedores[0],M_melhor,M_provedores)
fim = datetime.now()
print("contrato: ",s_contratos)
print("preço: ",soma)
print("Tempo de execução: ",(fim-ini).total_seconds()*1000,'ms')


import copy as cp
from datetime import datetime
# entrada de dado
def entrada(nome_do_arquivo): # Questão A e B
    arquivo=open(nome_do_arquivo,'r') # recebe arquivo em modo de leitura
    meses_provedores=list(map(int,arquivo.readline().strip().split(" ")))
    dado_bruto=arquivo.readlines() # cria um array com todas as linas do arquivo
    ## tratamento de dado
    # print(dado_bruto[0])
    dado=[cp.deepcopy([0]*meses_provedores[0]) for i in range(meses_provedores[0])]
    dado=[cp.deepcopy(dado)for i in range(meses_provedores[1])]
    for i in range(len(dado_bruto)): 
        x=dado_bruto[i].strip().split(" ")
        dado[int(x[0])-1][int(x[1])-1][int(x[2])-1]=float(x[3])

        #dado[i]= [int(x) for x in (list(map(float,dado[i].strip().split(" "))))] # converte de float para int pois como ultimo valor é uma string representando float converter direto pra int não é possivel 
    ## strip remove ultima posoção revendo o /n
    ## split separa a string onde tem " " espaço
    ## map(float, vetor) transforma o vetor de string para float
    ## list transforma o tipo da ariavel de volta para lista pois o map altera para tipo map

    return dado,meses_provedores # dado apos tratamentos


def menor_contrato_vendedor(vendedor,dado,meses): # Questão C e D
    valor=dado[vendedor][0][0]
    contrato=[vendedor,0,0,valor]
    for i in range(meses):                           #|   Melhor caso    |   Pior caso                                            #|
        if valor>dado[vendedor][i][i]:
            valor=dado[vendedor][i][i]
            contrato=[vendedor+1,i+1,i+1,valor]
    return contrato

def menor_contrato(dado,meses,provedores): # Questão E e F
    valor=dado[0][0][0]
    contrato=[0,0,valor]
    for i in range(provedores):
        for j in range(meses):      # Linear
            if valor>dado[i][j][j]:
                valor=dado[i][j][j]
                contrato=[i+1,j+1,j+1,valor]
    return contrato


def melhores(meses,provedores,dado):
    M_melhor = [cp.deepcopy([0]*meses) for i in range(meses)]#cria matriz de meses por meses
    M_provedores = cp.deepcopy(M_melhor)
    #M_provedores = cp.deepcopy(M_melhor) 
    #    ## ele cria uma copia do arquivo separando do indereço de memoria
    #       inicial do copiado , caso não seja usado as duas variaveia usaram o mesmo endereço de moria prejudicando
    #        o funcionamento do codigo
    

    for i in range(meses): 
        for j in  range(i,meses):
            valor=0
            for k in range(provedores):
                if valor==0 or valor>dado[k][i][j]: 
                    valor=dado[k][i][j]
                    M_melhor[i][j]=valor
                    M_provedores[i][j]=k

    return M_melhor,M_provedores


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
    return s_contratos,soma


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


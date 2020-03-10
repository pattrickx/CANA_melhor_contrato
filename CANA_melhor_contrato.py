import copy as cp
from datetime import datetime
# entrada de dado
def entrada(nome_do_arquivo): # Questão A e B
    arquivo=open(nome_do_arquivo,'r') # recebe arquivo em modo de leitura
    dado=arquivo.readlines() # cria um array com todas as linas do arquivo
    ## tratamento de dado

    for i in range(len(dado)): 
        dado[i]= [int(x) for x in (list(map(float,dado[i].strip().split(" "))))] # converte de float para int pois como ultimo valor é uma string representando float converter direto pra int não é possivel 
    ## strip remove ultima posoção revendo o /n
    ## split separa a string onde tem " " espaço
    ## map(float, vetor) transforma o vetor de string para float
    ## list transforma o tipo da ariavel de volta para lista pois o map altera para tipo map

    x=[]
    for i in dado: # ajusta dados adequar a matriz tornando mes de 1a3 para de 0a1 
        x.append([i[x]-1 if x>0 and x<3 else i[x] for x in range(0,len(i))]) 

    return x # dado apos tratamentos


def menor_contrato_vendedor(vendedor,dado,meses): # Questão C e D
    contrato=[]
    valor=0
    n=0
    for i in dado:                           #|   Melhor caso    |   Pior caso    |
        if vendedor==i[0] and n<meses:       #|
            n+=1
            if valor==0 or valor>i[3]:
                valor=i[3]
                contrato=i
    return contrato

def menor_contrato(dado): # Questão E e F
    contrato=dado[0]
    valor=dado[0][3]
    for i in dado:      # Linear
        if valor>i[3]:
            valor=i[3]
            contrato=i
    return contrato

dado= entrada('entrada.txt')
meses_provedores=dado.pop(0) ## pega cabeçalho
print("dados tratados")

print('menor_contrato_vendedor 1 : ',menor_contrato_vendedor(1,dado,meses_provedores[0])) 
print('menor_contrato total: ',menor_contrato(dado))


M_melhor = [cp.deepcopy([0]*meses_provedores[0]) for i in range(meses_provedores[0])]#cria matriz de meses por meses
M_provedores = cp.deepcopy(M_melhor)
#M_provedores = cp.deepcopy(M_melhor) 
#    ## ele cria uma copia do arquivo separando do indereço de memoria
#       inicial do copiado , caso não seja usado as duas variaveia usaram o mesmo endereço de moria prejudicando
#        o funcionamento do codigo
ini = datetime.now()

for i in range(meses_provedores[0]): 
    for j in  range(i,meses_provedores[0]): 
        valor=0
        for k in dado: 
            if k[1]== i and k[2]==j:
                if valor==0:
                    valor=k[3]
                    M_melhor[i][j]=valor
                    M_provedores[i][j]=k[0]
                if valor>k[3]: 
                    valor=k[3]
                    M_melhor[i][j]=valor
                    M_provedores[i][j]=k[0]

fim = datetime.now()
print("Tempo de execução:",(fim-ini).total_seconds()/60,'min')
'''            
for i in range(len(dado)):  ## os dois for percorem da diagonal principal da matriz para cima 
    for j in  range(i+1,len(dado)):
        
        if (dado[i][1]==dado[j][1]) and (dado[i][2]==dado[j][2]): # verifica se são os mesmos contratos que estão sendo comparados  
           
            if (dado[i][3]<=dado[j][3]): # verifica se quem é menor é o do primeiro provedor e adiciona nas matrizes o valor do contrato e seu provedor caso contrario faz o mesmo para o outro provedor
                M_melhor[(dado[i][1])][(dado[i][2])] = dado[i][3]  
                M_provedores[(dado[i][1])][(dado[i][2])] = dado[i][0] 
            else:
                M_melhor[(dado[i][1])][(dado[i][2])] = dado[j][3]
                M_provedores[(dado[i][1])][(dado[i][2])] = dado[j][0]
'''
print("organizado")
# print("Melhor preço: ",M_melhor)
# print("provedores: ",M_provedores)
def melhor_contrato(meses,M_melhor,M_provedores): # Questão G e I
    soma = 0 # variavel para armazenar o valor do contrato completo mais barato
    s_contratos=[] # variavel para armazenar os os contratos e seus provedores do total mais barato

    # os de baixo são variaveis auxiliares para descobrir os valores de cada teste de contrato
    soma2 =0    
    s2_contratos=[]
    #meses=len(M_melhor)
    #meses=5
    soma=0
    for i in range(meses):
        for j in range(i if i==0 else i+1 ,meses):
            soma2+=M_melhor[i][j]   # recebe o primeiro valor do teste 
            s2_contratos.append([M_provedores[i][j],i,j])
            for k in range(meses):
                for l in range(k,meses):
                    if k==i or (l>=i and l<=j):
                        break
                    soma2 += M_melhor[k][l]
                    s2_contratos.append([M_provedores[k][l],k,l,soma2])
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
    return s_contratos,soma

# melhor contrato para periodo de 1 a N
ini = datetime.now()
s_contratos,soma=melhor_contrato(5,M_melhor,M_provedores)
#s_contratos,soma=melhor_contrato(len(M_melhor),M_melhor,M_provedores)
fim = datetime.now()
print("Tempo de execução: ",(fim-ini).total_seconds()/60,'min')
print("contrato: ",s_contratos)
print("preço: ",soma)

# melhor contrato para periodo total
ini = datetime.now()
s_contratos,soma=melhor_contrato(len(M_melhor),M_melhor,M_provedores)
fim = datetime.now()
print("Tempo de execução: ",(fim-ini).total_seconds()/60,'min')
print("contrato: ",s_contratos)
print("preço: ",soma)
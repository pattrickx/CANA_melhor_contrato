import copy as cp
# entrada de dado
arquivo=open('entrada.txt','r') # recebe arquivo em modo de leitura
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

dado = x # dado apos tratamentos
meses_provedores=dado.pop(0) ## pega cabeçalho

M_melhor = [cp.deepcopy([0]*meses_provedores[0]) for i in range(meses_provedores[0])]#cria matriz de meses por meses
M_provedores = cp.deepcopy(M_melhor)
#M_provedores = cp.deepcopy(M_melhor) 
#    ## ele cria uma copia do arquivo separando do indereço de memoria
#       inicial do copiado , caso não seja usado as duas variaveia usaram o mesmo endereço de moria prejudicando
#        o funcionamento do codigo

for i in range(len(dado)):  ## os dois for percorem da diagonal principal da matriz para cima 
    for j in  range(i+1,len(dado)):
        
        if (dado[i][1]==dado[j][1]) and (dado[i][2]==dado[j][2]): # verifica se são os mesmos contratos que estão sendo comparados  
           
            if (dado[i][3]<=dado[j][3]): # verifica se quem é menor é o do primeiro provedor e adiciona nas matrizes o valor do contrato e seu provedor caso contrario faz o mesmo para o outro provedor
                M_melhor[(dado[i][1])][(dado[i][2])] = dado[i][3]  
                M_provedores[(dado[i][1])][(dado[i][2])] = dado[i][0] 
            else:
                M_melhor[(dado[i][1])][(dado[i][2])] = dado[j][3]
                M_provedores[(dado[i][1])][(dado[i][2])] = dado[j][0] 

print("                                                          ╔══════════•ೋೋ•══════════╗ ")
print("                                                                ANALISE DE CONTRATOS")
print("                                                          ╚══════════•ೋೋ•══════════╝")
print("")
print("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬| MESES PROVEDORES |▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬")
print(M_melhor)
print("")
print("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬")
print("")
print(M_provedores)

soma = 0 # variavel para armazenar o valor do contrato completo mais barato
s_contratos=[] # variavel para armazenar os os contratos e seus provedores do total mais barato

# os de baixo são variaveis auxiliares para descobrir os valores de cada teste de contrato
soma2 =0    
s2_contratos=[]

for i in range(len(M_melhor)):
    for j in range(i if i==0 else i+1 ,len(M_melhor)):
        soma2+=M_melhor[i][j]   # recebe o primeiro valor do teste 
        s2_contratos.append([M_provedores[i][j],i,j])
        for k in range(len(M_melhor)):
            for l in range(k,len(M_melhor)):
                if k==i or (l>=i and l<=j):
                    break
                soma2 += M_melhor[k][l]
                s2_contratos.append([M_provedores[k][l],k,l])
                break
        print(soma2)
        print("⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤")
        print(s2_contratos)
        if soma>soma2 or soma==0:
            soma=soma2
            s_contratos.clear()
            s_contratos=cp.deepcopy(s2_contratos)
            soma2=0
            s2_contratos.clear()
        else:
            soma2=0
            s2_contratos.clear()

print("")

print("●▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬●")
print("CONTRATO MAIS ACESSÍVEL:",s_contratos)
print("PREÇO:",soma,"$")
print("●▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬●")

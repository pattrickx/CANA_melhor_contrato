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
    x.append([i[x]-1 if x>0 and x<len(i) else i[x] for x in range(0,len(i))]) 

dado = x # dado apos tratamentos
meses_provedores=dado.pop(0) ## pega cabeçalho

M_melhor = [-1]*meses_provedores[0] #cria matriz de meses por meses
M_melhor= [cp.deepcopy(M_melhor) for i in range(meses_provedores[0])]
M_provedores = cp.deepcopy(M_melhor)
#M_provedores = cp.deepcopy(M_melhor) ## ele cria uma copia do arquivo separando do indereço de memoria inicial do copiado , caso não seja usado as duas variaveia usaram o mesmo endereço de moria prejudicando o funcionamento do codigo

for i in range(len(dado)):  ## os dois for percorem da diagonal principal da matriz para cima 
    for j in  range(i+1,len(dado)):
        
        if (dado[i][1]==dado[j][1]) and (dado[i][2]==dado[j][2]):  
           
            if (dado[i][3]<=dado[j][3]):
                M_melhor[(dado[i][1])][(dado[i][2])] = dado[i][3]  
                M_provedores[(dado[i][1])][(dado[i][2])] = dado[i][0] 
            else:
                M_melhor[(dado[i][1])][(dado[i][2])] = dado[j][3]
                M_provedores[(dado[i][1])][(dado[i][2])] = dado[j][0] 

print(M_melhor)
print(M_provedores)
            

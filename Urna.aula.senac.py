print("As opções são: ")
candidato=0
print("Digite 1 para Candidato Jair Rodrigues")
jair=0
print("Digite 2 para Candidato Carlos Luz")
carlos=0
print("Digite 3 para Candidato Neves Rocha")
neves=0
print("Digite 4 para Nulo")
nulo=0
print("Digite 5 para Branco")
branco=0
while candidato !=6:
    candidato = int(input("Digite 1, 2, 3, 4, 5 para escolher sua opção: "))
 
    if candidato == 1:
        jair += 1
        print("Candidato escolhido: Candidato Jair")
    elif candidato == 2:
        carlos += 1
        print("Candidato escolhido: Candidato Carlos")
    elif candidato == 3:
        neves += 1
        print("Candidato escolhido: Candidato Neves")
    elif candidato == 4:
        nulo += 1
        print("Você votou Nulo")
    elif candidato == 5:
        branco += 1
        print("Você votou em Branco")
    elif candidato == 6:
        print("Resultado da votação:")
        print("Saldo de votos do Jair:", jair)
        print("Saldo de votos do Carlos:", carlos)
        print("Saldo de votos do Neves:", neves)
        print("Saldo de votos Nulos:", nulo)
        print(nulo/(jair+carlos+neves+nulo+branco)*100,"%")
        print("Saldo de votos Brancos:", branco)
        print(branco/(jair+carlos+neves+nulo+branco)*100,"%")
        print("total de votos",jair+carlos+neves+nulo+branco)
    else:
        print("Opção inválida! Tente novamente.")

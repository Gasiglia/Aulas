senha=123
login="tds01"
login01="tds01"
senha01=000
contlogin=3
 
while ((login != login01 or senha != senha01) and contlogin>0):
    login01=input("Digite o login ")
    senha01=int(input("Digite o senha "))
    if (login == login01) and (senha == senha01):
        print("logado")
       
    else:
        contlogin-=1
        print("tente novamente, voce tem ", contlogin, " tentativas ")
 

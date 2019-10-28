# Deliver Test

###Incluir Conta
POST: http://localhost:8080/conta
```json
{
    "nome": "Carro",
    "valorOriginal": 130.00,
    "vencimento": "2000-11-14",
    "pagamento": "2000-11-29"
}
```
###Buscar Contas
GET: http://localhost:8080/conta

###Database Access
http://localhost:8080/h2-console/
<br>Driver: org.h2.Driver
<br>Url: jdbc:h2:mem:testdb
<br>User: sa
<br>Pass: 

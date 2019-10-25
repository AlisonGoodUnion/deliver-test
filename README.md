# Deliver Test

###Incluir Conta
POST: http://localhost:8080/conta
```json
{
    "nome": "Carro",
    "valorOriginal": 200.02,
    "vencimento": "2000-10-31",
    "pagamento": "2000-10-31"
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

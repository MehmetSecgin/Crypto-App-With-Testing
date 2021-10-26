## Team Name - Scar Mini_ Team09

---
##Team Members 

**Mehmet Ali Secgin , Fothul Karim Forhan** 

---
#Guide :

The program as default (Without any parameters) checks the value of BTC in these following Currencies [EUR,USD,AUD,CAD,JPY] and Calculates the trend change in percentage from the start of current month until current day.
 ``` 
  localhost:8080/api/crypto 
  ```
The program with the parameter of "symbol" checks the value of the symbol in these following Currencies [EUR,USD,AUD,CAD,JPY] and Calculates the trend change in percentage from the start of current month until current day.
 ``` 
  localhost:8080/api/crypto?symbol=BTC
  ```
The program with the parameter of "markets" checks the value of BTC in the currencies you've input to the program and Calculates the trend change in percentage from the start of current month until current day.
 ```
   localhost:8080/api/crypto?markets=EUR,TRY,JPY,BDT,USD
 ```
The program with parameters "markets" and "symbol" will check the value of "symbol" in the currencies you've input to the program and Calculates the trend change in percentage from the start of current month until current day.
 ```
   http://localhost:8080/api/crypto?markets=EUR,TRY,JPY,BDT,USD&symbol=XRP
 ```
> FYI :
> 
> > The Program only allows 5 real world currencies (markets) at once.
> 
> > The API call frequency is 5 calls per minute and 500 calls per day.
## Team Name - Scar Mini_Team09

---

## Team Members

**Mehmet Ali Seçgin , Fothul Karim Forhan**

---

# API

## Alpha Vantage API

[We're Using This Endpoint](https://www.alphavantage.co/documentation/#currency-monthly)

Our plan is to help the user to choose which Real World Currency to withdraw their cryptocurrency.

We're calculating inflation in every real world currency on the cryptocurrency that the user has chosen, and give the
amount of change in percentages.

---

# Example Results

On the request of:

```
http://localhost:8080/api/crypto?symbol=BTC&markets=EUR,USD,TRY,RUB
```

We get:

```
[
  {
    "currencyName": "EUR",
    "coinName": "BTC",
    "date": "2021-10-30",
    "open": 37899.926649,
    "close": 53811.457353,
    "change": 41.983011870604315
  },
  {
    "currencyName": "USD",
    "coinName": "BTC",
    "date": "2021-10-30",
    "open": 43820.01,
    "close": 62120.81,
    "change": 41.76356874405093
  },
  {
    "currencyName": "TRY",
    "coinName": "BTC",
    "date": "2021-10-30",
    "open": 420803.55603,
    "close": 591269.38596,
    "change": 40.50959824062113
  },
  {
    "currencyName": "RUB",
    "coinName": "BTC",
    "date": "2021-10-30",
    "open": 3105305.00865,
    "close": 4362931.99065,
    "change": 40.49930613890777
  }
]
```

The program checks the cryptocurrency on the real world currencies and calculates the percentage of change from the
start of the month until the date you've made the request.

As we can see, the changes are slightly different from each other. In this case, withdrawing your money from BTC to EUR
would be financially best for you because the change in EUR is the biggest one.

>If you make more than 5 requests in one minute, or more than 500 requests in the day, You get the response:
>```
>{
>  "Note": "Thank you for using Alpha Vantage! Our standard API call frequency is 5 calls per minute and 500 calls per day. Please visit https://www.alphavantage.co/premium/ if you would like to target a higher API call frequency."
>}
>```

>On a wrong API call, you'll get:
> ```
> {
>   "Error Message": "Invalid API call. Please retry or visit the documentation (https://www.alphavantage.co/documentation/) for DIGITAL_CURRENCY_MONTHLY."
> }
> ```

---

# Calculation Logic

The API retrieves Opening and Closing prices for the cryptocurrency. It makes a simple equation and calculates the
percentage of change.

From the example above, we can see that BTC on EUR opened on 2021/10/01 at 37899.926649 Euros and Closed on 2021/10/30 at 53811.457353 Euros. Hence, the amount has changed ±41.98% from the start of the month until this request was done (2021/10/30)

---

# Guide :

The program as default (Without any parameters) checks the value of BTC in these following
Currencies [EUR,USD,AUD,CAD,JPY] and Calculates the trend change in percentage from the start of current month until
current day.

 ``` 
  localhost:8080/api/crypto 
  ```

The program with the parameter of "symbol" checks the value of the symbol in these following
Currencies [EUR,USD,AUD,CAD,JPY] and Calculates the trend change in percentage from the start of current month until
current day.

 ``` 
  localhost:8080/api/crypto?symbol=BTC
  ```

The program with the parameter of "markets" checks the value of BTC in the currencies you've input to the program and
Calculates the trend change in percentage from the start of current month until current day.

 ```
   localhost:8080/api/crypto?markets=EUR,TRY,JPY,BDT,USD
 ```

The program with parameters "markets" and "symbol" will check the value of "symbol" in the currencies you've input to
the program and Calculates the trend change in percentage from the start of current month until current day.

 ```
   http://localhost:8080/api/crypto?markets=EUR,TRY,JPY,BDT,USD&symbol=XRP
 ```

> FYI :
>
> > The Program only allows 5 real world currencies (markets) at once.
>
> > The API call frequency is 5 calls per minute and 500 calls per day.
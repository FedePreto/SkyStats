# ProgettoEsame

<h3>Componenti gruppo:</h3>
Nicolò Raccichini <b>s1092919</b>
Federico Pretini <b>...</b>
Diego Vaccarini <b>... </b>

<h1>Spiegazione progetto<h1>
  Il progetto consiste nella gestione delle Call da SpringBoot che rimandino indietro info riguardanti il meteo:
  
  <b>Elenco delle Call:</b>
  
  <h2>Weather(Get)</h2>
  <i>localhost:8080/Weather?<b>Citta</b>=Soggetto?<b>Aggiornamento</b>=si</i>
  <b>Citta</b> = è l'oggetto della richiesta cioè il nome della Città oppure il suo ID ( di Default è "Roma")
  <b>Aggiornamento</b> = E un flag per dire alla call se si vuole aggiornare il Database quando si fa la Call ( di Default è "Si")  
  
  <h3><i>Esempi:</i></h3>
  <i>localhost:8080/Weather?<b>Citta</b>=Roma?<b>Aggiornamento</b>=Si</i>
  
  <h2>Stat(Post):</h2>
  <i>localhost:8080/Stat</i>
  <h3>Body da passare per post:</h3>
  ```json
  {
    "filtri":{
        "tempo":{
            "attivo":true,
            "filtro":"Giornaliero"
        },
        "ZoneGeografiche":{
            "attivo":false,
            "filtro":""
        },
        "nome":{
            "attivo":true,
            "filtro":"Fermo"
        }
    }
}
```
Ogni filtro ha 2 Attributi : Attivo e Filtro.
Attivo è un Boolean che se è True attiva il seguente filtro mentre se false il filtro viene saltato
Mentre per ogni genere di fitro il parametro filtro varia.
<h3>Filtro Tempo</h3>
Possibili opzioni:
  -Giornaliero : Il range delle statistiche sarà da il giorno prima della call alla stessa ora al momento attuale
  -Settimanale : Il range delle statistiche sarà da 7 giorni prima della call alla stessa ora al momento attuale
  -Mensile: Il range delle statistiche sarà da 30 giorni prima della call alla stessa ora al momento attuale
  -Annuale: Il range delle statistiche sarà da 365 giorni prima della call alla stessa ora al momento attuale
  -gg/mm/yyyy,gg/mm/yyyy : Il range di date customizzato
  
<h3>Filtro ZoneGeografiche</h3>
Possibili opzioni:
  -Nord : Prende solo le Città che si trovano a Nord
  -Centro : Prende solo le Citta che si trovano al Centro
  -Sud : Prende solo le Citta che si trovano al Sud

<h3>Filtro nome</h3>
 L'attributo filtro contiene il nome o l'id specifico dalla Città di cui cercare le statistiche
      

# ProgettoEsame

<h3>Componenti gruppo:</h3><br>
Nicolò Raccichini <b>s1092919</b><br>
Federico Pretini <b>...</b><br>
Diego Vaccarini <b>... </b><br>

<h1>Spiegazione progetto<h1><br>
  Il progetto consiste nella gestione delle Call da SpringBoot che rimandino indietro info riguardanti il meteo:<br>
  
  <b>Elenco delle Call:</b><br>
  
  <h2>Weather(Get)</h2><br>
  <i>localhost:8080/Weather?<b>Citta</b>=Soggetto?<b>Aggiornamento</b>=si</i><br>
  <b>Citta</b> = è l'oggetto della richiesta cioè il nome della Città oppure il suo ID ( di Default è "Roma")<br>
  <b>Aggiornamento</b> = E un flag per dire alla call se si vuole aggiornare il Database quando si fa la Call ( di Default è "Si")  <br>
  
  <h3><i>Esempi:</i></h3><br>
  <i>localhost:8080/Weather?<b>Citta</b>=Roma?<b>Aggiornamento</b>=Si</i><br>
  
  <h2>Stat(Post):</h2><br>
  <i>localhost:8080/Stat</i><br>
  <h3>Body da passare per post:</h3><br>
  
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

Ogni filtro ha 2 Attributi : Attivo e Filtro.<br>
Attivo è un Boolean che se è True attiva il seguente filtro mentre se false il filtro viene saltato<br>
Mentre per ogni genere di fitro il parametro filtro varia.<br>
<h3>Filtro Tempo</h3><br>
Possibili opzioni:<br>
  -Giornaliero : Il range delle statistiche sarà da il giorno prima della call alla stessa ora al momento attuale<br>
  -Settimanale : Il range delle statistiche sarà da 7 giorni prima della call alla stessa ora al momento attuale<br>
  -Mensile: Il range delle statistiche sarà da 30 giorni prima della call alla stessa ora al momento attuale<br>
  -Annuale: Il range delle statistiche sarà da 365 giorni prima della call alla stessa ora al momento attuale<br>
  -gg/mm/yyyy,gg/mm/yyyy : Il range di date customizzato<br>
  
<h3>Filtro ZoneGeografiche</h3><br>
Possibili opzioni:<br>
  -Nord : Prende solo le Città che si trovano a Nord<br>
  -Centro : Prende solo le Citta che si trovano al Centro<br>
  -Sud : Prende solo le Citta che si trovano al Sud<br>

<h3>Filtro nome</h3><br>
 L'attributo filtro contiene il nome o l'id specifico dalla Città di cui cercare le statistiche<br>
      

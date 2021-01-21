# ProgettoEsame

<h3>Componenti gruppo:</h3><br>
Nicolò Raccichini <b>s1092919</b><br>
Federico Pretini <b>s1092769</b><br>
Diego Vaccarini <b>S1093271 </b><br>

<h1>Spiegazione progetto<h1><br>
  Il progetto consiste nella gestione delle Call da SpringBoot che restituiscano informazioni riguardanti il meteo:<br>
  
  <b>Elenco delle Call:</b><br>
  
  <h2>Weather(Post)</h2><br>
  <i>localhost:8080/Weather</i><br>
  <h3>Body da passare in post:</h3><br>
  
  ```json
  
  {
    "citta":["Fermo",3171179]
  }
  
  ```
  
  Nel JsonArray citta inserire le citta delle quali si vorrà richiedere il meteo.<br>
  
  Ritorna un ArrayList contenente le città richieste: <br>
  
  ```json
  
  [
    {
        "id": 6541874,
        "nome": "Fermo",
        "meteo": "nubi sparse",
        "umidita": 54.0,
        "pressione": 1024.0,
        "temperatura": 10.94,
        "posizione": "Centro",
        "data": "Jan 20, 2021, 11:23:03 AM"
    },
    {
        "id": 3171179,
        "nome": "Perugia",
        "meteo": "cielo coperto",
        "umidita": 93.0,
        "pressione": 1024.0,
        "temperatura": 8.05,
        "posizione": "Centro",
        "data": "Jan 20, 2021, 11:23:03 AM"
    }
]
  
  ```
  
  
  <h2>Stat(Post):</h2><br>
  <i>localhost:8080/Stat</i><br>
  <h3>Body da passare in post:</h3><br>
  
  ```json
  
  {
    "filtri":{
        "tempo":{
            "attivo":true,
            "filtro":"Giornaliero"
        },
        "ZoneGeografiche":{
            "attivo":false,
            "filtro":"Centro"
        },
        "nome":{
            "attivo":true,
            "filtro":"Fermo"
        }
    }
}

```

Ogni filtro ha 2 Attributi : Attivo e Filtro.<br>
Attivo è un Boolean che se è True attiva il seguente filtro mentre se false il filtro viene saltato,<br>
E per ogni genere di fitro il parametro filtro varia.<br>
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

<h2>Max(Get)</h2><br>
<i>localhost:8080/Max?Periodo=Giornaliero</i><br><br>


L'attributo <b>Periodo</b> può valere: <br>
  -Giornaliero : Il range delle statistiche sarà da il giorno prima della call alla stessa ora al momento attuale<br>
  -Settimanale : Il range delle statistiche sarà da 7 giorni prima della call alla stessa ora al momento attuale<br>
  -Mensile: Il range delle statistiche sarà da 30 giorni prima della call alla stessa ora al momento attuale<br>
  -Annuale: Il range delle statistiche sarà da 365 giorni prima della call alla stessa ora al momento attuale<br>
  -gg/mm/yyyy,gg/mm/yyyy : Il range di date customizzato<br>
  
<h2>Min(Post)</h2><br>
<i>localhost:8080/Min?Periodo=Giornaliero</i><br>
<br>

L'attributo <b>Periodo</b> può valere: <br>
  -Giornaliero : Il range delle statistiche sarà da il giorno prima della call alla stessa ora al momento attuale<br>
  -Settimanale : Il range delle statistiche sarà da 7 giorni prima della call alla stessa ora al momento attuale<br>
  -Mensile: Il range delle statistiche sarà da 30 giorni prima della call alla stessa ora al momento attuale<br>
  -Annuale: Il range delle statistiche sarà da 365 giorni prima della call alla stessa ora al momento attuale<br>
  -gg/mm/yyyy,gg/mm/yyyy : Il range di date customizzato<br>
  
<h2>Fav(Get)</h2>
<i>localhost:8080/Fav?Action="Aggiungi"&name="Ancona"</i>
<br>
<h3>Parametri</h3><br>
Il parametro <b>action</b> può assumere i seguenti valori: <br>
-Aggiungi = Aggiunge <b>name</b> alla lista dei favoriti<br>
-Rimuovi = Rimuove <b>name</b> dalla lista dei favoriti<br>
-Stampa = Restituisce un JsonObject contenente la lista dei favoriti<br><br>

Il parametro <b>name</b> contiente l'oggetto dell'azione da eseguire sull'array di favoriti.

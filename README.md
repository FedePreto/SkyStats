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
  -gg/mm/yy,gg/mm/yy : Il range di date customizzato<br>
  
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
  -gg/mm/yy,gg/mm/yy : Il range di date customizzato<br>
  
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

<h2>Diagramma di casi d'uso:</h2><br>
<img src="https://github.com/Raccispini/ProgettoEsame/blob/Federico3/img/casiduso.PNG">

<h2>Diagramma delle classi</h2>

<h3>com.univpm.oop.statistiche</h3>
<img src="https://github.com/Raccispini/ProgettoEsame/blob/Federico3/img/statistiche.PNG">

<h3>com.univpm.oop.exception</h3>
<img src="https://github.com/Raccispini/ProgettoEsame/blob/Federico3/img/exception.PNG">

<h3>com.univpm.oop.filtri</h3>
<img src="https://github.com/Raccispini/ProgettoEsame/blob/Federico3/img/filtri.PNG">

<h3>com.univpm.oop.generalGui</h3>
<img src="https://github.com/Raccispini/ProgettoEsame/blob/Federico3/img/generalGui.PNG">

<h3>com.univpm.oop.log</h3>
<img src="https://github.com/Raccispini/ProgettoEsame/blob/Federico3/img/log.PNG">

<h3> com.univpm.oop.model</h3>
<img src="https://github.com/Raccispini/ProgettoEsame/blob/Federico3/img/model.PNG">

<h3> com.univpm.oop.services</h3>
<img src="https://github.com/Raccispini/ProgettoEsame/blob/Federico3/img/services.PNG">

<h3>com.univpm.oop.src</h3>
<img src="https://github.com/Raccispini/ProgettoEsame/blob/Federico3/img/src.PNG">

<h2>Diagramma delle sequenze</h2>
<img src="https://github.com/Raccispini/ProgettoEsame/blob/Federico3/img/diagrammadellesequenze.PNG">

<h2>Spiegazione delle sequenze:</h2>
Come si può notare per un fattore di sicurezza l'utente non interagisce mai con le classi esterne ma bensi solo con una, la classe Controller, che gestice tutte le rotte.
<h3>/Weather</h3><br>
Quando l'utente fa la call /Weather interagisce solo con la classe controller che a sua volta chide al database se esso ha il meteo richiesto aggiornato, altrimenti fa una call ad OpenWeather che rimanda tutto indietro a Controller e che a sua volta lo restistuisce all'utente.<br>

<h3>/Stat</h3><br>
Quando l'utente fa la call /Stat passandogli per Post gli eventuali filtri desisderati in JSon, inizialmente la classe controller converte il Json contenente i filtri in filtri veri e propri, poi una volta ottenuto il range di valori che rispettano i filtri essi vengono processati dalla classe Stat che poi restituisce alla controller e poi a sua volta all'utente.<br>

<h3>/Max e /Min</h3><br>
Quando l'utente fa una call /Max oppure /Min passandogli come parametri Get il periodo temporale sul quale calcolare questi valori, la classe Controller fa richiesta dei dati al Database per poi processarli nella classe Stat. Una volta processati sono ritornati alla classe Controller per essere mandati all'utente.<br>

<h3>/Fav</h3><br>
Quando l'utente fa una call /Fav passandogli come parametri le azioni da eseguire e gli eventuali oggetti di queste azioni. La classe Controller nel caso in cui l'azione sia un semplice Stampa chiede al Database l'array Favoriti per poi mandarlo all'utente. Mentre per le altre azioni come Aggiungi o Rimuovi la classe Controller va solo a modificare il contenuto dell'array favoriti nel Database.

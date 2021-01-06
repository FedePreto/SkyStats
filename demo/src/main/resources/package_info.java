/**
 *
 * @author Federico 
 *
 * All'interno dell'application.properties è stata aggiunta una proprietà che permette a
 * SpringBoot di utilizzare gson anziche jackson per serializzare gli oggetti. In questo
 * modo quando viene eseguito il return o il passaggio come parametro di un JsonObject
 * (o qualsiali altro oggetto derivante dalla libreria Gson) SpringBoot riuscirà a serializzare
 *  e quindi a leggere correttamente l'oggetto e a visualizzarlo in PostMan oppure a parsarlo all'interno del progetto 
 */
# Kildekode for restserveren

Bygget ved hjelp av Spring Boot rammeverket. 

Restserveren består av to klasser:
- Controller.java - Inneholder alle metoder for http-requests
- Helseapplication.java - Klasse for å kjøre Spring Boot server på localhost:8080.
<br/>

NB! 
Vi har kun implementert GET og POST-requests, ettersom det kun er disse vi har bruk for i appen per nå. 
Skulle vi arbeidet videre kunne det vært aktuelt å legge til PUT og DELETE-requests. 
På grunn av måten vi håndterer lagringen på per nå, så fungerer POST-requsten også litt på samme måte som en PUT-request. 
I praksis vil det si at POST-requesten benytter seg av save-dag metoden, og denne metoden vil overskrive dataen om du lagrer data på en dato hvor det allerede eksisterer data. 
Skulle vi arbeidet videre ville vi ha splittet dette, slik at POST-requesten blir en ren POST-request, og PUT-request blir en ren PUT-request. 
<br/>

#### Klassediagram for rest-modulen
![picture](../../../../../../img/klassediagram_rest.png)

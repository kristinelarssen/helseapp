
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2059/gr2059)

# Helseapp
En app hvor man kan lagre og følge med på helsedata over tid.

<br/>

## Hvordan kjøre appen:
I terminal 1: mvn clean install (PS! denne skal kræsje)
I terminal 1: mvn spring-boot:run -f rest/pom.xml
I terminal 2: cd helseapp
I terminal 2: mvn clean install
I terminal 2: mvn javafx:run -f fxui/pom.xml

<br/>

## Arkitektur
Prosjektet består av 3 moduler, fxui, core og rest. Dette er gjort for å separere
front-end(fxui modulen) fra back-end(core modulen). De to lagene knyttes sammen
gjennom et rest API(rest modulen). Dette er gjort slik at f.eks. hvis metode for
lagring i back-end endres til databaselagring i server, så trenger ikke noe
logikk i front-end å bli endret.

<br/>

## Mappestruktur
Prosjektet har en nøstet mappestruktur for å få god oversikt over hvor de ulike 
kodelementene ligger. I helseapp-mappen er selve prosjektmappen, hvor
kodeprosjektet ligger for en app som skal registrere ulike helsedata, som f.eks. 
kan være vekt, skritt, calorier og hvilepuls. 

    helseapp-mappen 

        src-mappen, hvor kildekoden fordeles videre med testkode i testmappen og
        appkoden i main-mappen. 
        
            main-mappen 
        
                java-mappen
            
                    helseapp-mappen, hvor koden fordeles til de ulike applagene.
            
                        ui-mappen, inneholder all kode for brukergrensesnitt(inkl. 
                        FXML-kode):
                        
                            GUI.java - Klasse som kjører appen
                            
                            GUI.fxml 
                        
                            GUIController - Klasse som håndterer actions gjort av
                            brukeren
                
                        json-mappen, inneholder kode for lagring av data til 
                        json-fil:
                    
                            DagDeserializer.java - Parser tekststrøm til tilsvarende
                            Dag-objekter
                        
                            DagerDeserializer.java - Parser tekstrøm til tilsvarende
                            Dager-objekter
                        
                            DagerModule.java - Klasse for å konfigurere et 
                            simpleModule objekt, som er nødvendig for serialisering
                        
                            DagerSerializer.java - Klasse for å konvertere dager-
                            objekt til tekststrøm
                        
                            DagPersistance.java - Klasse for å konfigurere et 
                            ObjektMapper objekt, som er nødvendig for serialisering
                        
                            DagSerializer.java - Klasse for å konvertere dag-objekt 
                            til tekststrøm
                        
                            FileData.java - Klasse som innholder metoder for lagring 
                            til json-fil og lesing fra json-fil, og metode som 
                            sørger for at det ikke er mulig å lagre samme dag flere 
                            ganger
                        
                            Dager.json - Json-fil som innheolder lagrede 
                            dag-objekter
                
                        core-mappen, innholder alle klasser og kjernelogikk knyttet 
                        til dataene som applikasjonen skal håndtere:
                    
                            Dag.java - Klasse som brukes for å registrere data for 
                            en gitt dag
                        
                            Dager.java - Klasse som implementerer logikk for å 
                            håndtere flere Dag-objekter
                        
                            Grafmetoder.java - Klasse som implementerer logikk for 
                            å lage grafer 

        test-mappen 
    
            java-mappen
        
                helseapp-mappen
            
                    ui-mappen som inneholder testkode for brukergrensesnitt
                
                    json-mappen som inneholder testkode for persistenslaget
                
                    core-mappen som inneholder testkode for domenelaget
                




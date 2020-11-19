# Group gr2059 repository
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2059/gr2059)

#Helseapp
Skal bli en app hvor man kan lagre og følge med på helsedata over tid.

#Hvordan kjøre appen:
1. I terminal 1: mvn clean install
2. I terminal 1: mvn spring-boot:run -f rest/pom.xml
3. I terminal 2: cd helseapp
4. I terminal 2: mvn clean install 
5. I terminal 2: mvn javafx:run -f fxui/pom.xml

#Struktur

Prosjektet består av 3 moduler, fxui, core og rest. Dette er gjort for å separere
front-end(fxui modulen) fra back-end(core modulen). De to lagene knyttes sammen 
gjennom et rest API(rest modulen). Dette er gjort slik at f.eks. hvis metode for
lagring i back-end endres til databaselagring i server, så trenger ikke noe 
logikk i front-end å bli endret. 

Appen har følgende mappestruktur:

    helseapp 
        
        core, hvor core modulen ligger
            
            pom.xml - Brukes for builde med Maven
        
            src, hvor kildekoden fordeles videre med testkode i testmappen og
            appkoden i main-mappen. 
        
                main-mappen 
        
                    java-mappen
            
                        helseapp-, hvor koden fordeles til lagring(json) og 
                        kjernelogikk(core)
                            
                            core, innholder alle klasser og kjernelogikk knyttet 
                            til dataene som applikasjonen skal håndtere:
                    
                                Dag.java - Klasse som brukes for å registrere 
                                data for en gitt dag
                            
                                Dager.java - Klasse som implementerer logikk for 
                                å håndtere flere Dag-objekter
                        
                            
                            json, inneholder kode for lagring av data til 
                            json-fil:
                    
                                DagDeserializer.java - Parser tekststrøm til 
                                tilsvarende Dag-objekter
                        
                                DagerDeserializer.java - Parser tekstrøm til 
                                tilsvarende Dager-objekter
                        
                                DagerModule.java - Klasse for å konfigurere et 
                                simpleModule objekt, som er nødvendig for 
                                serialisering
                        
                                DagerSerializer.java - Klasse for å konvertere 
                                dager-objekt til tekststrøm
                                
                                DagSerializer.java - Klasse for å konvertere 
                                dag-objekt til tekststrøm
                        
                                DagPersistance.java - Klasse for å konfigurere 
                                et ObjektMapper objekt, som er nødvendig for 
                                serialisering. Klassen innholder også metoder 
                                for lagring til json-fil og lesing fra json-fil,
                                og metode som sørger for at det ikke er mulig å 
                                lagre samme dag flere ganger. 
                                
                                Dager.json - Json-fil som innheolder lagrede 
                                dag-objekter
                        
                test, som inneholder testkode for core modulen.
                    
                    java
                    
                        helseapp
                        
                                core, testkode for kjernelogikken
                                
                                    DagerTest.java
                                
                                json, testkode for persistens
                                
                                    DagPersistanceTest.java - Klasse som tester
                                    lagringsmetodene.
                                    
                                    Dager.json - Fil med json-objekter som 
                                    brukes i testing.
                                    
                                    *Dager2.json - Fil med json-objekter som 
                                    brukes i testing.*

                                    
        fxui, hvor GUI-modulen ligger
            
            pom.xml - Brukes for builde med Maven
            
            src, hvor kildekoden fordeles videre med testkode i testmappen og
            appkoden i main-mappen. 
        
                main 
        
                    java
            
                        helseapp
                        
                            ui, inneholder all kode for brukergrensesnitt(inkl. 
                            FXML-kode):
                            
                                GUI.java - Klasse som kjører appen
            
                                GUI.fxml 
            
                                GUIController.java - Klasse som håndterer 
                                actions gjort av brukeren.
                                
                                Hjelpemetoder.java - Klasse som inneholder
                                hjelpemetoder til GUI
                                
                                Persistance.java - Klasse som metoder for kall
                                til rest-API.
                                
                Test
                
                    java
            
                        helseapp
                        
                            ui, inneholder all kode for testing av GUI-modulen
                            
                                GUITest.java - inneholder testkode for GUI.
                                
                                PersitanceTest.java - inneholder testkode for 
                                API kall.
                                
        rest, inneholder all kildekode for Rest-API modulen(rest)
            
            pom.xml - Brukes for builde med Maven
            
            src
            
                main
                    
                    java
                    
                        helseapp
                        
                                rest, inneholder all kode med rest-API logikk
                                
                                    Controller.java - Inneholder alle metoder
                                    for http-requests
                                    
                                    Helseapplication.java - Klasse for å kjøre
                                    Spring Boot server på localhost:8080.
                
                test
                    
                    java
                    
                        helseapp
                        
                                rest, inneholder alle tester for rest modulen
                                    
                                    ControllerTest.java - Tester http-request
                                    metodene
                    
                




# Group gr2059 repository
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2059/gr2059)

#Helseapp
Mappestruktur :
Prosjektet har en nøstet mappestruktur for å få god oversikt over hvor de ulike 
kodelementene ligger. I helseapp-mappen er selve prosjektmappen, hvor
kodeprosjektet ligger for en app som skal registrere ulike helsedata, som f.eks. 
kan være vekt, skritt, calorier og hvilepuls. Inne i helseapp-mappen ligger 
src-mappen, hvor kildekoden fordeles videre på testkode i testmappen og selve 
appkoden i main-mappen. 

I main-mappen ligger java-mappen, hvor helseapp-mappen ligger inni. I helseapp 
mappen fordeles koden til de ulike applagene. Der ui-mappen inneholder all kode 
for brukergrensesnitt(inkl. FXML-kode), json-mappen inneholder kode for lagring 
av data og, core-mappen innholder alle klasser og logikk knyttet til dataene som 
applikasjonen skal håndtere. 

I test-mappen ligger java-mappen, hvor helseapp-mappen ligger. Inne i 
helseapp-mappen ligger ui-mappen, json-mappen og core-mappen, som inneholder
testkode for henholdsvis brukergrensesnitt, lagring og domenelaget.



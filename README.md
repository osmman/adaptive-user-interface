# Instalační příručka

Softwarové požadavky
- Java JDK 7
- Maven
- JBoss AS 7.1.1.Final
- Mozilla Firefox

## Instalace serveru

Stáhněte aplikační server JBoss AS 7.1.1.Final

```
http://jbossas.jboss.org/downloads
```

Po stažení rozbalte a zprovozněte podle návodu:

```
https://docs.jboss.org/author/display/AS7/Getting+Started+Guide
```

Spusťte server s konfigurací standalone-full

```
Linux, OS X:     standalone.sh -c standalone-full.xml
Windows:         standalone.bat -c standalone-full.xml
```

## Kompilace projektu

Přejděte do složky obsahující zdrojové kódy kontextové aplikace. Na DVD se nachází v adresáři:

```
/sources/adaptive-user-interface/
```

Pro zkompilování kontextové aplikace s adaptivním uživatelským rozhraním a její nahrání na spuštěný server použijte níže uvedené Maven příkazy. Vždy se zkompiluje aplikace pouze s jedním UI, které se určí dle maven profilu.

```
jsf:    mvn clean package jboss-as:deploy -P jsf
xul:    mvn clean package jboss-as:deploy -P xul
uiml:   mvn package exec:java -P uiml
uip:    mvn clean package jboss-as:deploy -P uip
```

Sestavená aplikace v podobě EAR archívu se nachází v:

```
ear/target/AdaptiveUI.ear
```

Na DVD se nacházejí již sestavené aplikace. Jsou k nalezení v:

```
jsf:    /build/jsf/AdaptiveUI.ear
xul:    /build/xul/AdaptiveUI.ear
uip:    /build/uip/AdaptiveUI.ear
```

Pro jejich nahrání daný archiv zkopírujte do složky standalone/deployments ve složce se serverem.

## Spuštění klienta

Po nahrání jednotlivých kontextových aplikací lze spustit jejich klienty dle níže popsaného návodu.

### JSF

Pro spuštění JSF klienta zadejte do prohlížeče tuto url adresu:

```
http://localhost:8080/aspectfaces-jsf/
```

### XUL

Požadavkem pro spuštění je nainstalovaný webový prohlížeč Mozilla Firefox. Dále je potřeba aby byl tento prohlížeč obsažen v systémové proměnné PATH. Přejděte do složky se zdrojovým kóde:

```
/sources/adaptive-user-interface/aspectfaces-xul/src/main/xulrunner
```

Uživatelské rozhraní lze spustit pomocí scriptu:

```
Linux, OS X:   ./run-firefox
Windows:       ./run-firefox.bat
```

### UIP

Pro spuštění lze použít UIP klienta UIProtocol.dotNET.GenericClient.exe. Ten se nachází na DVD ve složce:

```
uip/client
```

Připojte se s tímto nastavením:

```
Connect to:       localhost
Elements:         Desktop: cz.ctu.uip.client.wpf.desktop
Client class:     cz.ctu.uip.client.wpf.desktop
User:             User 1
```

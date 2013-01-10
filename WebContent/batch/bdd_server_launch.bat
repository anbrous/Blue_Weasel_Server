echo off
cls
echo Bonjour, ICI Android
echo  vous aller lancer le serveur de base de donnees
pause
cd C:/Users/esigetel/workspace/Blue_Weasel_Server/WebContent/WEB-INF/lib
start java -classpath com.springsource.org.hsqldb-1.8.0.9.jar org.hsqldb.Server
echo Le serveur est lance
echo lancer l'interface de commande SQL, appuyer sur une touche pour lancer
pause
start java -classpath com.springsource.org.hsqldb-1.8.0.9.jar org.hsqldb.util.DatabaseManagerSwing
cls
echo Aurevoir !
pause
# project_final

* ne pas oublier de lancer DBData avant de tenter d'utiliser l'application, premier jet de donnée. 

## CMD à connaitre 
* docker-compose up -d (lancement de l'application)
* docker-compose up --build -d (build et lancement de l'application)
* docker exec -u root jenkins chgrp docker /var/run/docker.sock (en cas d'erreur sur l'utilisateur jenkins)


### Check network 
* vérifier avec la commande 'docker network ls ' s'il y a bien project_final_default

## Sonar 
* Allez sur son sonar -> se connecter -> my account -> security -> ajouter un token 
* Allez sur Jenkins et se connecter -> administrer -> système -> id global -> ajouter le token sonar (secret text)
* Jenkins -> administrer système, ajouter un sonarQube servers (url -> nom de container donc http://namecontainer:9000 , de base il s'appelle sonarqube ici) -> server authentication mettre le token 
* Attention, le nom dans withSonarQube doit être le même dans la config jenkin (ici le nom est SonarQube)

### Rappel
Il faut ajouter le dockerhub (credentials mot de passe) et le tokens git pour que le jenkins tourne correctement ! 
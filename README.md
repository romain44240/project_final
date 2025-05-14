# project_final
## CMD à connaitre 
* docker-compose up -d (lancement de l'application)
* docker-compose up --build -d (build et lancement de l'application)
* docker exec -u root jenkins chgrp docker /var/run/docker.sock (en cas d'erreur sur l'utilisateur jenkins)

### Rappel
Il faut ajouter le dockerhub (credentials mot de passe) et le tokens git pour que le jenkins tourne correctement ! 
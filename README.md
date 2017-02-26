# BaladeDuRobot

## Introduction 
Un robot est utilisé dans le dépôt d’un grand magasin pour le transport d’objets. On
s’intéresse à la minimisation du temps de transport du robot.
L’objectif de ce projet est à partir d’une instance et d’un cas d’exemple de produire
une modélisation du problème permettant de trouver le chemin qui minimise le temps de
transport du robot de sa position de départ jusqu’à son objectif.
La première partie explique comment le problème a été modélisé. La seconde partie pré-
sente l’algorithme utilisé, ainsi qu’une note sur sa complexité. La troisième partie montre les
résultats obtenu avec notre algorithme. La quatrième partie est dédiée à l’interface graphique
et son utilisation.

## Executer le programme à l'aide de la commande :
java -jar BaladeDuRobot.jar

Les ressources graphiques sont dans le dossier resources, celui-ci doit se trouver dans le même répertoire que l'exécutable.

Les sources sont dans le dossier Sources,
Le code correspondant à l'implémentation de l'algorithme sont dans :
./Sources/BaladeDuRobot/src/graphes/BFS.java

Le rapport dans Morane.Gruenpeter-Arthur.Ramolet.pdf 

Le dossier Samples contient quelques fichiers d'instance.

Le programme plante lorsque le robot se trouve au même endroit que l'objectif (Le fichier 3 correspond à ce cas).

Des libraires externes ont été utilisé (Graphstream et JGoodies), celles-ci ont été retiré des sources afin de réduire le poids de l'archive, elles sont jointes à l'exécutable qui lui devrait marcher.

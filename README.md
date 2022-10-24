# JCDecaux-Kata
Kata - Planification des tournées d'agents
par Steven QUIOT - CARBON
-------------------------------------------
<b>Sujet</b>

L'objectif du kata est de pouvoir calculer la densité de points d'intérêt présents dans une zone du monde. Les points d'intérêt sont fournis à travers un fichier CSV. Ces dits points d'intérêt comprennent une latitude et une longitude nous permettant de les représenter sur une grille simplifiée du monde.

-------------------------------------------
<b>Lancement du programme et des tests</b>

Le projet peut être cloné dans un IDE et être exécuté via le "Main".

Le "main" exécute simplement la génération d'un monde à partir d'un fichier CSV nommé "POIs_example.csv" comprenant les points d'intérêts fournis en exemple de l'énoncé. Puis, le calcul des points d'intérêt d'une zone ainsi que le calcul des deux zones les plus denses sont effectuées. Le résultat est renvoyé dans la console via des logs.

Des tests paramétrés sont aussi présents dans la partie "test" permettant de borner les différentes situations possibles que nous pouvons rencontrer dans le contexte de cet exercice. (WorldTest, ZoneTest, WorldServiceTest, FileToServiceTest)

-------------------------------------------
<b>Structure</b>

<b>Main.java</b> : Fonction main de l'application. Dans notre exercice, nous pouvons retrouver l'exécution des deux cas cités dans l'énoncé du kata. Ces derniers sont aussi présents dans les tests.
    
<b>model</b> : Modèles des objets créés lors de l'exécution du programme : 
  - World : représente le monde avec ses limites 
  - Zone : représente une case dans le monde avec un pas d'incrémentation défini à 0.5
  - PointOfInterest : représente un point d'intérêt avec ses coordonnées (latitude,longitude)
    
<b>exception</b> : Contient une exception pouvant être relevée lorsqu'une valeur liée à un attribut ou un argument s'avère être invalide.
    
<b>service</b> : Contient le service dédié à la génération du monde (FileToWorldService) et celui dédié aux traitements du monde au cours de son cycle de vie (WorldService).

Remarque pour WorldService : La méthode "getDensestZones(...)" fut réalisée avec l'API Stream simplement pour exposer une manière alternative de coder dans le cadre de l'exercice, ceci, par rapport à l'usage d'un foreach pour la méthode "countPointsOfInterestWithinZone(...). 
  
<b>resources</b> : Contient les fichiers CSV indiquant les informations relatives aux points d'intérêt à générer. Des fichiers CSV comportant des erreurs sont fournis pour tester la levée de l'exception dans les situations adéquates.
    
-------------------------------------------
 <b>Environnement</b>
  
L'exercice fut réalisé en Java 17 et testé sous JUnit 5.
  
Le projet fut produit sous Maven 4.
  
La librairie externe OpenCSV fut utilisée pour le traitement des fichiers d'entrée.



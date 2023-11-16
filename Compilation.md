# Manuel pour compiler, exécuter et configurer les simulations

## Lancement des simulations
Pour faire tourner le projet :
- Ouvrir le projet avec IntelliJ de manière usulle ;
- Se rendre dans `File` $\to$ `Project Structure` \quad (`CTRL+ALT+SHIFT+S`) ;
- Dans `Module`, allez dans `Dependencies` puis appuyer sur `+`" puis `JARs or Directories` et sélectionner `gui.jar` ;
- Pour lancer nos simulation, rendez-vous dans le fichier `Test...Simulator.java` (les `...` sont à remplacer par le nom du projet que vous souhaitez tester) et exécuter le directement depuis IntelliJ  \quad (`CRTL+SHIFT+F10`).



## Paramétrage des simulations
Si vous souhaitez configurer les simulations, vous pouvez changer certaines constantes.

### Pour Immigration, vous pouvez modifier :
-  Dans `TestImmiSimulator` : `DIVISON\_ECHELLE` et `NB\_ETAT` ;
-  Dans `ImmiSimulator` : `TAILLE\_CELLULE`.
### Pour Schelling, vous pouvez modifier :
- Dans `TestShellingSimulator` : `DIVISON\_ECHELLE`, `NB\_ETAT` et `NB\_VOISIN\_CHANGEMENT` ;
- Dans `ShellingSimulator` : `TAILLE\_CELLULE`.
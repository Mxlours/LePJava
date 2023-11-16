# Manuel pour compiler, exécuter et configurer les simulations

## Lancement des simulations
Pour faire tourner le projet :
- Ouvrir le projet avec IntelliJ de manière usulle ;
- Se rendre dans `File` $\to$ `Project Structure` \quad (`CTRL+ALT+SHIFT+S`) ;
- Dans `Module`, allez dans `Dependencies` puis appuyer sur `+`" puis `JARs or Directories` et sélectionner `gui.jar` ;
- Pour lancer nos simulation, rendez-vous dans le fichier `Test...Simulator.java` (les `...` sont à remplacer par le nom du projet que vous souhaitez tester) et exécuter le directement depuis IntelliJ  \quad (`CRTL+SHIFT+F10`).

Remarques : si vous ouvrez ce fichier Markdown depuis IntelliJ (uniquement !), 
vous pouvez cliquer sur les boutons suivant pour lancer les différentes simulations :
- `TestCellSimulator`
- `TestImmiSimulator`
- `TestShellingSimulator`
- `TestBoidsSimulator`


## Paramétrage des simulations
Si vous souhaitez configurer les simulations, vous pouvez changer certaines constantes.

### Pour le jeu de la vie de Conway, vous pouvez modifier :
- Dans `TestCellSimulator` : `DIVISON_ECHELLE` et `CHANCE` ;
- Dans `CellSimulator` : `TAILLE_CELLULE`.
### Pour Immigration, vous pouvez modifier :
-  Dans `TestImmiSimulator` : `DIVISON_ECHELLE` et `NB_ETAT` ;
-  Dans `ImmiSimulator` : `TAILLE_CELLULE`.
### Pour Schelling, vous pouvez modifier :
- Dans `TestShellingSimulator` : `DIVISON_ECHELLE`, `NB_ETAT` et `NB_VOISIN_CHANGEMENT` ;
- Dans `ShellingSimulator` : `TAILLE_CELLULE`.
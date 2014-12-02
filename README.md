Projet de Modélisation -  DUT Informatique S3 IUT A lille1

Utiliser le repository :
Installer EGit

Nous utilisons le plugin EGit avec Eclipse.

D'abord,il faut ouvrir l'utilitaire d'installation des plugins :

Help => Install new Software Cliquer sur Add, entrez l'url : http://download.eclipse.org/egit/updates , et en nom "EGit".

Dérouler le menu Eclipse Git Team Provider et cocher Eclipse Git Team Provider.

Eclipse Git Team Provider Source code, Task ... et JGit doivent rester décoché.

Laisser les box du bas dans l'état ou elles sont (Normalement une décochée, le reste cochée), puis cliquer sur next.

Suivez les instructions pour installer, jusqu'à ce que Eclipse demande de redemarrer (obéissez lui !).

Configurer EGit

Window => Preferences => Git => Team => Default Repository Folder Pour changer le dossier ou seront stockés les repository (dépôts), ça se passe ici ! Le dossier ne doit pas bouger par la suite, et ne doit pas être votre workspace eclipse.

Ensuite, dans le menu

Git => Configuration Cliquer sur "Add Entry"

Clé : user.name / Value.Name : Ton Nom (Exemple : Cuvillier Alexandre) Puis encore une fois

Clé : user.email / Value/Name : Ton email (Exemple : alexandre.cuvillier59@gmail.com) 

La dernière étape étant d'activer la toolbar :

Window => Customize perspective Et dans les command groups, ajouter Git et Git Navigation Actions.

Enfin :

Window => Show View => Other ... => Git => Git Staging Git est donc configuré, il suffit maintenant d'ajouter le projet.

Récuperer le dépôt

File => Import => Git => Project from Git

Url Dans URL : L'url du dépôt, dispo sur Git Hub Host : Laisser par défaut (github.com Tout laisser par défaut, puis next.

Laisser tout cocher sur la page "Import projects from git, puis next.

Encore un next.

Use project (ou du style, 1ère option en haut ! Qui permet de récuperer le projet déjà présent sur github) Puis Finish.

Le projet est maintenant prêt à être utilisé avec Git !

Utiliser le dépôt

A chaque fois que vous lancerez le projet (donc avant de commencer à coder) :

Git => Pull (dans la toolbar) pour recupere la dernière version mise en ligne (OBLIGATOIRE!!!) 

Ensuite, une fois que les changements effectués en codant vous conviennent, ou qu'une nouvelle classe a été crée, sauvegarder comme d'habitude, puis :

Git => Commit Entrer dans la description vos actions effectuées, de manière concise et claire. Puis selectionnez les fichiers à envoyer en bas. (Normalement tous ceux proposés). Vérifiez que l'auteur et le committer soient bien renseignés, et cliquez sur Commit and push.

Voilà, le commit a été créé et les changements sont en lign,e, les autres pourront le récupérer.

Pourquoi utiliser Git ?

D'une part, comme dans google drive, cela permet de partager facilement le code entre nous. De plus, Git offre en arrière un gestionnaire de version, permettant de récuperer un ancien fichier, de vérifier qui à modifier quoi (à la ligne de code prêt), et ainsi de mieux gérer un projet avec de nombreuses versions !

Concernant la BDD avec sqlite3:

pour que votre programme puisse utiliser la BDD, il vous faut faire la manip suivante sous eclipse: 

si ce n'est pas déjà fait, mettre l'archive sqlite-jdbc-3.8.7.jar dans votre dossier du projet, il apparaitra sous eclipse.
clic droit sur l'archive, build path, configure build path, libraries, add external jar, ajouter l'archive, retournez dans l'onglet order of export, cochez le fichier concernant l'archive puis validez tout. 

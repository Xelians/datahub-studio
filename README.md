#README

L'ajout de nouvelles capacités (collector, transformer et sender) à la solution est possible via le module **xdh-plugin**.
Pour cela il suffit d'importer le module dans un nouveau projet java et de suivre les instructions qui suivent.

## Collector

### Interface
Un collector doit nécessairement implémenter l'interface **Collector**

```java
List<String> collect(Path toDirectory, Logger logger) throws Exception;
```
La méthode **collect** prend en paramètre :
- le chemin du répertoire ou doivent être stockés les fichiers collectés.
- Le loggeur à utiliser pour écrire des logs dans le fichier "collector-yyyy-mm-dd.log"

Cette méthode renvoie le nom des fichiers stockés. La lecture ne doit pas être bloquées indéfiniment, elle doit stocker un nombre fini de fichier et renvoyé la liste.
A chaque opération de lecture entre 1 et n (éviter de dépasser un max d'une centaine de fichiers) fichiers doivent être stockés, on peut donc avoir un collector stateful pour stocker l'état de la lecture sur le channel.
Les collects se font de manières non concurrentes un seul thread à la fois effectuera une opération de lecture sur 1 channel particulier.

Les paramètres du collector correspondent à des variables d'instances de l'implémentation qui doivent être initialisés via le constructeur.
Pour ajouter des paramètres, il suffira donc de définir des paramètres dans le constructeur de l'implémentation.
Les types de paramètres autorisés :
- Integer / int
- Boolean / boolean
- Double /  double
- Float / float
- Long / long
- String

Les paramètres de type **List** sont autorisés avec comme type paramétré un des types précédemment cités. Le type paramétré par défaut sera String si aucun type paramétré n'est défini.

### Configuration

Pour qu'un collector soit pris en compte il faudra définir une configuration implémentant l'interface **CollectorConfiguration**

```java
Class<? extends Collector> getCollectorClass(); // renvoi la classe de l'implémentation du collector en question

String getName(); // le nom du collector

PluginForm getForm(); // la définition du formulaire de paramétrage du plugin
```


## Transformer

### Interface
Un transformer doit nécessairement implémenter l'interface **Transformer**

```java
String transform(List<String> fileNames, Path fromDirectory, Path toDirectory, Logger logger) throws Exception;
```
La méthode **transform** prend en paramètre :
- La liste des noms de fichiers à transformer
- le chemin du répertoire ou sont stockés les fichiers à transformer.
- le nom du répertoire ou il faut stocker le fichier transformé.
- Le loggeur à utiliser pour écrire des logs dans le fichier "transform-yyyy-mm-dd.log"

Cette méthode renvoie le nom du fichier transformé. La transformation est nécessairement stateless

Les paramètres du transformer correspondent à des variables d'instances de l'implémentation qui doivent être initialisés via le constructeur.
Pour ajouter des paramètres, il suffira donc de définir des paramètres dans le constructeur de l'implémentation.
Les types de paramètres autorisés :
- Integer / int
- Boolean / boolean
- Double /  double
- Float / float
- Long / long
- String

Les paramètres de type **List** sont autorisés avec comme type paramétré un des types précédemment cités. Le type paramétré par défaut sera String si aucun type paramétré n'est défini.

### Configuration

Pour qu'un transformer soit pris en compte il faudra définir une configuration implémentant l'interface **TransformerConfiguration**

```java
Class<? extends Transformer> getTransformerClass(); // renvoi la classe de l'implémentation du transformer en question

String getName(); // le nom du transformer

PluginForm getForm(); // la définition du formulaire de paramétrage du plugin
```


## Sender

### Interface
Un sender doit nécessairement implémenter l'interface **Sender**

```java
void send(String fileName, Path fromDirectory, Logger logger) throws Exception;
```
La méthode **send** prend en paramètre :
- Le nom du fichier à envoyer
- le chemin du répertoire ou se situe le fichier
- Le loggeur à utiliser pour écrire des logs dans le fichier "sender-yyyy-mm-dd.log"

Les paramètres du sender correspondent à des variables d'instances de l'implémentation qui doivent être initialisés via le constructeur.
Pour ajouter des paramètres, il suffira donc de définir des paramètres dans le constructeur de l'implémentation.
Les types de paramètres autorisés :
- Integer / int
- Boolean / boolean
- Double /  double
- Float / float
- Long / long
- String

Les paramètres de type **List** sont autorisés avec comme type paramétré un des types précédemment cités. Le type paramétré par défaut sera String si aucun type paramétré n'est défini.

### Configuration

Pour qu'un sender soit pris en compte il faudra définir une configuration implémentant l'interface **TransformerConfiguration**

```java
Class<? extends Sender> getSenderClass(); // renvoi la classe de l'implémentation du sender en question

String getName(); // le nom du transformer

PluginForm getForm(); // la définition du formulaire de paramétrage du plugin
```


## Ajout du plugin

Pour ajouter le nouveau plugin à la solution, il suffit de packager le projet en une archive de type **jar** et déposer cette archive dans un dossier "**/lib**" à créer à la racine du répertoire root de l'application.
Lors du lancement de l'application les collectors, transformers et senders implémentés correctement seront chargés dans le contexte de plugin de l'application.

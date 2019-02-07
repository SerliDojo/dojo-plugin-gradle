# Coding Dojo sur les Plugins Gradle

Deux projets :

`plugin-gradle` :
Le projet contient les plugins gradle créés lors du coding dojo.
Il y a deux plugins :

- `com.serli.dojo.hello` : fournit des tâches pour afficher des messages en ASCII Art.
- `com.serli.dojo.hook` : un plugin qui permet de brancher des [git hooks](https://git-scm.com/book/uz/v2/Customizing-Git-Git-Hooks) appelant des tâches gradle.

`plugin-gradle-demo-app` :
Il s'agit d'un projet gradle permettant de tester les plugins créés. Il contient une application Spring boot "vide".
Il n'y a qu'un test unitaire qui permet de tester les [git hooks](https://git-scm.com/book/uz/v2/Customizing-Git-Git-Hooks).

## Publication des plugins

Pour publier les plugins gradle, on va simplement utiliser le repository maven local dans cet exemple :

```sh
cd plugin-gradle
gradlew publishToMavenLocal
```

## Les plugins

### com.serli.dojo.hello

#### Installation

Le plugin ayant été publié dans le mavenLocal, on va pouvoir l'ajouter au projet `plugin-gradle-demo-app` :

```groovy
buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        classpath("com.serli.dojo:plugin-gradle:0.0.1-SNAPSHOT")
    }
}

apply plugin: 'com.serli.dojo.hello'
```

#### Tâche hello

Le plugin fournit une tâche gradle `hello` permettant d'afficher un message dans la console.
C'est complètement inutile et donc absolument nécessaire :happy.

Par défault, le message affichait est `Hello from GreetingPlugin`.
Ce message peut être configuré par une extension `greeting` (voir ci-dessous).

```sh
λ gradlew tasks

> Task :tasks

------------------------------------------------------------
Tasks runnable from root project
------------------------------------------------------------
...

Dojo tasks
----------
hello - Produces a greeting

λ gradlew hello
Starting a Gradle Daemon, 1 busy and 1 stopped Daemons could not be reused, use --status for details

> Task :hello
  _   _          _   _              __                                 ____                        _     _                   ____    _                   _
 | | | |   ___  | | | |   ___      / _|  _ __    ___    _ __ ___      / ___|  _ __    ___    ___  | |_  (_)  _ __     __ _  |  _ \  | |  _   _    __ _  (_)  _ __
 | |_| |  / _ \ | | | |  / _ \    | |_  | '__|  / _ \  | '_ ` _ \    | |  _  | '__|  / _ \  / _ \ | __| | | | '_ \   / _` | | |_) | | | | | | |  / _` | | | | '_ \
 |  _  | |  __/ | | | | | (_) |   |  _| | |    | (_) | | | | | | |   | |_| | | |    |  __/ |  __/ | |_  | | | | | | | (_| | |  __/  | | | |_| | | (_| | | | | | | |
 |_| |_|  \___| |_| |_|  \___/    |_|   |_|     \___/  |_| |_| |_|    \____| |_|     \___|  \___|  \__| |_| |_| |_|  \__, | |_|     |_|  \__,_|  \__, | |_| |_| |_|
                                                                                                                     |___/                       |___/
```

#### Extension greeting

L'extension permet de configurer le plugin `hello` en fournissant un message alternatif au message par défault `Hello from GreetingPlugin`.
Pour ce faire, on va ajouter au `build.gradle` du projet `plugin-gradle-demo-app` :

```groovy
greeting {
    message = "Hi, Serli Dojo"
}
```

Ce message est utilisé par la tâche `hello`.

#### Tâche Greeting

Le plugin fournit aussi une implémentation d'une tâche `Greeting` permettant d'ajouter autant de message que souhaité dans un projet.

Par exemple, on peut ajouter deux tâches `say-hello` pour dire bonjour en anglais et son pendant `dis-bonjour` pour le français.

```groovy
tasks.register("say-hello", com.serli.dojo.hello.Greeting) {
    group = "Dojo"
    description = "Produces a greeting in english with recipient and message"

    greeting = "Hello"
    recipient = "Serli Dojo"
}

tasks.register("dis-bonjour", com.serli.dojo.hello.Greeting) {
    group = "Dojo"
    description = "Produces a greeting in french with recipient and message"

    greeting = "Bonjour"
    recipient = "Serli Dojo"
}

```

### com.serli.dojo.hook

#### Installation

Le plugin ayant été publié dans le mavenLocal, on va pouvoir l'ajouter au projet `plugin-gradle-demo-app` :

```groovy
buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        classpath("com.serli.dojo:plugin-gradle:0.0.1-SNAPSHOT")
    }
}

apply plugin: 'com.serli.dojo.hook'
```

#### Tâche installGitHooks

Cette tâche va générer des scripts [git hooks](https://git-scm.com/book/uz/v2/Customizing-Git-Git-Hooks) dans le dossier `.git/hooks` pour chaque hook possible.

Le fichier hook contient le script suivant :

```sh
#!/bin/sh

hookName=`basename "$0"`
gitParams="$*"

./gradlew $hookName -Pparams="$gitParams" --quiet --console=plain
```

Ce script appelle la tâche gradle correspondant au nom du script invoqué.
Par exemple, pour le script `pre-commit`, on va lancer la commande `gradlew pre-commit ...`.

#### Tâche de hook

Le plugin ajoute des tâches pour chaque hook possible.
De cette façon, on peut se brancher sur le hook simplement en utilisant `dependsOn` : 

```groovy
// build project on pre-commit hook
tasks.named("pre-commit").configure {
    it.dependsOn tasks.named("build")
}
```

## Documentations gradle

La documentation gradle :

- [more_about_tasks](https://docs.gradle.org/current/userguide/more_about_tasks.html)
- [lazy_configuration](https://docs.gradle.org/current/userguide/lazy_configuration.html)
- [custom_plugins](https://docs.gradle.org/current/userguide/custom_plugins.html)
- [working_with_files](https://docs.gradle.org/current/userguide/working_with_files.html)
- [testing-gradle-plugins](https://guides.gradle.org/testing-gradle-plugins/)

## ASCII Art

On utilise la librarie [jfiglet](https://github.com/lalyos/jfiglet/blob/master/README.md) pour générer du texte en ASCII Art.
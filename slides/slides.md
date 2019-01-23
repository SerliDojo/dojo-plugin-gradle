<img alt="Serli Coding Dojo" src="/images/serli-coding-dojo.png" style="width: 50%" />

## Gradle Plugin

---

![](/images/but-why.png)

Note:

Altima - Client du domaine de l'assurance

Début 2018 :
  - 1 projet multi-module
  - 8 sous-projets
  - Libs, Spring boot, Java

Aujourd'hui :
- +30 projets
- Libs, Spring boot projects, Java & *Kotlin*

---

![](/images/so-what.png)

---

![](/images/nervous-breakdown.jpg)

Note:

- Difficultés à maintenir des builds cohérents
- Beaucoup de duplications dans les builds
- Plugins, repositories, tasks configurés de la même façon

---

![](/images/solution.png)

Note:
Création de plugins pour rationaliser les builds

4 plugins :
- Defaults : configuration par défaut
- Libs : config source-jar, javadoc-jar
- Api : config spring boot packaging
- Gzip : gzip files, utilisé pour gzipper les images

---

![](/images/lets-code.jpg)

---

## Un premier plugin : Hello World

But : Créer une tâche Gradle pour afficher "hello world"

Source : [Gradle - custom plugins](https://docs.gradle.org/current/userguide/custom_plugins.html)

Lib : [jfiglet](https://github.com/lalyos/jfiglet/blob/master/README.md) - Ascii art text

---

## Avec des paramètres !

Même plugin mais on peut paramétrer le texte affiché dans le build.gradle

---

## Un vrai projet !?

---

![](/images/ambitious.jpg)

---

## Plugin Git Hooks

- Un tâche pour installer des git hooks
- Des tâches sur lesquelles brancher les hooks

---

## Sources

La documentation gradle :

- [more_about_tasks](https://docs.gradle.org/current/userguide/more_about_tasks.html)
- [lazy_configuration](https://docs.gradle.org/current/userguide/lazy_configuration.html)
- [custom_plugins](https://docs.gradle.org/current/userguide/custom_plugins.html)
- [working_with_files](https://docs.gradle.org/current/userguide/working_with_files.html)
- [testing-gradle-plugins](https://guides.gradle.org/testing-gradle-plugins/)
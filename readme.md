# Coding Dojo sur les Plugins Gradle

## Notes

Starting the slides :

```sh
cd slides && npm start
```

There's a speaker view : 

It includes a timer, preview of the upcoming slide as well as your speaker notes.
Press the **S** key to try it out.

Script de hook qui appelle gradle : 

```sh
#!/bin/sh

hookName=`basename "\$0"`
gitParams="\$*"

./gradlew $hookName -Pparams="$gitParams"
```
### Description
Active Fire Supression to stop massive fires.

### Building a Jar

1) download src
2) run gradlew.bat
3) go to the plugin folder in cmd. (example: `cd C:\user\one\desk\pluginfolder\`)
4) type `gradlew jar` and execute
5) done, look for plugin.jar in pluginfolder\build\libs\

Note: Highly recommended to use Java 8.

### Installing

Simply place the output jar from the step above in your server's `config/mods` directory and restart the server.
List your currently installed plugins/mods by running the `mods` command.  
Then, place the settings.json in your mods folder.

### Configuring

To modify settings, modify the settings.json file.

It should look like this
```
{
  "afs":
  {
    "timer": 2
  }
}
```
If you have more than one mod using settings.json, it should look like this
```
{
  "mod1":
  {
    "stuff": ""
  },
  "afs":
  {
    "timer": 2
  }
}
```

### Self Promotion
Our discord server: http://cn-discord.ddns.net  
Our game servers:  
chaotic-neutral.ddns.net:1111  
chaotic-neutral.ddns.net:2222  

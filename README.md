# KingGen Java
###### API Wrapper developed for KingGen by Presti (Azura Dev)

## How to use?
You have to create a new Instance of the KingGen Wrapper. Example:
```java
KingGen kingGen = new KingGen("Your API-Key");
```
After creating a new Instance of the wrapper, you simply call the request methode with an Endpoint. Example:

```java
package your.very.cool.project.or.what.ever.lol;

import wtf.kinggen.KingGen;
import wtf.kinggen.api.Endpoint;
import wtf.kinggen.entities.KingGenResponse;

public class Tutorial {

    KingGen kingGen = new KingGen("Your API-Key");

    public void generate() {
        KingGenResponse kingGenResponse = kingGen.request(Endpoint.GENERATE);
    }

    public void profile() {
        KingGenResponse kingGenResponse = kingGen.request(Endpoint.PROFILE);
    }
}
```
The Response will give you a Profile or a generated Account if it was successful, otherwise it will throw an Exception, that you have to handle yourself.

## I have issues implementing this.
You can either go ratio or contact me.
Discord: El Rosé#6603
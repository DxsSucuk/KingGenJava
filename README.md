# KingGen Java
[![](https://jitpack.io/v/DxsSucuk/KingGenJava.svg)](https://jitpack.io/#DxsSucuk/KingGenJava)
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
import wtf.kinggen.entities.KingGenAccount;
import wtf.kinggen.entities.KingGenProfile;

public class Tutorial {

    KingGen kingGen = new KingGen("Your API-Key");

    public void generate() {
        KingGenAccount kingGenAccount = kingGen.generateAccount();
    }

    public void profile() {
        KingGenProfile kingGenAccount = kingGen.fetchProfile();
    }
}
```
For more Examples visit: [Examples](https://github.com/DxsSucuk/KingGenJava/tree/master/example) <br  />
The Response will give you a Profile or a generated Account if it was successful, otherwise it will throw an Exception, that you have to handle yourself.

## I have issues implementing this.
You can either go ratio or contact me.
Discord: El Rosé#6603
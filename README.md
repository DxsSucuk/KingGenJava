# KingGen Java

[![](https://jitpack.io/v/DxsSucuk/KingGenJava.svg)](https://jitpack.io/#DxsSucuk/KingGenJava)

###### API Wrapper developed for KingGen by Presti (Azura Dev)

## Maven
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.github.DxsSucuk</groupId>
    <artifactId>KingGenJava</artifactId>
    <version>Tag</version>
  </dependency>
</dependencies>
```

## How to use?

You have to create a new Instance of the KingGen Wrapper. Example:

```java
KingGen kingGen = new KingGen("Your API-Key");
```

After creating a new Instance of the wrapper, you simply call the request methode with an Endpoint. Example:

```java
package your.very.cool.project.or.what.ever.lol;

import java.lang.Exception;

import wtf.kinggen.KingGen;
import wtf.kinggen.entities.KingGenAccount;
import wtf.kinggen.entities.KingGenProfile;
import wtf.kinggen.entities.KingGenStock;

public class Tutorial {

    KingGen kingGen = new KingGen("Your API-Key");

    public void generate() throws Exception {
        KingGenAccount kingGenAccount = kingGen.fetchAccount();
    }

    public void profile() throws Exception {
        KingGenProfile kingGenAccount = kingGen.fetchProfile();
    }

    public void stock() throws Exception {
        KingGenStock kingGenStock = kingGen.fetchStock();
    }

    public void generateASync() throws Exception {
        kingGen.fetchAccountASync(kingGenAccount ->
                        login(kingGenAccount.getEmail(), kingGenAccount.getPassword()),
                Throwable::printStackTrace);
    }

    public void profileASync() throws Exception {
        kingGen.fetchProfileASync(kingGenProfile -> System.out.println(kingGenProfile.getUsername()),
                Throwable::printStackTrace);
    }

    public void stockASync() throws Exception {
        kingGen.fetchStockASync(kingGenStock -> System.out.println(kingGenStock.getStock()),
                Throwable::printStackTrace);
    }
}
```

For more Examples visit: [Examples](https://github.com/DxsSucuk/KingGenJava/tree/master/example) <br  />
Non-ASync methods will give you a Profile or a generated Account if it was successful, otherwise it will throw an Exception,
that you have to handle yourself. But ASync methods will not return anything and will throw an Exception if the success Callback is null.

## Note
The versions after 1.2.0 have been inspired by [unsmxrt](https://github.com/unsmxrt/kinggen-java)

## If you have Issues implementing it.

You can contact me. Discord: El Rosé#6603

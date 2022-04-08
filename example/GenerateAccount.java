package your.very.cool.project.or.what.ever.lol;

import java.lang.Exception;

import wtf.kinggen.KingGen;
import wtf.kinggen.entities.KingGenAccount;

public class GenerateAccount throws Exception {

    KingGen kingGen = new KingGen("Your API-Key");

    public void main() {

        KingGenAccount kingGenAccount = kingGen.generateAccount();

        // Your login code or call right here.
        loginInVeryCoolAccount(kingGenAccount.getAccountEmail(), kingGenAccount.getAccountPassword());
    }
}
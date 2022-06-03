package your.very.cool.project.or.what.ever.lol;

import java.lang.Exception;

import wtf.kinggen.KingGen;
import wtf.kinggen.entities.KingGenAccount;

public class FetchAccount {

    KingGen kingGen = new KingGen("Your API-Key");

    public void main() throws Exception {

        KingGenAccount kingGenAccount = kingGen.fetchAccount();

        // Your login code call right here.
        loginInVeryCoolAccount(kingGenAccount.getEmail(), kingGenAccount.getPassword());
    }
}
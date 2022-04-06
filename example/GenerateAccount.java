package your.very.cool.project.or.what.ever.lol;

import wtf.kinggen.entities.KingGenAccount;

public class GenerateAccount {

    KingGen kingGen = new KingGen("Your API-Key");

    public void main() {

        KingGenAccount kingGenAccount = kingGen.generateAccount();

        // Your login code or call right here.
        loginInVeryCoolAccount(kingGenAccount.getAccountEmail(), kingGenAccount.getAccountPassword());
    }
}
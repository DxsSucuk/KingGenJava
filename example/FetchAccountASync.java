package your.very.cool.project.or.what.ever.lol;

import wtf.kinggen.KingGen;
import wtf.kinggen.entities.KingGenAccount;

import java.lang.Exception;
import java.util.function.Consumer;

public class FetchAccountASync {

    KingGen kingGen = new KingGen("Your API-Key");

    public void main() throws Exception {
        kingGen.fetchAccountASync(kingGenAccount -> login(kingGenAccount.getAccountEmail(), kingGenAccount.getAccountPassword()), throwable -> throwable.printStackTrace());
    }
}
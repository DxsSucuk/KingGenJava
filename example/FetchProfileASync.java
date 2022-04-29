package your.very.cool.project.or.what.ever.lol;

import wtf.kinggen.KingGen;
import wtf.kinggen.entities.KingGenProfile;

import java.lang.Exception;
import java.util.function.Consumer;

public class FetchProfileASync {

    KingGen kingGen = new KingGen("Your API-Key");

    public void main() throws Exception {
        kingGen.fetchProfileASync(kingGenProfile -> /* what ever you wanna do with it */, throwable -> throwable.printStackTrace());
    }
}
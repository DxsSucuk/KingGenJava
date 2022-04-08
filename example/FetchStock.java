package your.very.cool.project.or.what.ever.lol;

import java.lang.Exception;

import wtf.kinggen.KingGen;
import wtf.kinggen.entities.KingGenStock;

public class FetchStock {

    KingGen kingGen = new KingGen("Your API-Key");

    public void main() throws Exception {

        KingGenStock kingGenStock = kingGen.fetchStock();

        // Your handling code here
        System.out.println("Stock: " + kingGenStock.getStock());
    }
}
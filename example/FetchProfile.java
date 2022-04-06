package your.very.cool.project.or.what.ever.lol;

public class FetchProfile {

    KingGen kingGen = new KingGen("Your API-Key");

    public void main() {

        KingGenProfile kingGenAccount = kingGen.fetchProfile();

        // Your handling code here
        System.out.println("Stock: " + kingGenAccount.getStock());
    }
}
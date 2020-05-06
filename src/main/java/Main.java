import CLI.gameCLI;
import hibernate.Provider;

public class Main {
    public static void main(String[] args) {
        Provider provider = Provider.getInstance();
        provider.open();
        gameCLI.Start();
        provider.close();
    }
}

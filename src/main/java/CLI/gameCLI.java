package CLI;

import Player.Player;
import hibernate.Provider;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;
//@Entity

public class gameCLI {
    public static final gameCLI gameCli = new gameCLI();

    public static gameCLI getInstance() {
        return gameCli;
    }

    //    @Id
    private @Getter
    @Setter
    Player currentPlayer;

    public static void Start() {
        Scanner scanner = new Scanner(System.in);
        Provider provider = Provider.getInstance();
        System.out.println("***WellCome to HearthStone:)***");
        System.out.println("Do you already have an account?(Yes/No/exit all/help)");
        while (true) {
            String haveAccount = scanner.nextLine();
            if (haveAccount.equalsIgnoreCase("No")) {
                new Player().sign_up();
                break;
            } else if (haveAccount.equalsIgnoreCase("yes")) {
                new Player().log_in();
                break;
            } else if (haveAccount.equalsIgnoreCase("exit all")) {
                System.exit(0);
                break;
            } else if (haveAccount.equalsIgnoreCase("help"))
                System.out.println("To login your existing account, enter keyword [Yes]. \n To create a new account, enter keyword [No]. \n To close game); enter keyword [exit all] ");
            System.out.println("Invalid answer, Retry...");
        }
    }

    public static void selectMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose one of these choices: (delete_player/ Collections/ Store/ Help/ exit all/ exit)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("delete_player")) {
                System.out.println("To delete your account, Enter your password. \n Password:");
                while (true) {
                    if (scanner.nextLine().equals("back")){
                        selectMenu();
                    }else
                    if (scanner.nextLine().equals(gameCli.currentPlayer.getPassword())) {
                        Provider provider = Provider.getInstance();
                        provider.beginTransaction();
                        provider.delete(gameCLI.getInstance().currentPlayer);
                        provider.commit();
                        Start();
                        break;
                    } else {
                        System.out.println("input password is wrong, reenter another password or enter keyword [back] to get in main menu. ");
                    }
                }
                break;
            }
            if (answer.equalsIgnoreCase("Collections")) {
                Menu.Collection();
                break;
            }
            if (answer.equalsIgnoreCase("Store")) {
                Menu.Store();
                break;
            }
            if (answer.equalsIgnoreCase("Help")) {
                System.out.println("*To manage your heroes and cards, Enter keyword [Collections]. \n " +
                        "*To bye and sell cards and manage your wallet, Enter keyword [Store]." +
                        " \n To close game); enter keyword [exit all]." +
                        " \n To delete current account, enter keyword [delete_player]. ");
                selectMenu();
                break;
            }
            if (answer.equalsIgnoreCase("exit all")) {
                System.exit(0);
                break;
            }
            if (answer.equalsIgnoreCase("exit")) {
                Start();
                break;
            } else {
                System.out.println("Invalid input!!! ");
            }
        }
    }
}


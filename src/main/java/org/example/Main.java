
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int Options;
        int password;

        do{
        
            System.out.println("Welcome");
            System.out.println("Select an Option");
            System.out.println("1. Change Password");
            System.out.println("2. withdraws");
            System.out.println("3. Transfers");
        

        

         Options = scanner.nextInt();

            switch(Options){

            case 1:
                changePassword(scanner);
                break;
            case 2:
                withdraws(scanner);
                break;
            case 3:
                transfers(scanner);
                break;
            default:
                System.out.println("Invalid option. Try again");
        }
        
    } while (Options != 3);

    }

    public static void changePassword(Scanner scanner){

        int passwordOption;

        do{
            System.out.println("[-Password Menu-]");
            System.out.println("Change your password");

            switch (scanner) {
                case password:
                    if(password == password){
                        System.out.println("Type your new password");
                        int newPassword = password;

                    }
                    break;
            
                default:
                    break;
            }
        }

    }

}
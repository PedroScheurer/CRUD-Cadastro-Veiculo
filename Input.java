import java.util.Scanner;

public class Input {
    public static int scanInt(String message, Scanner scan) {
        
        while (true) {
            try {
                System.out.print(message);
                int value = scan.nextInt();
                return value;
            } catch (Exception e) {
                System.out.print("Valor inválido, digite um valor inteiro\n");
            } finally{
                scan.nextLine();
            }
        }
    }

    public static String scanString(String message, Scanner scan){
        while (true) {
            try {
               System.out.print(message);
               String value = scan.nextLine();
               value = value.trim();

                if(value.isEmpty()){
                    System.out.println("Valor inválido");
                } else{
                    return value;
                }
            } catch (Exception e) {
                System.out.print("Valor inválido, digite um valor do tipo String");
            }
        }
    }
};

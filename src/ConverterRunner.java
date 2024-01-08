import java.util.Scanner;
import java.util.Arrays;

class ConverterRunner {
    public static void main(String[] args) {
        System.out.println("Welcome to the Number Converter!");
        System.out.println("--------------------------------");
        System.out.print("Enter the base of your number (2, 8 or 10): ");

        Scanner s = new Scanner(System.in);
        String choice = s.nextLine();
        if(!choice.equals("2") && !choice.equals("8") && !choice.equals("10"))
        {
            System.out.print("Please enter a valid base (2, 8 or 10): ");
            choice = s.nextLine();
        }
        int base = Integer.parseInt(choice);

        System.out.print("Enter your number: ");
        String number = s.nextLine();
        int n = Integer.parseInt(number);
        boolean correct = checkInput(n,base);
        while(!correct)
        {
            System.out.print("Please enter a valid number: ");
            number = s.nextLine();
            n = Integer.parseInt(number);
            correct = checkInput(n,base);
        }

        s.close();

        NumberConverter nc = new NumberConverter(n, base);
        int[] digits = nc.getDigits();
        //System.out.println("\n\nDigit array: " + Arrays.toString(digits));
        //System.out.println("Number: " + nc.displayOriginalNumber());
        System.out.println();
        if(base == 10)
        {
            System.out.println("Binary Number: " + nc.convertArray(nc.convertToBinary(),2));
            System.out.println("Octal Number: " +nc.convertArray(nc.convertToOctal(),8));
        }
        else if(base == 8)
        {
            System.out.println("Binary Number: " +nc.convertArray(nc.convertToBinary(),2));
            System.out.println("Decimal Number: " +nc.convertArray(nc.convertToDecimal(),10));
        }
        else if ( base == 2)
        {
            System.out.println("Octal Number: " +nc.convertArray(nc.convertToOctal(),8));
            System.out.println("Decimal Number: " +nc.convertArray(nc.convertToDecimal(),10));
        }
    }

    public static boolean checkInput(int value,int base)
    {
        String sValue = Integer.toString(value);
        for(int i = 0;i<sValue.length();i++)
        {
            int letterValue = Integer.parseInt(sValue.substring(i,i+1));
            if(letterValue>(base-1)) return false;
        }
        return true;
    }
}


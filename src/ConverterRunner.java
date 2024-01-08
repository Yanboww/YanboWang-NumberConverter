import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
class ConverterRunner {
    public static HashMap<String,Integer> digiKey = new HashMap<>();
    public static HashMap<Integer,String> reverseDigiKey = new HashMap<>();
    public static void main(String[] args) {

        File f;
        try{
            f = new File("DataFile/DigitValue");
            Scanner ss = new Scanner(f);
            while(ss.hasNextLine())
            {
                String line = ss.nextLine();
                String digit = line.substring(line.indexOf(" ")+1);
                int value = Integer.parseInt(line.substring(0,line.indexOf(" ")));
                digiKey.put(digit,value);
                reverseDigiKey.put(value,digit);
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
            System.exit(0);
        }
        System.out.println("Welcome to the Number Converter!");
        System.out.println("--------------------------------");
        System.out.print("Enter the base of your number (2, 8, 10 or 16): ");

        Scanner s = new Scanner(System.in);
        String choice = s.nextLine();
        if(!choice.equals("2") && !choice.equals("8") && !choice.equals("10") && !choice.equals("16"))
        {
            System.out.print("Please enter a valid base (2, 8, 10 or 16): ");
            choice = s.nextLine();
        }
        int base = Integer.parseInt(choice);
        int convertBase = 0;
        if(base == 10)
        {
            System.out.print("Please enter a base number between 1 - 64 to convert to: ");
            String sConvertBase = s.nextLine();
            convertBase = Integer.parseInt(sConvertBase);
            while(convertBase>63 || convertBase<1 )
            {
                System.out.print("Please enter a valid base number between 1 - 64 to convert to: ");
                sConvertBase = s.nextLine();
                convertBase = Integer.parseInt(sConvertBase);
            }
        }
        int n = 0;
        String[] numberArray = new String[10];
        if(base<11)
        {
            System.out.print("Enter your number: ");
            String number = s.nextLine();
            n = Integer.parseInt(number);
            boolean correct = checkInput(n,base);
            while(!correct)
            {
                System.out.print("Please enter a valid number: ");
                number = s.nextLine();
                n = Integer.parseInt(number);
                correct = checkInput(n,base);
            }
        }
        else
        {
            System.out.print("Enter your number: ");
            String number = s.nextLine();
            number = number.toUpperCase();
            numberArray = complexToArray(number);
            boolean correct = checkComplex(numberArray,base);
            while(!correct)
            {
                System.out.print("Please enter a valid number: ");
                number = s.nextLine();
                number = number.toUpperCase();
                numberArray = complexToArray(number);
                correct = checkComplex(numberArray,base);
            }
        }

        s.close();

        NumberConverter nc;
        if(base<11)
        {
            nc = new NumberConverter(n, base, digiKey,reverseDigiKey);
        }
        else {
            nc = new NumberConverter(numberArray,base,digiKey,reverseDigiKey);
        }
        System.out.println();
        if(base == 10)
        {
            System.out.println("Binary Number: " + nc.convertArray(nc.convertToBinary(),2));
            System.out.println("Octal Number: " +nc.convertArray(nc.convertToOctal(),8));
            System.out.println("Hex Number: " +nc.convertComplexArray(nc.convertToAbove(16)));
            System.out.println("Base " + convertBase + " Number: " + nc.convertComplexArray(nc.convertToAbove(convertBase)));
        }
        else if(base == 8)
        {
            System.out.println("Binary Number: " +nc.convertArray(nc.convertToBinary(),2));
            System.out.println("Decimal Number: " +nc.convertArray(nc.convertToDecimal(),10));
            System.out.println("Hex Number: " +nc.convertComplexArray(nc.convertToAbove(16)));
        }
        else if ( base == 2)
        {
            System.out.println("Octal Number: " +nc.convertArray(nc.convertToOctal(),8));
            System.out.println("Decimal Number: " +nc.convertArray(nc.convertToDecimal(),10));
            System.out.println("Hex Number: " +nc.convertComplexArray(nc.convertToAbove(16)));
        }
        else if ( base == 16)
        {
            System.out.println("Binary Number: " +nc.convertArray(nc.convertToBinary(),2));
            System.out.println("Decimal Number: " +nc.convertArray(nc.convertToDecimal(),10));
            System.out.println("Octal Number: " +nc.convertArray(nc.convertToOctal(),8));
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

    public static String[] complexToArray(String value)
    {
        String[] valueArray = new String[value.length()];
        for(int i = 0;i<valueArray.length;i++)
        {
            valueArray[i] = value.substring(i,i+1);
        }
        return valueArray;
    }

    public static boolean checkComplex(String[] value, int base)
    {
        for(String piece : value)
        {
            int trueValue = digiKey.get(piece);
            if(trueValue>base-1) return false;
        }
        return true;
    }

}


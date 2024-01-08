import java.util.HashMap;
public class NumberConverter {
    int[] digits;
    int base;
    private HashMap<String,Integer> key = new HashMap<>();
    private HashMap<Integer,String> reverseKey = new HashMap<>();

    public NumberConverter(int number, int base,HashMap<String,Integer> digi, HashMap<Integer, String> reverseDigi) {
        String numberAsString = Integer.toString(number);
        digits = new int[numberAsString.length()];
        for (int i = 0; i < numberAsString.length(); i++) {
            String single = numberAsString.substring(i,i+1);
            int d = Integer.parseInt(single);
            digits[i] = d;
        }
        this.base = base;
        key = digi;
        reverseKey = reverseDigi;
    }

    public NumberConverter(String[] number, int base, HashMap<String,Integer> digi, HashMap<Integer, String> reverseDigi)
    {
        digits = new int[number.length];
        for(int i = 0; i<digits.length;i++)
        {
            digits[i] = digi.get(number[i]);
        }
        this.base = base;
        key = digi;
        reverseKey = reverseDigi;
    }


    public String displayOriginalNumber() {
        String o = "";
        for (int i = 0; i < digits.length; i++) {
            o = o + digits[i];
        }
        o = o + "\n";
        return o;
    }

    public int[] getDigits() {
        return digits;
    }

    public int[] convertToDecimal()
    {
        int[] decimalValue = new int[digits.length];
        int count = 0;
        for(int i = digits.length-1;i>=0;i--)
        {
            int currentValue = digits[i];
            decimalValue[i] = currentValue * (int)Math.pow(base,count);
            count++;
        }
        return decimalValue;
    }

    public int[] convertToBinary() {
        int[] binaryValue = new int[digits.length*4];
        int decimal = convertArrayToDecimal(convertToDecimal());
        for(int i = binaryValue.length-1;i>=0;i--)
        {
            if(decimal%2!=0)
            {
                binaryValue[i] = 1;
            }
            else
            {
                binaryValue[i]=0;
            }
            decimal /=2;
        }
        return binaryValue;
    }

    public int[] convertToOctal()
    {
        int decimal = convertArrayToDecimal(convertToDecimal());
        int[] octalValue = new int[digits.length*4];
        for(int i = octalValue.length-1;i>=0;i--)
        {
            octalValue[i] = decimal%8;
            decimal/=8;
        }
        return octalValue;
    }

    public int[] convertToAbove(int base)
    {
        int decimal = convertArrayToDecimal(convertToDecimal());
        int[] hexValue = new int[digits.length*4];
        for(int i = hexValue.length-1;i>=0;i--)
        {
            hexValue[i] = decimal%base;
            decimal/=base;
        }
        return hexValue;
    }


    public int convertArrayToDecimal(int[] array)
    {
        int total = 0;
        for(int value : array)
        {
            total+=value;
        }
        return total;
    }

    public String convertArray(int[] array, int base)
    {
        if(base == 10)
        {
            return Integer.toString(convertArrayToDecimal(array));
        }
        else{
            String returnValue = "";
            boolean start = false;
            for(int value : array)
            {
                if(start){
                    returnValue+= Integer.toString(value);
                }
                else{
                    if(value!=0)
                    {
                        start = true;
                        returnValue+=Integer.toString(value);
                    }

                }
            }
            return returnValue;
        }
    }
    public String convertComplexArray(int[] array)
    {
        String num = "";
        boolean start = false;
        for(int value : array)
        {
            if(start){
                num+= reverseKey.get(value);
            }
            else{
                if(value!=0)
                {
                    start = true;
                    num+=reverseKey.get(value);
                }

            }
        }
        return num;
    }

}


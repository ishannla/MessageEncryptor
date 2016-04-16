package methods;

import objects.PlainMessage;

public class ConverterMethods {

    // converts decimal value to hexadecimal (ex: input = 111, output = 6F)
    public static String convertDecimalToHex(int decValue){

        int firstDigit = decValue/16;
        int secondDigit = decValue % (firstDigit * 16);
        char secondChar = ' ';

        if (secondDigit < 10)
            secondChar = String.valueOf(secondDigit).charAt(0);

        else if (secondDigit >= 10) {
            int remainder = secondDigit % 10;
            secondChar = PlainMessage.hexArray[remainder];
        }

        String hexString = firstDigit + "" + secondChar;

        return hexString;
    }

    // converts individual hexadecimal digit to decimal value (ex: input = A, output = 10)
    public static int convertHexDigitToDecimal (String hexDigit) {

        int digit = -1;

        boolean isSecondChar = false;
        int isSecondCharPos = 0;

        for (int y = 0; y < PlainMessage.hexArray.length; y++) {

            char letter = PlainMessage.hexArray[y];

            if (hexDigit.indexOf(letter) == 0) {
                isSecondChar = true;
                isSecondCharPos = y + 1;
                break;
            }
        }

        if (isSecondChar)
            digit = 9 + isSecondCharPos;

        else if (!isSecondChar)
            digit = Integer.parseInt(hexDigit);

        return digit;
    }

    // converts decimal value between 0 and 15 to binary (ex: input = 6, output = 0110)
    public static String convertDecimalToBinary(int number){

        String finalBinary = "";

        if (number/8 == 1) {
            number = number % 8;
            finalBinary = finalBinary + 1;
        } else finalBinary = finalBinary + 0;

        if (number/4 == 1) {
            number = number % 4;
            finalBinary = finalBinary + 1;
        } else finalBinary = finalBinary + 0;

        if (number/2 == 1) {
            number = number % 2;
            finalBinary = finalBinary + 1;
        } else finalBinary = finalBinary + 0;

        if (number/1 == 1) {
            number = number % 1;
            finalBinary = finalBinary + 1;
        } else finalBinary = finalBinary + 0;

        return finalBinary;
    }

    // converts binary to hexadecimal value (ex: input = 1111, output = F)
    public static String convertBinaryToHex(String binary) {

        int sum = 0;
        sum = sum + (8 * Integer.parseInt(binary.substring(0, 1)));
        sum = sum + (4 * Integer.parseInt(binary.substring(1, 2)));
        sum = sum + (2 * Integer.parseInt(binary.substring(2, 3)));
        sum = sum + (1 * Integer.parseInt(binary.substring(3)));

        char digit = ' ';

        if (sum > 9) {
            int difference = sum - 10;
            digit = PlainMessage.hexArray[difference];
        } else if (sum <= 9) {
            digit = Character.forDigit(sum, 10);
        }

        return String.valueOf(digit);
    }

    // converts hexadecimal value to decimal value (ex: input = 6F, output = 111)
    public static int convertHexToDecimal(String hexValue) {

        int finalDecimal = 0;

        String firstHex = hexValue.substring(0, 1);
        String secondHex = hexValue.substring(1);

        int firstDecimal = convertHexDigitToDecimal(firstHex);
        int secondDecimal = convertHexDigitToDecimal(secondHex);

        finalDecimal = (16 * firstDecimal) + secondDecimal;
        return finalDecimal;

    }

    // converts decimal value to ASCII character
    public static char convertDecimalToChar(int decimal) {
        return (char) decimal;
    }

}

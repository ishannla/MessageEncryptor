package objects;

public class PlainMessage {

    private String messageText;
    private int characterNumber;

    private int[] messageDecimalArray;
    private String messageDecimal;
    private String messageHex;
    private String messageBinary;

    public static char[] hexArray = {'A','B','C','D','E','F'};

    public PlainMessage(String message) {
        messageText = message;
        characterNumber = messageText.length();
        messageDecimalArray = returnDecimalArray();

        messageDecimal = returnDecimal();
        messageHex = returnHexadecimal();
        messageBinary = returnBinary();
    }

    public int[] returnDecimalArray() {

        int[] decimalArray = new int[characterNumber];
        int messageLength = messageText.length();

        for (int x = 0; x < messageLength; x++){

            char current = messageText.charAt(x);
            int decValue = (int) current;
            decimalArray[x] = decValue;
        }

        return decimalArray;

    }

    public String returnDecimal() {

        String decimal = "";

        for (int decValue : messageDecimalArray)
            decimal = decimal + String.valueOf(decValue) + " ";

        return decimal;
    }

    public String returnHexadecimal() {

        String finalHexString = "";

        for (int x = 0; x < characterNumber; x++){

            int decValue = messageDecimalArray[x];
            String hexString = methods.ConverterMethods.convertDecimalToHex(decValue);

            finalHexString = finalHexString + hexString;

        }

        return finalHexString;

    }

    public String returnBinary() {

        String finalBinary = "";

        for (int x = 0; x < characterNumber; x++){

            int startIndex = x * 2;
            String hexValue = messageHex.substring(startIndex, startIndex + 2);

            String firstHex = hexValue.substring(0, 1);
            String secondHex = hexValue.substring(1);

            int firstDigit = Integer.parseInt(firstHex);
            int secondDigit = methods.ConverterMethods.convertHexDigitToDecimal(secondHex);

            String firstBinary = methods.ConverterMethods.convertDecimalToBinary(firstDigit);
            String secondBinary = methods.ConverterMethods.convertDecimalToBinary(secondDigit);

            finalBinary = finalBinary + firstBinary + secondBinary;

        }

        while (finalBinary.length() % 64 != 0)
            finalBinary = finalBinary + "0";

        return finalBinary;
    }

    public String getMessageBinary() {
        return messageBinary;
    }

    public String getMessageHex() {
        return messageHex;
    }

    public String getMessageText() {
        return messageText;
    }

    public int getCharacterNumber() {
        return characterNumber;
    }

    public String getMessageDecimal() {
        return messageDecimal;
    }

    public int[] getMessageDecimalArray() {
        return messageDecimalArray;
    }
}

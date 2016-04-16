package objects;

public class PlainMessage {

    private String messageText;
    private int messageLength;

    private int[] messageDecimalArray;
    private String messageDecimal;

    private String messageHex;
    private String messageBinary;

    public static char[] hexArray = {'A','B','C','D','E','F'};

    public PlainMessage(String message) {
        messageText = generateMessage(message);
        messageLength = messageText.length();
        messageDecimalArray = generateDecimalArray();

        messageDecimal = generateDecimal();
        messageHex = generateHexadecimal();
        messageBinary = generateBinary();
    }

    //generates formatted message by adding extra spaces at end to ensure messageLength divisible by 8
    public String generateMessage(String message) {

        int remainder = 0;
        int length = message.length();

        if (length % 8 != 0) {
            int extra = length % 8;
            remainder = 8 - extra;
        }

        String spaces = "";
        for (int x = 0; x < remainder; x++)
            spaces = spaces + " ";

        return message + spaces;


    }

    //generates array of decimal values by casting each individual character within messageText to int
    public int[] generateDecimalArray() {

        int[] decimalArray = new int[messageLength];
        int messageLength = messageText.length();

        for (int x = 0; x < messageLength; x++){

            char current = messageText.charAt(x);
            int decValue = (int) current;
            decimalArray[x] = decValue;
        }

        return decimalArray;

    }

    //generates String of decimal values separated by spaces from messageDecimalArray
    public String generateDecimal() {

        String decimal = "";

        for (int decValue : messageDecimalArray)
            decimal = decimal + String.valueOf(decValue) + " ";

        return decimal;
    }

    //converts each decimal value to 2-digit hexadecimal value, returns entire String
    public String generateHexadecimal() {

        String finalHexString = "";

        for (int x = 0; x < messageLength; x++){

            int decValue = messageDecimalArray[x];
            String hexString = methods.ConverterMethods.convertDecimalToHex(decValue);

            finalHexString = finalHexString + hexString;

        }

        return finalHexString;

    }

    //converts each 2-digit hexadecimal value to 8-bit binary value, returns entire String
    public String generateBinary() {

        String finalBinary = "";

        for (int x = 0; x < messageLength; x++){

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
        return messageLength;
    }

    public String getMessageDecimal() {
        return messageDecimal;
    }

    public int[] getMessageDecimalArray() {
        return messageDecimalArray;
    }
}

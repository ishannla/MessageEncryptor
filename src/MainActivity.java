import methods.ConverterMethods;
import objects.EncryptedMessage;
import objects.PlainMessage;

import java.util.Arrays;

public class MainActivity {

    public static PlainMessage plainMessage;
    public static EncryptedMessage encryptedMessage;

    public static void main (String[] args) {

        String tester = "";

        for (int x = 0; x < 1; x++)
            tester = tester + "hello my name is ishan";

        final long startTime = System.currentTimeMillis();

        plainMessage = new PlainMessage(tester);
        printPlainMessage(plainMessage);

        encryptedMessage = new EncryptedMessage(plainMessage.getMessageBinary());
        printBinaryMessage(encryptedMessage);

        printEncryptedText(encryptedMessage.getCipherBinary());

        final long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println();
        System.out.println(elapsedTime/1000.0 + " seconds");

    }

    public static void printPlainMessage(PlainMessage message){

        System.out.println();

        System.out.println(message.getMessageText());
        System.out.println(Arrays.toString(message.getMessageDecimalArray()));
        System.out.println(message.getMessageHex());
        System.out.println(message.getMessageBinary());

    }

    public static void printBinaryMessage(EncryptedMessage message) {

        System.out.println();

        int[] switcher = message.getSwitcher();
        System.out.println(Arrays.toString(switcher));

        Arrays.sort(switcher);
        System.out.println(Arrays.toString(switcher));

        System.out.println(message.getCipherBinary());

    }

    public static void printEncryptedText(String cipherBinary) {

        String cipherText = "";
        int numberOf64Bits = cipherBinary.length()/64;
        int[] decimals = new int[cipherBinary.length()/8];

        System.out.println();

        int counter = 0;
        for (int x = 0; x < numberOf64Bits; x++) {

            String current64Bits = cipherBinary.substring((x * 64), (x * 64) + 64);

            for (int y = 0; y < 8; y++) {

                String currentByte = current64Bits.substring((y * 8), (y * 8) + 8);

                String firstHex = ConverterMethods.convertBinaryToHex(currentByte.substring(0, 4));
                String secondHex = ConverterMethods.convertBinaryToHex(currentByte.substring(4, 8));

                int decimal = ConverterMethods.convertHexToDecimal(firstHex + secondHex);
                decimals[counter] = decimal;

                char random = ConverterMethods.convertDecimalToChar(decimal);
                counter++;

                cipherText = cipherText + random;

            }
        }

        System.out.println(Arrays.toString(decimals));
        System.out.println(cipherText);

    }

}

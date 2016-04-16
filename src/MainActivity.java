import methods.ConverterMethods;
import objects.BinaryMessage;
import objects.PlainMessage;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity {

    public static PlainMessage plainMessage;
    public static BinaryMessage binaryMessage;

    public static void main (String[] args) {

        int c = 97;
        System.out.print((char)c);
        int d = 33;
        System.out.print((char)d);
        int e = 97;
        System.out.print((char)e);

        String tester = "";

        for (int x = 0; x < 1; x++)
            tester = tester + "he";

        final long startTime = System.currentTimeMillis();

        plainMessage = new PlainMessage(tester);
        printPlainMessage(plainMessage);

        binaryMessage = new BinaryMessage(plainMessage.returnBinary());
        int[] switcher = binaryMessage.getSwitcher();
        System.out.println(Arrays.toString(switcher));
        Arrays.sort(switcher);
        System.out.println(Arrays.toString(switcher));

        final long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println();
        System.out.println(elapsedTime/1000.0 + " seconds");

    }

    public static void printPlainMessage(PlainMessage message){

        System.out.println();

        System.out.println(message.getMessageText());
        System.out.println(Arrays.toString(message.getMessageDecimalArray()));
        System.out.println(message.getMessageDecimal());
        System.out.println(message.getMessageHex());
        System.out.println(message.getMessageBinary());

    }

}

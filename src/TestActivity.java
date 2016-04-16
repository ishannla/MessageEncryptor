import methods.ConverterMethods;
import objects.PlainMessage;

import java.util.*;

public class TestActivity {

    public static void main (String[] args) {

        Random randomGenerator = new Random();
        int[] randomArray = new int[64];
        int[] finalArray = new int[64];
        ArrayList<Integer> randomList = new ArrayList<Integer>();

        long currentArray = System.currentTimeMillis();

        for (int c = 0; c < 1000; c++) {

            int counter = 0;
            int shifter = randomGenerator.nextInt(8);
            int counterShifter = shifter;
            int[] counterArray = new int[56];

            for (int x = 0; x < 64; x++) {
                randomArray[x] = x;
            }

            for (int x = 0; x < 64; x++) {
                if (x == counterShifter) {
                    counterShifter = counterShifter + 8;
                    counter++;
                }

                else
                    counterArray[x-counter] = randomArray[x];
            }

            System.out.println(shifter);
            System.out.println(Arrays.toString(randomArray));
            System.out.println(Arrays.toString(counterArray));

            for (int x = 0; x < 56; x++) {
                int firstIndex = randomGenerator.nextInt(56);
                int secondIndex = randomGenerator.nextInt(56);
                int first = counterArray[firstIndex];
                counterArray[firstIndex] = counterArray[secondIndex];
                counterArray[secondIndex] = first;
            }

            System.out.println(Arrays.toString(counterArray));

            counter = 0;
            counterShifter = shifter;

            for (int x = 0; x < 64; x++) {
                if (x == counterShifter) {
                    finalArray[counterShifter] = randomArray[counterShifter];
                    counterShifter = counterShifter + 8;
                    counter++;
                }

                else {
                    finalArray[x] = counterArray[x-counter];
                }
            }

            System.out.println(Arrays.toString(finalArray));

            Arrays.sort(finalArray);

            System.out.println(Arrays.toString(finalArray));

        }

        System.out.println((System.currentTimeMillis()-currentArray)/1000.0 + " seconds");

        long currentList = System.currentTimeMillis();

        for (int counter = 0; counter < 1000; counter++) {

            for (int x = 0; x < 56; x++) {
                randomList.add(x);
            }

            Collections.shuffle(randomList);
        }

        System.out.println((System.currentTimeMillis()-currentList)/1000.0 + " seconds");

        String tester = "";

        for (int x = 0; x < 1; x++)
            tester = tester + "hello";

        PlainMessage plainMessage = new PlainMessage(tester);

        //converts entire binary string to hexadecimal string
        String finalHex = "";
        for (int x = 0; x < plainMessage.getCharacterNumber(); x++) {

            int startIndex = x * 8;
            String currentByte = plainMessage.getMessageBinary().substring(startIndex, startIndex + 8);

            String firstPart = currentByte.substring(0, 4);
            String secondPart = currentByte.substring(4, 8);

            finalHex = finalHex + ConverterMethods.convertBinaryToHex(firstPart) + ConverterMethods.convertBinaryToHex(secondPart);
        }

        //converts entire hexadecimal string to decimal string
        String finalDec = "";
        for (int x = 0; x < plainMessage.getCharacterNumber(); x++) {

            int startIndex = x * 2;
            String currentHex = plainMessage.getMessageHex().substring(startIndex, startIndex + 2);

            finalDec = finalDec + ConverterMethods.convertHexToDecimal(currentHex) + " ";
        }

        System.out.println(finalHex);
        System.out.println(finalDec);
    }


}

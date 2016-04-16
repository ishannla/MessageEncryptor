package objects;

import java.util.Arrays;
import java.util.Random;

public class BinaryMessage {

    public String originalBinary;
    public String cipherBinary;
    public int[] validIndexes = {3, 4, 5, 6, 10, 11, 12, 13, 17, 18, 19, 20, 24, 25, 26, 27, 31, 32, 33, 34, 38, 39, 40, 41, 45, 46, 47, 48, 52, 53, 54, 55};

    public int[] switcher;
    public int shifter;

    public String key;

    public BinaryMessage(String originalBinary) {
        this.originalBinary = originalBinary;
        this.switcher = generateSwitcher();
        this.cipherBinary = generateCipherBinary();
    }

    //generate array of integers from 0 to 64 with indexes shuffled , but first 3/8 ints in each 8 set untouched
    public int[] generateSwitcher() {

        Random random = new Random();

        while(shifter < 3)
            shifter = random.nextInt(8);

        int[] switcher = new int[64];

        int[] numberArray = new int[64];
        int[] shuffleArray = new int[56];

        for (int x = 0; x < 64; x++)
            numberArray[x] = x;

        int counter = 0;
        int counterShifter = shifter;

        for (int x = 0; x < 64; x++) {

            if (x == counterShifter) {
                counterShifter = counterShifter + 8;
                counter++;
            }

            else
                shuffleArray[x-counter] = numberArray[x];
        }

        for (int x = 0; x < 56; x++) {

            int first = random.nextInt(32);
            int second = random.nextInt(32);

            int firstIndex = validIndexes[first];
            int secondIndex = validIndexes[second];

            int temp = shuffleArray[firstIndex];
            shuffleArray[firstIndex] = shuffleArray[secondIndex];
            shuffleArray[secondIndex] = temp;
        }

        counter = 0;
        counterShifter = shifter;

        for (int x = 0; x < 64; x++) {
            if (x == counterShifter) {
                switcher[counterShifter] = numberArray[counterShifter];
                counterShifter = counterShifter + 8;
                counter++;
            }

            else {
                switcher[x] = shuffleArray[x-counter];
            }
        }

        return switcher;
    }

    //switches positions of original binary digits based on values in switcher
    public String generateCipherBinary() {

        String cipherBinary = "";
        int numberOf64Bits = originalBinary.length()/64;

        for (int x = 0; x < numberOf64Bits; x++) {
            String current64Bits = originalBinary.substring((x * 64), (x * 64) + 64);

            for (int y = 0; y < 64; y++)
                cipherBinary = cipherBinary + current64Bits.charAt(switcher[y]);
        }

        return cipherBinary;
    }

    public String getOriginalBinary() {
        return originalBinary;
    }

    public String getCipherBinary() {
        return cipherBinary;
    }

    public int[] getSwitcher() {
        return switcher;
    }

    public int getShifter() {
        return shifter;
    }

    public String getKey() {
        return key;
    }


    //next step = make sure that each individual 8bit value is between 33 and 126

}

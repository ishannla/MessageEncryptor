package objects;

import java.util.Arrays;
import java.util.Random;

public class BinaryMessage {

    public String originalBinary;
    public String cipherBinary;

    public int[] switcher;
    public int shifter;

    public String key;

    public BinaryMessage(String originalBinary) {
        this.originalBinary = originalBinary;
        this.switcher = generateSwitcher();
    }

    public int[] generateSwitcher() {

        Random random = new Random();

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
            int firstIndex = random.nextInt(56);
            int secondIndex = random.nextInt(56);
            int first = shuffleArray[firstIndex];
            shuffleArray[firstIndex] = shuffleArray[secondIndex];
            shuffleArray[secondIndex] = first;
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

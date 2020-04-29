import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String[] data;
        int wordIndex = -1;
        int numOfSymbols = -1;

        data = _readFile("D:\\Users\\drako\\Projects\\IJProjects\\SMTLab1\\src\\filename.txt");

        if (data == null || data.length == 0) {
            System.out.println("Введены не корректные данные");
            return;
        }

        for(int i = 0; i < data.length; i += 1) {
            int numOfSymbolsInFile = _checkWord(data[i]);
            System.out.println(numOfSymbolsInFile);

            if(numOfSymbols == -1) {
                wordIndex = i;
                numOfSymbols = numOfSymbolsInFile;
                continue;
            }

            if (numOfSymbols > numOfSymbolsInFile) {
                wordIndex = i;
                numOfSymbols = numOfSymbolsInFile;
            }
        }

        System.out.println("Слово " + data[wordIndex] + " содержит " + numOfSymbols + " знаков");
    }

    private static String[] _readFile(String pathToFile) {
        String[] arrayOfWords = {};
        try {
            File myObj = new File(pathToFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                arrayOfWords = _mergeArrays(arrayOfWords, data.split(" "));
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return arrayOfWords;
    }

    private static int _checkWord(String word) {
        int counter = 0;

        do {

            String letter = word.substring(0,1);
            counter += 1;
            word = word.replace(letter, "");
        } while (word.length() > 0);

        return counter;
    }

    private static String[] _mergeArrays(String[] firstArr, String[] secondArr) {
        List list = new ArrayList(Arrays.asList(firstArr));
        list.addAll(Arrays.asList(secondArr));
        Object[] words = list.toArray();
        return  Arrays.copyOf(words, words.length, String[].class);
    }

}

package Choices_Choices;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

abstract class Algorithm {
    String str = "";
    abstract String encode(String text, int key);
    String decode(String text, int key) {
        return encode(text, -key);
    }
}

class ShiftAlgorithm extends Algorithm {
    int pos, posE;
    char charE = ' ';
    String abc = "abcdefghijklmnopqrstuvwxyz", ABC = abc.toUpperCase();
    @Override
    String encode(String text, int key) {
        for (char i : text.toCharArray()) {
            if (i == ' ') {
                str += " ";
            }else {
                if (abc.contains(String.valueOf(i))) {
                    pos = abc.indexOf(i);
                    posE = (pos + key) % 26;
                    posE += (posE < 0) ? abc.length(): 0;
                    charE = abc.charAt(posE);
                } else {
                    pos = ABC.indexOf(i);
                    posE = (pos + key) % 26;
                    posE += (posE < 0) ? abc.length(): 0;
                    charE = ABC.charAt(posE);
                }
                str += charE;
            }
        }
        System.out.println(str);
        return str;
    }
}

class UnicodeAlgorithm extends Algorithm {
    int c;
    @Override
    String encode(String text, int key) {
        for (int i = 0; i < text.length(); i++) {
            c = text.charAt(i) + key;
            str += (char) c;
        }
        System.out.println(str);
        return str;
    }
}

class Encrypt_Decrypt {
    String[] args;
    String algVal, modeVal, dataVal, inputVal, outputVal;
    int keyVal;
    Algorithm algorithm = null;
    public Encrypt_Decrypt (String[] args) {
        this.args = args;
        int alg = Arrays.asList(args).indexOf("-alg");
        int mode = Arrays.asList(args).indexOf("-mode");
        int key = Arrays.asList(args).indexOf("-key");
        int data = Arrays.asList(args).indexOf("-data");
        int input = Arrays.asList(args).indexOf("-in");
        int output = Arrays.asList(args).indexOf("-out");
        algVal = (alg == -1) ? "shift" : args[alg + 1];
        modeVal = (mode == -1) ? "enc" : args[mode + 1];
        keyVal = (key == -1) ? 0 : Integer.parseInt(args[key + 1]);
        dataVal = (data == -1) ? "" : args[data + 1];
        inputVal = (input == -1) ? "" : args[input + 1];
        outputVal = (output == -1) ? "" : args[output + 1];
        switch (algVal) {
            case "shift" -> algorithm = new ShiftAlgorithm();
            case "unicode" -> algorithm = new UnicodeAlgorithm();
        }
    }

    public void getAlgorithm () throws IOException {
        if (!dataVal.equals("")) {
            switch (modeVal) {
                case "enc" -> algorithm.encode(dataVal, keyVal);
                case "dec" -> algorithm.decode(dataVal, keyVal);
            }
        } else {
            File fileToRead = new File(inputVal);
            File fileToWrite = new File(outputVal);
            Scanner sc = new Scanner(fileToRead);
            FileWriter fw = new FileWriter(fileToWrite);
            while (sc.hasNext()) {
                if (!outputVal.equals("")) {
                    switch (modeVal) {
                        case "enc" -> fw.write(algorithm.encode(sc.nextLine(), keyVal));
                        case "dec" -> fw.write(algorithm.decode(sc.nextLine(), keyVal));
                    }
                } else {
                    switch (modeVal) {
                        case "enc" -> algorithm.encode(sc.nextLine(), keyVal);
                        case "dec" -> algorithm.decode(sc.nextLine(), keyVal);
                    }
                }
            }
            sc.close();
            fw.close();
        }
    }
}

class Main {
    public static void main(String[] args) throws IOException{
        Encrypt_Decrypt ec = new Encrypt_Decrypt(args);
        ec.getAlgorithm();
    }
}
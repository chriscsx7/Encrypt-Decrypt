package X_files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws IOException {
        int mode = Arrays.asList(args).indexOf("-mode");
        int key = Arrays.asList(args).indexOf("-key");
        int data = Arrays.asList(args).indexOf("-data");
        int input = Arrays.asList(args).indexOf("-in");
        int output = Arrays.asList(args).indexOf("-out");
        String modeVal = (mode == -1) ? "enc" : args[mode + 1];
        int keyVal = (key == -1) ? 0 : Integer.parseInt(args[key + 1]);
        String dataVal = (data == -1) ? "" : args[data + 1];
        String inputVal = (input == -1) ? "" : args[input + 1];
        String outputVal = (output == -1) ? "" : args[output + 1];
        if (!dataVal.equals("")) {
            switch (modeVal) {
                case "enc" -> enc(dataVal, keyVal);
                case "dec" -> dec(dataVal, keyVal);
            }
        } else {
            File fileToRead = new File(inputVal);
            File fileToWrite = new File(outputVal);
            Scanner sc = new Scanner(fileToRead);
            FileWriter fw = new FileWriter(fileToWrite);
            while (sc.hasNext()) {
                if (!outputVal.equals("")) {
                    switch (modeVal) {
                        case "enc" -> fw.write(enc(sc.nextLine(), keyVal));
                        case "dec" -> fw.write(dec(sc.nextLine(), keyVal));
                    }
                } else {
                    switch (modeVal) {
                        case "enc" -> enc(sc.nextLine(), keyVal);
                        case "dec" -> dec(sc.nextLine(), keyVal);
                    }
                }
            }
            sc.close();
            fw.close();
        }
    }

    public static String enc(String text, int key) {
        String str = "";
        int c;
        for (int i = 0; i < text.length(); i++) {
            c = text.charAt(i) + key;
            str += (char) c;
        }
        System.out.println(str);
        return str;
    }

    public static String dec(String text, int key) {
        enc(text, -key);
        return enc(text, -key);
    }
}


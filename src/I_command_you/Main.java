package I_command_you;

import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        int mode = Arrays.asList(args).indexOf("-mode");
        int key = Arrays.asList(args).indexOf("-key");
        int data = Arrays.asList(args).indexOf("-data");
        String modeVal = (mode == -1) ? "enc" : args[mode + 1];
        int keyVal = (key == -1) ? 0 : Integer.parseInt(args[key + 1]);
        String dataVal = (data == -1) ? "" : args[data + 1];
        if (!dataVal.equals("")) {
            switch (modeVal) {
                case "enc" -> enc(dataVal, keyVal);
                case "dec" -> dec(dataVal, keyVal);
            }
        }
    }

    public static void enc(String text, int key) {
        String str = "";
        int c;
        for (int i = 0; i < text.length(); i++) {
            c = text.charAt(i) + key;
            str += (char) c;
        }
        System.out.println(str);
    }

    public static void dec(String text, int key) {
        enc(text, -key);
    }
}


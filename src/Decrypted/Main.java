package Decrypted;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String modeVal = sc.nextLine();
        String dataVal = sc.nextLine();
        int keyVal = sc.nextInt();
        switch (modeVal) {
            case "enc" -> enc(dataVal, keyVal);
            case "dec" -> dec(dataVal, keyVal);
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
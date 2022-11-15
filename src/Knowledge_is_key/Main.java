package Knowledge_is_key;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        int key = sc.nextInt();
        sc.close();
        int pos;
        int posE;
        char charE;
        String str = null;
        String abc = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == ' ') {
                str += " ";
            } else {
                pos = abc.indexOf(msg.charAt(i));
                posE = (key + pos) % 26;
                charE = abc.charAt(posE);
                str += charE;
            }
        }
        System.out.println(str);
    }
}

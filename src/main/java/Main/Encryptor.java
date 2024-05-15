package Main;

import java.util.*;

public class Encryptor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(funcEncrypt(s));
    }

    public static String funcEncrypt(String s) {
        List<Pair> encTree = new ArrayList<>(Collections.nCopies((1 << 19), new Pair(0, 0)));
        for (int i = 1; i <= s.length(); i++) {
            encTree.set(i, new Pair(i * 2, i * 2 + 1));
        }
        return encrypt(1, encTree, s);
    }

    private static String encrypt(int i, List<Pair> encTree, String s) {
        if (i > s.length()) return "";
        return encrypt(encTree.get(i).first, encTree, s) + encrypt(encTree.get(i).second, encTree, s) + s.charAt(i - 1);
    }

    private static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}

package Main;

import java.util.*;

public class Decryptor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(funcDecrypt(s));
    }

    public static String funcDecrypt(String s) {
        List<Pair> decTree = new ArrayList<>(Collections.nCopies((1 << 19), new Pair(0, 0)));
        for (int i = 1; i <= s.length(); i++) {
            decTree.set(i, new Pair(i * 2, i * 2 + 1));
        }
        return decrypt(1, decTree, new StringBuilder(s));
    }

    private static String decrypt(int i, List<Pair> decTree, StringBuilder s) {
        if (i > s.length()) return "";
        char lastChar = s.charAt(s.length() - 1);
        s.deleteCharAt(s.length() - 1);
        return lastChar + decrypt(decTree.get(i).first, decTree, s) + decrypt(decTree.get(i).second, decTree, s);
    }

    private static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
//Note: Most of this code are just some utility functions in order to get a working code as fast as possible

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {
        long millis = System.currentTimeMillis();
        Set<Integer> set = new HashSet<>();
        String input = readFile("input.txt").trim();
        Scanner scanner = new Scanner(input);
        boolean finished = false;
        long res = 0;
        long x = 0;
        long y = 0;
        long q = 0;
        long min = Long.MAX_VALUE;

        String mask = scanner.nextLine();
        scanner.nextLine();

        //Size 400 should be sufficient for now
        int size = 400;
        int offset = (size / 2) - 50;
        int n = 2;

        int[][] map = new int[size][size];

        for (int i = offset; i < offset + 100; i++) {
            String line = scanner.nextLine();
            char[] arrr = line.toCharArray();

            for (int j = 0; j < arrr.length; j++) {
                int jndex = j + offset;
                if (arrr[j] == '#') {
                    map[i][jndex] = 1;
                } else {
                    map[i][jndex] = 0;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            map = enhance(map, mask);
        }
        out.println(count(map));
    }

    public static int[][] enhance(int[][] map, String mask) {
        int[][] resMap = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                StringBuilder builder = new StringBuilder();
                for (int k = i-1; k < i+2; k++) {
                    for (int m = j-1; m < j+2; m++) {
                        int ck = Math.max(0, k);
                        ck = Math.min(map.length-1, ck);
                        int cm = Math.max(0, m);
                        cm = Math.min(map.length-1, cm);
                        builder.append(map[ck][cm]);
                    }
                }
                String res = builder.toString();
                int val = Integer.parseInt(res, 2);
                char at = mask.charAt(val);
                if (at == '#') {
                    resMap[i][j] = 1;
                } else {
                    resMap[i][j] = 0;
                }
            }
        }
        return resMap;
    }
    
    public static int count(int[][] map) {
        int sum = 0;
        for (int[] ints : map) {
            for (int j = 0; j < map.length; j++) {
                sum += ints[j];
            }
        }
        return sum;
    }

    public static void printmap(int[][] map) {
        for (int[] ints : map) {
            StringBuilder builder = new StringBuilder();
            for (int i : ints) {
                builder.append(i);
            }
            out.println(builder.toString());
        }
    }

    public static String readFile(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Couldn't find path specified");
            exit(0);
            return "";
        }
    }

    public static void print(String x) {
        System.out.println(x);
    }

    public static void print(int x) {
        System.out.println(x);
    }

    public static void print(float x) {
        System.out.println(x);
    }

    public static void print(double x) {
        System.out.println(x);
    }

    public static void print(long x) {
        System.out.println(x);
    }

    public static void print(BigInteger x) {
        System.out.println(x);
    }

    public static int parseInt(char c) {
        return Character.getNumericValue(c);
    }

    public static int parseInt(String s) {
        return Integer.parseInt(s);
    }

    public static String string(char c) {
        return Character.toString(c);
    }

    public static void putOnClipboard(String s) {
        StringSelection selection = new StringSelection(s);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
}

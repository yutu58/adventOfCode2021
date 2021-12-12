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

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        String input = readFile("input.txt").trim();
        Scanner scanner = new Scanner(input);
        boolean finished = false;
        long res = 0;
        long x = 0;
        long y = 0;
        long q = 0;
        long min = Long.MAX_VALUE;

        //Scanner stuff
        int[][] map = new int[10][10];

        String[] parts = input.split("\n");
        for (int i = 0; i < parts.length; i++) {
            char[] cs = parts[i].toCharArray();
            for (int j = 0; j < cs.length; j++) {
                map[i][j] = parseInt(cs[j]);
            }
        }

        for (int total = 0; total < 100; total++) {
            Set<Pair> flashed = new HashSet<>();

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    map[i][j]++;
                }
            }

            for (int times = 0; times < 101; times++) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (map[i][j] > 9 && !flashed.contains(new Pair(i, j))) {
                            flashed.add(new Pair(i, j));
                            if (i > 0) {
                                if (j > 0) {
                                    map[i - 1][j - 1]++;
                                }
                                map[i - 1][j]++;
                                if (j < 9) {
                                    map[i - 1][j + 1]++;
                                }
                            }
                                if (j > 0) {
                                    map[i][j - 1]++;
                                }
                                //map[i][j]++;
                                if (j < 9) {
                                    map[i][j + 1]++;
                                }

                            if (i < 9) {
                                if (j > 0) {
                                    map[i + 1][j - 1]++;
                                }
                                map[i + 1][j]++;
                                if (j < 9) {
                                    map[i + 1][j + 1]++;
                                }
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (flashed.contains(new Pair(i, j))) {
                        map[i][j] = 0;
                        res++;
                    }
                }
            }
        }

        System.out.println(res);
        putOnClipboard(Long.toString(res));
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

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

        //Scanner stuff
        int[][] map = new int[2000][2000];
        String line;

        while (true) {
            line = scanner.nextLine();
            if (line.equals("")) {
                break;
            }
            String[] parts = line.split(",");
            map[parseInt(parts[0])][parseInt(parts[1])]++;
        }

        boolean first = true;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] parts = line.split(" ");
            String[] parts2 = parts[2].split("=");
            boolean horizontal = true;

            if (parts2[0].equals("y")) {
                horizontal = false;
            }
            fold(map, parseInt(parts2[1]), horizontal);
            if (first) {
                res = sum(map);
            }
            first = false;
        }

        System.out.println(res);
        putOnClipboard(Long.toString(res));

    }

    public static void fold(int[][] map, int where, boolean x) {
        for (int i = where + 1; i < 2000; i++) {
            if (2*where - i < 0) {
                continue;
            }
            for (int j = 0; j < 2000; j++) {
                if (x) {
                    if (j >= map[0].length) {
                        continue;
                    }
                    map[2*where - i][j] = Math.max(map[2*where - i][j], map[i][j]);
                    map[i][j] = 0;
                } else {
                    if (j >= map.length) {
                        continue;
                    }
                    map[j][2*where - i] = Math.max(map[j][2*where - i], map[j][i]);
                    map[j][i] = 0;
                }
            }
        }
    }

    public static long sum(int[][] map) {
        long res = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                res += map[i][j];
            }
        }
        return res;
    }

    public static void printmap(int[][] map) {
        for (int i = 0; i < 6; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 40; j++) {
                int val = map[j][i];
                if (val > 0) {
                    sb.append('*');
                } else {
                    sb.append(' ');
                }
            }
            out.println(sb.toString());
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

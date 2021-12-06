//Note: Most of this code are just some utility functions in order to get a working code as fast as possible

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        String input = readFile("input.txt").trim();
        Scanner scanner = new Scanner(input);
        boolean finished = false;
        long res = 0;
        long x = 0;
        long y = 0;
        long q = 0;

        int[][] field = new int[1000][1000];


        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
        }

        char[] array = input.toCharArray();
        for (int i = 0; i < array.length; i++) {
            break;
        }

        String[] parts = input.split("\n");
        for (int i = 0; i < parts.length; i++) {
            String[] z = parts[i].split(" -> ");
            String[] m = z[0].split(",");
            String[] n = z[1].split(",");


            int firstX = parseInt(m[0]);
            int firsty = parseInt(m[1]);

            int secondX = parseInt(n[0]);
            int secondY = parseInt(n[1]);

            if (firstX == secondX) {
                if (firsty > secondY) {
                    int temp = firsty;
                    firsty = secondY;
                    secondY = temp;
                }
                for (int j = firsty; j <= secondY; j++) {
                    field[firstX][j]++;
                }

            } else if (firsty == secondY) {
                if (firstX > secondX) {
                    int temp = firstX;
                    firstX = secondX;
                    secondX = temp;
                }
                for (int j = firstX; j <= secondX; j++) {
                    field[j][firsty]++;
                }
            }
            else if (Math.abs(secondX - firstX) == Math.abs(secondY - firsty)) {
                boolean switched = false;
                System.out.println(parts[i]);
                if (firstX > secondX) {
                    int temp1 = firstX;
                    firstX = secondX;
                    secondX = temp1;

                    int temp2 = firsty;
                    firsty = secondY;
                    secondY = temp2;

                    switched = true;
                }

                for (int j = firstX; j <= secondX; j++) {
                    int diff = j - firstX;
                    if (secondY > firsty) {
                        field[j][firsty + diff]++;
                    } else {
                        field[j][firsty - diff]++;
                    }
                }
            }
        }

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (field[i][j] > 1) {
                    res++;
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

    public static void putOnClipboard(String s) {
        StringSelection selection = new StringSelection(s);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }


}

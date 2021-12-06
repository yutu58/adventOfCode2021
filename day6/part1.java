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
        Map<Integer, Integer> map = new HashMap<>();
        String input = readFile("input.txt").trim();
        Scanner scanner = new Scanner(input);
        boolean finished = false;
        long res = 0;
        long x = 0;
        long y = 0;
        long q = 0;

        List<Integer> fish = new ArrayList<>();

        String[] parts = input.split(",");
        for (int i = 0; i < parts.length; i++) {
            String line = parts[i];
            fish.add(parseInt(line));
        }

        for (int i = 0; i < 80; i++) {
            long size = fish.size();
            for (int j = 0; j < size; j++) {
                if (fish.get(j) == 0) {
                    fish.set(j, 6);
                    fish.add(8);
                } else {
                    fish.set(j, fish.get(j) -1);
                }
            }
        }

        res = fish.size();

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

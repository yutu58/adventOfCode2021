//Note: Most of this code are just some utility functions in order to get a working code as fast as possible
//Finished in 00:02:44, 103d place

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        String input = readFile("input.txt").trim();
        Scanner scanner = new Scanner(input);
        boolean finished = false;
        int res = 0;
        int x = 0;
        int y = 0;
        int z = Integer.MAX_VALUE;
        String line;


        //For line by line
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
        }

        //For character by character
        char[] arr = input.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            break;
        }

        //Otherwhise maybe with .split
        String[] parts = input.split("\n");
        for (int i = 0; i < parts.length-2; i++) {
            int first = parseInt(parts[i]);
            int second = parseInt(parts[i+1]);
            int third = parseInt(parts[i+2]);
            int sum = first + second + third;
            if (sum > z) {
                res++;
            }
            z = sum;
        }


        String answer = String.valueOf(res);
        print(answer);
        putOnClipboard(answer);
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

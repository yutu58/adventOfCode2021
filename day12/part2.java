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
        Map<String, List<String>> map = new HashMap<>();

        String[] parts = input.split("\n");
        for (int gi = 0; gi < parts.length; gi++) {
            String[] zz = parts[gi].split("-");
            if (!map.containsKey(zz[0])) {
                map.put(zz[0], new ArrayList<>());
            }

            if (!map.containsKey(zz[1])) {
                map.put(zz[1], new ArrayList<>());
            }

            map.get(zz[0]).add(zz[1]);
            map.get(zz[1]).add(zz[0]);
        }
        res = traverse(map, "start", new ArrayList<>(), false);

        System.out.println(res);
        putOnClipboard(Long.toString(res));
    }

    public static long traverse(Map<String, List<String>> map, String src, List<String> visited, Boolean bool) {
        if (src.equals("end")) {
            return 1;
        } else if (!src.equals("start") && src.charAt(0) >= 'a' && (Collections.frequency(visited, src) == 1) && !bool) {
            bool = true;
        }
        else if (src.charAt(0) >= 'a' && (Collections.frequency(visited, src) > 0)) {
            return 0;
        }

        visited.add(src);
        Long total = 0L;
        for (String s : map.get(src)) {
            List<String> list = new ArrayList<>(visited);
            long tempres = traverse(map, s, list, bool);
            total += tempres;
        }
        return total;
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

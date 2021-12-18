//Note: Most of this code are just some utility functions in order to get a working code as fast as possible

import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.TreeVisitor;

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
        String[] lines = input.split("\n");

        Stack<Node> stack = new Stack<>();

        for (int e = 0; e < lines.length; e++) {
            boolean first = false;
            if (stack.empty()) {
                first = true;
            }
            char[] arr = lines[e].toCharArray();

            for (char c : arr) {
                if (Character.isDigit(c)) {
                    stack.push(new Node(parseInt(c)));
                } else if (c == ']') {
                    Node b = stack.pop();
                    Node a = stack.pop();
                    Node d = new Node(-1);
                    a.parent = d;
                    b.parent = d;
                    d.left = a;
                    d.right = b;
                    stack.push(d);
                }
            }

            if (!first) {
                Node b = stack.pop();
                Node a = stack.pop();
                Node d = new Node(-1);
                a.parent = d;
                b.parent = d;
                d.left = a;
                d.right = b;
                stack.push(d);
            }

            boolean changed = true;

            while (changed) {
                changed = false;
                changed = traverseAndExplode(stack.peek(), 0);
                changed = split(stack.peek(), false);
            }
        }
        int finish = calcMagnitude(stack.peek());
        out.println(finish);
    }

    public static int calcMagnitude(Node node) {
        if (node.left != null) {
            int total = 0;
            total += 3*calcMagnitude(node.left);
            total += 2*calcMagnitude(node.right);
            return total;
        }
        return node.value;
    }

    public static boolean split(Node node, Boolean bool) {
        if (node.left != null) {
            if (!bool) {
                bool = split(node.left, bool);
            }
            if (!bool) {
                bool = split(node.right, bool);
            }
        } else {
            if (node.value > 9) {
                int a = node.value / 2;
                int b = node.value / 2 + (node.value % 2);
                node.left = new Node(a);
                node.right = new Node(b);
                node.left.parent = node;
                node.right.parent = node;
                node.value = -1;
                return true;
            }
        }
        return bool;
    }

    public static boolean traverseAndExplode(Node node, int depth) {
        if (depth == 4 && node.left != null) {
            int toLeft = node.left.value;
            int toRight = node.right.value;
            findLeftDest(node, toLeft);
            findRightDest(node, toRight);
            node.left = null;
            node.right = null;
            node.value = 0;
            return true;
        }

        boolean res = false;

        if (node.left != null) {
            res = traverseAndExplode(node.left, depth +1);
        }

        if (node.right != null) {
            res = traverseAndExplode(node.right, depth +1);
        }
        return res;
    }

    public static void findLeftDest(Node node, int value) {
        if (node.parent == null) {
            return;
        }
        if (node.parent.right == node) {
            Node temp = node.parent.left;
            while (temp.right != null) {
                temp = temp.right;
            }
            temp.value += value;
        } else {
            findLeftDest(node.parent, value);
        }
    }

    public static void findRightDest(Node node, int value) {
        if (node.parent == null) {
            return;
        }
        if (node.parent.left == node) {
            Node temp = node.parent.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            temp.value += value;
        } else {
            findRightDest(node.parent, value);
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

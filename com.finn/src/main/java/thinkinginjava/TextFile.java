package thinkinginjava;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TextFile extends ArrayList<String> {

    public static String read(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(
                    new File(fileName).getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            System.out.println("error：" + e.getMessage());
        }
        return sb.toString();
    }

    public static void write(String fileName, String text) {
        try {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public TextFile(String fileName, String splitter) {
        super(Arrays.asList(read(fileName).split(splitter))); // 以行输出
        if (get(0).equals("")) remove(0);
    }

    public TextFile(String fileName) {
        this(fileName, "\n");
    }

    public void write (String fileName) {
        try {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            try {
                for (String item : this) {
                    out.println(item);
                }
            } finally {
                out.close();
            }
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String file = read("/Users/zhangjunhui/Learn/keep-learning/com.finn/src/main/java/thinkinginjava/TextFile.java");
        write("/Users/zhangjunhui/Learn/keep-learning/com.finn/src/main/java/thinkinginjava/test.txt", file);
        TextFile text = new TextFile("/Users/zhangjunhui/Learn/keep-learning/com.finn/src/main/java/thinkinginjava/test.txt");
        text.write("/Users/zhangjunhui/Learn/keep-learning/com.finn/src/main/java/thinkinginjava/test2.txt");
    }
}

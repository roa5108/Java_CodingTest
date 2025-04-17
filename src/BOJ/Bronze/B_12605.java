package BOJ.Bronze;
import java.io.*;
import java.util.Stack;

public class B_12605 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 케이스 개수

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            String[] words = line.trim().split("\\s+");

            Stack<String> stack = new Stack<>();
            for (String word : words) {
                stack.push(word);
            }

            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
                if (!stack.isEmpty()) sb.append(" ");
            }

            System.out.println("Case #" + i + ": " + sb);
        }
    }
}
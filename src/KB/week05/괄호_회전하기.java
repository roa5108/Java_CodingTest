package KB.week05;

/* ================================================================
 *
 * Problem  : PGS - 괄호 회전하기
 * Author   : 김로아
 * Date     : 2025-05-24
 *
 * ================================================================
 * 📌 문제 분석 요약
 * (), [], {} 는 모두 올바른 괄호 문자열
 * A, B가 올바른 괄호 문자열 -> (A),[A],{A}, AB도 올바른 괄호 문자열
 * 괄호들로 이루어진 문자열 s를 왼쪽으로 x칸 만큼 회전시켰을 때,
 * 올바른 괄호 문자열이 되게 하는 x의 개수는?
 *
 * # 입력
 * 대괄호, 중괄호, 그리고 소괄호로 이루어진 문자열 s
 *
 * # 출력
 * x의 개수
 *
 * 💻 알고리즘 설계
 * 괄호 회전 -> 문자열 s를 두 배로 늘린 뒤, substring으로 잘라 회전 효과
 * 올바른 괄호인지 검사하는 isRight 함수
 * 올바른 괄호일 경우 count++
 *
 * ⏰ 시간복잡도
 *
 * ================================================================
 */

import java.util.ArrayDeque;
import java.util.Deque;

class 괄호_회전하기 {
    public int solution(String s) {
        int count = 0;
        String doubleS = s + s;
        for (int i = 0; i < s.length(); i++) {
            String subS = doubleS.substring(i, i + s.length());
            char[] arr = subS.toCharArray();
            if (isRight(arr)) count++;
        }
        return count;
    }

    public boolean isRight(char[] arr) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : arr) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == ']' && top != '[') return false;
                if (c == '}' && top != '{') return false;
            }
        }
        return stack.isEmpty();
    }
}


//import java.util.ArrayDeque;
//import java.util.Deque;
//
//class 괄호_회전하기 {
//    public int solution(String s) {
//        String doubleS = s + s;
//        int result = 0;
//
//        for (int i = 0; i < s.length(); i++) {
//            String rotated = doubleS.substring(i, i + s.length());
//            char[] arr = rotated.toCharArray();
//            if (isValid(arr)) {
//                result++;
//            }
//        }
//        return result;
//    }
//
//    public boolean isValid(char[] c) {
//        Deque<Character> stack = new ArrayDeque<>();
//        for (int i = 0; i < c.length; i++) {
//            if (c[i] == '(' || c[i] == '[' || c[i] == '{') {
//                stack.push(c[i]);
//            } else {
//                if (stack.isEmpty()) {
//                    return false;
//                }
//                char top = stack.peek();
//                if ((top == '(' && c[i] == ')')
//                        || (top == '[' && c[i] == ']')
//                        || (top == '{' && c[i] == '}')) {
//                    stack.pop();
//                } else {
//                    return false;
//                }
//            }
//        }
//        return stack.isEmpty();
//    }
//}
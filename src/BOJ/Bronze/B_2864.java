package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* ================================================================
 *
 * Problem  : BOJ2864 - 5와 6의 차이
 * Author   : 김로아
 * Date     : 2025-05-08
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 5를 6으로, 6을 5로 잘못 볼 수 있음. 두 수 A, B 합의 최솟값과 최댓값 구하기
 *
 * # 입력
 * 1행 - 두 정수 A, B가 공백으로 구분되어 입력
 *
 * # 출력
 * 두 수의 합 중 최솟값과 최댓값을 공백으로 구분해 출력
 *
 * 💻 알고리즘 설계
 * 1) String 배열에 각각 두 숫자를 답음
 * 2) minA, minB에 A와 B의 6을 5로 바꾼 결과를 대입
 * 3) maxA, maxB에 A와 B의 5를 6으로 바꾼 결과를 대입
 * 4) 합의 최솟값, 최댓값 출력
 *
 * ⏰ 시간복잡도
 *
 * ================================================================
 */

public class B_2864 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] num = br.readLine().split(" ");

        String A = num[0];
        String B = num[1];

        int minA = Integer.parseInt(A.replace('6', '5'));
        int minB = Integer.parseInt(B.replace('6', '5'));

        int maxA = Integer.parseInt(A.replace('5', '6'));
        int maxB = Integer.parseInt(B.replace('5', '6'));

        System.out.println((minA + minB) + " " + (maxA + maxB));
    }
}

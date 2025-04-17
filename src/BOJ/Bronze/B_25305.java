package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Collections;

public class B_25305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄: N, K 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 총 인원
        int K = Integer.parseInt(st.nextToken()); // 상 받는 인원 수

        // 점수 입력
        Integer[] scores = new Integer[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        // 내림차순 정렬
        Arrays.sort(scores, Collections.reverseOrder());

        // K번째 점수 출력 (인덱스는 K-1)
        System.out.println(scores[K - 1]);
    }
}


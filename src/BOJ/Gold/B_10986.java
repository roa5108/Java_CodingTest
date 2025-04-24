package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long result = 0;
        long[] S = new long[N + 1]; //누적합 배열
        long[] cnt = new long[M]; //나머지 개수 카운트

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            S[i] = (S[i - 1] + Integer.parseInt(st.nextToken())) % M;
            if (S[i] == 0) {
                result++; //바로 나눠떨어지는 경우 (0부터 i까지)
            }
            cnt[(int) S[i]]++; //나머지 별 개수 세기
        }

        //(S[i] - S[i-1]) % M = 0
        //S[j] % M = S[i-1] % M
        //같은 나머지를 가진 누적합끼리는 그 사이 구간이 M으로 나눠떨어짐
        for (int i = 0; i < M; i++) {
            if (cnt[i] > 1) {
                result += (cnt[i] * (cnt[i] - 1) / 2);
            }
        }
        System.out.println(result);
    }
}

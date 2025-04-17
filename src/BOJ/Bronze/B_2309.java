package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class B_2309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] dwarfs = new int[9];
        int total = 0;

        // 9명의 키 입력 및 총합 계산
        for (int i = 0; i < 9; i++) {
            dwarfs[i] = Integer.parseInt(br.readLine());
            total += dwarfs[i];
        }

        // 어떤 두 명을 제외할지 찾기
        outerLoop:
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (total - dwarfs[i] - dwarfs[j] == 100) {
                    // 두 명 제외하고 나머지 7명 배열 만들기
                    int[] result = new int[7];
                    int idx = 0;
                    for (int k = 0; k < 9; k++) {
                        if (k != i && k != j) {
                            result[idx++] = dwarfs[k];
                        }
                    }

                    // 오름차순 정렬
                    Arrays.sort(result);

                    // 출력
                    for (int h : result) {
                        System.out.println(h);
                    }
                    break outerLoop;  // 정답 찾았으니 종료
                }
            }
        }
    }
}

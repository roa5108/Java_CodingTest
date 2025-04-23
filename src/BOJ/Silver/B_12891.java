package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_12891 {
    public static void main(String[] args) throws IOException {

        //입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        String DNA = br.readLine();

        int[] min = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++){
            min[i]=Integer.parseInt(st.nextToken());
        }

        // A/C/G/T 개수 세기
        int[] count = new int[4];
        for(int i=0;i<P;i++){
            char c=DNA.charAt(i);
            count[getIndex(c)]++;
        }

        int result=0;
        if(isValid(count,min)) result++;

        //슬라이딩 윈도우
        for(int i=P;i<S;i++){
            char left=DNA.charAt(i-P);
            count[getIndex(left)]--; //왼쪽 지우고

            char right=DNA.charAt(i);
            count[getIndex(right)]++; //오른쪽으로 하나 밀기

            if(isValid(count,min)) result++; //검증해서 조건 만족하면 result++
        }

        System.out.println(result);

    }

    //현재 윈도우 안에 있는 A/C/G/T 개수가 최소 개수 조건을 만족하는지 확인
    static boolean isValid(int[] count, int[] min){
        for(int i = 0; i < 4; i++){
            if(count[i]<min[i]) return false;
        }
        return true;
    }

    //문자 인덱스 구하기
    static int getIndex(char c){
        if(c=='A') return 0;
        if(c=='C') return 1;
        if(c=='G') return 2;
        return 3;
    }
}

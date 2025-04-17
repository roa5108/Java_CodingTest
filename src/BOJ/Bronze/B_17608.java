package BOJ.Bronze;
import java.io.*;

public class B_17608 {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine()); //막대기 개수
        int[] sticks=new int[N];

        for(int i=0;i<N;i++){
            sticks[i]=Integer.parseInt(br.readLine());
        }

        int count=1;
        int max=sticks[N-1]; //현재 가장 높은 막대기

        // 오른쪽에서 왼쪽으로 탐색
        for(int i=N-2;i>=0;i--){
            if(sticks[i]>max){
                count++;
                max=sticks[i];
            }
        }
        System.out.println(count);
    }
}
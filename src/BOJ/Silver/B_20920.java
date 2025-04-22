package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_20920  {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        Map<String,Integer> wordCnt=new HashMap<>();

        for(int i=0;i<N;i++){
            String word=br.readLine();
            if(word.length()>=M){
                wordCnt.put(word,wordCnt.getOrDefault(word,0)+1);
            }
        }

        //keySet을 리스트로 변환
        List<String> wordList=new ArrayList<>(wordCnt.keySet());

        //정렬 기준 3가지 적용
        wordList.sort((w1,w2)->{
            int c1=wordCnt.get(w1);
            int c2=wordCnt.get(w2);

            if(c1!=c2){
                return c2-c1;
            } else if(w1.length()!=w2.length()){
                return w2.length()-w1.length();
            }else{
                return w1.compareTo(w2);
            }
        });

        StringBuilder sb = new StringBuilder();
        for(String word:wordList){
            sb.append(word).append("\n");
        }
        System.out.print(sb);
    }

}

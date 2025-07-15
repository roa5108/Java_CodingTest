package KB.추가스터디.week01;

/* ================================================================
 *
 * Problem  : 
 * Author   : 김로아
 * Date     : 2025년 07월 15일
 * 
 * ================================================================
 * 📌 문제 분석 요약
 * -
 *
 * # 입력
 * -
 *
 * # 출력
 * - 
 *
 * 💻 알고리즘 설계
 * 1.
 *
 * ⏰ 시간복잡도
 * -
 * ================================================================
 */

import java.util.*;

class 보물지도 {
    int[] dr = {1,-1,0,0};
    int[] dc = {0,0,-1,1};
    public int solution(int n, int m, int[][] hole) {
        boolean[][][] visited = new boolean[n+1][m+1][2]; //n, m, usedShoes
        boolean[][] isHole = new boolean[n+1][m+1];

        for(int[] h : hole){
            isHole[h[0]][h[1]]=true; //함정 표시
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{1,1,0,0}); //n, m, usedShoes, time
        visited[1][1][0]=true;
        while(!q.isEmpty()){
            int[] now = q.remove();
            int cr = now[0], cc = now[1], usedShoes=now[2], time=now[3];
            if(cr==n && cc==m) return time;

            for(int i=0; i<4; i++){
                int nr=cr+dr[i], nc=cc+dc[i];
                if(1<=nr && nr<=n&& 1<=nc && nc<=m && !visited[nr][nc][usedShoes] && !isHole[nr][nc]){
                    visited[nr][nc][usedShoes]=true;
                    q.add(new int[]{nr,nc,usedShoes,time+1});
                }
            }

            if(usedShoes==0){
                for(int i=0; i<4; i++){
                    int nr=cr+dr[i]*2, nc=cc+dc[i]*2;
                    if(1<=nr && nr<=n && 1<=nc && nc<=m && !visited[nr][nc][1] && !isHole[nr][nc]){
                        visited[nr][nc][1]=true;
                        q.add(new int[]{nr,nc,1,time+1});
                    }
                }
            }
        }
        return -1;
    }
}

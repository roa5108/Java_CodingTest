package KB.week01;

/* ================================================================
 *
 * Problem  : PGS - 완주하지 못한 선수
 * Author   : 김로아
 * Date     : 2025-05-30
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 마라톤에서 완주하지 못한 선수 이름을 구하기
 *
 * # 입력
 * 마라톤에 참여한 선수들의 이름이 담긴 배열 participant
 * 완주한 선수들의 이름이 담긴 배열 completion
 *
 * # 출력
 * 완주하지 못한 선수의 이름
 *
 * 💻 알고리즘 설계
 * 1) 해시맵에 participant 이름과 등장 횟수 저장
 * 2) 맵에서 완주한 사람의 횟수는 -1
 * 3) 맵에 횟수가 남아있는 사람의 이름 리턴
 *
 * ⏰ 시간복잡도
 *
 * ================================================================
 */

import java.util.HashMap;
import java.util.Map;

class 완주하지_못한_선수 {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();
        for (String p : participant) {
            map.put(p, map.getOrDefault(p, 0) + 1);
        }
        for (String c : completion) {
            map.put(c, map.get(c) - 1);
        }

        for (String key : map.keySet()) {
            if (map.get(key) > 0) {
                return key;
            }
        }
        return "";
    }
}
package KB.week05;

/* ================================================================
 *
 * Problem  : PGS - 신고 결과 받기
 * Author   : 김로아
 * Date     : 2025-05-23
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 신고 처리 결과를 메일로 발송하는 시스템 개발하기
 * 1) 각 유저는 한 번에 한 명의 유저 신고 O (신고 횟수 제한 X)
 * 2) 동일한 유저에 대한 신고 횟수는 아무리 많아도 1회로 처리
 * 3) 주어진 k번 이상 신고된 유저는 게시판 이용 정지
 * 4) 해당 유저를 신고한 모든 유저에게 정지 사실을 메일로 발송
 *
 * # 입력
 * 전체 이용자 ID 문자열 배열 id_list,
 * "신고한_사용자id 신고된_이용자id" ID 문자열 배열 report,
 * 정지 기준이 되는 신고 횟수 k
 *
 * # 출력
 * 각 유저별로 처리 결과 메일을 받은 횟수 배열
 *
 * 💻 알고리즘 설계
 * 1) 중복된 신고 없앤 reportSet, 신고 횟수 저장하는 reportedMap, 누가 누구 신고했는지 whoReportwho
 * 2) reportSet에서 신고자와 신고된사람을 분리해 reportedMap에 횟수 카운트, 신고자와 신고된 사람도 whoReportwho에 기록
 * 3) k번 이상 신고된 사람을 찾으면 정답
 *
 * ⏰ 시간복잡도
 *
 * ================================================================
 */

import java.util.*;

class 신고_결과_받기 {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        //신고 중복 없애기 -> set 사용
        Set<String> reportSet = new HashSet<>(Arrays.asList(report));
        //신고 횟수 저장
        Map<String, Integer> reportedMap = new HashMap<>();
        //누가 누구 신고했는지 저장
        Map<String, List<String>> whoReportwho = new HashMap<>();

        for (String rs : reportSet) {
            String[] s = rs.split(" ");
            String reporter = s[0];
            String reportee = s[1];
            reportedMap.put(reportee, reportedMap.getOrDefault(reportee, 0) + 1);
            whoReportwho.computeIfAbsent(reporter, key -> new ArrayList<>()).add(reportee);
        }

        //k번 이상 신고된 사람 찾기
        for (int i = 0; i < id_list.length; i++) {
            String reporter = id_list[i];
            for (String reportee : whoReportwho.getOrDefault(reporter, new ArrayList<>())) {
                if (reportedMap.getOrDefault(reportee, 0) >= k) {
                    answer[i] += 1;
                }
            }
        }
        return answer;
    }
}


//class 신고결과받기 {
//    public int[] solution(String[] id_list, String[] report, int k) {
//        // 중복 제거된 신고 기록
//        Set<String> reportSet = new HashSet<>(Arrays.asList(report));
//        // 신고당한 유저의 신고 횟수
//        Map<String, Integer> reportedCnt = new HashMap<>();
//
//        // 누가 누구를 신고했는지 기록
//        Map<String, List<String>> whoReport = new HashMap<>();
//
//        // 정지 대상자
//        Set<String> banned = new HashSet<>();
//
//        // 각 유저의 메일 수
//        int[] result = new int[id_list.length];
//
//        // 신고된 회수 카운트, 이용자별 신고한 유저 카운트
//        for (String r : reportSet) {
//            String[] parts = r.split(" ");
//            String reporter = parts[0];
//            String reported = parts[1];
//
//            reportedCnt.put(reported, reportedCnt.getOrDefault(reported, 0) + 1);
//            whoReport.putIfAbsent(reporter, new ArrayList<>());
//            whoReport.get(reporter).add(reported);
//        }
//
//        for (String user : reportedCnt.keySet()) {
//            if (reportedCnt.get(user) >= k) {
//                banned.add(user);
//            }
//        }
//
//        for (int i = 0; i < id_list.length; i++) {
//            String user = id_list[i];
//            List<String> reportedList = whoReport.get(user);
//            if (reportedList == null) continue;
//
//            for (String bannedUser : reportedList) {
//                if (banned.contains(bannedUser)) {
//                    result[i]++;
//                }
//            }
//        }
//        return result;
//    }
//}
package KB.week05;

import java.util.*;

class 신고결과받기 {
    public int[] solution(String[] id_list, String[] report, int k) {
        // 중복 제거된 신고 기록
        Set<String> reportSet = new HashSet<>(Arrays.asList(report));
        // 신고당한 유저의 신고 횟수
        Map<String, Integer> reportedCnt = new HashMap<>();

        // 누가 누구를 신고했는지 기록
        Map<String, List<String>> whoReport = new HashMap<>();

        // 정지 대상자
        Set<String> banned = new HashSet<>();

        // 각 유저의 메일 수
        int[] result = new int[id_list.length];

        for (String r : reportSet) {
            String[] parts = r.split(" ");
            String reporter = parts[0];
            String reported = parts[1];

            reportedCnt.put(reported, reportedCnt.getOrDefault(reported, 0) + 1);
            whoReport.putIfAbsent(reporter, new ArrayList<>());
            whoReport.get(reporter).add(reported);
        }

        for (String user : reportedCnt.keySet()) {
            if (reportedCnt.get(user) >= k) {
                banned.add(user);
            }
        }

        for (int i = 0; i < id_list.length; i++) {
            String user = id_list[i];
            List<String> reportedList = whoReport.get(user);
            if (reportedList == null) continue;

            for (String bannedUser : reportedList) {
                if (banned.contains(bannedUser)) {
                    result[i]++;
                }
            }
        }
        return result;
    }
}
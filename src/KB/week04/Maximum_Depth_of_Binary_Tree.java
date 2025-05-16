package KB.week04;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/* ================================================================
 *
 * Problem  : LeetCode - Maximum Depth of Binary Tree
 * Author   : 김로아
 * Date     : 2025-05-16
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 이진 트리의 루트가 주어졌을 때, 최대 깊이를 구하기
 *
 * # 입력
 * 이진 트리
 * Input: root = [3,9,20,null,null,15,7]
 *
 * # 출력
 * 최대 깊이
 * Output: 3
 *
 * 💻 알고리즘 설계
 * 깊이를 구하는 문제 -> BFS 사용
 * HashMap으로 깊이 저장, 깊이와 큐를 따로 관리
 * 트리의 왼쪽, 오른쪽 각각 value가 있는지 & 깊이에 값이 있는지 확인하면서 깊이 추가 & 큐에 노드 추가
 * 깊이 max값 리턴
 *
 * ⏰ 시간복잡도
 * O(N) - N은 이진 트리의 노드 개수
 * ================================================================
 */

class Maximum_Depth_of_Binary_Tree {
    static int depth;

    public static int bfs(TreeNode root) {
        int max = 0;
        Map<TreeNode, Integer> depth = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();

        depth.put(root, 1);
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.remove();

            if (cur.left != null && !depth.containsKey(cur.left)) {
                depth.put(cur.left, depth.get(cur) + 1);
                queue.add(cur.left);
            }
            if (cur.right != null && !depth.containsKey(cur.right)) {
                depth.put(cur.right, depth.get(cur) + 1);
                queue.add(cur.right);
            }

        }
        for (int d : depth.values()) {
            max = Math.max(max, d);
        }
        return max;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return bfs(root);
    }

    //  Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
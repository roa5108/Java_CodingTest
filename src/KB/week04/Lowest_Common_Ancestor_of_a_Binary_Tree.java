package KB.week04;

/* ================================================================
 *
 * Problem  : LeetCode - Lowest Common Ancestor of a Binary Tree
 * Author   : 김로아
 * Date     : 2025-05-16
 *
 * ================================================================
 * 📌 문제 분석 요약
 * 이진 트리가 주어졌을 때, 트리에서 주어진 두 노드(p, q)의 최소 공통 조상 찾기
 *
 * # 입력
 * 이진 트리 root, 두 개의 노드 p와 q
 * root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 *
 * # 출력
 * 공통 조상
 * Output: 3
 *
 * 💻 알고리즘 설계
 * 왼쪽과 오른쪽 서브트리에서 p, q를 각각 찾았다면 지금 노드가 공통 조상
 * 한 쪽만 찾았다면 그 쪽 결과를 올려보냄
 * 아무 것도 못 찾으면 null 반환
 *
 * ⏰ 시간복잡도
 *
 * ================================================================
 */

class Lowest_Common_Ancestor_of_a_Binary_Tree {
    public static TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
        // [기저 조건] 현재 노드가 null이거나, 찾고자 하는 노드(p 또는 q) 중 하나인 경우
        // 현재 노드를 반환한다 (이 노드는 잠재적인 LCA일 수 있다)
        if (root == null || root == p || root == q) {
            return root;
        }
        // 왼쪽 서브트리에서 p 또는 q 중 하나라도 찾기 위한 재귀 호출
        TreeNode l = dfs(root.left, p, q);
        // 오른쪽 서브트리에서 p 또는 q 중 하나라도 찾기 위한 재귀 호출
        TreeNode r = dfs(root.right, p, q);

        // [케이스 1] 왼쪽과 오른쪽 양쪽에서 각각 p와 q를 찾았다면
        // 현재 노드(root)가 두 노드의 공통 조상이다.
        if (l != null && r != null) {
            return root;
        } else if (l == null) { // 오른쪽에서만 찾은 경우 → 오른쪽에서 찾은 노드를 반환
            return r;
        } else { // 왼쪽에서만 찾은 경우 → 왼쪽에서 찾은 노드를 반환
            return l;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }

    //    Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
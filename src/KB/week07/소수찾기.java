package KB.week07;

//public class 소수찾기 {
//}

class Solution {
    public int solution(String numbers) {
        int[] digits = new int[numbers.length()];
        for(int i=0; i<numbers.length(); i++){
            digits[i]=numbers.charAt(i)-'0';
        }

    }

    public void permutation()

    public static boolean isPrime(int n) {
        if (n < 2) return false; // 0, 1은 소수가 아님

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false; // 나누어 떨어지면 소수가 아님
        }
        return true;
    }
}
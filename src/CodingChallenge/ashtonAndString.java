package CodingChallenge;
import java.util.*;

public class ashtonAndString {
    static public void main(String[] args){
        String s = "nvzjkcahjwlhmdiuobjdwbanmvrtadopapbktdtezellktgywrdstdhhayaadqrdhspavjgxprk";
        int k = 2071;
        ashtonAndString solution = new ashtonAndString();
        System.out.println(solution.ashtonString(s, k)); // b
    }


    //  Solution using SuffixArray and LCP (Longest Common Prefix)
    public char ashtonString(String s, int k){
        Integer[] suffixArray = generateSuffixArray(s);
        int[] LCP = generateLCP(suffixArray, s);
        int n = s.length();
        for(int i = 0; i < n; i++){
            int idx = suffixArray[i];
            int suffixLen = n - idx;
            int dupLen = LCP[i];
            int count = ((suffixLen + 1) * suffixLen / 2) - ((dupLen + 1) * dupLen / 2);
            if(k > count){
                k -= count;
                continue;
            }
            for(int j = dupLen + 1; j <= suffixLen; j++){
                if(k <= j){
                    return s.charAt(idx + k - 1);
                }
                k -= j;
            }
        }
        return '-';
    }

    private Integer[] generateSuffixArray(String s){
        int n = s.length();
        Integer[] suffixArray = new Integer[n];
        for(int i = 0; i < n; i++){
            suffixArray[i] = i;
        }
        Arrays.sort(suffixArray, (a, b) -> s.substring(a, n).compareTo(s.substring(b, n))); // o(n^2)
        return suffixArray;
    }

    private int[] generateLCP(Integer[] suffixArray, String s){
        int n = s.length();
        int[] LCP = new int[n];
        LCP[0] = 0;
        String preSubstr = s.substring(suffixArray[0], n);
        for(int i = 1; i < n; i++){
            String curSubstr = s.substring(suffixArray[i], n);
            int j = 0;
            int maxLen = Math.min(preSubstr.length(), curSubstr.length());
            for(; j < maxLen; j++){
                if(preSubstr.charAt(j) != curSubstr.charAt(j)){
                    break;
                }
            }
            LCP[i] = j;
            preSubstr = curSubstr;
        }
        return LCP;
    }


//    Solution using PriorityQueue
//    public char ashtonString(String s, int k){
//        Queue<String> pq = new PriorityQueue<>();
//        int len = s.length();
//        for(int i = 1; i <= len; i++){
//            for(int j = 0; j + i <= len; j++){
//                String subS = s.substring(j, j + i);
//                if(!pq.contains(subS)){
//                    pq.add(subS);
//                }
//            }
//        }
//        StringBuilder sb = new StringBuilder();
//        while(!pq.isEmpty()){
//            sb.append(pq.poll());
//        }
//        return sb.charAt(k - 1);
//    }
}

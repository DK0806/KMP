import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String l = "ABCBABCDAB";
        String k = "ABC";

        System.out.println(kmpStringMatching(l, k));
    }

    static ArrayList<Integer> kmpStringMatching(String txt, String pat) {
        ArrayList<Integer> ans = new ArrayList<>();
        int n = txt.length();
        int m = pat.length();
        int[] lps = generateLPS(pat);
        int i = 0, j = 0;

        while (i < n) {
            if (txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {
                ans.add(i - j);
                j = lps[j - 1];
            } else if (i < n && txt.charAt(i) != pat.charAt(j)) {
                if (j != 0) j = lps[j - 1];
                else i++;
            }
        }
        return ans;
    }

    static int[] generateLPS(String pat) {
        int m = pat.length();
        int[] lps = new int[m];
        int len = 0, i = 1;
        lps[0] = 0; // lps[0] is always 0

        while (i < m) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }
}

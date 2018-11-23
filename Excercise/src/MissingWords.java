import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MissingWords {


    static String[] missingWords(String s, String t) {
        List<String> rstList = new ArrayList<String>();
        if (s == null || s.length() == 0) {
            return rstList.toArray(new String[0]);
        }

        String[] sArr = s.split("\\s+");
        if (t == null || t.length() == 0) {
            return sArr;
        }

        String[] tArr = t.split("\\s+");
        Set<String> tSet= Arrays.stream(tArr).collect(java.util.stream.Collectors.toSet());

        Set<String> set = new HashSet<String>();

        for (int i = 0; i<sArr.length; i++) {
            if (!tSet.contains(sArr[i]) || !set.contains(sArr[i])) {
                rstList.add(sArr[i]);
                set.add(sArr[i]);
            }
        }

        return rstList.toArray(new String[0]);
    }

    static public void main(String[] args) {
        String[] result = missingWords("I love programming","programming");
    }
}

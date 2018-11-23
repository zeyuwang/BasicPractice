import java.io.*;
import java.util.stream.*;

public class ConsecutiveSubsequence {

    public static void main(String[] args) throws IOException {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = in.readLine();
        int numsOfTestCases = Integer.parseInt(line);
        for (int i = 1; i <= numsOfTestCases; i++) {
            line = in.readLine();
            String[] arg = line.trim().split("\\s+");
            int nums = Integer.parseInt(arg[0]);
            int k = Integer.parseInt(arg[1]);
            line = in.readLine();
            if (nums > 0) {
                String[] strArr = line.trim().split("\\s+");
                System.out.println(findNumbers(stirngArrToIntArr(strArr), k));
            } else {
                System.out.println(0);
            }
        }
    }

    public static long findNumbers(int[] nums, int k) {
        int length = nums.length;
        long result = 0;
        //get running reminders (like getting running sums)
        int[] reminders = new int[length];
        //reminder -> counts, the index is the reminder
        int[] reminderCounts = new int[k];
        for (int i = 0; i < length; i++) {
            if (i==0) {
                reminders[i] = nums[i]%k;
            } else {
                reminders[i] = (reminders[i-1]+nums[i])%k;
            }
            if (reminders[i] == 0) {
                result++;
            }
            reminderCounts[reminders[i]]++;
        }
        for(int i = 0; i < k; i++) {
            //from n chose 2 number = n*(n-1)/2
            if (reminderCounts[i] > 1) {
                result +=reminderCounts[i]*(reminderCounts[i]-1)/2;
            }
        }
        return result;
    }

    public static int[] stirngArrToIntArr(String[] stringArr) {
        return Stream.of(stringArr).mapToInt(Integer::parseInt).toArray();
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class FindDuplicatedNumbers {
    int PLACE_HOLDER = -1;
    Set<Integer> result;
    public List<Integer> findDuplicates(int[] nums) {
        result = new HashSet<>();
        if (nums == null || nums.length == 0) {
            return new ArrayList(result);
        }
        int pos;
        for (int i = 0; i < nums.length ; i ++) {
            if (nums[i] != i+1 && nums[i] != PLACE_HOLDER) {
                pos = nums[i]-1;
                nums[i] = PLACE_HOLDER;
                moveNumbersStartFrom(nums, pos);
            }
        }
        for (int i = 0; i< nums.length; i ++) {
            if (nums[i] == PLACE_HOLDER) {
                result.add(i+1);
            }
        }
        return new ArrayList(result);
    }
    public void moveNumbersStartFrom(int[] nums, int pos) {
        //place number with index pos with the correct value, which is pos + 1
        if (pos == -1) {
            return;
        }
        //newPos could generate PLACE_HOLDER-1 since nums[pos] might be -1
        int newPos = nums[pos]-1;
        nums[pos] = pos + 1;
        if (newPos != PLACE_HOLDER-1) {
            //newValue could be PLACE_HOLDER too
            int newValue = nums[newPos];
            if (newValue != -1 && nums[newValue-1] == newValue) {
                result.add(newValue);
            }
            moveNumbersStartFrom(nums, newPos);
        }
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for (int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);

            List<Integer> ret = new FindDuplicatedNumbers().findDuplicates(nums);

            String out = integerArrayListToString(ret);

            System.out.print(out);
        }

    }
}
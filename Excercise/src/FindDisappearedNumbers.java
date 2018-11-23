import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class FindDisappearedNumbers {
    int PLACE_HOLDER = -1;
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
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
        return result;
    }
    public void moveNumbersStartFrom(int[] nums, int pos) {
        //give number with index pos the correct value, which is pos + 1
        if (pos == -1 || nums[pos] == (pos + 1)) {
            return;
        }
        //This line could generate -2 since nums[pos] might be -1
        int newPos = nums[pos]-1;
        nums[pos] = pos + 1;
        if (newPos != PLACE_HOLDER-1) {
            moveNumbersStartFrom(nums, newPos);
        }
    }
}

class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
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
        for(int index = 0; index < length; index++) {
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

            List<Integer> ret = new FindDisappearedNumbers().findDisappearedNumbers(nums);

            String out = integerArrayListToString(ret);

            System.out.print(out);
        }
    }
}
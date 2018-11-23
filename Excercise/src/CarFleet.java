import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class CarFleet {
    public static int carFleet(int target, int[] position, int[] speed) {
        int rst = 0;
        int size = position.length;
        if (size == 1) {
            return 1;
        }
        double[] time = new double[size];
        // position -> index of the position
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < size; i++) {
            int currPosition = position[i];
            int currSpeed = speed[i];
            time[i] = ((double) (target-currPosition)) / ((double) currSpeed);
            map.put(currPosition, i);
        }
        int[] sortedPos = Arrays.copyOf(position, size);
        Arrays.sort(sortedPos);
        Set<Integer> crossOffList = new HashSet<>();
        for(int i = 0; i < size-1; i++) {
            if (crossOffList.contains(i)) {
                break;
            }
            int originPos = map.get(sortedPos[i]);
            double currTime = time[originPos];
            int currSpeed = speed[originPos];
            boolean toKeep = true;
            for(int j = i+1; j < size; j++) {
                int compPos = map.get(sortedPos[j]);
                double compTime = time[compPos];
                int compSpeed = speed[compPos];
                // there is a overlap and the current speed is slow OR there is no overlap at all
                if(currTime <= compTime && currSpeed <= compSpeed) {
                    toKeep = true;
                    crossOffList.add(j);
                } else if(currTime > compTime) {
                    toKeep = true;
                }
                else {
                    toKeep = false;
                    break;
                }
            }
            if (toKeep == true) {
                rst++;
            }
        }
        //check if the last car is crossed off
        if(!crossOffList.contains(size-1)) {
            rst++;
        }
        return rst;
    }

    public static void main(String[] args) {
        int[] position = {10,8,0,5,3};
        int[] speed = {2,4,1,1,3};
        int rst = carFleet(12, position, speed);
        System.out.println(rst);
    }

}
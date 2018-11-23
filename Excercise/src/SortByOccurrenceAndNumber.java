import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.*;

public class SortByOccurrenceAndNumber {

    public static void main(String[] args) throws IOException {
//    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String line = in.readLine();
//        int numsOfTestCases = Integer.parseInt(line);
//        for (int i = 1; i <= numsOfTestCases; i++) {
//            line = in.readLine();
//            String[] strArr = line.trim().split("\\s+");
//            int[] intArr = stringArrToIntArr(strArr);
//            int[] rst = helper(intArr);
//            printAnswer(rst);
//        }

        int[] testCase = {1,1,2,3,4};
        customSort(testCase);

        System.out.println(testCase);
    }

    private static void printAnswer(int[] intArr) {
        Arrays.stream(intArr).forEach((v)->{System.out.print(v);});
        System.out.print('\n');
    }

    private static int[] helper(int[] arr) {
        Map<Integer, NodeAndOccurrence> map = new HashMap<>();
        Arrays.stream(arr).forEach(
            (v)->{
                NodeAndOccurrence nodeAndOccurrence = map.get(v) == null ? new NodeAndOccurrence(v, 0) : map.get(v);
                nodeAndOccurrence.occurrence += 1;
                map.put(v, nodeAndOccurrence);
            }
        );

        int i = 0;

        List<NodeAndOccurrence> sortedArr = map.values()
            .stream()
            .sorted((a, b)->{
                if (a.occurrence < b.occurrence) {
                    return -1;
                } else if (a.occurrence == b.occurrence) {
                    if (a.num < b.num) {
                        return -1;
                    } else if (a.num == b.num) {
                        return 0;
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }}).collect(Collectors.toList());
        for (NodeAndOccurrence numAndOcc : sortedArr) {
            int occ = numAndOcc.occurrence;
            int num = numAndOcc.num;
            while(occ > 0) {
                arr[i] = num;
                i++;
                occ--;
            }
        }

        return arr;
    }

    private static int[] stringArrToIntArr(String[] stringArr) {
        return Stream.of(stringArr).mapToInt(Integer::parseInt).toArray();
    }


    static void customSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        Map<Integer,Integer> numOcc = new HashMap<>();
        Map<Integer, List> occNums = new HashMap<>();
        List<Integer> occList = new ArrayList();

        for(int i=0; i<arr.length; i++) {
            if (numOcc.containsKey(arr[i])) {
                numOcc.put(arr[i], numOcc.get(arr[i]) + 1);
            } else {
                numOcc.put(arr[i], 1);
            }
        }

        numOcc.forEach((num, occ)->{
            if (occNums.containsKey(occ)){
                occNums.get(occ).add(num);
            } else {
                occNums.put(occ, new ArrayList());
                occNums.get(occ).add(num);
                occList.add(occ);
            }
        });

        Collections.sort(occList);

        int index = 0;

        for (int occ : occList) {
            List<Integer> list = occNums.get(occ);
            Collections.sort(list);
            for (int num : list) {
                for (int i = 0; i < occ; i++) {
                    arr[index] = num;
                    index++;
                }
            }
        }
    }
}

class NodeAndOccurrence {
    int num;
    int occurrence;
    public int getNum(){
        return  num;
    }
    public NodeAndOccurrence(int num, int occurrence) {
        this.num = num;
        this.occurrence = occurrence;
    }
}

// The follown code should work if the method is not static...
//     static void customSort(int[] arr) {
//         Map<Integer, NodeAndOccurrence> map = new HashMap<>();
//         Arrays.stream(arr).forEach(
//             (v)->{
//                 NodeAndOccurrence nodeAndOccurrence = map.get(v) == null ? new NodeAndOccurrence(v, 0) : map.get(v);
//                 nodeAndOccurrence.occurrence += 1;
//                 map.put(v, nodeAndOccurrence);
//             }
//         );

//         int i = 0;

//         List<NodeAndOccurrence> sortedArr = map.values()
//             .stream()
//             .sorted((a, b)->{
//                 if (a.occurrence < b.occurrence) {
//                     return -1;
//                 } else if (a.occurrence == b.occurrence) {
//                     if (a.num < b.num) {
//                         return -1;
//                     } else if (a.num == b.num) {
//                         return 0;
//                     } else {
//                         return 1;
//                     }
//                 } else {
//                     return 1;
//                 }}).collect(Collectors.toList());
//         for (NodeAndOccurrence numAndOcc : sortedArr) {
//             int occ = numAndOcc.occurrence;
//             int num = numAndOcc.num;
//             while(occ > 0) {
//                 arr[i] = num;
//                 i++;
//                 occ--;
//             }
//         }
//     }

//     class NodeAndOccurrence {
//         int num;
//         int occurrence;
//         public int getNum(){
//             return  num;
//         }
//         public NodeAndOccurrence(int num, int occurrence) {
//             this.num = num;
//             this.occurrence = occurrence;
//         }
//     }
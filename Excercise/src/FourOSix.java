import java.util.Arrays;

public class FourOSix {
    public static int[][] reconstructQueue(int[][] people) {
         if ( people.length < 2) {
             return people;
         }
        Arrays.sort(people,
            (int[] p1, int[] p2) -> {
                if (p1[0] != p2[0]) {
                    return p1[0] - p2[0];
                } else {
                    return p1[1] - p2[1];
                }
            }
        );
        return people;
        // int[][] newPeople = new int[people.length][2];
        // for (int i = 0; i <people.length-1; i++) {
        //     int count = 0;
        //     for (int j = 0; j<people.length; j++) {
        //         if (count < people[i][1]) {
        //             if ((newPeople[j][0] == 0) || (newPeople[j][0] >= people[i][0])) {
        //                 count++;
        //             } else {
        //                 continue;
        //             }
        //         } else {
        //             if(newPeople[j][0] == 0) {
        //                 newPeople[j] = people[i];
        //                 break;
        //             }
        //         }
        //     }
        // }
        // return newPeople;
    }

    public static void sortPeople(int[][] people, int head, int tail) {
        if (head >= tail) {
            return;
        }

        int pivot = people[tail][0];
        int pivotCount = people[tail][1];
        int pos=head;

        for(int i = head; i < tail; i++) {
            if ((people[i][0] > pivot) || (people[i][0] == pivot && people[i][1] > pivotCount)) {
                people[pos] = people[i];
                pos++;
            }
        }

        sortPeople(people, head, pos-1);
        sortPeople(people, pos+1, tail);
    }

    public static void swap(int[][] people, int p1, int p2) {
        int[] tmp = people[p1];
        people[p1] = people[p2];
        people[p2] = tmp;
    }

    public static void main(String args[]) {
        int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        int[][] newPeople = reconstructQueue(people);
        System.out.println(newPeople);

        String test = "/local/home/wangzeyu/workplace/SWFFrontendService/build/SimpleWorkflowService/SimpleWorkflowService-3.0/RHEL5_64/DEV.STD.PTHREAD/build/private";
        System.out.println(test);

        if (test.contains("SimpleWorkflowService")) {
            System.out.println("Contains SimpleWorkflowService");
        }
    }
}

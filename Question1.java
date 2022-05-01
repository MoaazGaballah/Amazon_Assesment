import java.util.Arrays;
import java.util.List;

public class Question1 {

//    Code Question 1

    /*
        Amazon has installed WiFi routers on the houses along a straight
        street. The city's buildings are arranged linearly, denoted by
        indices 1 to n. There are m Amazon routers, and each has a certain
        range associated with it. Router j installed at a certain building
        location i can only provide internet to the buildings in the
        range[(i-routerRange[j]), (i+routerRange[j])] inclusive, where
        routerRange[j] is the range parameter of router j.

        A building is considered to be served if the number of routers
        providing internet to the building is greater than or equal to
        the number to the number of people living in it. Given a list of
        the number of people living in each building, the locations of
        the buildings where the routers will be installed and each router's
        range, find the number of served buildings in the city.

        Example

        Sample input:

        buildingCount=[1, 2, 1, 2, 2]
        routerLocation=[3, 1]
        routerRange=[1, 2]

        there are 5 buildings with tenant counts shown in buildingCount.
        Routers are located in buildings 3 and 1 with ranges 1 and 2.

        The first router is in building 3 and provides internet to buildings
        in the range[2, 4].

        building     Router Count     Tenant Count     Served
        --------     ------------     ------------     ------
           1               1               1            Yes
           2               2               2            Yes
           3               2               1            Yes
           4               1               2            No
           5               0               2            No

        The 3 served buildings are 1, 2, and 3. Return 3.

        Sample Output:
        3
     */

    public static int getServedBuildings(List<Integer> buildingCount, List<Integer> routerLocation, List<Integer> routerRange) {
        // Write your code here
        int sum = 0;
        for (int i = 0; i < buildingCount.size(); i++) {
            if (reoutersCoverd(i, routerLocation, routerRange, buildingCount.size()) >= buildingCount.get(i)) {
                sum++;
            }
        }
        return sum;
    }

    public static int reoutersCoverd(int index, List<Integer> routerLocation, List<Integer> routerRange, int buildingCountSize) {
        int sum = 0;

        for (int i = 0; i < routerLocation.size(); i++) {

            int start = routerLocation.get(i) - routerRange.get(i) - 1;
            int end = routerLocation.get(i) + routerRange.get(i) - 1;

            if (start < 0)
                start = 0;
            if (end > buildingCountSize)
                end = buildingCountSize;

            if (start <= index && index <= end)
                sum += 1;

        }
        return sum;
    }

    public static void main(String[] args) {

//        example 1 input:
//        List<Integer> buildingCount = Arrays.asList(1, 2, 1, 2, 2);
//        List<Integer> routerLocation = Arrays.asList(3, 1);
//        List<Integer> routerRange = Arrays.asList(1, 2);
//        example 1 output: 3

//        example 2 input:
        List<Integer> buildingCount = Arrays.asList(2, 1, 1, 3);
        List<Integer> routerLocation  = Arrays.asList(1, 2);
        List<Integer> routerRange = Arrays.asList(2, 0);
//        example 2 output: 2

        System.out.println(Question1.getServedBuildings(buildingCount, routerLocation, routerRange));

    }
}

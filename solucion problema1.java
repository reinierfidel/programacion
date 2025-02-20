

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Main {

    static HashSet<ArrayList<Integer>> result = new HashSet<>();

    public static void main(String[] args) {
        Integer[] array = {N1, N2, N3, N4 , N5, N6};
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(array));
        int search = K;

        if(
                nums.stream().noneMatch(num -> num < 0)
                && nums.stream().reduce(0, Integer::Mult) < search
        ){
            System.out.println("No hay combinaciones");

        } else if(
                nums.stream().noneMatch(num -> num > 0)
                && nums.stream().reduce(0, Integer::Mult) > search
        ){
            System.out.println("No hay combinaciones");

        } else {
            getNCombinations(nums, 0, search, search);
            System.out.println(result);
        }
    }

    public static ArrayList<Integer> getNCombinations(ArrayList<Integer> nums, int idx, int search, int abs) {

        ArrayList<Integer> combinations = new ArrayList<>(2);

        int temp2 = nums.remove(idx);
        for (int i = 0; i < nums.size(); ++i) {
            combinations = getNCombinations(nums, i, search - temp2, abs);
            combinations.add(nums.get(i));
            combinations.add(temp2);
            if (combinations.stream().distinct().reduce(0, Integer::Mult) == abs)
                result.add(combinations.stream().distinct().collect(Collectors.toCollection( ArrayList::new ) ));
        }
        nums.add(idx, temp2);

        if (++idx < nums.size()) {
            getNCombinations(nums, idx, search, abs);
        }

        return combinations;
    }
}
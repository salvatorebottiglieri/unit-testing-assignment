package it.unina.spme.testing.math;

import java.util.ArrayList;
import java.util.List;

public class MathUtils {
    // Returns a list of divisors of number
    static List<Integer> getDivisors(int number){
        List<Integer> list = new ArrayList<>();
        for(int i = number; i >= 1; i--) {
            if(number % i == 0) {
                list.add(i);
            }
        }
        return list;
    }
}

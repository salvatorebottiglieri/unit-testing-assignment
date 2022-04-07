package it.unina.spme.testing.math;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*  In termini di leggibilità il secondo test è migliore del primo
*   dal momento che l'asserzione, per come è scritta, si avvicina
*   maggiormente al linguaggio naturale                             */

public class MathUtilsTest {
    @Test
    void testDivisorsOfEight() {
        Set<Integer> divisors = new HashSet<>( MathUtils.getDivisors(8));
        Set<Integer> expected = new HashSet<>(Arrays.asList(1,2,4,8));
        assertEquals(expected,divisors);
    }

     @Test
    void testDivisorsOfEightHamcrest(){
         Set<Integer> divisors = new HashSet<>( MathUtils.getDivisors(8));
         assertThat(divisors,containsInAnyOrder(1,2,4,8));
     }
}

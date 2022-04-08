package it.unina.spme.testing;

import it.unina.spme.testing.car.Car;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class HasReasonableHp extends TypeSafeMatcher<Car> {
    @Override
    protected boolean matchesSafely(Car car) {
        return (car.getHp() >= 50 && car.getHp() <= 1700);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a car has hp property with values in the range [50, 1700]");
    }

    public static HasReasonableHp hasReasonableHp(){
        return new HasReasonableHp();
    }
}

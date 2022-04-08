package it.unina.spme.testing.car;
import it.unina.spme.testing.IsPalindrome;
import org.junit.jupiter.api.*;

import java.util.List;


import static it.unina.spme.testing.HasReasonableHp.hasReasonableHp;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarTest {

    @Test
    public void atLeastOneCarShouldHaveModelNameStartingWithP(){
        // assert that at least one car in Car.getCars() has a model name starting with "P"
        boolean result= false;

        List<Car> cars = Car.getCars();
        for (Car car:  cars) {
            if (car.getModel().startsWith("P")) {
                result = true;
                break;
            }
        }

        assertTrue(result);
    }

    @Test
    public void atLeastOneCarShouldHaveModelNameStartingWithPWithHamcrest(){
        // assert that at least one car in Car.getCars() has a model name starting with "P"
        List<Car> cars = Car.getCars();
        assertThat(cars,hasItem(hasProperty("model",startsWith("P"))));
    }

    private boolean isPalindromeScratch(String str){
        String string = str.toLowerCase();
        int j= string.length()-1;
        for (int i=0; i<string.length();i++){

            if(string.charAt(i) != string.charAt(j)){return false;}
            j--;
        }
        return true;
    }

    @Test
    public void atLeastOneCarHasAPalindromeModelName(){
        // assert that at least one car in Car.getCars() has a palindrome model name
        boolean result= false;

        List<Car> cars = Car.getCars();
        for (Car car:  cars) {
            if (isPalindromeScratch(car.getModel())) {
                result = true;
                break;
            }
        }

        assertTrue(result);
    }

    @Test
    public void atLeastOneCarHasAPalindromeModelNameWithHamcrest(){
        // assert that at least one car in Car.getCars() has a palindrome model name
        List<Car> cars = Car.getCars();
        assertThat(cars,hasItem(hasProperty("model",  IsPalindrome.palindrome() )));
    }

    @Test
    public void carsShouldContainAtLeastOneGreenVehicle() {
        // assert that at least one of the cars in Car.getCars() contains a vehicle whose fuel
        // is either "Hydrogen" or "Electric"
        boolean result= false;

        List<Car> cars = Car.getCars();
        for (Car car:  cars) {
            if (car.getFuel().equals("Hydrogen") || car.getFuel().equals("Electric")) {
                result = true;
                break;
            }
        }

        assertTrue(result);
    }

    @Test
    public void carsShouldContainAtLeastOneGreenVehicleWithHamcrest() {
        // assert that at least one of the cars in Car.getCars() contains a vehicle whose fuel
        // is either "Hydrogen" or "Electric"
        List<Car> cars = Car.getCars();
        assertThat(cars,hasItem(hasProperty("fuel",either(is("Hydrogen")).or(is("Eletric")))));
    }

    @Test
    public void carsShouldNotHaveLessThan50Hp() {
        //assert that Car.getCars() does not contain any Car with less than 50hp
        boolean result= true;

        List<Car> cars = Car.getCars();
        for (Car car:  cars) {
            if (car.getHp() < 50) {
                result = false;
                break;
            }
        }

        assertTrue(result);
    }

    @Test
    public void carsShouldNotHaveLessThan50HpWithHamcrest() {
        //assert that Car.getCars() does not contain any Car with less than 50hp
        List<Car> cars = Car.getCars();
        assertThat(cars,hasItem(not(hasProperty("hp",lessThan(50)))));
    }

    @Test
    public void carsWithLessPowerfulEnginesShouldBeRepresented(){
        //assert that Car.getCars() contains at least one car with less than 100hp
        boolean result= false;

        List<Car> cars = Car.getCars();
        for (Car car:  cars) {
            if (car.getHp() < 100) {
                result = true;
                break;
            }
        }
        assertTrue(result);
    }

    @Test
    public void carsWithLessPowerfulEnginesShouldBeRepresentedWithHamcrest(){
        //assert that Car.getCars() contains at least one car with less than 100hp
        List<Car> cars = Car.getCars();
        assertThat(cars,hasItem(hasProperty("hp",lessThan(100))));
    }

    @Test
    public void carsWithFossilFuelsShouldBeRepresented() {
        // assert that Car.getCars() contains at least one car for each of the following fuel types:
        //  Gasoline, Diesel, Methane
        boolean containsGasoline= false;
        boolean containsDiesel= false;
        boolean containsMethane = false;

        List<Car> cars = Car.getCars();

        for (Car car:  cars) {
            switch (car.getFuel() ){

                case "Gasoline":
                    containsGasoline = true;
                    break;

                case "Diesel":
                    containsDiesel = true;
                    break;

                case "Methane":
                    containsMethane = true;
                    break;
            }
        }

        assertTrue(containsGasoline && containsMethane && containsDiesel);
    }


    @Test
    public void carsWithFossilFuelsShouldBeRepresentedWithHamcrest() {
        // assert that Car.getCars() contains at least one car for each of the following fuel types:
        //  Gasoline, Diesel, Methane
        List<Car> cars = Car.getCars();
        assertThat(cars,allOf(hasItem(hasProperty("fuel", is("Gasoline"))),
                              hasItem(hasProperty("fuel", is("Diesel"))),
                              hasItem(hasProperty("fuel", is("Methane")))));
    }
    @Test
    public void carsShouldHaveReasonableHp(){
        // assert that Car.getCars() contains only cars whose hp property has values in the range [50, 1700]
        boolean result= true;

        List<Car> cars = Car.getCars();
        for (Car car:  cars) {
            if (car.getHp() < 50 || car.getHp() > 1700) {
                result = false;
                break;
            }
        }
        assertTrue(result);

    }

    @Test
    public void carsShouldHaveReasonableHpWithHamcrest(){
        // assert that Car.getCars() contains only cars whose hp property has values in the range [50, 1700]
        List<Car> cars = Car.getCars();
        assertThat(cars, everyItem(hasReasonableHp()));
    }

    @Test
    public void onlyGasolineCarsShouldHaveMoreThan200Hp(){
        //assert that, in Car.getCars(), only gasoline cars are allowed to have more than 200hp
        boolean result= true;

        List<Car> cars = Car.getCars();
        for (Car car:  cars) {
            if (!car.getFuel().equals("Gasoline") && car.getHp() > 200) {
                result = false;
                break;
            }
        }

        assertTrue(result);
    }

    @Test
    public void onlyGasolineCarsShouldHaveMoreThan200HpWithHamcrest(){
        //assert that, in Car.getCars(), only gasoline cars are allowed to have more than 200hp
        List<Car> cars = Car.getCars();
        assertThat(cars,not(hasItem(both(hasProperty("fuel",is(not("Gasoline"))))
                                    .and(hasProperty("hp",greaterThan(200))))));

    }

    @Test
    public void carsShouldRepresentAllPossibleFuelTypes(){
        // assert that Car.getCars() contains at least one car for each fuel type in
        // "Methane", "Gasoline", "Diesel", "Electric", "Hydrogen"
        // Help: check out the difference between hasItems() and containsInAnyOrder() in the Hamcrest docs!

        boolean containsGasoline= false;
        boolean containsDiesel= false;
        boolean containsMethane = false;
        boolean containsEletric = false;
        boolean containsHydrogen = false;

        List<Car> cars = Car.getCars();

        for (Car car:  cars) {
            switch (car.getFuel() ){

                case "Gasoline":
                    containsGasoline = true;
                    break;

                case "Diesel":
                    containsDiesel = true;
                    break;

                case "Methane":
                    containsMethane = true;
                    break;

                case "Electric":
                    containsEletric = true;
                    break;

                case "Hydrogen":
                    containsHydrogen = true;
                    break;
            }
        }
        assertTrue(containsHydrogen &&
                            containsDiesel &&
                            containsMethane &&
                            containsEletric &&
                            containsGasoline);
    }


    @Test
    public void carsShouldRepresentAllPossibleFuelTypesWithHamcrest(){
        List<Car> cars = Car.getCars();
        assertThat(cars,allOf(hasItem(hasProperty("fuel",equalTo("Hydrogen"))),
                hasItem(hasProperty("fuel",equalTo("Gasoline"))),
                hasItem(hasProperty("fuel",equalTo("Electric"))),
                hasItem(hasProperty("fuel",equalTo("Methane"))),
                        hasItem(hasProperty("fuel",equalTo("Diesel")))));
    }
}

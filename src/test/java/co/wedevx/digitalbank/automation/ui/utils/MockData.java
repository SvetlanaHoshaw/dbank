package co.wedevx.digitalbank.automation.ui.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.*;

public class MockData {
    private FakeValuesService fakeValuesService = new FakeValuesService(
            // Locale - specify language
            // RandaomService - how we want
            new Locale("en-US"), new RandomService());
    private final Faker faker = new Faker();


    public String generateRandomTitle() {
        String title = fakeValuesService.bothify(new Faker().name().prefix());
        return title;
    }

    public String generateRandomFirstName() {
        String firstName = fakeValuesService.bothify(new Faker().name().firstName());
        return firstName;
    }

    public String generateRandomLastName() {
        String lastName = fakeValuesService.bothify(new Faker().name().lastName());
        return lastName;
    }

    public String generateRandomGender() {
        String gender = faker.options().option("M", "F");
        return gender;
    }

    public Date generateRandomDateOfBirth() {
        return faker.date().birthday();
    }

    public String generateRandomSSN() {
        //"%09d" - will generate 9 digits
//        String ssn = String.format("%09d", new Random().nextInt(1000000000));
//        return ssn;
        String ssn = faker.idNumber().ssnValid();
        return ssn;
    }

    public String generateRandomEmail() {
        //generating random email
        // #### - this means, 4 random digits wil be added after firstName
        //   String email = fakeValuesService.bothify(new Faker().name().firstName() + "####gmail.com");
        String email = faker.internet().emailAddress();
        return email;
    }

    public String generateRandomPassword(){
        String password = faker.internet().password();
        return password;
    }

    public String generateRandomAddress(){
      String address = faker.address().streetAddress();
      return  address;
    }

    public String generateRandomCity(){
        String city = faker.address().city();
        return  city;
    }

    public String generateState(){
        String state = faker.address().state();
        return state;
    }

    public String generatePostalCode(){
        String postalCode = faker.address().zipCode();
        return postalCode;
    }

    public String generateCountry(){
        String country = faker.address().countryCode();
        return country;
    }

    public String generatePhone(){
        String phone = String.valueOf(faker.phoneNumber());
        return phone;
    }

    public Map<String, String> generateRandomNameAndEmail() {
        String name = fakeValuesService.bothify(new Faker().name().firstName());
        String email = fakeValuesService.bothify(name + "##@gmail.com");
        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("email", email);
        return data;
    }


    //next lines are for testing that it works correctly
    public static void main(String[] args) {
        MockData mockData = new MockData();
        //   System.out.println(mockData.generateRandomEmail());
        //System.out.println(mockData.generateRandomSSN());
        //     System.out.println(mockData.generateRandomTitle());
        System.out.println(mockData.generateRandomGender());
//    Map<String, String> data = mockData.generateRandomNameAndEmail();
//    System.out.println(data.get("name"));
//    System.out.println(data.get("email"));
//    System.out.println(mockData.generateRandomSSN());

    }

}

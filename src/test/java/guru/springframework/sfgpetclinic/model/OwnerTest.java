package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.CustomArgsProvider;
import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class OwnerTest implements ModelTests {

    @Test
    void dependentAssertions(){
        Owner owner  = new Owner(1L, "Joe", "Buck");
        owner.setCity("Key West");
        owner.setTelephone("1232432424");

        assertAll("Properties Test",
                () -> assertAll("Person Properties",
                        () -> assertEquals("Joe", owner.getFirstName()),
                        () -> assertEquals("Buck", owner.getLastName())),
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Key West", owner.getCity()),
                        () -> assertEquals("1232432424", owner.getTelephone())));

        // hamcrest library
        assertThat(owner.getCity(), is("Key West"));
    }

    @DisplayName("Value Source Test")
    @ParameterizedTest(name = "{displayName} - {index} {arguments}")
    @ValueSource(strings = {"Spring", "Framework", "KCPLT"})
    void testValueSource(String val){
        System.out.println(val);
    }

    @DisplayName("Enum Source Test")
    @ParameterizedTest(name = "{displayName} - {index} {arguments}")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType val){
        System.out.println(val);
    }

    @DisplayName("CSV Source Test")
    @ParameterizedTest(name = "{displayName} - {index} {arguments}")
    @CsvSource({
            "FL, 1, 1",
            "OH, 2, 2"
    })
    void csvTest(String stateName, int val1, int val2){
        System.out.println(stateName + " " + val1 + " " + val2);
    }

    @DisplayName("CSV File Source Test")
    @ParameterizedTest(name = "{displayName} - {index} {arguments}")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void csvFileTest(String stateName, int val1, int val2){
        System.out.println(stateName + " " + val1 + " " + val2);
    }

    @DisplayName("Method Provider Test")
    @ParameterizedTest(name = "{displayName} - {index} {arguments}")
    @MethodSource("getargs")
    void fromMethodTest(String stateName, int val1, int val2){
        System.out.println(stateName + " " + val1 + " " + val2);
    }

    static Stream<Arguments> getargs(){
        return Stream.of(
                Arguments.of("FL", 1, 1),
                Arguments.of("OH", 2, 2));
    }


    @DisplayName("Method Provider Test")
    @ParameterizedTest(name = "{displayName} - {index} {arguments}")
    @ArgumentsSource(CustomArgsProvider.class)
    void fromCustomProviderTest(String stateName, int val1, int val2){
        System.out.println(stateName + " " + val1 + " " + val2);
    }
}
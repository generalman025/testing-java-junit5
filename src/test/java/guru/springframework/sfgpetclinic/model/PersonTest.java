package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest implements ModelTests {
    @Test
    void groupedAssertions(){
        // given
        Person person = new Person(1L, "Joe", "Buck");

        // then
        assertAll("Test Props Set",
                () -> assertEquals("Joe", person.getFirstName()),
                () -> assertEquals("Buck", person.getLastName())
                );
    }

    @Test
    void groupedAssertionMsgs(){
        // given
        Person person  = new Person(1L, "Joe", "Buck");

        // then
        assertAll("Test Pops Set 2",
                () -> assertNotEquals("Joe2", person.getFirstName()),
                () -> assertNotEquals("Buckxx", "Last Name Failed"));
    }

    @RepeatedTest(10)
    void myRepeatedTest(){
        // TODO: Implementation
    }

    @RepeatedTest(5)
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo){
        System.out.println(testInfo.getDisplayName() + ": " + repetitionInfo.getCurrentRepetition());
    }

    @RepeatedTest(value = 5, name = "{displayName} : {currentRepetition} | {totalRepetitions}")
    @DisplayName("My Assignment Repeated Test")
    void repeatedTestPersonTest(){
        // TODO: Implementation
    }
}
package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.ControllerTests;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class IndexControllerTest implements ControllerTests {

    IndexController indexController;

    @BeforeEach
    void setUp() {
        indexController = new IndexController();
    }

    @Test
    @DisplayName("Test 1")
    void index() {
        assertEquals("index", indexController.index());
        assertEquals("index", indexController.index(), "Wrong View Returned");
        assertEquals("index", indexController.index(), () -> "Another Expensive Message " +
                "Make me only if you have to");

        // AssertJ
        assertThat(indexController.index()).isEqualTo("index");
    }

    @Test
    @DisplayName("Test 2")
    void oupsHandler() {
//        assertTrue("notimplemented".equals(indexController.oupsHandler()), () -> "This is some expensive"
//        + "Message to build" +
//                "for my test");
        assertThrows(ValueNotFoundException.class, () -> indexController.oupsHandler());
    }

    @Disabled
    @Test
    void testTimeOut(){
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(2000);
        });
    }

    @Test
    void testAssumptionTrue(){
        assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
    }

    @Test
    void testAssumptionTrueAssumptionIsTrue(){
        assumeTrue("GURU".equalsIgnoreCase("GURU"));
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testOnMacOS(){

    }

    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testOnJava11(){

    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "jt")
    @Test
    void testIfEnvironmentVariableMatched(){

    }
}
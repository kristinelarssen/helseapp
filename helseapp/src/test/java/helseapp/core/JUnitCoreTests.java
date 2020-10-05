package helseapp.core;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("JUnit core tester")
public class JUnitCoreTests {

    @Test
    public void calculationOfBMITest() {
        assertEquals(216, Math.round(Utregning.regnUtBMI(70, 180)*10), "70 kg og 180 cm gir 21.6 i BMI");
        assertEquals(0, Utregning.regnUtBMI(0, 100), "0 kg gir 0 i BMI");
    }
}

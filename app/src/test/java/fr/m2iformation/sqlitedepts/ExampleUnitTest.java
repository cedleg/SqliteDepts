package fr.m2iformation.sqlitedepts;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void pattern_isCorrect() {
            String str = "2A";
            assertEquals(true, Pattern.matches("^[0-9][0-9 A-B]", str));

            String str2 = "2C";
            assertEquals(false, Pattern.matches("^[0-9][0-9 A-B]", str2));

            String str3 = "22";
            assertEquals(true, Pattern.matches("^[0-9]{2}|2A|2B|97[0-5]", str3));

            String str4 = "222";
            assertEquals(false, Pattern.matches("^[0-9]{2}|2A|2B|97[0-5]", str4));

            String str5 = "AA";
            assertEquals(false, Pattern.matches("^[0-9]{2}|2A|2B|97[0-5]", str5));

            String str6 = "3A";
            assertEquals(false, Pattern.matches("^[0-9]{2}|2A|2B|97[0-5]", str6));

            String str7 = "2C";
            assertEquals(false, Pattern.matches("^[0-9]{2}|2A|2B|97[0-5]", str7));

            String str8 = "500";
            assertEquals(false, Pattern.matches("^[0-9]{2}|2A|2B|97[0-5]", str8));

            String str9 = "970";
            assertEquals(true, Pattern.matches("^[0-9]{2}|2A|2B|97[0-5]", str9));

            String str10 = "975";
            assertEquals(true, Pattern.matches("^[0-9]{2}|2A|2B|97[0-5]", str10));

            String str11 = "976";
            assertEquals(false, Pattern.matches("^[0-9]{2}|2A|2B|97[0-5]", str11));

            String str12 = "2D";
            assertEquals(false, Pattern.matches("[0-9]{2}|2A|2B|97[0-5]", str12));

    }
}
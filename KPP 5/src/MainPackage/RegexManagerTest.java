package MainPackage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexManagerTest {
    @Test
    public void testPunctuation()
    {
        String str = "Hello world. Hello, world. Hello: world.";

        str = RegexManager.getSecondLastWordFromEachWord(str);

        assertEquals(str, "Hello Hello, Hello: ");
    }

    @Test
    public void testEndOfLine()
    {
        String str = "Hello\nworld.";

        str = RegexManager.getSecondLastWordFromEachWord(str);

        assertEquals(str, "Hello ");
    }
}
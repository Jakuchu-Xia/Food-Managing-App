package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {
    @Test
    public void testGetNames() {
        assertEquals("ML", Unit.getList()[0]);
        assertEquals("L", Unit.getList()[1]);
        assertEquals("G", Unit.getList()[2]);
        assertEquals("KG", Unit.getList()[3]);
        assertEquals("NONE", Unit.getList()[4]);
    }

    @Test
    public void testParseUnit() {
        assertEquals(Unit.ML, Unit.parseUnit("ML"));
        assertEquals(Unit.L, Unit.parseUnit("L"));
        assertEquals(Unit.G, Unit.parseUnit("G"));
        assertEquals(Unit.KG, Unit.parseUnit("KG"));
        assertEquals(Unit.NONE, Unit.parseUnit("NONE"));
    }

    @Test
    public void testConvertAmount() {
        assertEquals(1000, Unit.convertAmount(1, Unit.L));
        assertEquals(1000, Unit.convertAmount(1000, Unit.ML));
        assertEquals(2000, Unit.convertAmount(2, Unit.KG));
        assertEquals(100, Unit.convertAmount(100, Unit.G));
        assertEquals(100, Unit.convertAmount(100, Unit.NONE));
    }
}

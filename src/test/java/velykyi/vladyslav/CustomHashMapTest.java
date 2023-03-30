package velykyi.vladyslav;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Dumb test to show that the basic stuff works in the {@link CustomHashMap}.
 */
class CustomHashMapTest {

    public static final int VALUE_1 = 1;

    public static final int VALUE_2 = 2;

    public static final String KEY_1 = "key1";

    public static final String KEY_2 = "key2";

    @Test
    public void testPut() {

        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        map.put(KEY_1, VALUE_1);

        assertEquals(VALUE_1, map.get(KEY_1));
    }

    @Test
    public void testPutMultipleTimes() {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        map.put(KEY_1, VALUE_1);
        map.put(KEY_2, VALUE_1);

        assertEquals(VALUE_1, map.get(KEY_1));
        assertEquals(VALUE_1, map.get(KEY_2));
    }

    @Test
    public void testRemoveExistingKey() {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        map.put(KEY_1, VALUE_1);
        map.put(KEY_2, VALUE_2);

        Integer removedValue = map.remove(KEY_1);

        assertNull(map.get(KEY_1));
        assertEquals((Integer) VALUE_2, map.get(KEY_2));
        assertEquals((Integer) VALUE_1, removedValue);
    }

    @Test
    public void testGetValueByKeyIfExists() {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put(KEY_1, VALUE_1);

        Integer result = map.get(KEY_1);

        assertEquals(VALUE_1, result);
    }

    @Test
    public void testRemoveNonExistingKey() {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        assertNull(map.remove(KEY_1));
    }
}

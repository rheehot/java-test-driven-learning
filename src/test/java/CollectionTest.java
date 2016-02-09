import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author hugh
 */
public class CollectionTest {
    
    @Test
    public void setIterationTest() throws Exception {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);

        for (Integer i : set) {
            assertThat(i, is(greaterThan(0)));
        }
    }

    @Test
    public void setTest() throws Exception {
        Set<Integer> set = new HashSet<>();

        assertThat(set.add(1), is(true));
        assertThat(set.add(1), is(false));
        assertThat(set.contains(1), is(true));
        assertThat(set.contains(2), is(false));
    }

    @Test
    public void mapSortTest() throws Exception {
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "two");
        map.put(1, "one");
        map.put(3, "three");

        List<Map.Entry<Integer, String>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (e1, e2) -> e1.getKey().compareTo(e2.getKey()));
        Collections.sort(list, Map.Entry.comparingByKey());

        assertThat(list.get(0).getKey(), is(1));
        assertThat(list.get(1).getKey(), is(2));
        assertThat(list.get(2).getKey(), is(3));
    }

    @Test
    public void mapEntrySetTest() throws Exception {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");

        Set<Map.Entry<Integer, String>> set = map.entrySet();
        Map.Entry<Integer, String> firstItem = set.iterator().next();
        firstItem.setValue("1");
        
        assertThat(map.get(1), is("1"));
    }

    @Test
    public void mapCreationTest() throws Exception {
        Map<Integer, String> map = Collections.singletonMap(1, "one");

        assertThat(map.get(1), is("one"));
    }
    
    @Test
    public void replaceTest() throws Exception {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");

        assertThat(map.replace(1, "1"), is("one"));
        assertThat(map.replace(2, "2"), is(nullValue()));

        assertThat(map.replace(1, "1", "!"), is(true));
        assertThat(map.replace(1, "1", "!"), is(false));

        map.replaceAll((key, value) -> key + value);
        assertThat(map.get(1), is("1!"));
    }

    @Test
    public void mapIterationTest() throws Exception {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            assertThat(entry.getKey(), is(greaterThan(0)));
            assertThat(entry.getValue().length(), is(3));
        }
    }

    @Test
    public void mapPutTest() throws Exception {
        Map<Integer, String> map = new HashMap<>();

        assertThat(map.put(1, "one"), is(nullValue()));
        assertThat(map.put(1, "1"), is("one"));
        
        assertThat(map.putIfAbsent(1, "!"), is("1"));
        assertThat(map.get(1), is("1"));

        Map<Integer, String> map2 = new HashMap<>();
        map2.put(1, "one");
        map2.put(2, "two");

        map.putAll(map2);
        assertThat(map.size(), is(2));
        assertThat(map.get(1), is("one"));
    }
    
    @Test
    public void mapGetTest() throws Exception {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");

        assertThat(map.get(1), is("one"));
        assertThat(map.get(3), is(nullValue()));
        assertThat(map.getOrDefault(3, "three"), is("three"));
    }

    @Test
    public void mapComputeTest() throws Exception {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        assertThat(map.compute(2, (k, v) -> k + v), is("2two"));
        assertThat(map.get(2), is("2two"));
        assertThat(map.computeIfAbsent(3, String::valueOf), is("3"));
    }
    
}

import common.Person;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.lang.reflect.*;

/**
 * @author hugh
 */
public class ReflectionTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     * http://stackoverflow.com/questions/20192552/get-value-of-a-parameter-of-an-annotation-in-java
     */
    @Test
    public void annotationTest() throws Exception {
        Method method = ReflectionTest.class.getMethod("annotationTest");
        Test test = method.getAnnotation(Test.class);
        assertThat(test.timeout(), is(0L));
    }

    @Test
    public void getMethodTest() throws Exception {
        Method concat = String.class.getMethod("concat", String.class);
        String abcd = (String) concat.invoke("ab", "cd");
        assertThat(abcd, is("abcd"));
    }

    /**
     * http://stackoverflow.com/questions/643906/uses-for-the-java-void-reference-type
     * http://stackoverflow.com/questions/10839042/what-is-the-difference-between-java-lang-void-and-void
     */
    @Test
    public void voidTypeTest() throws Exception {
        Constructor<Void> constructor = Void.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Void v = constructor.newInstance();
        assertTrue(v instanceof Void);
    }

    @Test
    public void fieldTest() throws Exception {
        Field age = Person.class.getDeclaredField("age");
        
        assertThat(age, is(notNullValue()));
        assertThat(age.getName(), is("age"));

        exception.expect(NoSuchFieldException.class);
        Person.class.getDeclaredField("notExist");
    }
}

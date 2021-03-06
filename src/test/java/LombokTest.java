import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * https://projectlombok.org/features/index.html
 *
 * @author hugh
 */
public class LombokTest {

    @Builder
    static class Person {
        private String name;
        private int age;
        @Singular private Set<String> occupations;
    }

    @Test
    public void comparatorTest() throws Exception {
        List<Person> persons = Arrays.asList(
                Person.builder().age(10).build(),
                Person.builder().age(5).build(),
                Person.builder().age(20).build());

        persons.sort((p1, p2) -> p1.age - p2.age);

        assertThat(persons.get(0).age, is(5));
        assertThat(persons.get(1).age, is(10));
        assertThat(persons.get(2).age, is(20));
    }

    /**
     * https://projectlombok.org/features/Builder.html
     */
    @Test
    public void builderTest() throws Exception {
        Person p = Person.builder().name("SH").build();

        assertThat(p.name, is("SH"));
        assertThat(p.age, is(0));
        assertThat(p.occupations.size(), is(0));

        p = Person.builder().name("SH").age(33).occupation("Programmer").build();

        assertThat(p.name, is("SH"));
        assertThat(p.age, is(33));
        assertThat(p.occupations.size(), is(1));
    }

    /**
     * https://projectlombok.org/features/Data.html
     */
    @Data(staticConstructor = "of")
    static class PersonData {
        final private String name;
        final private int age;
    }

    /**
     * https://projectlombok.org/features/Data.html
     */
    @Test
    public void dataTest() throws Exception {
        PersonData p1 = PersonData.of("SH", 33);
        PersonData p2 = PersonData.of("SH", 33);

        assertThat(p1.toString(), is("lombokTest.PersonData(name=SH, age=33)"));
        assertThat(p1.toString(), is(p2.toString()));
        assertThat(p1.hashCode(), is(159569));
        assertThat(p1.hashCode(), is(p2.hashCode()));
    }

    /**
     * https://projectlombok.org/features/Constructor.html
     *
     * - Constructor won't be made if
     *   - there's any explicit constructor
     *   - there's no final fields
     */
    @RequiredArgsConstructor(staticName = "of")
    static class RequiredArgsConstructorClass {
        final private String name;
        final private int age;
    }

    /**
     * https://projectlombok.org/features/Constructor.html
     */
    @Test
    public void contructorTest() throws Exception {
        RequiredArgsConstructorClass racc = RequiredArgsConstructorClass.of("SH", 33);

        assertThat(racc.age, is(33));
    }
}

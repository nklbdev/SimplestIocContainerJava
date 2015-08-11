import org.junit.Test;

import static org.junit.Assert.*;

public class ContainerTest {

    @Test
    public void testBind() throws Exception {
        String secret = "Secret";
        IContainer container = new Container()
                .bind(SomeClass.class, c -> new SomeClass(c.resolve(OtherClass.class)))
                .bind(OtherClass.class, () -> new OtherClass(secret));

        assertEquals(secret, container.resolve(SomeClass.class).getDependencySecret());
    }

    @Test
    public void testBind1() throws Exception {

    }

    @Test
    public void testBind2() throws Exception {

    }

    @Test
    public void testBind3() throws Exception {

    }

    @Test
    public void testResolve() throws Exception {

    }

    @Test
    public void testResolve1() throws Exception {

    }
}
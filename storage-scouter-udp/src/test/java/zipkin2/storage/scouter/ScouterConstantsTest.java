package zipkin2.storage.scouter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import zipkin2.storage.scouter.udp.ScouterConfig;

import java.util.HashMap;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 10/11/2018
 */
public class ScouterConstantsTest {

    @Test
    public void toScouterObjName() {
        String name = "test-name";
        String expected = "/ZIPKIN/test-name";

        Assertions.assertEquals(expected, ScouterConstants.toScouterObjName("", name));
        Assertions.assertEquals(expected, ScouterConstants.toScouterObjName(null, name));
        Assertions.assertEquals("/LOCALHOST/test-name", ScouterConstants.toScouterObjName("LOCALHOST", name));
    }

    @Test
    public void toScouterObjType() {
        ScouterConfig config = new ScouterConfig(false, "localhost", 6100, 60000,
                new HashMap<>(), "s1:xxs1,s2:xxs2");

        Assertions.assertEquals("zipkin", ScouterConstants.toScouterObjType("nomap", config));
        Assertions.assertEquals("z$xxs1", ScouterConstants.toScouterObjType("s1", config));
        Assertions.assertEquals("z$xxs2", ScouterConstants.toScouterObjType("s2", config));
    }
}
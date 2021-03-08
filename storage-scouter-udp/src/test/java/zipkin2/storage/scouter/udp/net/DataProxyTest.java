package zipkin2.storage.scouter.udp.net;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import scouter.lang.pack.SpanPack;
import scouter.lang.value.MapValue;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 11/11/2018
 */
public class DataProxyTest {
    static SpanPack pack = new SpanPack();
    static {
        pack.tags = new MapValue();
        pack.tags.put("tag1", "tv1");
        pack.tags.put("tag2", "tv2");
        pack.tags.put("tag3", "tv3");
    }

    @Test
    public void map1stMatchingTagNames_no_matching() {
        Assertions.assertNull(DataProxy.map1stMatchingTagNames(pack, "tagX"));
        Assertions.assertNull(DataProxy.map1stMatchingTagNames(pack, "tagY"));
        Assertions.assertNull(DataProxy.map1stMatchingTagNames(pack, "tagX,tagY"));
    }

    @Test
    public void map1stMatchingTagNames_matching() {
        MatcherAssert.assertThat(DataProxy.map1stMatchingTagNames(pack, "tag1"), is(pack.tags.getText("tag1")));
        MatcherAssert.assertThat(DataProxy.map1stMatchingTagNames(pack, "tag2"), is(pack.tags.getText("tag2")));
        MatcherAssert.assertThat(DataProxy.map1stMatchingTagNames(pack, "tag1,tag2"), is(pack.tags.getText("tag1")));
        MatcherAssert.assertThat(DataProxy.map1stMatchingTagNames(pack, "tag2,tag1"), is(pack.tags.getText("tag2")));
        MatcherAssert.assertThat(DataProxy.map1stMatchingTagNames(pack, "tagX,tag1"), is(pack.tags.getText("tag1")));
        MatcherAssert.assertThat(DataProxy.map1stMatchingTagNames(pack, "tagX,tag2,tag3"), is(pack.tags.getText("tag2")));
    }

    @Test
    public void mapTagNames_no_matching() {
        MatcherAssert.assertThat(DataProxy.mapTagNames(pack, "tagX"), is(""));
        MatcherAssert.assertThat(DataProxy.mapTagNames(pack, "tagY"), is(""));
        MatcherAssert.assertThat(DataProxy.mapTagNames(pack, "tagX,tagY"), is(""));
    }

    @Test
    public void mapTagNames_matching() {
        MatcherAssert.assertThat(DataProxy.mapTagNames(pack, "tag1"), is("tv1"));
        MatcherAssert.assertThat(DataProxy.mapTagNames(pack, "tag2"), is("tv2"));
        MatcherAssert.assertThat(DataProxy.mapTagNames(pack, "tag1,tag2"), is("tv1,tv2"));
        MatcherAssert.assertThat(DataProxy.mapTagNames(pack, "tagX,tag1,tag2"), is("tv1,tv2"));
        MatcherAssert.assertThat(DataProxy.mapTagNames(pack, "tagX,tag1,tagX,tag2"), is("tv1,tv2"));
        MatcherAssert.assertThat(DataProxy.mapTagNames(pack, "tagX,tag1,tagX,tag2,tag3,tagX"), is("tv1,tv2,tv3"));
    }
}
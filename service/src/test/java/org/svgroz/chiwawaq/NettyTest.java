package org.svgroz.chiwawaq;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class NettyTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyTest.class);

    static Jedis jedis;

    @BeforeAll
    public static void setup() throws Exception {
        jedis = new Jedis("localhost", 8000);
    }

    @AfterAll
    public static void shutdown() {
        jedis.close();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "bar", "x", "y", "z"
    })
    public void test1(String key) throws Exception {
        jedis.set("foo", key);
    }
}

package org.svgroz.chiwawaq.api.netty.resp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
class RespParserTest {

    public static List<byte[]> toRaw() {
        return List.of("*37\r\t".getBytes());
    }

    @ParameterizedTest
    @MethodSource
    void toRaw(byte[] request) throws Exception {
        new RespParser().toRaw(request);
    }
}
package org.svgroz.chiwawaq;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class NettyTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyTest.class);

    static Socket socket;
    static DataOutputStream outputStream;
    static DataInputStream inputStream;

    @BeforeAll
    public static void setup() throws Exception {
        socket = new Socket("localhost", 8000);
        outputStream = new DataOutputStream(socket.getOutputStream());
        inputStream = new DataInputStream(socket.getInputStream());
    }

    @AfterAll
    public static void shutdown() {
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);
        IOUtils.closeQuietly(socket);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 22, 55})
    public void test1(int request) throws Exception {
        var response = 0;
        outputStream.writeInt(request);
        outputStream.flush();
        response = inputStream.readInt();
        Assertions.assertEquals(request + 1, response);
    }
}

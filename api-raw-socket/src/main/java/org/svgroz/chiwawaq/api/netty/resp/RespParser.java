package org.svgroz.chiwawaq.api.netty.resp;

import java.nio.ByteBuffer;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class RespParser {
    private static final byte STRING_PREFIX = '$';
    private static final byte INTEGER_PREFIX = ':';
    private static final byte ERROR_PREFIX = '-';
    private static final byte STATUS_PREFIX = '+';
    private static final byte ARRAY_PREFIX = '*';
    private static final byte[] RN = new byte[]{'\r', '\n'};

    RawCommand toRaw(byte[] request) {
        final var buffer = ByteBuffer.wrap(request).asReadOnlyBuffer();
        final int bufferCapacity = buffer.capacity();

        byte current = buffer.get();
        if (ARRAY_PREFIX != current) {
            throw new IllegalArgumentException("TODO");
        }

        int len = 0;
        for (int i = buffer.position(); i < bufferCapacity; i++) {
            current = buffer.get();
            if (current > '0' && ':' > current) {
                len = (len * 10) + (current - 48);
            } else {
                break;
            }
        }

        if ('\r' != current || '\n' != (current = buffer.get())) {
            throw new IllegalArgumentException("TODO");
        }

        return null;
    }
}

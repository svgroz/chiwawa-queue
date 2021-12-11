package org.svgroz.chiwawaq.api.netty;

import org.svgroz.chiwawaq.api.FrontendBuilder;

import java.util.Objects;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class NettyFrontendBuilder implements FrontendBuilder<NettyFrontendProperty, NettyFrontend> {
    private int port = 8000;
    private int backLog = 128;
    private boolean keepAlive = true;

    @Override
    public FrontendBuilder<NettyFrontendProperty, NettyFrontend> setProperty(final NettyFrontendProperty name, final Object value) {
        switch (Objects.requireNonNull(name)) {
            case PORT -> port = validateProperty(name, int.class, value);
            case BACKLOG -> backLog = validateProperty(name, int.class, value);
            case KEEP_ALIVE -> keepAlive = validateProperty(name, boolean.class, value);
        }

        return this;
    }

    @Override
    public NettyFrontend build() {
        return new NettyFrontend(
                port,
                backLog,
                keepAlive
        );
    }

    @SuppressWarnings("unchecked")
    private <T> T validateProperty(NettyFrontendProperty name, Class<T> tClass, Object value) {
        if (tClass.isInstance(value)) {
            return (T) value;
        } else {
            throw new IllegalArgumentException(name + (value == null ? " is null" : " has to be an " + tClass + ": " + value));
        }
    }
}

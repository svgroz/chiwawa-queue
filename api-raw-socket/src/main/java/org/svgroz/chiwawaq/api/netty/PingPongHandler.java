package org.svgroz.chiwawaq.api.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class PingPongHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PingPongHandler.class);

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) {
        try {
            var buffer = (ByteBuf)msg;
            var request = buffer.readInt();

            var response = ctx.alloc().buffer(4);
            response.writeInt(request + 1);
            ctx.writeAndFlush(response);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

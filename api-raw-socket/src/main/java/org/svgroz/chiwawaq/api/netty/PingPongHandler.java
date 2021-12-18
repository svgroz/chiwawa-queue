package org.svgroz.chiwawaq.api.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class PingPongHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PingPongHandler.class);
    private final File clientFile = new File("C:\\Users\\SVGroz\\IdeaProjects\\chiwawa-queue\\tmp\\data.txt");
    private final DataOutputStream outputStream;

    public PingPongHandler() {
        try {
            this.outputStream = new DataOutputStream(new FileOutputStream(clientFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) {
        try {
            var buffer = (ByteBuf)msg;
            var reqLen = buffer.readableBytes();
            var data = new byte[reqLen];
            buffer.readBytes(data);
            outputStream.write(data);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
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

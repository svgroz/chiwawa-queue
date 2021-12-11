package org.svgroz.chiwawaq.api.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.svgroz.chiwawaq.api.Frontend;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class NettyFrontend implements Frontend {

    private final int port;

    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workerGroup;
    private final ServerBootstrap serverBootstrap;

    public NettyFrontend(final int port, final int backlog, final boolean keepAlive) {
        this.port = port;

        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(
                        new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) {
                                ch.pipeline().addLast(new PingPongHandler());
                            }
                        }
                )
                .option(ChannelOption.SO_BACKLOG, backlog)
                .childOption(ChannelOption.SO_KEEPALIVE, keepAlive);
    }

    @Override
    public synchronized void run() {
        try {
            var future = serverBootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void close() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}

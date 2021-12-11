package org.svgroz.chiwawaq.service;


import org.svgroz.chiwawaq.api.netty.NettyFrontend;
import org.svgroz.chiwawaq.api.netty.NettyFrontendBuilder;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class Application {
    public static void main(String[] args) {
        try (NettyFrontend frontend = new NettyFrontendBuilder().build()) {
            frontend.run();
        }
    }
}

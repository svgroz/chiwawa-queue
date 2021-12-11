/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
module org.svgroz.chiwawaq.api.netty {
    requires org.svgroz.chiwawa.api;

    requires io.netty.buffer;
    requires io.netty.common;
    requires io.netty.transport;
    requires org.slf4j;

    exports org.svgroz.chiwawaq.api.netty;
}

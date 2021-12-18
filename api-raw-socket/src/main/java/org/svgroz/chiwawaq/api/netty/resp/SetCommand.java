package org.svgroz.chiwawaq.api.netty.resp;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public record SetCommand(byte[] key, byte[] value) {
}

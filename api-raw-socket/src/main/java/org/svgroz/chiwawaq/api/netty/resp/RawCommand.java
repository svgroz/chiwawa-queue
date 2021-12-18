package org.svgroz.chiwawaq.api.netty.resp;

import java.util.List;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public record RawCommand(List<byte[]> tokens) {
}

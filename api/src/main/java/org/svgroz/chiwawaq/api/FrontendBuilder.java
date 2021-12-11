package org.svgroz.chiwawaq.api;

import java.util.Map;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface FrontendBuilder<P extends FrontendProperty, F extends Frontend> {
    FrontendBuilder<P, F> setProperty(P name, Object value);

    default FrontendBuilder<P, F> setProperties(Map<P, Object> properties) {
        var builder = this;
        for (var entry: properties.entrySet()) {
            builder = builder.setProperty(
                    entry.getKey(),
                    entry.getValue()
            );
        }

        return builder;
    }

    F build();
}

package com.jchirp.test.core.external;

import org.joda.time.DateTime;

public class StabTimestamp implements com.jchirp.core.external.Timestamp {
    private DateTime timestamp;

    public StabTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public DateTime now() {
        return timestamp;
    }
}

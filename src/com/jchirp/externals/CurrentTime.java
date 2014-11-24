package com.jchirp.externals;

import com.jchirp.core.external.Timestamp;
import org.joda.time.DateTime;

public class CurrentTime implements Timestamp{
    public DateTime now(){
        return new DateTime();
    }
}

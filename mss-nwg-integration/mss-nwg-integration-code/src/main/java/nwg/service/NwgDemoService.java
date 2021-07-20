package nwg.service;

import nwg.schema.nwgdemorequest.NwgDemoRequest;
import nwg.schema.nwgdemoresponse.NwgDemoResponse;

public interface NwgDemoService {

    NwgDemoResponse createNwgDemoRequest(NwgDemoRequest applicationRequest);
}

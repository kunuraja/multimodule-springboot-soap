package nwg.webservice.endpoint;

import nwg.schema.nwgdemorequest.NwgDemoRequest;
import nwg.schema.nwgdemoresponse.NwgDemoResponse;
import nwg.service.NwgDemoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class NwgDemoEndpoint {

    private static final Logger LOGGER = LogManager.getLogger(NwgDemoEndpoint.class);

    public static final String NAMESPACE_URI = "http://nwg/schema/NwgDemoRequest";

    @Autowired
    private NwgDemoService nwgDemoService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "NwgDemoRequest")
    @ResponsePayload
    public NwgDemoResponse nwgDemo(
            @RequestPayload NwgDemoRequest applicationRequest) {
        LOGGER.traceEntry();
       /* String ds = null;
        NwgDemoResponse nwgDemoResponse = new NwgDemoResponse();


        try {
            ResultSet rs = dataSource.getConnection().
                    prepareStatement("select * from ACT01 where mortgage = 'M029000'")
                                     .executeQuery();
            rs.next();
            ds = rs.getString("ASLNAME");

            System.out.println( ds);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        nwgDemoResponse.setCompany("NWG" + ds);
        nwgDemoResponse.setResponse("Hello World !" );*/
        return LOGGER.traceExit(nwgDemoService.createNwgDemoRequest(applicationRequest));
    }

}



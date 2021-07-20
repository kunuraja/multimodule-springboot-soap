package nwg.service;

import nwg.schema.nwgdemorequest.NwgDemoRequest;
import nwg.schema.nwgdemoresponse.NwgDemoResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class NwgDemoServiceImpl implements NwgDemoService {

    private static final Logger LOGGER = LogManager.getLogger(NwgDemoServiceImpl.class);
    //@Autowired
    // private DataSource dataSource;

    @Override
    public NwgDemoResponse createNwgDemoRequest(NwgDemoRequest applicationRequest) {
        LOGGER.traceEntry();
        NwgDemoResponse nwgDemoResponse = new NwgDemoResponse();
        String ds = null;
       /* try {
            ResultSet rs = dataSource.getConnection().
                    prepareStatement("select * from ACT01 where mortgage = 'M029000'")
                                     .executeQuery();
            rs.next();
            ds = rs.getString("ASLNAME");
            System.out.println(ds);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        LOGGER.info("Testing logging");
        nwgDemoResponse.setCompany(applicationRequest.getCompany());
        nwgDemoResponse.setResponse("Hello World !");
        return LOGGER.traceExit(nwgDemoResponse);

    }
}

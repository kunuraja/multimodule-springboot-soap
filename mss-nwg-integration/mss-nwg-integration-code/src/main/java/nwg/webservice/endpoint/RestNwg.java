package nwg.webservice.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestNwg {

    @GetMapping(value = "/restNwg")
    public String restNwg() {
        return "Rest Nwg endpoint";
    }

}

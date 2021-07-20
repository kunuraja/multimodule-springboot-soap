package nwg.configuration;

import nwg.webservice.endpoint.NwgDemoEndpoint;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    //private static final Logger LOGGER = LogManager.getLogger(WebServiceConfig.class);

    //private static final String COMMON_TYPES_XSD_FILE = "CommonTypes.xsd";

    private static final String WEB_SERVICE_URL_SUFFIX = "/ws/";

    private static final String URL_MAPPINGS = WEB_SERVICE_URL_SUFFIX + "*";

    private static final String NWG_DEMO_REQUEST_XSD_FILE = "NwgDemoRequest.xsd";

    private static final String NWG_DEMO_RESPONSE_XSD_FILE = "NwgDemoResponse.xsd";

    private static final String NWG_DEMO_RESPONSE_SUFFIX = "Search";

    private static final String NWG_DEMO_WSDL = "NwgDemo";

    public static final String NWG_DEMO_URL_SUFFIX = WEB_SERVICE_URL_SUFFIX + "NwgDemo";

    private static final String NWG_DEMO_PORT = "NwgDemoPort";

   // private static final String CREDIT_SEARCH_REQUEST_PACKAGE = "ais.creditsearch.webservice.creditsearch.performcreditsearch";

   // private static final String CREDIT_SEARCH_RESPONSE_PACKAGE = "ais.creditsearch.webservice.creditsearch.performcreditsearchresponse";

    //public static final String CREDIT_SEARCH_REQUEST_MARSHALLER = CREDIT_SEARCH_REQUEST_PACKAGE + " marshaller";

   // public static final String CREDIT_SEARCH_RESPONSE_UNMARSHALLER = CREDIT_SEARCH_RESPONSE_PACKAGE + " unmarshaller";

   // private static final String CREATE_DE_DECISION_HISTORY_REQUEST_XSD_FILE = "CreateDEDecisionHistoryRequest.xsd";

    //private static final String CREATE_DE_DECISION_HISTORY_RESPONSE_XSD_FILE = "CreateDEDecisionHistoryResponse.xsd";

    //private static final String CREATE_DE_DECISION_HISTORY_SERVICE_WSDL = "CreateDEDecisionHistoryService";

    //public static final String CREATE_DE_DECISION_HISTORY_URL_SUFFIX = WEB_SERVICE_URL_SUFFIX
                                                                     //  + "CreateDEDecisionHistoryRequest";

   // private static final String CREATE_DE_DECISION_HISTORY_PORT = "CreateDEDecisionHistory";

   // private static final String STATUS_CHECK_REQUEST_XSD_FILE = "PerformDEStatusCheckRequest.xsd";

   // private static final String STATUS_CHECK_RESPONSE_XSD_FILE = "PerformDEStatusCheckResponse.xsd";

   // private static final String STATUS_CHECK_RESPONSE_SUFFIX = "Check";

    //private static final String STATUS_CHECK_WSDL = "statusCheck";

    //public static final String STATUS_CHECK_URL_SUFFIX = WEB_SERVICE_URL_SUFFIX + "PerformDEStatusCheck";

    //private static final String STATUS_CHECK_PORT = "PerformStatusCheckPort";

   // private static final String DE_SAVE_REQUEST_XSD_FILE = "PerformDESaveRequest.xsd";

   // private static final String DE_SAVE_RESPONSE_XSD_FILE = "PerformDESaveResponse.xsd";

   // private static final String DE_SAVE_RESPONSE_SUFFIX = "Save";

   // private static final String DE_SAVE_WSDL = "DESave";

    //public static final String DE_SAVE_URL_SUFFIX = WEB_SERVICE_URL_SUFFIX + "PerformDESave";

   // private static final String DE_SAVE_PORT = "PerformDESavePort";

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(
            ApplicationContext applicationContext) {
        //LOGGER.traceEntry();
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(applicationContext);
        messageDispatcherServlet.setTransformWsdlLocations(true);/**/
        //messageDispatcherServlet.setEnableLoggingRequestDetails(true);
        return new ServletRegistrationBean(messageDispatcherServlet, URL_MAPPINGS);
        //return LOGGER
        // .traceExit(new ServletRegistrationBean<MessageDispatcherServlet>(messageDispatcherServlet, URL_MAPPINGS));
    }

    @Bean(name = NWG_DEMO_WSDL)
    public DefaultWsdl11Definition nwgDemoWsdl() {
        //LOGGER.traceEntry();
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName(NWG_DEMO_PORT);
        wsdl11Definition.setLocationUri(NWG_DEMO_URL_SUFFIX);
        wsdl11Definition.setTargetNamespace(NwgDemoEndpoint.NAMESPACE_URI);
        wsdl11Definition.setSchemaCollection(nwgDemoSchema());
        // The following line is required because the top-level element in the request
        // does not end in the standard 'Request' suffix. Without this line the sample
        // requests generated in SoapUI contain the response rather than the request.
        wsdl11Definition.setResponseSuffix(NWG_DEMO_RESPONSE_SUFFIX);
        return wsdl11Definition;
    }

   /* @Bean(name = CREATE_DE_DECISION_HISTORY_SERVICE_WSDL)
    public DefaultWsdl11Definition createDEDecisionHistoryServiceWsdl() {
        LOGGER.traceEntry();
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName(CREATE_DE_DECISION_HISTORY_PORT);
        wsdl11Definition.setLocationUri(CREATE_DE_DECISION_HISTORY_URL_SUFFIX);
        wsdl11Definition.setTargetNamespace(CreateDEDecisionHistoryEndpoint.NAMESPACE_URI);
        wsdl11Definition.setSchemaCollection(createDEDecisionHistorySchemaCollection());
        return LOGGER.traceExit(wsdl11Definition);
    }

    @Bean(name = STATUS_CHECK_WSDL)
    public DefaultWsdl11Definition deStatusCheckWsdl() {
        LOGGER.traceEntry();
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName(STATUS_CHECK_PORT);
        wsdl11Definition.setLocationUri(STATUS_CHECK_URL_SUFFIX);
        wsdl11Definition.setTargetNamespace(PerformDEStatusCheckEndpoint.NAMESPACE_URI);
        wsdl11Definition.setSchemaCollection(deStatusCheckSchemaCollection());
        wsdl11Definition.setResponseSuffix(STATUS_CHECK_RESPONSE_SUFFIX);
        return LOGGER.traceExit(wsdl11Definition);
    }

    @Bean(name = DE_SAVE_WSDL)
    public DefaultWsdl11Definition deSaveWsdl() {
        LOGGER.traceEntry();
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName(DE_SAVE_PORT);
        wsdl11Definition.setLocationUri(DE_SAVE_URL_SUFFIX);
        wsdl11Definition.setTargetNamespace(PerformDESaveEndpoint.NAMESPACE_URI);
        wsdl11Definition.setSchemaCollection(deSaveSchemaCollection());
        wsdl11Definition.setResponseSuffix(DE_SAVE_RESPONSE_SUFFIX);
        return LOGGER.traceExit(wsdl11Definition);
    }*/

   /* @Bean
    public XsdSchemaCollection createDEDecisionHistorySchemaCollection() {
        LOGGER.traceEntry();
        CommonsXsdSchemaCollection commonsXsdSchemaCollection = new CommonsXsdSchemaCollection(
                new ClassPathResource(CREATE_DE_DECISION_HISTORY_REQUEST_XSD_FILE),
                new ClassPathResource(CREATE_DE_DECISION_HISTORY_RESPONSE_XSD_FILE));
        commonsXsdSchemaCollection.setInline(true);
        return LOGGER.traceExit(commonsXsdSchemaCollection);
    }

    @Bean
    public XsdSchemaCollection completeSchemaCollection() {
        LOGGER.traceEntry();
        CommonsXsdSchemaCollection commonsXsdSchemaCollection = new CommonsXsdSchemaCollection(
                new ClassPathResource(COMMON_TYPES_XSD_FILE),
                new ClassPathResource(CREATE_DE_DECISION_HISTORY_REQUEST_XSD_FILE),
                new ClassPathResource(CREATE_DE_DECISION_HISTORY_RESPONSE_XSD_FILE),
                new ClassPathResource(CREDIT_SEARCH_REQUEST_XSD_FILE), new ClassPathResource(CREDIT_SEARCH_RESPONSE_XSD_FILE),
                new ClassPathResource(STATUS_CHECK_REQUEST_XSD_FILE), new ClassPathResource(STATUS_CHECK_RESPONSE_XSD_FILE),
                new ClassPathResource(DE_SAVE_REQUEST_XSD_FILE), new ClassPathResource(DE_SAVE_RESPONSE_XSD_FILE));
        commonsXsdSchemaCollection.setInline(true);
        return LOGGER.traceExit(commonsXsdSchemaCollection);
    }*/

    @Bean
    public XsdSchemaCollection nwgDemoSchema() {
        // LOGGER.traceEntry();
        CommonsXsdSchemaCollection commonsXsdSchemaCollection = new CommonsXsdSchemaCollection(
                new ClassPathResource(NWG_DEMO_REQUEST_XSD_FILE), new ClassPathResource(NWG_DEMO_RESPONSE_XSD_FILE));
        commonsXsdSchemaCollection.setInline(true);
        return (commonsXsdSchemaCollection);
    }

   /* @Bean
    public XsdSchemaCollection deStatusCheckSchemaCollection() {
        LOGGER.traceEntry();
        CommonsXsdSchemaCollection commonsXsdSchemaCollection = new CommonsXsdSchemaCollection(
                new ClassPathResource(STATUS_CHECK_REQUEST_XSD_FILE), new ClassPathResource(STATUS_CHECK_RESPONSE_XSD_FILE));
        commonsXsdSchemaCollection.setInline(true);
        return LOGGER.traceExit(commonsXsdSchemaCollection);
    }

    @Bean
    public XsdSchemaCollection deSaveSchemaCollection() {
        LOGGER.traceEntry();
        CommonsXsdSchemaCollection commonsXsdSchemaCollection = new CommonsXsdSchemaCollection(
                new ClassPathResource(DE_SAVE_REQUEST_XSD_FILE), new ClassPathResource(DE_SAVE_RESPONSE_XSD_FILE));
        commonsXsdSchemaCollection.setInline(true);
        return LOGGER.traceExit(commonsXsdSchemaCollection);
    }

    @Bean(name = CREDIT_SEARCH_REQUEST_MARSHALLER)
    public Jaxb2Marshaller createSearchRequestMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(CREDIT_SEARCH_REQUEST_PACKAGE);
        return marshaller;
    }

    @Bean(name = CREDIT_SEARCH_RESPONSE_UNMARSHALLER)
    public Jaxb2Marshaller createSearchResponseUnmarshaller() {
        Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
        unmarshaller.setContextPath(CREDIT_SEARCH_RESPONSE_PACKAGE);
        return unmarshaller;
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        LOGGER.traceEntry();
        interceptors.add(getCustomPayloadInterceptor());
        LOGGER.traceExit();
    }

    private PayloadValidatingInterceptor getCustomPayloadInterceptor() {
        CustomValidationInterceptor validatingInterceptor = new CustomValidationInterceptor();
        validatingInterceptor.setValidateRequest(true);
        validatingInterceptor.setValidateResponse(true);
        validatingInterceptor.setXsdSchemaCollection(completeSchemaCollection());
        return validatingInterceptor;
    }*/

}

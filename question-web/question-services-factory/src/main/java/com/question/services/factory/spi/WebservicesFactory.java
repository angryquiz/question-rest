package com.question.services.factory.spi;


public class WebservicesFactory {
    public static Webservices getInstance(WebservicesType type) {
        Webservices provider = null;
        switch (type) {
        case GLASSSFISH_JERSEY_REST_CLIENT:
        	provider = new GlassfishJerseyRestImpl();
            break;
            
        case GLASSSFISH_JERSEY_JACKSON_REST_CLIENT:
        	provider = new GlassfishJerseyJacksonRestImpl();
            break;            
              
        case SOAP:
        	provider = new SoapImpl();
            break;
            
        case JERSEY_REST_CLIENT:
        	provider = new JerseyRestImpl();
            break;            
            
        default:
            // throw some exception
            break;
        }
        return provider;
    }
}
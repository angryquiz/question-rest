package com.question.rest.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.question.rest.filter.CheckRequestFilter;
import com.question.rest.filter.ResponseFilter;
import com.question.rest.mapper.EntityExceptionMapper;

@ApplicationPath("resources")
public class BaseResourceConfig extends ResourceConfig {

    public BaseResourceConfig() {
        final String myRestPackage = "com.question.rest.resource";
        final String jacksonPackage = "org.codehaus.jackson.jaxrs";

        final String swaggerJaxrsJsonPackage = "com.wordnik.swagger.jaxrs.json";
        final String swaggerJaxrsListingPackage = "com.wordnik.swagger.jaxrs.listing";

        packages(swaggerJaxrsJsonPackage, swaggerJaxrsListingPackage, jacksonPackage, myRestPackage);

        // enable multipart
        register(MultiPartFeature.class);
        register(EntityExceptionMapper.class);
        register(ResponseFilter.class);
        register(CheckRequestFilter.class);

        
    }
}

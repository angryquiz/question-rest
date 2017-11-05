package com.question.services.factory.spi;

import java.io.File;


public abstract class Webservices {
	
    public Webservices(WebservicesType type) {
        this.type = type;
        initialize();
    }
 
    private void initialize() {
        // Do one time processing here
    }
 
    // Do subclass level processing in this method
    protected abstract void construct();
 
    private WebservicesType type = null;
 
    public WebservicesType getType() {
        return type;
    }
 
    public void setType(WebservicesType type) {
        this.type = type;
    }
    
    public abstract <T> T get(String url, Class<T> clazz);
    
    public abstract <T> T get(String baseUrl, String pathUrl, Class<T> clazz);
    
    public abstract <T> T put(String url, File fileToUpload, Class<T> clazz);
    
    
    
}
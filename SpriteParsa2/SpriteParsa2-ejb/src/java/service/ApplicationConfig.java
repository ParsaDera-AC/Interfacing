package service;

import java.util.Set;

/**
 * @ApplicationPath: This annotation identifies the application path that serves as the base URI for all resources.
 * 
 * This class is part of the generated configurations when implementing the REST interface
 * @author parsa
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
    }
    
}

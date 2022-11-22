
package service;

import cst8218.dera0014.entity.Sprite;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This is the most important class of RESTfUL API. It adds support to RESTFUL API in many ways and it includes helping operations like:
 * 1. Creating sprite entities
 * 2. Finding sprite entities 
 * 3. Deleting sprite entities and etc
 * @author parsa
 */
@javax.ejb.Stateless
@javax.ws.rs.Path("cst8218.dera0014.entity.sprite")
public class SpriteFacadeREST extends AbstractFacade<Sprite> {
   
    @PersistenceContext(unitName = "SpriteParsa2-ejbPU")
    private EntityManager em;
    
    /**
     * The Default Constructor
     */
    public SpriteFacadeREST() {
        super(Sprite.class);
    }
    /**
     * This method creates a new sprite entity 
     * @param entity is the Sprite entity 
     */
    @javax.ws.rs.POST
    @Override
    @javax.ws.rs.Consumes({javax.ws.rs.core.MediaType.APPLICATION_XML, javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public void create(Sprite entity) {
        super.create(entity);
    }
    
    /**
     * Same as above method but this one updates an existing entity , first it finds the sprite by ID then it updates it
     * @param id of the sprite that is being updated
     * @param entity the sprite entity that is updated 
     * @return an HTTP response
     */
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response post(@PathParam("id") Long id,Sprite entity) {

        Sprite sprite = super.find(id);
        sprite.update(entity);
        return Response.status(Response.Status.OK).entity(sprite).build();
            
    }
    /**
     * This method is responsible for overwriting an existing entity, first it finds the sprite by ID then it updates it
     * @param id of the sprite that is being updated
     * @param entity the sprite entity that is updated 
     * @return the updated new sprite
     */
    @javax.ws.rs.PUT
    @javax.ws.rs.Path("{id}")
    @javax.ws.rs.Consumes({javax.ws.rs.core.MediaType.APPLICATION_XML, javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public Sprite edit(@javax.ws.rs.PathParam("id") Long id, Sprite entity) {
        Sprite sprite = super.find(id);
        sprite.update(entity);
        return sprite;
    }

    
    
    /**
     * removes a sprite entity , if it is found by id
     * @param id of the sprite entity that is about to be deleted
     */
    @javax.ws.rs.DELETE
    @javax.ws.rs.Path("{id}")
    public void remove(@javax.ws.rs.PathParam("id") Long id) {
        super.remove(super.find(id));
    }
    /**
     * Searches(gets) an entity by searching for the id of it. 
     * @param id of the sprite that is being searched for
     * @return id of the found sprite
     */
    @javax.ws.rs.GET
    @javax.ws.rs.Path("{id}")
    @javax.ws.rs.Produces({javax.ws.rs.core.MediaType.APPLICATION_XML, javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public Sprite find(@javax.ws.rs.PathParam("id") Long id) {
        return super.find(id);
    }
    /**
     * It gets all the sprite entities existing and all the ones that we have created 
     * @return list of the sprite entities 
     */
    @javax.ws.rs.GET
    @Override
    @javax.ws.rs.Produces({javax.ws.rs.core.MediaType.APPLICATION_XML, javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public List<Sprite> findAll() {
        return super.findAll();
    }
    /**
     * This method is responsible for getting the sprite entities within a certain range. The range is determined by from and to
     * @param from is the starting point of the range 
     * @param to is the upper bound of the range 
     * @return a list which included the specified limited sprite entities
     */
    @javax.ws.rs.GET
    @javax.ws.rs.Path("{from}/{to}")
    @javax.ws.rs.Produces({javax.ws.rs.core.MediaType.APPLICATION_XML, javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public List<Sprite> findRange(@javax.ws.rs.PathParam("from") Integer from, @javax.ws.rs.PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    /**
     * This method is responsible for counting the number of all the current sprite entities
     * @return a number which is the number of all available existing sprite entities 
     */
    @javax.ws.rs.GET
    @javax.ws.rs.Path("count")
    @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    /**
     * Is responsible for returning the entity manager of the entity class of the Sprite
     * @return the entity manager
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

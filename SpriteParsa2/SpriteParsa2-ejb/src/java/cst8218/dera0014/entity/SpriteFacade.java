package cst8218.dera0014.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *      This class is responsible for returning the entity manager for the
 *      persistent unit which is called SpriteParsa2-ejbPU. 
 *      This class is essentially the "facade" class for the entities of the sprite
 * @author parsa
 */
@Stateless
public class SpriteFacade extends AbstractFacade<Sprite> {
    @PersistenceContext(unitName = "SpriteParsa2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SpriteFacade() {
        super(Sprite.class);
    }
    
}

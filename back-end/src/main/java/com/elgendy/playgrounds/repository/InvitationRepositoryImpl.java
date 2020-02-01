package com.elgendy.playgrounds.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elgendy.playgrounds.model.Invitation;

@Repository
public class InvitationRepositoryImpl implements InvitationRepository{

	
	@PersistenceContext
    private EntityManager em;

    @Autowired
    public InvitationRepositoryImpl(EntityManager em) {
        this.em = em;
    }
    
	@Override
	public List<Invitation> findAll() {
		List<Invitation> invitations = null;
        try{
        	invitations = em.createQuery("From Invitation", Invitation.class).getResultList();
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return invitations;	
	}

	@Override
	public Invitation findById(Integer id) {
		Invitation invitationById = null;
        try{
        	invitationById = em.find(Invitation.class, id);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return invitationById;
	}

	@Override
	public void save(Invitation invitation) {
		try{
            em.persist(invitation);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
		
	}

	@Override
	public void update(Invitation invitation) {
		try{
            em.merge(invitation);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
		
	}

	@Override
	public void delete(Invitation invitation) {
		try{
            if(em.contains(invitation)){
                em.remove(invitation);
            }else {
                throw new RuntimeException("photo You Want To Delete is Not Found!");
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
		
	}

}

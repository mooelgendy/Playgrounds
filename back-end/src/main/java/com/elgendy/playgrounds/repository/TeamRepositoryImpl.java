package com.elgendy.playgrounds.repository;

import com.elgendy.playgrounds.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TeamRepositoryImpl implements TeamRepository {

    @PersistenceContext
    EntityManager em;

    @Autowired
    public TeamRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Team> findAll() {
        List<Team> teams = null;
        try{
            teams = em.createQuery("From Team", Team.class).getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return teams;
    }

    @Override
    public Team findById(Integer id) {
        Team teamById = null;
        try{
            teamById = em.find(Team.class, id);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return teamById;
    }

    @Override
    public void save(Team team) {
        try{
            em.persist(team);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Team team) {
        try{
            em.merge(team);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Team team) {
        try{
            if(em.contains(team)){
                em.remove(team);
            }else {
                throw new RuntimeException("Team You Want To Delete is Not Found!");
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}

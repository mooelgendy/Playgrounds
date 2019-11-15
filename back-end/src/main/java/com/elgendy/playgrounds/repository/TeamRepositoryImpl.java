package com.elgendy.playgrounds.repository;

import com.elgendy.playgrounds.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TeamRepositoryImpl implements TeamRepository {

    @PersistenceContext
    EntityManager em;

    @Autowired
    public TeamRepositoryImpl(EntityManager em) {
        Assert.notNull(em, "EntityManager must not be null!");
        this.em = em;
    }

    @Override
    public List<Team> findAll() {
        List<Team> teams = em.createQuery("From Team", Team.class).getResultList();
        Assert.notEmpty(teams, "List is Empty");
        return teams;
    }

    @Override
    public Team findById(Integer id) {
        Assert.notNull(id, "Team Id Must Not be Null!");
        Team teamById = em.find(Team.class, id);
        Assert.notNull(teamById, "Team Not Found!");
        return teamById;
    }

    @Override
    public void save(Team team) {
        Assert.notNull(team, "Team You Want To Save Must Not be Null");
        try{
            em.persist(team);
        } catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void update(Team team) {
        Assert.notNull(team, "Team You Want To update is Not Found!");
        try{
            em.merge(team);
        } catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void delete(Team team) {
        Assert.notNull(team, "Team You Want To Delete Must Not be Null");
        try{
            if(em.contains(team)){
                em.remove(team);
            }else {
                throw new RuntimeException("Team You Want To Delete is Not Found!");
            }
        } catch (Exception ex){
            System.out.println(ex.toString());
        }
    }
}

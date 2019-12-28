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
        this.em = em;
    }

    @Override
    public List<Team> findAll() {
        List<Team> teams = null;
        try{
            teams = em.createQuery("From Team", Team.class).getResultList();
            Assert.notEmpty(teams, "Team's List is Empty");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return teams;
    }

    @Override
    public Team findById(Integer id) {
        Team teamById = null;
        try{
            Assert.notNull(id, "Team Id Must Not be Null!");
            teamById = em.find(Team.class, id);
            Assert.notNull(teamById, "Team Not Found!");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return teamById;
    }

    @Override
    public void save(Team team) {
        try{
            Assert.notNull(team, "Team You Want To Save Must Not be Null");
            em.persist(team);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Team team) {
        try{
            Assert.notNull(team, "Team You Want To update is Not Found!");
            em.merge(team);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Team team) {
        try{
            Assert.notNull(team, "Team You Want To Delete Must Not be Null");
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

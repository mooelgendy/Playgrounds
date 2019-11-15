package com.elgendy.playgrounds.service;

import com.elgendy.playgrounds.model.Playground;
import com.elgendy.playgrounds.repository.PlaygroundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PlaygroundServiceImpl implements PlaygroundService {

    private PlaygroundRepository repository;

    @Autowired
    public PlaygroundServiceImpl(PlaygroundRepository repository) {
        Assert.notNull(repository, "Repository must not be null!");
        this.repository = repository;
    }

    @Override
    public List<Playground> getAll() throws RuntimeException {
        try {
            List<Playground> playgroundList = repository.findAll();
            return playgroundList;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    @Override
    public Playground getOne(Integer id) {
        try {
            Playground playgroundById = repository.findById(id);
            return playgroundById;
        }catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }

    @Override
    public void add(Playground playground) {
        Assert.notNull(playground, "Playground You Want To Save Must Not be Null!");
        try {
            repository.save(playground);
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void update(Playground playground) {
        Assert.notNull(playground, "playground You Want To Update Must Not be Null!");
        try {
            repository.update(playground);
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public void delete(Integer id) {
        Assert.notNull(id, "Playground Id Must Not be Null!");
        try {
            Playground deletedPlayground = repository.findById(id);
            Assert.notNull(deletedPlayground, "Playground You Want To Delete is Not Found!");
            repository.delete(deletedPlayground);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}

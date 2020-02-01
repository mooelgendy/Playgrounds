package com.elgendy.playgrounds.repository;

import java.util.List;

import com.elgendy.playgrounds.model.Invitation;

public interface InvitationRepository {

	List<Invitation> findAll();

	Invitation findById(Integer id);

    void save(Invitation invitation);

    void update(Invitation invitation);

    void delete(Invitation invitation);
}

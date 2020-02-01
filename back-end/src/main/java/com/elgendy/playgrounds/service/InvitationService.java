package com.elgendy.playgrounds.service;

import java.util.List;

import com.elgendy.playgrounds.model.Invitation;

public interface InvitationService {

	List<Invitation> getAll();

	Invitation getOne(Integer id);

    void add(Invitation invitation);

    void update(Invitation invitation);

    void delete(Integer id);
}

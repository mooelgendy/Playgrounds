package com.elgendy.playgrounds.model.DTO;

import java.util.Date;

public class InvitationDTO {

	private Integer id;

    private String name;

    private Date date;

    private String expiryDate;

	public InvitationDTO() {
		super();
	}

	public InvitationDTO(Integer id, String name, Date date, String expiryDate) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.expiryDate = expiryDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
}

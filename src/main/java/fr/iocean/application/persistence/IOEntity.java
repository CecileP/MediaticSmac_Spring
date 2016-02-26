package fr.iocean.application.persistence;

import java.io.Serializable;

public interface IOEntity extends Serializable{

	Long getId();
	void setId(Long id);
		
}

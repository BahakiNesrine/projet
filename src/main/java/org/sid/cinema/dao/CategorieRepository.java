package org.sid.cinema.dao;

import org.sid.cinema.entities.Categorie;
import org.sid.cinema.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// toutes les methodes hérité de JpaRepo sont accessible via un api rest
@RepositoryRestResource
public interface CategorieRepository extends JpaRepository<Categorie, Long>{
	
}

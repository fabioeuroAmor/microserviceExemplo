package br.com.code.microserviceExemplo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.code.microserviceExemplo.domain.Cidade;




@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
	
	
	@Query("FROM Cidade c WHERE LOWER(c.dcNome) like %:searchTerm% ")
	List<Cidade> search(@Param("searchTerm") String searchTerm);
	
	
	
	
	
	

}

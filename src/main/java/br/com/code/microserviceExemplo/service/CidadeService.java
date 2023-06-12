package br.com.code.microserviceExemplo.service;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.code.microserviceExemplo.domain.Cidade;
import br.com.code.microserviceExemplo.dto.CidadeDto;
import br.com.code.microserviceExemplo.dto.PaginatedResponseDto;
import br.com.code.microserviceExemplo.exception.BDException;
import br.com.code.microserviceExemplo.repository.CidadeRepository;


@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;	
	
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
	
	public CidadeDto inserirCidade(CidadeDto cidadeDto) throws BDException {
		
		try {
			ModelMapper modelMapper = new ModelMapper();
			
			var cidade = modelMapper.map(cidadeDto, Cidade.class);
			var cidadePersistida = cidadeRepository.save(cidade);
			
			cidadeDto  = modelMapper.map(cidadePersistida, CidadeDto.class);			
		} catch (Exception e) {
			
			throw new BDException(e.getMessage());	
		}
		return cidadeDto;
		
	}
	
	public CidadeDto buscarPorId(Integer id) throws BDException {
		
		
		try {
			var cidadePersistida = cidadeRepository.findById(id);
			var domain = cidadePersistida.get();
			
			ModelMapper modelMapper = new ModelMapper();
			var cidadeDto  = modelMapper.map(domain, CidadeDto.class);
			
			return cidadeDto;
			
		} catch (Exception e) {
			
			throw new BDException(e.getMessage());	
		}		

		
	}
	
	
	public Page<Cidade> buscarTodasCidades(int page, int size) throws BDException{
		
		
		try {
			
			PageRequest pageRequest = PageRequest.of(
	                page,
	                size,
	                Sort.Direction.ASC,
	                "dcNome");
			
						
			return new PageImpl<>(
					cidadeRepository.findAll(), 
	                pageRequest, size);
			
		} catch (Exception e) {
			
			throw new BDException(e.getMessage());	
		}
				
	}
	
	
	public PaginatedResponseDto search(String searchTerm, int page, int pageSize) throws BDException{
		
		try {
			
			List<Cidade> cidades =	cidadeRepository.search(searchTerm.toLowerCase());
			
			// Calcule o índice inicial e final com base na página e no tamanho da página
	        int startIndex = page * pageSize;

	        int endIndex = Math.min(startIndex + pageSize, cidades.size());
	        
	       // Crie a sublista com base nos índices calculados

	        List<Cidade> paginatedCidades = cidades.subList(startIndex, endIndex);
	        
	        // Crie o objeto PaginatedResponse e retorne-o
	        PaginatedResponseDto response = new PaginatedResponseDto();
	        

	        response.setData(paginatedCidades);

	        response.setPage(page);

	        response.setPageSize(pageSize);

	        response.setTotalElements(cidades.size());
	        
	        return response;
	        
		} catch (Exception e) {
			throw new BDException(e.getMessage());	
		}
		//return null;
		
	}
	

}

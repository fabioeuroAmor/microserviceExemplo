package br.com.code.microserviceExemplo.controller.v1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.code.microserviceExemplo.dto.CidadeDto;
import br.com.code.microserviceExemplo.json.Response;
import br.com.code.microserviceExemplo.service.CidadeService;




@RestController
@RequestMapping("/api/v1")
public class CidadeControllerV1{
	
	@Autowired
	CidadeService cidadeService;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@PostMapping(value = "/cidades")
	public ResponseEntity<Response> inserirCidade(@RequestParam(value = "dcTemperatura", required = true) Double dcTemperatura, @RequestParam(value = "v10m", required = true) Double v10m,
			@RequestParam(value = "dcNome", required = true) String dcNome) {
         
		 Response response = new Response();
         CidadeDto cidadeDto = new CidadeDto();
		
		try {	
			 //Exemplo Integracoes sincronas
//			 HttpHeaders headers = new HttpHeaders();
//		     headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		     HttpEntity <String> entity = new HttpEntity<String>(headers);
//		     
//		     restTemplate.exchange("http://localhost:8082/api/supporttables/v1/cidade/busca/todas", HttpMethod.GET, entity, String.class).getBody();
		      
		      
			cidadeDto.setDcTemperatura(dcTemperatura);
			cidadeDto.setV10m(v10m);
			cidadeDto.setDcNome(dcNome);
			
			response.setModeloRetorno(cidadeService.inserirCidade(cidadeDto));
			response.setMensagensRetorno("Cidade inserida com sucesso!");
			
		} catch (Exception e) {
			response.setMensagensRetorno(e.getMessage());	
           return  (ResponseEntity<Response>) ResponseEntity.status(500);
		}		
		
		return ResponseEntity.ok(response);
	}
	
	
	
	@GetMapping(value = "/cidades/{id}")
	public ResponseEntity<Response> buscarPorId(@PathVariable("id") Integer id) {
		
		Response response = new Response();
		
		
		try {			
			response.setModeloRetorno(cidadeService.buscarPorId(id));
			response.setMensagensRetorno("Cidade enconatda com id = " + id);
			
		} catch (Exception e) {
			response.setMensagensRetorno(e.getMessage());
			return (ResponseEntity<Response>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping(value = "/cidades")
	public ResponseEntity<Response> buscarTodasCidades(@RequestParam(value="page", required = false, defaultValue = "0")int page,@RequestParam(value="size", required = false, defaultValue = "10")int size) {
      
		Response response = new Response();
		
		try {			
			response.setModeloRetorno(cidadeService.buscarTodasCidades(page, size));
			response.setMensagensRetorno("Cidade buscarCidadesPorV10m() paginada!" );
			
		} catch (Exception e) {
			response.setMensagensRetorno(e.getMessage());	
			return (ResponseEntity<Response>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		return ResponseEntity.ok(response);
		
	}	
	
	
	@GetMapping(value = "/cidades/teste")
	public ResponseEntity<Response> buscarTodasCidadesPaginacaoNaMao(@RequestParam(value="searchTerm", required = false, defaultValue = "")String searchTerm,@RequestParam(value="page", required = false, defaultValue = "0")int page,
			@RequestParam(value="pageSize", required = false, defaultValue = "10")int pageSize){
      
		Response response = new Response();
		
		try {			
			response.setModeloRetorno(cidadeService.search(searchTerm, page, pageSize));
			response.setMensagensRetorno("Cidade buscarCidadesPorV10m() paginada!" );
			
		} catch (Exception e) {
			response.setMensagensRetorno(e.getMessage());
			return (ResponseEntity<Response>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		return ResponseEntity.ok(response);
		
	}	
}

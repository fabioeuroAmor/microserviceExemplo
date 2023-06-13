package br.com.code.microserviceExemplo.controller.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.code.microserviceExemplo.dto.CidadeDto;
import br.com.code.microserviceExemplo.json.Response;
import br.com.code.microserviceExemplo.service.CidadeService;




@RestController
@RequestMapping("/api/v2")
public class CidadeControllerV2{
	
	@Autowired
	CidadeService cidadeService;
	
	
	@PostMapping(value = "/cidades")
	public ResponseEntity<Response> inserirCidade(@RequestParam(value = "dcTemperatura", required = true) Double dcTemperatura, @RequestParam(value = "v10m", required = true) Double v10m,
			@RequestParam(value = "dcNome", required = true) String dcNome) throws Exception {
         
		 Response response = new Response();
         CidadeDto cidadeDto = new CidadeDto();
		
		try {		
			
			cidadeDto.setDcTemperatura(dcTemperatura);
			cidadeDto.setV10m(v10m);
			cidadeDto.setDcNome(dcNome);
			
			response.setModeloRetorno(cidadeService.inserirCidade(cidadeDto));
			response.setMensagensRetorno("Cidade inserida com sucesso!");
			
		} catch (Exception e) {
			response.setMensagensRetorno(e.getMessage());			
		}		
		
		return ResponseEntity.ok(response);
	}
	
	
	
	@GetMapping(value = "/cidades/{id}")
	public ResponseEntity<Response> buscarPorId(@PathVariable("id") Integer id) throws Exception {
		
		Response response = new Response();
		
		try {			
			response.setModeloRetorno(cidadeService.buscarPorId(id));
			response.setMensagensRetorno("Cidade enconatda com id = " + id);
			
		} catch (Exception e) {
			response.setMensagensRetorno(e.getMessage());			
		}		
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping(value = "/cidades")
	public ResponseEntity<Response> buscarTodasCidades(@RequestParam(value="page", required = false, defaultValue = "0")int page,@RequestParam(value="size", required = false, defaultValue = "10")int size) throws Exception {
      
		Response response = new Response();
		
		try {			
			response.setModeloRetorno(cidadeService.buscarTodasCidades(page, size));
			response.setMensagensRetorno("Cidade buscarCidadesPorV10m() paginada!" );
			
		} catch (Exception e) {
			response.setMensagensRetorno(e.getMessage());			
		}		
		
		return ResponseEntity.ok(response);
		
	}	
	
	
	@GetMapping(value = "/cidades/teste")
	public ResponseEntity<Response> buscarTodasCidadesPaginacaoNaMao(@RequestParam(value="searchTerm", required = false, defaultValue = "")String searchTerm,@RequestParam(value="page", required = false, defaultValue = "0")int page,
			@RequestParam(value="pageSize", required = false, defaultValue = "10")int pageSize) throws Exception {
      
		Response response = new Response();
		
		try {			
			response.setModeloRetorno(cidadeService.search(searchTerm, page, pageSize));
			response.setMensagensRetorno("Cidade buscarCidadesPorV10m() paginada!" );
			
		} catch (Exception e) {
			response.setMensagensRetorno(e.getMessage());			
		}		
		
		return ResponseEntity.ok(response);
		
	}	
}

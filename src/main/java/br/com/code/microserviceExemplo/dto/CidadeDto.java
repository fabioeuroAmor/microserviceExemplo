package br.com.code.microserviceExemplo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CidadeDto {
	
	
	Integer idCidade;
	
	Double dcTemperatura;
	
	Double v10m;	
	
	String dcNome;

}

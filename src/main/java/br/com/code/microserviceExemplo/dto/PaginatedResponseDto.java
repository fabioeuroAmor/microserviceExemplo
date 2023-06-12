package br.com.code.microserviceExemplo.dto;

import java.util.List;

import br.com.code.microserviceExemplo.domain.Cidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponseDto {
	
	private List<Cidade> data;

    private int page;

    private int pageSize;

    private long totalElements;

}

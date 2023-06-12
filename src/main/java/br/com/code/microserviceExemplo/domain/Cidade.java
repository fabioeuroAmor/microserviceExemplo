package br.com.code.microserviceExemplo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="TBL_CIDADE")
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@SequenceGenerator(name="tbl_cidade_id_cidade_seq", sequenceName="tbl_cidade_id_cidade_seq",initialValue=1, allocationSize=1)
public class Cidade implements Serializable{
	
	
	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tbl_cidade_id_cidade_seq")
	@Column(name="id_cidade")
	Integer idCidade;
	
	@Column(name="dc_temperatura")
	Double dcTemperatura;
	
	@Column(name="v10m")
	Double v10m;
	
	@Column(name="dc_nome")
	String dcNome;
	

}

package microservico.fornecedor.data.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import microservico.fornecedor.entity.Anuncio;

@JsonPropertyOrder({"id","nomeFornecedor","texto","imagem","noVisualizacao"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AnuncioVO extends RepresentationModel<AnuncioVO> implements Serializable{

	private static final long serialVersionUID = 2313505221600732814L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nomeFornecedor")
	private String nomeFornecedor;
	
	@JsonProperty("texto")
	private String texto;
	
	@JsonProperty("imagem")
	private String imagem;
	
	@JsonProperty("noVisualizacao")
	private int noVisualizacao;
	
	public static AnuncioVO create(Anuncio anuncio) {
		return new ModelMapper().map(anuncio, AnuncioVO.class);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public int getNoVisualizacao() {
		return noVisualizacao;
	}

	public void setNoVisualizacao(int noVisualizacao) {
		this.noVisualizacao = noVisualizacao;
	}

		
}

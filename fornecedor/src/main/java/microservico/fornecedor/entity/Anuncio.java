package microservico.fornecedor.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import microservico.fornecedor.data.vo.AnuncioVO;

@Entity
@Table(name="anuncio")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Anuncio implements Serializable  {

	private static final long serialVersionUID = 7262271175404640748L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nomeFornecedor", nullable=false, length=255)
	private String nomeFornecedor;
	
	@Column(name="texto", nullable=false, length=255)
	private String texto;
	
	@Column(name="imagem", nullable=false, length=255)
	private String imagem;
	
	@Column(name="noVisualizacao", nullable=false, length=10)
	private int noVisualizacao;
	
	public static Anuncio create(AnuncioVO anuncioVO) {
		return new ModelMapper().map(anuncioVO, Anuncio.class);
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

package microservico.cliente.entity;

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
import microservico.cliente.data.vo.UsuarioVO;

@Entity
@Table(name="usuario")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Usuario implements Serializable  {

	private static final long serialVersionUID = 7262271175404640748L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome", nullable=false, length=255)
	private String nome;
	
	@Column(name="email", nullable=false, length=255)
	private String email;
	
	@Column(name="senha", nullable=false, length=10)
	private String senha;
	
	@Column(name="distancia", nullable=false, length=10)
	private int distancia;
	
	public static Usuario create(UsuarioVO UsuarioVO) {
		return new ModelMapper().map(UsuarioVO, Usuario.class);
	}
	
}

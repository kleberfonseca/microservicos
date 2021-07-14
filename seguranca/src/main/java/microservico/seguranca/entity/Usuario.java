package microservico.seguranca.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="usuario")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Usuario implements UserDetails, Serializable  {

	private static final long serialVersionUID = 7262271175404640748L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="userName", unique=true)
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="accountNonExpired")
	private Boolean accountNonExpired;
	
	@Column(name="accountNonLocked")
	private Boolean accountNonLocked;
	
	@Column(name="credentialsNonExpired")
	private Boolean credentialsNonExpired;
	
	@Column(name="enabled")
	private Boolean enabled;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="permissaoUsuario", joinColumns = {@JoinColumn(name="id_usuario")},
	inverseJoinColumns = {@JoinColumn(name="id_permissao")})
	private List<Permissao> permissoes; 
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissoes;
	}
	
	public List<String> getRoles(){
		List<String> roles = new ArrayList<>();
		this.permissoes.stream()
			.forEach(p->{
				roles.add(p.getDescricao());
			});
		return roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
		
}

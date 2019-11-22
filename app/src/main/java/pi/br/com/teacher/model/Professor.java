package pi.br.com.teacher.model;

public class Professor {
	private float id;
	private String nome;
	private String email;
	private String senha;


	// Getter Methods

	public float getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	// Setter Methods

	public void setId( float id ) {
		this.id = id;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public void setSenha( String senha ) {
		this.senha = senha;
	}
}

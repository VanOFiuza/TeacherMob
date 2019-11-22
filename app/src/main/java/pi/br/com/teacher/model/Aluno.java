package pi.br.com.teacher.model;

import java.io.Serializable;
import java.util.List;


public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	private long ra;


	private String nome;


	private String senha;


	private String email;


	//private Turma turma;
	

	private List<Aula> aulas;

	

	public long getRa() {
		return this.ra;
	}

	public void setRa(long ra) {
		this.ra = ra;
	}

	public String getNome() {
		return this.nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public Turma getTurma() {
//		return this.turma;
//	}
//
//	public void setTurma(Turma turma) {
//		this.turma = turma;
//	}

	public List<Aula> getAulas() {
		return this.aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

}

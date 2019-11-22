package pi.br.com.teacher.model;

import java.io.Serializable;
import java.util.List;


public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;


	private String nome;


//	List<Turma> turmas;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	public List<Turma> getTurmas() {
//		return this.turmas;
//	}

//	public void setTurmas(List<Turma> turmas) {
//		this.turmas = turmas;
//	}

}

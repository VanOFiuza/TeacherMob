package pi.br.com.teacher.model;

import java.util.ArrayList;

import pi.br.com.teacher.model.Professor;

public class Disciplina {
	private float id;
	private DisciplinaDetalhe disciplinaDetalhe;
	private Professor professor;
	private String diasDaSemana;
	private ArrayList<Aula> aulas = new ArrayList<Aula>();
	private String unidade;


	// Getter Methods

	public float getId() {
		return id;
	}


	public String getDiasDaSemana() {
		return diasDaSemana;
	}

	public String getUnidade() {
		return unidade;
	}

	// Setter Methods

	public void setId( float id ) {
		this.id = id;
	}



	public void setDiasDaSemana( String diasDaSemana ) {
		this.diasDaSemana = diasDaSemana;
	}

	public void setUnidade( String unidade ) {
		this.unidade = unidade;
	}


	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public ArrayList<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(ArrayList<Aula> aulas) {
		this.aulas = aulas;
	}


	public DisciplinaDetalhe getDisciplinaDetalhe() {
		return disciplinaDetalhe;
	}

	public void setDisciplinaDetalhe(DisciplinaDetalhe disciplinaDetalhe) {
		this.disciplinaDetalhe = disciplinaDetalhe;
	}
}

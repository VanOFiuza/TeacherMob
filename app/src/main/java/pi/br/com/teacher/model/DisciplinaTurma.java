package pi.br.com.teacher.model;


import java.io.Serializable;
import java.util.List;

public class DisciplinaTurma implements Serializable {

	private static final long serialVersionUID = 1L;


	private long id;


//	private Turma turma;


	private Disciplina disciplina;

	private Professor professor;
	

	private String diaDaSemana;
	

	private List<Aula> aulas;
	

	private String unidade;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	public Turma getTurma() {
//		return this.turma;
//	}
//
//	public void setTurma(Turma turma) {
//		this.turma = turma;
//	}

	public Disciplina getDisciplina() {
		return this.disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getDiaDaSemana() {
		return this.diaDaSemana;
	}

	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public List<Aula> getAulas() {
		return this.aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	public String getUnidade() {
		return this.unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
}

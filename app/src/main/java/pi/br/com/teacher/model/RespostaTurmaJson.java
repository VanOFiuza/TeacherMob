package pi.br.com.teacher.model;

public class RespostaTurmaJson {
	private Disciplina disciplina;

	private String turma;

	public Disciplina getDisciplina ()
	{
		return disciplina;
	}

	public void setDisciplina (Disciplina disciplina)
	{
		this.disciplina = disciplina;
	}

	public String getTurma ()
	{
		return turma;
	}

	public void setTurma (String turma)
	{
		this.turma = turma;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [disciplina = "+disciplina+", turma = "+turma+"]";
	}
}

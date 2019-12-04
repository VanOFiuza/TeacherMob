package pi.br.com.teacher.model;

import java.io.Serializable;

public class Aula implements  Serializable
{
	private String data;

	private String horario;

	private String ocorrida;

	private String id;

	public String getData ()
	{
		return data;
	}

	public void setData (String data)
	{
		this.data = data;
	}

	public String getHorario ()
	{
		return horario;
	}

	public void setHorario (String horario)
	{
		this.horario = horario;
	}

	public String getOcorrida ()
	{
		return ocorrida;
	}

	public void setOcorrida (String ocorrida)
	{
		this.ocorrida = ocorrida;
	}

	public String getId ()
	{
		return id;
	}

	public void setId (String id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [data = "+data+", horario = "+horario+", ocorrida = "+ocorrida+", id = "+id+"]";
	}
}

package pi.br.com.teacher.model;

public class DisciplinaDetalhe {
    private String nome;

    private String id;

    public String getNome ()
    {
        return nome;
    }

    public void setNome (String nome)
    {
        this.nome = nome;
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
        return "ClassPojo [nome = "+nome+", id = "+id+"]";
    }
}

package pi.br.com.teacher.model;

import java.io.Serializable;

public class AlunoSession implements Serializable {

    private int ra;
    private String nome;
    private String email;
    private String id_turma;

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId_turma() {
        return id_turma;
    }

    public void setId_turma(String id_turma) {
        this.id_turma = id_turma;
    }
}

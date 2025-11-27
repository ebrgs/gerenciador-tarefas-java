public class Tarefa {
    private int id;
    private String descricao;
    private Boolean concluida;

    public Tarefa(String descricao, boolean concluida) {
        this.descricao = descricao;
        this.concluida = concluida; // Toda atividade começa não concluida
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean setConcluida(boolean concluida) {
        return this.concluida = concluida;
    }
}

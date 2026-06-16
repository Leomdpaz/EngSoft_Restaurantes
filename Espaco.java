public class Espaco {

    private int id;

    private String nome;

    private String tipo;

    private int capacidade;

    public Espaco(int id,
                  String nome,
                  String tipo,
                  int capacidade) {

        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.capacidade = capacidade;
    }

    public Espaco(String nome,
                  String tipo,
                  int capacidade) {

        this.nome = nome;
        this.tipo = tipo;
        this.capacidade = capacidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    @Override
    public String toString() {

        return "\nID: " + id +
                "\nNome: " + nome +
                "\nTipo: " + tipo +
                "\nCapacidade: " + capacidade +
                "\n---------------------------";
    }
}
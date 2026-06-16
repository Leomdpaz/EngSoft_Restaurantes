public class Reserva {

    private int id;

    private String cliente;

    private int quantidadePessoas;

    private String tipoEspaco;

    private int espacoId;

    private String dataReserva;

    private String horarioInicio;

    private String horarioFim;

    private String requerimentos;

    public Reserva(int id,
                   String cliente,
                   int quantidadePessoas,
                   String tipoEspaco,
                   int espacoId,
                   String dataReserva,
                   String horarioInicio,
                   String horarioFim,
                   String requerimentos) {

        this.id = id;
        this.cliente = cliente;
        this.quantidadePessoas = quantidadePessoas;
        this.tipoEspaco = tipoEspaco;
        this.espacoId = espacoId;
        this.dataReserva = dataReserva;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.requerimentos = requerimentos;
    }

    public Reserva(String cliente,
                   int quantidadePessoas,
                   String tipoEspaco,
                   int espacoId,
                   String dataReserva,
                   String horarioInicio,
                   String horarioFim,
                   String requerimentos) {

        this.cliente = cliente;
        this.quantidadePessoas = quantidadePessoas;
        this.tipoEspaco = tipoEspaco;
        this.espacoId = espacoId;
        this.dataReserva = dataReserva;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.requerimentos = requerimentos;
    }

    public int getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public String getTipoEspaco() {
        return tipoEspaco;
    }

    public int getEspacoId() {
        return espacoId;
    }

    public String getDataReserva() {
        return dataReserva;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public String getRequerimentos() {
        return requerimentos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setQuantidadePessoas(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public void setTipoEspaco(String tipoEspaco) {
        this.tipoEspaco = tipoEspaco;
    }

    public void setEspacoId(int espacoId) {
        this.espacoId = espacoId;
    }

    public void setDataReserva(String dataReserva) {
        this.dataReserva = dataReserva;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

    public void setRequerimentos(String requerimentos) {
        this.requerimentos = requerimentos;
    }

    @Override
    public String toString() {

        return "\nID: " + id +
                "\nCliente: " + cliente +
                "\nQuantidade de Pessoas: " + quantidadePessoas +
                "\nTipo de Espaço: " + tipoEspaco +
                "\nEspaço ID: " + espacoId +
                "\nData: " + dataReserva +
                "\nHorário Início: " + horarioInicio +
                "\nHorário Fim: " + horarioFim +
                "\nRequerimentos: " + requerimentos +
                "\n--------------------------------";
    }
}
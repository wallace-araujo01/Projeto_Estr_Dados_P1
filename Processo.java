import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Processo {
    private static int contador = 0;

    private int protocolo;
    private String solicitante;
    private String tipoServico;
    private int prioridade;
    private String dataHora;

    public Processo(String solicitante, String tipoServico, int prioridade) {
        this.protocolo = ++contador;
        this.solicitante = solicitante;
        this.tipoServico = tipoServico;
        this.prioridade = prioridade;
        this.dataHora = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    // Getters
    public int getProtocolo()    { return protocolo; }
    public String getSolicitante() { return solicitante; }
    public String getTipoServico() { return tipoServico; }
    public int getPrioridade()   { return prioridade; }
    public String getDataHora()  { return dataHora; }

    // Setters (protocolo não tem setter)
    public void setSolicitante(String solicitante) { this.solicitante = solicitante; }
    public void setTipoServico(String tipoServico) { this.tipoServico = tipoServico; }
    public void setPrioridade(int prioridade)      { this.prioridade = prioridade; }
    public void setDataHora(String dataHora)       { this.dataHora = dataHora; }

    private String prioridadeTexto() {
        switch (prioridade) {
            case 1: return "Baixa";
            case 2: return "Normal";
            case 3: return "Urgente";
            default: return "Desconhecida";
        }
    }

    @Override
    public String toString() {
        return String.format("[#%03d] %s | %s | %s | %s",
                protocolo, solicitante, tipoServico, prioridadeTexto(), dataHora);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Processo)) return false;
        Processo outro = (Processo) obj;
        return this.protocolo == outro.protocolo;
    }
}

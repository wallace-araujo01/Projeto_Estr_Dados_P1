public class PilhaPrioridade {
    private final PilhaProcesso baixa;
    private final PilhaProcesso normal;
    private final PilhaProcesso urgente;

    public PilhaPrioridade() {
        this.baixa   = new PilhaProcesso();
        this.normal  = new PilhaProcesso();
        this.urgente = new PilhaProcesso();
    }

    public void push(Processo processo) {
        switch (processo.getPrioridade()) {
            case 1: baixa.push(processo);   break;
            case 2: normal.push(processo);  break;
            case 3: urgente.push(processo); break;
            default:
                throw new IllegalArgumentException(
                        "Prioridade inválida: " + processo.getPrioridade() +
                        ". Use 1 (baixa), 2 (normal) ou 3 (urgente).");
        }
    }

    public Processo pop() {
        return pilhaMaisUrgente().pop();
    }

    public Processo peek() {
        return pilhaMaisUrgente().peek();
    }

    public int tamanho() {
        return urgente.tamanho() + normal.tamanho() + baixa.tamanho();
    }

    public boolean estaVazia() {
        return tamanho() == 0;
    }

    public void listar() {
        System.out.println("  --- Urgentes ---");
        urgente.imprimir();
        System.out.println("  --- Normais ---");
        normal.imprimir();
        System.out.println("  --- Baixa prioridade ---");
        baixa.imprimir();
    }

    private PilhaProcesso pilhaMaisUrgente() {
        if (!urgente.estaVazia()) return urgente;
        if (!normal.estaVazia())  return normal;
        if (!baixa.estaVazia())   return baixa;
        throw new PilhaVaziaException("Não há processos pendentes em nenhuma fila de prioridade.");
    }
}

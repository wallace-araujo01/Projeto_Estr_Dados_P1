public class PilhaPrioridade {
    private PilhaProcesso urgente = new PilhaProcesso();
    private PilhaProcesso normal = new PilhaProcesso();
    private PilhaProcesso baixa = new PilhaProcesso();

    public void push(Processo p) { // [cite: 80, 83]
        if (p.getPrioridade() == 3) urgente.push(p);
        else if (p.getPrioridade() == 2) normal.push(p);
        else baixa.push(p);
    }

    public Processo pop() { // [cite: 84]
        if (!urgente.estaVazia()) return urgente.pop();
        if (!normal.estaVazia()) return normal.pop();
        if (!baixa.estaVazia()) return baixa.pop();
        throw new PilhaVaziaException("Nenhum processo pendente em nenhuma prioridade!");
    }

    public int tamanhoTotal() { // [cite: 86]
        return urgente.tamanho() + normal.tamanho() + baixa.tamanho();
    }

    public void listarAgrupado() { // [cite: 89]
        System.out.println("--- URGENTES ---"); urgente.imprimir();
        System.out.println("--- NORMAIS ---"); normal.imprimir();
        System.out.println("--- BAIXAS ---"); baixa.imprimir();
    }
}

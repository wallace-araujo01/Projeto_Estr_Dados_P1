public class CentralAtendimento {
    private final PilhaPrioridade pendentes;
    private final PilhaProcesso   historico;

    public CentralAtendimento() {
        this.pendentes = new PilhaPrioridade();
        this.historico = new PilhaProcesso();
    }

    public void abrirProcesso(Processo processo) {
        pendentes.push(processo);
        limparHistorico();
        System.out.println("  Processo aberto: " + processo);
    }

    public void atenderProximo() {
        Processo atendido = pendentes.pop();
        historico.push(atendido);
        System.out.println("  Atendido: " + atendido);
    }

    public void desfazerUltimoAtendimento() {
        if (historico.estaVazia()) {
            throw new PilhaVaziaException("Nenhum atendimento para desfazer.");
        }
        Processo restaurado = historico.pop();
        pendentes.push(restaurado);
        System.out.println("  Atendimento desfeito — processo devolvido à fila: " + restaurado);
    }

    public void listarPendentes() {
        System.out.println("=== Processos Pendentes (por prioridade) ===");
        if (pendentes.estaVazia()) {
            System.out.println("  (nenhum processo pendente)");
        } else {
            pendentes.listar();
        }
    }

    public void listarHistorico() {
        System.out.println("=== Histórico de Atendimentos ===");
        historico.imprimir();
    }

    private void limparHistorico() {
        while (!historico.estaVazia()) historico.pop();
    }
}

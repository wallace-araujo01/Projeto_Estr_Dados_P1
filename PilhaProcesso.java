public class PilhaProcesso {
    private final VetorDinamico vetor;
    public PilhaProcesso() {
        this.vetor = new VetorDinamico();
    }

    public void push(Processo processo) {
        vetor.inserir(processo);
    }

    public Processo pop() {
        if (vetor.estaVazio()) {
            throw new PilhaVaziaException("Não é possível desempilhar: pilha vazia.");
        }
        return vetor.remover(vetor.tamanho() - 1);
    }

    public Processo peek() {
        if (vetor.estaVazio()) {
            throw new PilhaVaziaException("Não é possível consultar o topo: pilha vazia.");
        }
        return vetor.obter(vetor.tamanho() - 1);
    }

    public int tamanho()       { return vetor.tamanho(); }
    public boolean estaVazia() { return vetor.estaVazio(); }

    public void imprimir() {
        if (estaVazia()) {
            System.out.println("  (pilha vazia)");
            return;
        }
        for (int i = vetor.tamanho() - 1; i >= 0; i--) {
            System.out.printf("  %s%n", vetor.obter(i));
        }
    }
}

public class PilhaProcesso {
    private VetorDinamico repositorio = new VetorDinamico();

    public void push(Processo p) { repositorio.adicionar(p); } // [cite: 56]

    public Processo pop() { // 
        if (estaVazia()) throw new PilhaVaziaException("A pilha está vazia!");
        return repositorio.removerFinal();
    }

    public Processo peek() { // [cite: 58]
        if (estaVazia()) throw new PilhaVaziaException("A pilha está vazia!");
        return repositorio.get(repositorio.getTamanho() - 1);
    }

    public boolean estaVazia() { return repositorio.getTamanho() == 0; }
    public int tamanho() { return repositorio.getTamanho(); }

    public void imprimir() { // [cite: 59]
        for (int i = repositorio.getTamanho() - 1; i >= 0; i--) {
            System.out.println(repositorio.get(i));
        }
    }
}

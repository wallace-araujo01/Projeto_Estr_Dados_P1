public class VetorDinamico {
    private Processo[] dados;
    private int tamanho;
    private int capacidadeMinima;

    public VetorDinamico() { this(4); }

    public VetorDinamico(int capacidadeInicial) {
        this.capacidadeMinima = capacidadeInicial;
        this.dados = new Processo[capacidadeInicial];
        this.tamanho = 0;
    }
    
    public void inserir(Processo processo) {
        if (tamanho == dados.length) {
            redimensionar(dados.length * 2);
        }
        dados[tamanho++] = processo;
    }
    public Processo remover(int indice) {
        validarIndice(indice);
        Processo removido = dados[indice];

        // Desloca elementos para a esquerda
        for (int i = indice; i < tamanho - 1; i++) {
            dados[i] = dados[i + 1];
        }
        dados[--tamanho] = null;

        if (tamanho > 0 && tamanho < dados.length / 4 && dados.length / 2 >= capacidadeMinima) {
            redimensionar(dados.length / 2);
        }
        return removido;
    }

    public Processo removerPorProtocolo(int protocolo) {
        int indice = buscarPorProtocolo(protocolo);
        if (indice == -1) return null;
        return remover(indice);
    }

    public int buscarPorProtocolo(int protocolo) {
        for (int i = 0; i < tamanho; i++) {
            if (dados[i].getProtocolo() == protocolo) return i;
        }
        return -1;
    }

    public Processo obter(int indice) {
        validarIndice(indice);
        return dados[indice];
    }
    
    public int tamanho()     { return tamanho; }
    public int capacidade()  { return dados.length; }
    public boolean estaVazio() { return tamanho == 0; }

    public void listar() {
        if (tamanho == 0) {
            System.out.println("  (nenhum processo cadastrado)");
            return;
        }
        for (int i = 0; i < tamanho; i++) {
            System.out.printf("  %d. %s%n", i + 1, dados[i]);
        }
    }

    private void redimensionar(int novaCapacidade) {
        int capacidadeAnterior = dados.length;
        Processo[] novoArray = new Processo[novaCapacidade];
        System.arraycopy(dados, 0, novoArray, 0, tamanho);
        dados = novoArray;
        System.out.printf("  [VetorDinamico] Redimensionado: %d → %d posições%n",
                capacidadeAnterior, novaCapacidade);
    }

    private void validarIndice(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException(
                    "Índice " + indice + " fora do intervalo [0, " + (tamanho - 1) + "]");
        }
    }
}

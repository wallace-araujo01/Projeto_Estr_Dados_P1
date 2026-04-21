public class VetorDinamico {

    private static final int CAPACIDADE_INICIAL = 4;

    private Processo[] dados;
    private int tamanho;
    private final int capacidadeMinima;

    public VetorDinamico() {
        this(CAPACIDADE_INICIAL);
    }

    public VetorDinamico(int capacidadeInicial) {
        this.capacidadeMinima = capacidadeInicial;
        this.dados = new Processo[capacidadeInicial];
        this.tamanho = 0;
    }

    // -------------------------------------------------------------------------
    // Inserção
    // -------------------------------------------------------------------------

    public void inserir(Processo processo) {
        if (tamanho == dados.length) {
            redimensionar(dados.length * 2);
        }
        dados[tamanho++] = processo;
    }

    // -------------------------------------------------------------------------
    // Remoção por índice
    // -------------------------------------------------------------------------

    public Processo remover(int indice) {
        validarIndice(indice);
        Processo removido = dados[indice];

        // Desloca elementos para a esquerda
        for (int i = indice; i < tamanho - 1; i++) {
            dados[i] = dados[i + 1];
        }
        dados[--tamanho] = null;

        // Reduz se ocupação < 25 %, sem descer abaixo da capacidade mínima
        if (tamanho > 0 && tamanho < dados.length / 4 && dados.length / 2 >= capacidadeMinima) {
            redimensionar(dados.length / 2);
        }

        return removido;
    }

    /** Remove pelo protocolo; retorna o processo removido ou null se não encontrado. */
    public Processo removerPorProtocolo(int protocolo) {
        int indice = buscarPorProtocolo(protocolo);
        if (indice == -1) return null;
        return remover(indice);
    }

    // -------------------------------------------------------------------------
    // Busca
    // -------------------------------------------------------------------------

    /** Retorna o índice do processo com o protocolo informado, ou -1 se não encontrado. */
    public int buscarPorProtocolo(int protocolo) {
        for (int i = 0; i < tamanho; i++) {
            if (dados[i].getProtocolo() == protocolo) return i;
        }
        return -1;
    }

    // -------------------------------------------------------------------------
    // Acesso direto
    // -------------------------------------------------------------------------

    public Processo obter(int indice) {
        validarIndice(indice);
        return dados[indice];
    }

    // -------------------------------------------------------------------------
    // Informações
    // -------------------------------------------------------------------------

    public int tamanho()     { return tamanho; }
    public int capacidade()  { return dados.length; }
    public boolean estaVazio() { return tamanho == 0; }

    // -------------------------------------------------------------------------
    // Listagem
    // -------------------------------------------------------------------------

    public void listar() {
        if (tamanho == 0) {
            System.out.println("  (nenhum processo cadastrado)");
            return;
        }
        for (int i = 0; i < tamanho; i++) {
            System.out.printf("  %d. %s%n", i + 1, dados[i]);
        }
    }

    // -------------------------------------------------------------------------
    // Redimensionamento interno
    // -------------------------------------------------------------------------

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

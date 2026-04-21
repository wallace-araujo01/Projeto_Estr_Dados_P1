import java.util.Scanner;

public class CentralAtendimento {
    private PilhaPrioridade pendentes = new PilhaPrioridade(); // Refatorado para Prioridade [cite: 91]
    private PilhaProcesso historico = new PilhaProcesso(); // Auxiliar para Undo [cite: 63]

    public void abrirProcesso(Processo p) {
        pendentes.push(p);
        // "Uma nova ação elimina o histórico de desfazer" [cite: 65]
        while (!historico.estaVazia()) historico.pop(); 
        System.out.println("Processo aberto com sucesso!");
    }

    public void atenderProximo() { // [cite: 66, 67]
        try {
            Processo p = pendentes.pop();
            historico.push(p);
            System.out.println("Atendendo agora: " + p);
        } catch (PilhaVaziaException e) {
            System.out.println(e.getMessage());
        }
    }

    public void desfazer() { // [cite: 68, 69]
        try {
            Processo p = historico.pop();
            pendentes.push(p);
            System.out.println("Atendimento desfeito. Processo retornou à fila.");
        } catch (PilhaVaziaException e) {
            System.out.println("Nada para desfazer.");
        }
    }

    public static void main(String[] args) { // Menu Interativo [cite: 74]
        CentralAtendimento central = new CentralAtendimento();
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("\n--- MENU CENTRAL ---");
            System.out.println("1. Abrir Processo");
            System.out.println("2. Atender Próximo");
            System.out.println("3. Desfazer Último Atendimento");
            System.out.println("4. Listar Pendentes");
            System.out.println("0. Sair");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: "); String nome = sc.nextLine();
                    System.out.print("Serviço: "); String serv = sc.nextLine();
                    System.out.print("Prioridade (1-Baixa, 2-Normal, 3-Urgente): ");
                    int prio = sc.nextInt();
                    central.abrirProcesso(new Processo(nome, serv, prio, "21/04/2026 18:00"));
                    break;
                case 2: central.atenderProximo(); break;
                case 3: central.desfazer(); break;
                case 4: central.pendentes.listarAgrupado(); break;
            }
        } while (opcao != 0);
    }
}

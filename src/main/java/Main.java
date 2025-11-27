import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // --- BLOCO DE TESTE DE CONEXÃO ---
        try {
            Conexao.conectar();
            System.out.println("✅ CONECTADO AO BANCO DE DADOS COM SUCESSO!");
        } catch (Exception e) {
            System.out.println("❌ ERRO AO CONECTAR: " + e.getMessage());
            return; // Para o programa se não conectar
        }
        // ---------------------------------

        TarefaDAO tarefaDAO = new TarefaDAO();
        boolean executando = true;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Tarefa> listaTarefas = carregarDados();

        while (executando) {
            System.out.println("1 - Adicionar");
            System.out.println("2 - Listar");
            System.out.println("3 - Concluir");
            System.out.println("4 - Deletar");
            System.out.println("5 - Sair");
            System.out.println("Escolha uma das opções:");

            String opcao = scanner.nextLine();

            switch (opcao){
                case "1":
                    System.out.println("Digite a descrição da tarefa:");
                    String descricaoTarefa = scanner.nextLine();

                    Tarefa novaTarefa = new Tarefa(descricaoTarefa, false);

                    tarefaDAO.adicionarTarefa(novaTarefa);
                    break;
                case "2":
                    ArrayList<Tarefa> listaDoBanco = tarefaDAO.listarTarefas();
                    for (Tarefa t: listaDoBanco) {
                        String status = t.isConcluida() ? "[X]" : "[ ]";
                        System.out.println(t.getId() + " - " + t.getDescricao() + " " + status);
                    }
                    System.out.println("-------FIM LISTA-------");
                    break;
                case "3":
                    System.out.println("Digite o ID da atividade que deseja concluir");
                    int id = scanner.nextInt();
                    tarefaDAO.concluirTarefa(id);
                    break;
                case "4":
                    System.out.println("Digite o ID da atividade que deseja excluir");
                    String idStr = scanner.nextLine();
                    int idDelete = Integer.parseInt(idStr);

                    boolean verificacao = true;

                    while(verificacao){
                        System.out.println("Dejesa realmente excluir a tarefa (S) ou (N): " + idDelete);
                        String confirmacao = scanner.nextLine();
                        switch (confirmacao.toUpperCase()) {
                            case "S":
                                tarefaDAO.removerTarefa(idDelete);
                                System.out.println("Tarefa removida com sucesso!");
                                verificacao = false;
                                break;
                            case "N":
                                System.out.println("Voltando ao menu");
                                verificacao = false;
                                break;
                            default:
                                System.out.println("Opção invalida");
                        }
                    }
                    break;
                case "5":
                    System.out.println("Saindo...");
                    salvarDados(listaTarefas);
                    executando = false;
                    break;
                default:
                    System.out.println("Opção invalida, escolha outra opção.");
            }
        }
    }
    private static void salvarDados(ArrayList<Tarefa> lista) {
        Gson gson = new Gson();

        String json = gson.toJson(lista);

        try (FileWriter writer = new FileWriter("tarefas.json")) {
            writer.write(json);
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivos: " + e.getMessage());
        }
    }

    private static ArrayList<Tarefa> carregarDados() {
        Gson gson = new Gson();
        File arquivo = new File("tarefas.json");
        if (!arquivo.exists()){
            return new ArrayList<>();
        }
        try (FileReader reader = new FileReader(arquivo)){
            Type tipoLista = new TypeToken<ArrayList<Tarefa>>(){}.getType();

            ArrayList<Tarefa> dados = gson.fromJson(reader, tipoLista);

            if (dados == null) return new ArrayList<>();

            System.out.println("Dados carregados com sucesso!");
            return dados;
        } catch (IOException e) {
            System.out.println("Erro ao ler: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

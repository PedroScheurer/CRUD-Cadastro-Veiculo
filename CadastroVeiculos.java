import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastroVeiculos {
    static Scanner scan = new Scanner(System.in);
    static List<Veiculo> veiculos = new ArrayList<>();

    public static void main(String[] args) {
        String menu = """
                \n=== Bem Vindo ao Controle de Frotas ===
                1 - Cadastro de Veículos
                2 - Listar Veículos
                3 - Excluir Veículo
                4 - Pesquisar Veículo
                0 - Sair
                """;

        int opcao;
        do {
            System.out.println(menu);
            opcao = Input.scanInt("Digite uma opção: ", scan);

            try {
                switch (opcao) {
                    case 1 -> cadastrarVeiculo();
                    case 2 -> listarVeiculos();
                    case 3 -> removerVeiculo();
                    case 4 -> pesquisarVeiculo();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    static void cadastrarVeiculo() {
        System.out.println("\n=== Cadastro de Veículo ===");
        String marca = Input.scanString("Digite a marca: ", scan);
        String modelo = Input.scanString("Digite o modelo: ", scan);
        int ano = Input.scanInt("Digite o ano: ", scan);
        String placa = Input.scanString("Digite a placa (ABC-1234): ", scan);

        if (pesquisarPlaca(placa) != null) {
            System.out.println("Erro: já existe um veículo com essa placa!");
            return;
        }

        Veiculo novo = new Veiculo(marca, modelo, ano, placa);
        veiculos.add(novo);
        System.out.println("Veículo cadastrado com sucesso!");
    }

    static void listarVeiculos() {
        System.out.println("\n=== Veículos Cadastrados ===");
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            for (int i = 0; i < veiculos.size(); i++) {
                System.out.println((i + 1) + " - " + veiculos.get(i));
            }
        }
    }

    static void removerVeiculo() {
        System.out.println("\n=== Remover Veículo ===");
        String placa = Input.scanString("Digite a placa do veículo: ", scan);
        Veiculo veiculo = pesquisarPlaca(placa);

        if (veiculo != null) {
            veiculos.remove(veiculo);
            System.out.println("Veículo removido com sucesso!");
        } else {
            System.out.println("Erro: veículo não encontrado.");
        }
    }

    static void pesquisarVeiculo() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }

        String menuPesquisa = """
                \n=== Pesquisa de Veículos ===
                1 - Por Placa
                2 - Por Modelo
                0 - Voltar
                """;
        System.out.println(menuPesquisa);

        int opcao = Input.scanInt("Digite uma opção: ", scan);

        switch (opcao) {
            case 1 -> {
                String placa = Input.scanString("Digite a placa: ", scan);
                Veiculo v = pesquisarPlaca(placa);
                if (v != null) {
                    System.out.println("Veículo encontrado: " + v);
                } else {
                    System.out.println("Nenhum veículo encontrado.");
                }
            }
            case 2 -> {
                String modelo = Input.scanString("Digite parte do modelo: ", scan);
                List<Veiculo> encontrados = pesquisarModelo(modelo);
                if (!encontrados.isEmpty()) {
                    System.out.println("Veículos encontrados:");
                    for (Veiculo v : encontrados) {
                        System.out.println(v);
                    }
                } else {
                    System.out.println("Nenhum veículo encontrado.");
                }
            }
            case 0 -> System.out.println("Voltando...");
            default -> System.out.println("Opção inválida.");
        }
    }

    private static Veiculo pesquisarPlaca(String placa) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

    private static List<Veiculo> pesquisarModelo(String modelo) {
        List<Veiculo> encontrados = new ArrayList<>();
        for (Veiculo v : veiculos) {
            if (v.getModelo().toLowerCase().contains(modelo.toLowerCase())) {
                encontrados.add(v);
            }
        }
        return encontrados;
    }
}


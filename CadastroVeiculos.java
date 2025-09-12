import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastroVeiculos {
    static Scanner scan = new Scanner(System.in);

    static List<Veiculo> veiculos = new ArrayList<>();

    public static void main(String[] args) {
        String menu = """
                \n===Bem Vindo ao Controle de Frotas ===
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
                    case 1:
                        CadastroVeiculos.cadastrarVeiculo();
                        break;
                    case 2:
                        CadastroVeiculos.listarVeiculos();
                        break;
                    case 3:
                        CadastroVeiculos.removerVeiculo();
                        break;
                    case 4:
                        Veiculo pesquisaResultado = CadastroVeiculos.pesquisarVeiculo();
                        if (pesquisaResultado != null) {
                            System.out.println(pesquisaResultado);
                            break;
                        }
                        System.out.println("Nenhum veículo encontrado");
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;

                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (opcao != 0);
    }

    static void cadastrarVeiculo() {
        System.out.println("Cadastro de Veículo");

        String marcaVeiculo = Input.scanString("Digite a marca do veículo: ", scan);
        String modeloVeiculo = Input.scanString("Digite o modelo do veículo: ", scan);
        int anoVeiculo = Input.scanInt("Digite o ano do veículo: ", scan);
        String placaVeiculo = Input.scanString("Digite a placa do veículo (ABC-1234): ", scan);

        Veiculo novoVeiculo = new Veiculo(marcaVeiculo, modeloVeiculo, anoVeiculo, placaVeiculo);
        veiculos.add(novoVeiculo);
    }

    static void listarVeiculos() {
        System.out.println("Veículos cadastrados");
        int i = 1;
        for (Veiculo veiculo : veiculos) {
            System.out.println(i++ + " - " + veiculo);
        }
    }

    static void removerVeiculo() {
        listarVeiculos();
        String removerVeiculoPlaca = Input.scanString(("Remover veículo pela placa: "), scan);
        Veiculo resultadoPlacaPesquisada = pesquisarPlaca(removerVeiculoPlaca);
        if(resultadoPlacaPesquisada != null){
            for(int i = 0; i < veiculos.size(); i++){
                if(resultadoPlacaPesquisada == veiculos.get(i)){
                    veiculos.remove(i);
                    System.out.println("Veículo removido");
                    break;
                }
            }
        }
    }

    static Veiculo pesquisarVeiculo() {
        if (veiculos.size() == 0) {
            System.out.println("Nenhum veículo cadastrado");
            return null;
        }

        String menuPesquisa = """
                \n=== Pesquisa de Veículos ===
                1 - Por Placa
                2 - Por Modelo
                0 - Sair
                """;

        System.out.println(menuPesquisa);
        int opcao = Input.scanInt("Opção: ", scan);

        return switch (opcao) {
            case 1:
                String placaPesquisa = Input.scanString("Pesquisa por placa: ", scan);
                Veiculo resultadoPlacaPesquisada = pesquisarPlaca(placaPesquisa);
                if (resultadoPlacaPesquisada != null) {
                    yield resultadoPlacaPesquisada;
                }
                yield null;
            case 2:

                String modeloPesquisa = Input.scanString("Pesquisa por modelo: ", scan);
                Veiculo resultadoModeloPesquisado = pesquisarModelo(modeloPesquisa);
                if (resultadoModeloPesquisado != null) {
                    yield resultadoModeloPesquisado;
                }
                yield null;
            case 0:
                System.out.println("Saindo...");
                yield null;

            default:
                System.out.println("Inválido...");
                yield null;
        };
    }

    private static Veiculo pesquisarPlaca(String placaProcurada) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placaProcurada)) {
                return veiculo;
            }
        }
        return null;
    }

    private static Veiculo pesquisarModelo(String modeloProcurada) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getModelo().toUpperCase().contains(modeloProcurada.toUpperCase())) {
                return veiculo;
            }
        }
        return null;
    }
}

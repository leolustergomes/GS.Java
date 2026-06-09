import java.util.Scanner;

public class SistemaMonitoramento {

    // Instâncias dos componentes do sistema
    private static SensorTemperatura sensorTemp;
    private static SensorPressao sensorPress;
    private static SensorRadiacao sensorRad;
    private static PropulsaoQuimica propQuimica;
    private static PropulsaoEletrica propEletrica;
    private static DadosMissao dadosMissao;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        inicializarSistema();

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                opcao = -1;
            }
            processarOpcao(opcao);
        }

        System.out.println("\nEncerrando sistema de monitoramento espacial. Até logo!");
        scanner.close();
    }

    // Inicializa todos os componentes com valores padrão
    private static void inicializarSistema() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  PLATAFORMA DE MONITORAMENTO ESPACIAL    ║");
        System.out.println("║         Inicializando sistema...         ║");
        System.out.println("╚══════════════════════════════════════════╝");

        sensorTemp  = new SensorTemperatura("ST-01", "Sensor Temperatura Principal", 80.0);
        sensorPress = new SensorPressao("SP-01", "Sensor Pressao do Modulo", 85.0);
        sensorRad   = new SensorRadiacao("SR-01", "Sensor Radiacao Solar", 5.0);

        propQuimica  = new PropulsaoQuimica("PQ-01", "Motor Quimico RS-25", 1860.0, "LOX/LH2", 500.0);
        propEletrica = new PropulsaoEletrica("PE-01", "Motor Ionico X3", 5.4, 100000.0, "Xenonio");

        // Senha padrão: "NASA2026"
        dadosMissao = new DadosMissao("Missao Apolo-X", "NASA2026", 3);
        dadosMissao.setTrajetoria("Terra -> Orbita Lunar -> Superficie");
        dadosMissao.setCoordenadas("NASA2026", 4521.3, -3310.7, 8800.0);

        // Liga os sensores
        sensorTemp.ligar();
        sensorPress.ligar();
        sensorRad.ligar();

        System.out.println("\nSistema inicializado com sucesso!\n");
    }

    private static void exibirMenu() {
        System.out.println("\n╔══════════════════════════╗");
        System.out.println("║       MENU PRINCIPAL     ║");
        System.out.println("╠══════════════════════════╣");
        System.out.println("║ 1. Verificar Sensores    ║");
        System.out.println("║ 2. Controlar Propulsao   ║");
        System.out.println("║ 3. Dados da Missao       ║");
        System.out.println("║ 4. Simular Alertas       ║");
        System.out.println("║ 5. Status Completo       ║");
        System.out.println("║ 0. Sair                  ║");
        System.out.println("╚══════════════════════════╝");
        System.out.print("Escolha uma opcao: ");
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> menuSensores();
            case 2 -> menuPropulsao();
            case 3 -> menuDadosMissao();
            case 4 -> simularAlertas();
            case 5 -> exibirStatusCompleto();
            case 0 -> {}  // sair
            default -> System.out.println("Opcao invalida!");
        }
    }

    // ==================== MENU SENSORES ====================
    private static void menuSensores() {
        System.out.println("\n--- VERIFICACAO DE SENSORES ---");
        System.out.println("1. Ler todos os sensores");
        System.out.println("2. Verificar funcionamento");
        System.out.println("3. Leitura individual");
        System.out.print("Opcao: ");

        int op;
        try { op = Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { op = -1; }

        switch (op) {
            case 1 -> lerTodosSensores();
            case 2 -> verificarFuncionamento();
            case 3 -> leituraIndividual();
            default -> System.out.println("Opcao invalida.");
        }
    }

    private static void lerTodosSensores() {
        System.out.println("\n>> Realizando leitura de todos os sensores...");
        sensorTemp.lerValor();
        sensorPress.lerValor();
        sensorRad.lerValor();
        verificarAlertasSensores();
    }

    private static void verificarFuncionamento() {
        System.out.println("\n>> Verificando funcionamento dos sensores...");
        sensorTemp.verificarFuncionamento();
        sensorPress.verificarFuncionamento();
        sensorRad.verificarFuncionamento();
    }

    private static void leituraIndividual() {
        System.out.println("1. Temperatura  2. Pressao  3. Radiacao");
        System.out.print("Sensor: ");
        int s;
        try { s = Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { s = -1; }

        switch (s) {
            case 1 -> {
                sensorTemp.lerValor();
                emitirAlerta(sensorTemp);
            }
            case 2 -> {
                sensorPress.lerValor();
                emitirAlerta(sensorPress);
            }
            case 3 -> {
                sensorRad.lerValor();
                emitirAlerta(sensorRad);
            }
            default -> System.out.println("Sensor invalido.");
        }
    }

    // ==================== MENU PROPULSAO ====================
    private static void menuPropulsao() {
        System.out.println("\n--- CONTROLE DE PROPULSAO ---");
        System.out.println("1. Ligar motor quimico");
        System.out.println("2. Desligar motor quimico");
        System.out.println("3. Acelerar motor quimico");
        System.out.println("4. Ligar motor ionico");
        System.out.println("5. Desligar motor ionico");
        System.out.println("6. Acelerar motor ionico");
        System.out.println("7. Status dos motores");
        System.out.print("Opcao: ");

        int op;
        try { op = Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { op = -1; }

        switch (op) {
            case 1 -> propQuimica.ligarMotor();
            case 2 -> propQuimica.desligarMotor();
            case 3 -> {
                System.out.print("Potencia (0-100): ");
                try {
                    double pot = Double.parseDouble(scanner.nextLine().trim());
                    propQuimica.acelerar(pot);
                } catch (NumberFormatException e) {
                    System.out.println("Valor invalido.");
                }
            }
            case 4 -> propEletrica.ligarMotor();
            case 5 -> propEletrica.desligarMotor();
            case 6 -> {
                System.out.print("Potencia (0-100): ");
                try {
                    double pot = Double.parseDouble(scanner.nextLine().trim());
                    propEletrica.acelerar(pot);
                } catch (NumberFormatException e) {
                    System.out.println("Valor invalido.");
                }
            }
            case 7 -> {
                propQuimica.exibirStatusPropulsao();
                System.out.println();
                propEletrica.exibirStatusPropulsao();
            }
            default -> System.out.println("Opcao invalida.");
        }
    }

    // ==================== MENU DADOS DA MISSAO ====================
    private static void menuDadosMissao() {
        System.out.println("\n--- DADOS DA MISSAO ---");
        System.out.println("1. Exibir resumo da missao");
        System.out.println("2. Atualizar combustivel");
        System.out.println("3. Ver coordenadas (senha necessaria)");
        System.out.println("4. Atualizar coordenadas (senha necessaria)");
        System.out.println("5. Atualizar trajetoria");
        System.out.print("Opcao: ");

        int op;
        try { op = Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { op = -1; }

        switch (op) {
            case 1 -> dadosMissao.exibirResumo();
            case 2 -> {
                System.out.print("Novo nivel de combustivel (0-100): ");
                try {
                    double nivel = Double.parseDouble(scanner.nextLine().trim());
                    dadosMissao.setNivelCombustivel(nivel);
                } catch (NumberFormatException e) {
                    System.out.println("Valor invalido.");
                }
            }
            case 3 -> {
                System.out.print("Digite a senha de acesso: ");
                String senha = scanner.nextLine().trim();
                String coords = dadosMissao.getCoordenadas(senha);
                if (coords != null) System.out.println("Coordenadas: " + coords);
            }
            case 4 -> {
                System.out.print("Senha: ");
                String senha = scanner.nextLine().trim();
                System.out.print("X: "); double x = Double.parseDouble(scanner.nextLine().trim());
                System.out.print("Y: "); double y = Double.parseDouble(scanner.nextLine().trim());
                System.out.print("Z: "); double z = Double.parseDouble(scanner.nextLine().trim());
                dadosMissao.setCoordenadas(senha, x, y, z);
            }
            case 5 -> {
                System.out.print("Nova trajetoria: ");
                String traj = scanner.nextLine().trim();
                dadosMissao.setTrajetoria(traj);
            }
            default -> System.out.println("Opcao invalida.");
        }
    }

    // ==================== SIMULACAO DE ALERTAS ====================
    private static void simularAlertas() {
        System.out.println("\n>> Simulando leituras e verificando alertas...");
        System.out.println("(Realizando 5 leituras consecutivas)\n");

        for (int i = 1; i <= 5; i++) {
            System.out.println("--- Leitura #" + i + " ---");
            sensorTemp.lerValor();
            sensorPress.lerValor();
            sensorRad.lerValor();
            verificarAlertasSensores();
            System.out.println();
        }
    }

    // ==================== STATUS COMPLETO ====================
    private static void exibirStatusCompleto() {
        System.out.println("\n╔═══════════════════════════════════╗");
        System.out.println("║         STATUS COMPLETO           ║");
        System.out.println("╚═══════════════════════════════════╝");

        System.out.println("\n>> SENSORES:");
        System.out.println(sensorTemp.obterDescricao());
        System.out.println(sensorPress.obterDescricao());
        System.out.println(sensorRad.obterDescricao());

        System.out.println("\n>> PROPULSAO:");
        System.out.println(propQuimica.obterDescricao());
        System.out.println(propEletrica.obterDescricao());

        System.out.println();
        dadosMissao.exibirResumo();
    }

    // ==================== SISTEMA DE ALERTAS ====================
    private static void verificarAlertasSensores() {
        emitirAlerta(sensorTemp);
        emitirAlerta(sensorPress);
        emitirAlerta(sensorRad);
    }

    private static void emitirAlerta(Sensor sensor) {
        if (!sensor.verificarAlerta()) return;

        double valor = sensor.lerValor();
        double limite = sensor.getLimiteAlerta();
        double diferenca = Math.abs(valor - limite);
        double percentual = (diferenca / limite) * 100;

        String nivel;
        if (percentual < 10) {
            nivel = "⚠  ATENCAO";
        } else if (percentual < 30) {
            nivel = "🔶 ALERTA";
        } else {
            nivel = "🔴 CRITICO";
        }

        System.out.printf("[%s][%s] Sensor de %s fora do limite! Valor: %.2f | Limite: %.2f%n",
                nivel, sensor.retornarTipo(), sensor.retornarTipo(), valor, limite);
    }
}

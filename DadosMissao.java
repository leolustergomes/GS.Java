
public class DadosMissao {

    private String nomeMissao;
    private double coordenadaX;       // Protegida por senha
    private double coordenadaY;       // Protegida por senha
    private double coordenadaZ;       // Protegida por senha
    private double nivelCombustivel;  // Em porcentagem (0-100%)
    private String trajetoria;
    private int numeroDeTripulantes;
    private String codigoAcesso;      // Senha para dados restritos

    // Construtor
    public DadosMissao(String nomeMissao, String codigoAcesso, int numeroDeTripulantes) {
        this.nomeMissao = nomeMissao;
        this.codigoAcesso = codigoAcesso;
        this.numeroDeTripulantes = numeroDeTripulantes;
        this.nivelCombustivel = 100.0;
        this.trajetoria = "Trajetória não definida";
        this.coordenadaX = 0.0;
        this.coordenadaY = 0.0;
        this.coordenadaZ = 0.0;
    }

    // --- Getters/Setters públicos (sem restrição) ---

    public String getNomeMissao() {
        return nomeMissao;
    }

    public String getTrajetoria() {
        return trajetoria;
    }

    public void setTrajetoria(String trajetoria) {
        if (trajetoria != null && !trajetoria.isBlank()) {
            this.trajetoria = trajetoria;
            System.out.println("Trajetória atualizada: " + trajetoria);
        } else {
            System.out.println("Trajetória inválida!");
        }
    }

    public int getNumeroDeTripulantes() {
        return numeroDeTripulantes;
    }

    public void setNumeroDeTripulantes(int numero) {
        if (numero > 0) {
            this.numeroDeTripulantes = numero;
        } else {
            System.out.println("Número de tripulantes deve ser positivo!");
        }
    }

    public double getNivelCombustivel() {
        return nivelCombustivel;
    }

    public void setNivelCombustivel(double nivel) {
        if (nivel < 0 || nivel > 100) {
            System.out.println("Nível de combustível inválido! Use valores entre 0 e 100.");
            return;
        }
        this.nivelCombustivel = nivel;
        // Alerta automático se combustível estiver abaixo de 20%
        if (nivel < 20.0) {
            System.out.println("⚠ ALERTA: Nível de combustível crítico! Apenas " + nivel + "% restante!");
        }
    }

    // --- Dados restritos (protegidos por senha) ---

    public String getCoordenadas(String senha) {
        if (validarSenha(senha)) {
            return String.format("X: %.2f | Y: %.2f | Z: %.2f", coordenadaX, coordenadaY, coordenadaZ);
        } else {
            System.out.println("Acesso negado! Senha incorreta.");
            return null;
        }
    }

    public void setCoordenadas(String senha, double x, double y, double z) {
        if (validarSenha(senha)) {
            this.coordenadaX = x;
            this.coordenadaY = y;
            this.coordenadaZ = z;
            System.out.println("Coordenadas atualizadas com sucesso.");
        } else {
            System.out.println("Acesso negado! Senha incorreta.");
        }
    }

    public boolean alterarCodigoAcesso(String senhaAtual, String novaSenha) {
        if (validarSenha(senhaAtual)) {
            if (novaSenha != null && novaSenha.length() >= 4) {
                this.codigoAcesso = novaSenha;
                System.out.println("Código de acesso alterado com sucesso.");
                return true;
            } else {
                System.out.println("Nova senha deve ter pelo menos 4 caracteres.");
                return false;
            }
        } else {
            System.out.println("Senha atual incorreta.");
            return false;
        }
    }

    // Método privado de validação de senha
    private boolean validarSenha(String senha) {
        return codigoAcesso.equals(senha);
    }

    // Exibe resumo da missão (sem dados restritos)
    public void exibirResumo() {
        System.out.println("====== DADOS DA MISSÃO ======");
        System.out.println("Missão       : " + nomeMissao);
        System.out.println("Tripulantes  : " + numeroDeTripulantes);
        System.out.println("Trajetória   : " + trajetoria);
        System.out.printf ("Combustível  : %.1f%%\n", nivelCombustivel);
        System.out.println("Coordenadas  : [PROTEGIDAS - necessário senha]");
        System.out.println("=============================");
    }
}

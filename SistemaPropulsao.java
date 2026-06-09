
public abstract class SistemaPropulsao extends ComponenteEspacial {

    private double potencia;       // Porcentagem de 0 a 100
    private double empuxoMaximo;   // Em kN (kilonewtons)

    public SistemaPropulsao(String id, String nome, double empuxoMaximo) {
        super(id, nome);
        this.potencia = 0.0;
        this.empuxoMaximo = empuxoMaximo;
    }

    // Método comum: liga o motor
    public void ligarMotor() {
        ligar(); // chama o método da classe mãe (ComponenteEspacial)
        System.out.println("[" + getNome() + "] Motor pronto para operação.");
    }

    // Método comum: desliga o motor
    public void desligarMotor() {
        this.potencia = 0.0;
        desligar(); // chama o método da classe mãe
        System.out.println("[" + getNome() + "] Motor desligado. Potência zerada.");
    }

    // Calcula o empuxo com base na potência atual
    public double calcularEmpuxo() {
        return empuxoMaximo * (potencia / 100.0);
    }

    // Método para acelerar - será sobrescrito pelas subclasses
    public void acelerar(double novaPotencia) {
        if (novaPotencia < 0 || novaPotencia > 100) {
            System.out.println("Potência inválida! Use valores entre 0 e 100.");
            return;
        }
        if (!isStatus()) {
            System.out.println("Motor desligado! Ligue antes de acelerar.");
            return;
        }
        this.potencia = novaPotencia;
    }

    // Exibe status da propulsão
    public void exibirStatusPropulsao() {
        exibirInfo();
        System.out.printf("Potência    : %.1f%%\n", potencia);
        System.out.printf("Empuxo      : %.2f kN\n", calcularEmpuxo());
        System.out.printf("Empuxo Máx. : %.2f kN\n", empuxoMaximo);
    }

    // Getters e Setters
    public double getPotencia() { return potencia; }
    public double getEmpuxoMaximo() { return empuxoMaximo; }

    protected void setPotencia(double potencia) {
        this.potencia = potencia;
    }
}


public class PropulsaoQuimica extends SistemaPropulsao {

    // Atributos específicos da propulsão química
    private String tipoCombustivel;
    private double consumoPorSegundo; // kg/s
    private double temperaturaDaChama; // °C

    public PropulsaoQuimica(String id, String nome, double empuxoMaximo,
                             String tipoCombustivel, double consumoPorSegundo) {
        super(id, nome, empuxoMaximo);
        this.tipoCombustivel = tipoCombustivel;
        this.consumoPorSegundo = consumoPorSegundo;
        this.temperaturaDaChama = 3500.0; // temperatura típica da chama
    }

    // Sobrescreve o método acelerar com comportamento específico
    @Override
    public void acelerar(double novaPotencia) {
        super.acelerar(novaPotencia); // chama o método da classe mãe para validação
        if (novaPotencia >= 0 && novaPotencia <= 100 && isStatus()) {
            System.out.printf("[%s] PROPULSÃO QUÍMICA ativada a %.1f%% de potência.%n", getNome(), novaPotencia);
            System.out.printf("[%s] Consumo de combustível: %.2f kg/s | Empuxo: %.2f kN%n",
                    getNome(), consumoPorSegundo * (novaPotencia / 100.0), calcularEmpuxo());
            temperaturaDaChama = 2000 + (novaPotencia * 15); // temperatura sobe com potência
        }
    }

    @Override
    public String obterDescricao() {
        return "Propulsão Química | Combustível: " + tipoCombustivel
                + " | Consumo: " + consumoPorSegundo + " kg/s"
                + " | Temp. da chama: " + String.format("%.0f", temperaturaDaChama) + " °C";
    }

    // Getters específicos
    public String getTipoCombustivel() { return tipoCombustivel; }
    public double getConsumoPorSegundo() { return consumoPorSegundo; }
    public double getTemperaturaDaChama() { return temperaturaDaChama; }
}

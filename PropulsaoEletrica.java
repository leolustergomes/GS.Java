
public class PropulsaoEletrica extends SistemaPropulsao {

    // Atributos específicos da propulsão elétrica
    private double potenciaEletrica;     // Watts consumidos
    private double impulsoEspecifico;    // Isp em segundos (eficiência)
    private String tipoIon;              // Ex: "Xenônio", "Kriptônio"

    public PropulsaoEletrica(String id, String nome, double empuxoMaximo,
                              double potenciaEletrica, String tipoIon) {
        super(id, nome, empuxoMaximo);
        this.potenciaEletrica = potenciaEletrica;
        this.tipoIon = tipoIon;
        this.impulsoEspecifico = 3000.0; // Isp típico de motor iônico
    }

    // Sobrescreve o método acelerar com comportamento específico
    @Override
    public void acelerar(double novaPotencia) {
        super.acelerar(novaPotencia); // chama o método da classe mãe para validação
        if (novaPotencia >= 0 && novaPotencia <= 100 && isStatus()) {
            System.out.printf("[%s] PROPULSÃO ELÉTRICA (íon de %s) ativada a %.1f%% de potência.%n",
                    getNome(), tipoIon, novaPotencia);
            double consumoEletrico = potenciaEletrica * (novaPotencia / 100.0);
            System.out.printf("[%s] Consumo elétrico: %.1f W | Empuxo: %.4f kN | Isp: %.0f s%n",
                    getNome(), consumoEletrico, calcularEmpuxo(), impulsoEspecifico);
            System.out.println("[" + getNome() + "] (Motor iônico: empuxo baixo, mas extremamente eficiente)");
        }
    }

    @Override
    public String obterDescricao() {
        return "Propulsão Elétrica (Iônica) | Ion: " + tipoIon
                + " | Potência: " + potenciaEletrica + " W"
                + " | Isp: " + impulsoEspecifico + " s";
    }

    // Getters específicos
    public double getPotenciaEletrica() { return potenciaEletrica; }
    public double getImpulsoEspecifico() { return impulsoEspecifico; }
    public String getTipoIon() { return tipoIon; }
}

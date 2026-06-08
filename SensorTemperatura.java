import java.util.Random;

/**
 * Sensor de Temperatura - implementa a interface Sensor.
 * Herda de ComponenteEspacial e monitora a temperatura dos módulos.
 */
public class SensorTemperatura extends ComponenteEspacial implements Sensor {

    private double limiteAlerta;   // Temperatura máxima permitida (°C)
    private double valorAtual;
    private Random random;

    public SensorTemperatura(String id, String nome, double limiteAlerta) {
        super(id, nome);
        this.limiteAlerta = limiteAlerta;
        this.valorAtual = 20.0;
        this.random = new Random();
    }

    @Override
    public double lerValor() {
        // Simula leitura com variação aleatória entre -5 e +10 graus
        valorAtual = 15.0 + random.nextDouble() * 80.0;
        System.out.println("[" + getNome() + "] Temperatura lida: " + String.format("%.1f", valorAtual) + " °C");
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        boolean ok = valorAtual > -273.15 && valorAtual < 1000.0;
        System.out.println("[" + getNome() + "] Funcionamento: " + (ok ? "OK" : "FALHA"));
        return ok;
    }

    @Override
    public String retornarTipo() {
        return "Temperatura";
    }

    @Override
    public boolean verificarAlerta() {
        return valorAtual > limiteAlerta;
    }

    @Override
    public double getLimiteAlerta() {
        return limiteAlerta;
    }

    @Override
    public String obterDescricao() {
        return "Sensor de Temperatura | Limite: " + limiteAlerta + " °C | Último valor: "
                + String.format("%.1f", valorAtual) + " °C";
    }

    public void setLimiteAlerta(double limite) {
        if (limite > 0) this.limiteAlerta = limite;
    }
}

import java.util.Random;

/**
 * Sensor de Radiação - implementa a interface Sensor.
 * Monitora o nível de radiação ionizante na estação espacial (em mSv/h).
 */
public class SensorRadiacao extends ComponenteEspacial implements Sensor {

    private double limiteAlerta;   // Limite máximo seguro de radiação (mSv/h)
    private double valorAtual;
    private Random random;

    public SensorRadiacao(String id, String nome, double limiteAlerta) {
        super(id, nome);
        this.limiteAlerta = limiteAlerta;
        this.valorAtual = 0.0;
        this.random = new Random();
    }

    @Override
    public double lerValor() {
        // Simula radiação entre 0.1 e 20 mSv/h
        valorAtual = 0.1 + random.nextDouble() * 19.9;
        System.out.println("[" + getNome() + "] Radiação lida: " + String.format("%.3f", valorAtual) + " mSv/h");
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        boolean ok = valorAtual >= 0;
        System.out.println("[" + getNome() + "] Funcionamento: " + (ok ? "OK" : "FALHA"));
        return ok;
    }

    @Override
    public String retornarTipo() {
        return "Radiação";
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
        return "Sensor de Radiação | Limite: " + limiteAlerta + " mSv/h | Último valor: "
                + String.format("%.3f", valorAtual) + " mSv/h";
    }
}

import java.util.Random;

public class SensorPressao extends ComponenteEspacial implements Sensor {

    private double limiteAlerta;   // Pressão mínima segura (kPa)
    private double valorAtual;
    private Random random;

    public SensorPressao(String id, String nome, double limiteAlerta) {
        super(id, nome);
        this.limiteAlerta = limiteAlerta;
        this.valorAtual = 101.3; // Pressão atmosférica padrão em kPa
        this.random = new Random();
    }

    @Override
    public double lerValor() {
        // Simula variação de pressão entre 70 e 110 kPa
        valorAtual = 70.0 + random.nextDouble() * 40.0;
        System.out.println("[" + getNome() + "] Pressão lida: " + String.format("%.2f", valorAtual) + " kPa");
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        boolean ok = valorAtual > 0;
        System.out.println("[" + getNome() + "] Funcionamento: " + (ok ? "OK" : "FALHA"));
        return ok;
    }

    @Override
    public String retornarTipo() {
        return "Pressão";
    }

    @Override
    public boolean verificarAlerta() {
        // Alerta se pressão cair abaixo do limite (perigo de despressurização)
        return valorAtual < limiteAlerta;
    }

    @Override
    public double getLimiteAlerta() {
        return limiteAlerta;
    }

    @Override
    public String obterDescricao() {
        return "Sensor de Pressão | Limite mínimo: " + limiteAlerta + " kPa | Último valor: "
                + String.format("%.2f", valorAtual) + " kPa";
    }
}

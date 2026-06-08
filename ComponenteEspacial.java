/**
 * Classe abstrata que representa um componente genérico da estação espacial.
 * Serve como "molde" para todos os componentes do sistema.
 */
public abstract class ComponenteEspacial {

    // Atributos comuns a todos os componentes
    private String id;
    private String nome;
    private boolean status; // true = ligado, false = desligado
    private double temperatura;

    // Construtor
    public ComponenteEspacial(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.status = false;
        this.temperatura = 20.0;
    }

    // Métodos concretos (comuns a todos)
    public void ligar() {
        this.status = true;
        System.out.println("[" + nome + "] Componente LIGADO.");
    }

    public void desligar() {
        this.status = false;
        System.out.println("[" + nome + "] Componente DESLIGADO.");
    }

    public void exibirInfo() {
        System.out.println("------------------------------");
        System.out.println("ID       : " + id);
        System.out.println("Nome     : " + nome);
        System.out.println("Status   : " + (status ? "LIGADO" : "DESLIGADO"));
        System.out.println("Temp.    : " + temperatura + " °C");
    }

    // Método abstrato - cada subclasse DEVE implementar
    public abstract String obterDescricao();

    // Getters e Setters
    public String getId() { return id; }
    public String getNome() { return nome; }
    public boolean isStatus() { return status; }
    public double getTemperatura() { return temperatura; }

    public void setTemperatura(double temperatura) {
        if (temperatura >= -273.15) { // Limite físico: zero absoluto
            this.temperatura = temperatura;
        } else {
            System.out.println("Temperatura inválida! Abaixo do zero absoluto.");
        }
    }
}

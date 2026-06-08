/**
 * Interface Sensor - "contrato" que todos os sensores devem cumprir.
 * Define os métodos obrigatórios para qualquer tipo de sensor.
 */
public interface Sensor {

    // Realiza a leitura do valor atual do sensor
    double lerValor();

    // Verifica se o sensor está funcionando corretamente
    boolean verificarFuncionamento();

    // Retorna o tipo do sensor (ex: "Temperatura", "Pressão")
    String retornarTipo();

    // Verifica se o valor lido ultrapassou o limite de alerta
    boolean verificarAlerta();

    // Retorna o limite máximo configurado
    double getLimiteAlerta();
}

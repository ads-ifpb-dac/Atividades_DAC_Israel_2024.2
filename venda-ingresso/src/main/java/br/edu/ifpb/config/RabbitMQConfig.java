package br.edu.ifpb.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Nome da fila que será usada para enviar e receber mensagens
    public static final String FILA_INGRESSO_COMPRADO = "ingresso.comprado";

    @Bean
    public Queue filaIngressoComprado() {
        return new Queue(FILA_INGRESSO_COMPRADO, true); // Fila durável
    }
}
package br.edu.ifpb.service;

import br.edu.ifpb.model.Compra;
import br.edu.ifpb.model.Ingresso;
import br.edu.ifpb.repository.CompraRepository;
import br.edu.ifpb.repository.IngressoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BilheteriaService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private IngressoRepository ingressoRepository;

    @RabbitListener(queues = RabbitMQConfig.FILA_INGRESSO_COMPRADO)
    public void receberMensagem(String mensagem) {
        System.out.println("Mensagem recebida: " + mensagem);
        Long compraId = extrairCompraIdDaMensagem(mensagem);

        if (compraId != null) {
            Compra compra = compraRepository.findById(compraId).orElse(null);

            if (compra != null) {

                Ingresso ingresso = compra.getIngresso();
                if (ingresso != null && !ingresso.isUtilizado()) {
                    ingresso.setUtilizado(true);
                    ingressoRepository.save(ingresso);
                    System.out.println("Ingresso ID " + ingresso.getId() + " marcado como utilizado.");
                } else {
                    System.out.println("Ingresso já utilizado ou inválido.");
                }
            } else {
                System.out.println("Compra não encontrada.");
            }
        } else {
            System.out.println("Mensagem inválida: não foi possível extrair o ID da compra.");
        }
    }

    private Long extrairCompraIdDaMensagem(String mensagem) {
        try {
            String[] partes = mensagem.split("Compra ID ")[1].split(",");
            return Long.parseLong(partes[0].trim());
        } catch (Exception e) {
            System.err.println("Erro ao extrair ID da compra da mensagem: " + e.getMessage());
            return null;
        }
    }
}
package io.github.geancarloslc;

import io.github.geancarloslc.domain.entity.Cliente;
import io.github.geancarloslc.domain.entity.Pedido;
import io.github.geancarloslc.domain.repository.ClienteDAO;
import io.github.geancarloslc.domain.repository.PedidoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired ClienteDAO clienteDAO,
            @Autowired PedidoDAO pedidoDAO
    ){
        return args ->{

            Cliente cliente = new Cliente("Gean");
            clienteDAO.save(cliente);

            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setDataPedido(LocalDate.now());
            pedido.setTotal(new BigDecimal(100.00));

            pedidoDAO.save(pedido);

            Cliente clienteAtualizado = clienteDAO.findClienteFetchPedidos(cliente.getId());
            System.out.println(clienteAtualizado);
            System.out.println(clienteAtualizado.getPedidos());

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}

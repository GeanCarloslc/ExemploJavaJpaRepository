package io.github.geancarloslc.domain.repository;

import io.github.geancarloslc.domain.entity.Cliente;
import io.github.geancarloslc.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoDAO extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente (Cliente cliente);

}

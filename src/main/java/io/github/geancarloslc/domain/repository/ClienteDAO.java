package io.github.geancarloslc.domain.repository;
import io.github.geancarloslc.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//QueryMethods
public interface ClienteDAO extends JpaRepository<Cliente, Integer> {

    //Like
    List<Cliente> findByNomeContains(String nome);

    List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

    @Query(value = " select c from Cliente c where c.nome like %:nome% ")
    List<Cliente> encontrarPorNomeJPQL(@Param("nome") String nome);

    @Query(value = " select * from cliente c where c.nome like :nome ", nativeQuery = true)
    List<Cliente> encontrarPorNomeSQL(@Param("nome") String nome);

    @Modifying// Usado quando não for apenas pesquisa, exemplo: update, insert e delete
    @Query(value = " delete from Cliente c where c.nome = :nome")
    List<Cliente> deletarPorNome(@Param("nome") String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id ")
    // Utilizando left traz os tendo pedido ou não
    Cliente findClienteFetchPedidos(@Param("id") Integer id );




    boolean existsByNome(String nome);

}

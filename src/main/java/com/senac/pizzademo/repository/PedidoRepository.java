
package com.senac.pizzademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.senac.pizzademo.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}


package com.cibertec.jama.repositories.pedido;

import com.cibertec.jama.entities.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository  extends JpaRepository<Pedido, Long> {

}

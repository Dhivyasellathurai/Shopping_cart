package com.example.shopping_cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.shopping_cart.dto.AddToCartInterface;
import com.example.shopping_cart.entity.AddToCart;

@Repository
public interface AddToCartRepository extends JpaRepository<AddToCart, Integer> {

	@Query(value = "select ac.id as id, c.name as customerName, p.product_name as productName, ac.product_price as productPrice, "
			+ "ac.total_amount as totalAmount, ac.quantity as quantity \r\n"
			+ "from cart ac inner join our_customers c on ac.customer_id=c.id \r\n"
			+ "inner join products p on ac.product_id=p.id \r\n"
			+ "where ac.customer_id=:customerId", nativeQuery = true)
	public AddToCartInterface findByCustomerId(Integer customerId);

	@Query(value = "select * from cart ac where ac.product_id =:productId and ac.customer_id =:customerId", nativeQuery = true)
	Optional<AddToCart> findProduct(int productId, int customerId);

}

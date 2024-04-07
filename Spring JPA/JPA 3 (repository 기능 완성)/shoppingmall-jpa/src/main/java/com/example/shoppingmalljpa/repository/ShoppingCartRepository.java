//package com.example.shoppingmalljpa.repository;
//
//
//import com.example.shoppingmalljpa.entity.ShoppingCart;
//import java.util.List;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface ShoppingCartRepository extends JpaRepository<ShoppingCart.Pk, Long> {
//
//    List<ShoppingCart.Pk> findByUserId(String userId);
//
//    void deleteByUserIdAndProductId(String userId, Long productId);
//
//    int updateAmountByUserIdAndProductId(String userId, Long productId, int newAmount);
//
//    int countByUserIdAndProductId(String userId, Long productId);
//}
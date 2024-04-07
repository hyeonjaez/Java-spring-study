package com.example.shoppingmalljpa.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @NotNull
    @Column(name = "user_id")
    private String userId;

    @NotNull
    @Column(name = "user_name")
    private String userName;

    @NotNull
    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_birth")
    private String userBirth;

    @NotNull
    @Column(name = "user_auth")
    private String userAuth;

    @NotNull
    @Column(name = "user_point")
    private Integer userPoint;

    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "latest_login_at")
    private LocalDateTime latestLoginAt;

    @OneToMany
    private List<ShoppingCart> shoppingCart;

    @OneToMany
    private List<OrderDetail> orderDetail;

    @OneToMany
    private List<Order> order;

    @OneToMany
    private List<UserAddress> userAddress;

//    @OneToMany(mappedBy = "user")
//    private List<Us>
}

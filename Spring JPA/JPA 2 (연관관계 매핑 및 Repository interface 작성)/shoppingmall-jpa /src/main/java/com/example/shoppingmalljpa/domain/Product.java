package com.example.shoppingmalljpa.domain;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Products")
public class Product {
    @Id
    @NotNull
    @Column(name = "ProductID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotNull
    @Column(name = "ModelNumber")
    private String modelNumber;

    @NotNull
    @Column(name = "ModelName")
    private String modelName;

    @Column(name = "ProductImage")
    private String productImage;

    @NotNull
    @Column(name = "UnitCost")
    private BigDecimal unitCost;

    @Column(name = "Description")
    private String description;


}

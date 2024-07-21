package org.example.ecommerce.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "web_order_quantities")
public class WebOrderQuantities {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

        @ManyToOne(optional = false)
        @JoinColumn(name = "product_id", nullable = false)
        private Product product;

        @Column(name = "quantity", nullable = false)
        private Integer quantity;

        @ManyToOne(optional = false)
        @JoinColumn(name = "order_id", nullable = false)
        private WebOrder order;

        public WebOrder getOrder() {
                return order;
        }

        public void setOrder(WebOrder order) {
                this.order = order;
        }

        public Integer getQuantity() {
                return quantity;
        }

        public void setQuantity(Integer quantity) {
                this.quantity = quantity;
        }

        public Product getProduct() {
                return product;
        }

        public void setProduct(Product product) {
                this.product = product;
        }

        public Long getId() {
                return id;
        }
}

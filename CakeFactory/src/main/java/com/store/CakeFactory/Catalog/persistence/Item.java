package com.store.CakeFactory.Catalog.persistence;


import lombok.*;
import org.hibernate.validator.constraints.Normalized;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "catalog")
@Data
@NoArgsConstructor
public class Item {

    @Id
    public String sku;

    @NotBlank
    public String title;

    @NotNull
    public BigDecimal price;

    public Item(@NotBlank String title, @NotNull BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item other = (Item) obj;
            return Objects.equals(this.sku, other.sku);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.sku);
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

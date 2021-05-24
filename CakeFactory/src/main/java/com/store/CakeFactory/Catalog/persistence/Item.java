package com.store.CakeFactory.Catalog.persistence;


import lombok.*;

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
}
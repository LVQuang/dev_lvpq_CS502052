package dev.lvpq.CS502052.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailKey implements Serializable {
    String invoice;
    String product;
}

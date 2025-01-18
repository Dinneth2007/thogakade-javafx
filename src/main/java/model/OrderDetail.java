package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OrderDetail {
    private String Orderid;
    private String Itemcode;
    private int Qty;
    private double UnitPrice;
}

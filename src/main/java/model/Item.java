package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Item {
    private String Itemcode;
    private String desc;
    private double unitPrice;
    private int QtyOnHand;
}

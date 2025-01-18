package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartTableModel {
    private String ItemCode;
    private String Desc;
    private int QTY;
    private double unitPrice;
    private double total;


}

package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class Order {
    private String id;
    private String date;
    private String customer_id;
    private ArrayList<OrderDetail> orderDetailList;
}

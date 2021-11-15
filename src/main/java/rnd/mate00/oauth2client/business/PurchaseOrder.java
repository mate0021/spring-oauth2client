package rnd.mate00.oauth2client.business;

import lombok.Data;
import rnd.mate00.oauth2client.user.DbUser;

import javax.persistence.*;

@Entity
@Table(name = "purchase_order")
@Data
public class PurchaseOrder {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private int orderId;

    @Column
    private String productName;

    @OneToOne
    private DbUser user;
}

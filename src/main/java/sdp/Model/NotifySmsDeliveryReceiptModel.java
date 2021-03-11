
package sdp.Model;



import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Suvonkar Kundu on 11/5/2017.
 */
@Entity
@Table(name = "tbl_sms_delivery")
public class NotifySmsDeliveryReceiptModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;
    @Column(name = "correlator")
    String correlator;
    @Column(name = "address")
    String address;
    @Column(name = "delivery_status")
    String deliveryStatus;
    @Column(name = "created_at")
    Timestamp created_at;
    @Column(name = "updated_at")
    Timestamp updated_at;

    public NotifySmsDeliveryReceiptModel() {
    }

    public NotifySmsDeliveryReceiptModel(String correlator, String address, String deliveryStatus, Timestamp created_at, Timestamp updated_at) {
        this.correlator = correlator;
        this.address = address;
        this.deliveryStatus = deliveryStatus;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCorrelator() {
        return correlator;
    }

    public void setCorrelator(String correlator) {
        this.correlator = correlator;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}



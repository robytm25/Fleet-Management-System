package app.vehiclemanagement.accounts.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TransactionStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   private String description;
    private String details;

    @ManyToOne
    @JoinColumn(name = "transactionid", insertable = false, updatable = false)
    private Transaction transaction;
    private Integer transactionid;

    @OneToMany(mappedBy = "transactionStatus")
    private List<Transaction> transactions;


}

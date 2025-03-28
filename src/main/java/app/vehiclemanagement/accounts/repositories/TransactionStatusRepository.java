package app.vehiclemanagement.accounts.repositories;



import app.vehiclemanagement.accounts.models.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionStatusRepository extends JpaRepository<TransactionStatus, Integer> {
}

package com.upperlink.fcmb.Repository;


import com.upperlink.fcmb.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Long> {
}

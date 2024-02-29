package com.natixis.transfers.repos;

import com.natixis.transfers.domain.Transfer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransferRepository extends MongoRepository<Transfer, String> {
    List<Transfer> findByDate(LocalDate date);
}

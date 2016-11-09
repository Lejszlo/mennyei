package com.mennyei.core.transfer.infrastructure;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mennyei.core.transfer.aggregator.Transfer;
import com.mennyei.core.transfer.service.TransferRepository;

public interface TransferMemoryDao extends TransferRepository, JpaRepository<Transfer, Long> {

}

package com.mennyei.core.transfer.service;

import org.springframework.stereotype.Repository;

import com.mennyei.core.transfer.aggregator.Transfer;

@Repository
public interface TransferRepository {

	void save(Transfer transfer);
}

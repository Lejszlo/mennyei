package com.mennyei.core.transfer.infrastructure;


import org.springframework.stereotype.Repository;

import com.mennyei.core.transfer.aggregator.Transfer;
import com.mennyei.core.transfer.service.TransferRepository;

@Repository
public class TransferMemoryDao implements TransferRepository {

	@Override
	public void save(Transfer transfer) {
		// TODO Auto-generated method stub
	}

}

package com.db.trade.processing.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.trade.processing.entity.Trade;
import com.db.trade.processing.repository.TradeStoreRepository;
import com.db.trade.processing.util.StoreStatus;
import com.db.trade.processing.util.TradeExpiryFlag;

@Service
public class TradeStoreServiceImpl implements TradeStoreService {

    @Autowired
    private TradeStoreRepository tradeRepository;

    @Override
    public StoreStatus isValid(Trade trade) {
        StoreStatus status = StoreStatus.REJECT_INVALID_MATURITY;
        if (hasValidMaturityDate(trade)) {
            Optional<Trade> exsitingTrade = this.tradeRepository.findTrade(trade.getTradeId());
            if (exsitingTrade.isPresent()) {
                status = hasValidVersion(trade, exsitingTrade.get()) ? StoreStatus.OK
                        : StoreStatus.REJECT_INVALID_VERSION;
            } else {
                status = StoreStatus.OK;
            }
        }
        return status;
    }

    @Override
    public void persist(Trade trade) {
        trade.setCreatedDate(LocalDate.now());
        tradeRepository.save(trade);
    }

    @Override
    public List<Trade> findAll() {
        return this.tradeRepository.findAll();
    }

    @Override
    public void expireTrade() {
        this.tradeRepository.findAll().stream().forEach(t -> {
            if (!hasValidMaturityDate(t)) {
                t.setExpiredFlag(TradeExpiryFlag.YES.getFlag());
                this.tradeRepository.save(t);
            }
        });

    }

    @Override
    public Optional<Trade> findTrade(String tradeId) {
        return this.tradeRepository.findTrade(tradeId);
    }

}

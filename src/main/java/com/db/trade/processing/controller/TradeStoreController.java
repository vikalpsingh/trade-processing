package com.db.trade.processing.controller;

import java.util.List;

import com.db.trade.processing.entity.Trade;
import com.db.trade.processing.service.TradeStoreService;
import com.db.trade.processing.util.InvalidTradeException;
import com.db.trade.processing.util.StoreStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;


@Component
public class TradeStoreController {

    @Autowired
    private TradeStoreService tradeService;

    public StoreStatus storeTrade(Trade trade) {
        StoreStatus status = this.tradeService.isValid(trade);

        if (StoreStatus.OK.equals(status)) {
            tradeService.persist(trade);
        } else {
            throw new InvalidTradeException(trade, status.getMessage());
        }
        return status;
    }

    public List<Trade> findAllTrades() {
        return this.tradeService.findAll();
    }
}

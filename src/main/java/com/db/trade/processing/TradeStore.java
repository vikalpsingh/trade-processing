package com.db.trade.processing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.db.trade.processing.entity.Trade;
import com.db.trade.processing.util.TradeValidationException;


public class TradeStore {
    private List<Trade> trades;

    public TradeStore() {
        trades = new ArrayList<>();
    }

    public void addTrade(Trade newTrade) throws TradeValidationException {
        for (Trade existingTrade : trades) {
            if (existingTrade.getTradeId().equals(newTrade.getTradeId())) {
                if (newTrade.getVersion() < existingTrade.getVersion()) {
                    throw new TradeValidationException("Lower version trade received.");
                } else if (newTrade.getVersion() == existingTrade.getVersion()) {
                    trades.remove(existingTrade); // Remove the existing trade
                    break;
                }
            }
        }

        if (newTrade.getMaturityDate().before(new Date())) {
            throw new TradeValidationException("Trade has an expired maturity date.");
        }

        trades.add(newTrade);
    }

    public void updateExpiredTrades() {
        Date currentDate = new Date();
        for (Trade trade : trades) {
            if (trade.getMaturityDate().before(currentDate)) {
                trade.setExpired(true);
            }
        }
    }

    public List<Trade> getAllTrades() {
        return trades;
    }
}

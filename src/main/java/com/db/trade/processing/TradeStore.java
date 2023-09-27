/**
 * Trade store class which does trade validation before calling persistence service
 * It also provides trade retrieval service
 * @author  Vikalp
 * @version 1.0
 * @since   2023-09-26
 */

package com.db.trade.processing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;


import com.db.trade.processing.entity.Trade;
import com.db.trade.processing.util.TradeExpiryFlag;
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

        if (newTrade.getMaturityDate().isBefore(LocalDate.now())) {
            throw new TradeValidationException("Trade has an expired maturity date.");
        }

       // trades.add(newTrade);
    }

    public void updateExpiredTrades() {
        LocalDate currentDate = LocalDate.now();
        for (Trade trade : trades) {
            if (trade.getMaturityDate().isBefore(currentDate)) {
                trade.setExpired(TradeExpiryFlag.YES.getFlag());
            }
        }
    }

    public List<Trade> getAllTrades() {
        return trades;
    }
}

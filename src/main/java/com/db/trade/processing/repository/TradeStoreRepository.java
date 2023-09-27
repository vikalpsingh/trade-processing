package com.db.trade.processing.repository;

import java.util.List;
import java.util.Optional;

import com.db.trade.processing.entity.Trade;
/**
 * Trade store repository API
 *
 * @author Vikalp Singh
 *
 */
public interface TradeStoreRepository {

    /**
     * Save trade
     *
     * @param trade
     */
    void save(Trade trade);

    /**
     * Find all trades
     *
     * @return
     */
    List<Trade> findAll();

    /**
     * Fins trade by trade id
     *
     * @param tradeId
     * @return
     */
    Optional<Trade> findTrade(String tradeId);

}
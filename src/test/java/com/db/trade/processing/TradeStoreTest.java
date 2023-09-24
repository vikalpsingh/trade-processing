package com.db.trade.processing;

import com.db.trade.processing.entity.Trade;
import com.db.trade.processing.util.TradeValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TradeStoreTest {

    private TradeStore tradeStore;
    private static final Logger LOGGER = LogManager.getLogger(TradeStoreTest.class.getName());

    @Before
    public void setUp() {
        tradeStore = new TradeStore();
    }

    @Test
    public void testAddValidTrade() throws TradeValidationException {
        LOGGER.info("testAddValidTrade!");
        Trade validTrade = new Trade("T1", 1, "CP1", "B1", new Date(), new Date(), false);
        tradeStore.addTrade(validTrade);
        List<Trade> trades = tradeStore.getAllTrades();
        assertEquals(1, trades.size());

        assertEquals(validTrade, trades.get(0));
    }

    @Test(expected = TradeValidationException.class)
    public void testAddTradeWithLowerVersion() throws TradeValidationException {
        LOGGER.debug("testAddTradeWithLowerVersion!");
        Trade trade1 = new Trade("T1", 2, "CP1", "B1", new Date(), new Date(), false);
        Trade trade2 = new Trade("T1", 1, "CP1", "B1", new Date(), new Date(), false);
        tradeStore.addTrade(trade1);
        tradeStore.addTrade(trade2); // This should throw an exception
    }

    @Test(expected = TradeValidationException.class)
    public void testAddTradeWithExpiredMaturityDate() throws TradeValidationException {
        LOGGER.debug("testAddTradeWithExpiredMaturityDate!");
        Date pastDate = new Date(System.currentTimeMillis() - 86400000); // 24 hours ago
        Trade expiredTrade = new Trade("T2", 1, "CP2", "B2", pastDate, new Date(), false);
        tradeStore.addTrade(expiredTrade); // This should throw an exception
    }

    @Test
    public void testUpdateExpiredTrades() throws TradeValidationException {
        Date futureDate = new Date(System.currentTimeMillis() + 86400000); // 24 hours from now
        Trade nonExpiredTrade = new Trade("T3", 1, "CP3", "B3", futureDate, new Date(), false);
        tradeStore.addTrade(nonExpiredTrade);
        tradeStore.updateExpiredTrades();
        List<Trade> trades = tradeStore.getAllTrades();
        assertFalse(trades.isEmpty());
        assertFalse(trades.get(0).isExpired());
    }

    @Test(expected = TradeValidationException.class)
    public void testUpdateExpiredTradeFlag() throws TradeValidationException {
        Date pastDate = new Date(System.currentTimeMillis() - 86400000); // 24 hours ago
        Trade expiredTrade = new Trade("T4", 1, "CP4", "B4", pastDate, new Date(), false);
        tradeStore.addTrade(expiredTrade);
        tradeStore.updateExpiredTrades();
        List<Trade> trades = tradeStore.getAllTrades();
        assertFalse(trades.isEmpty());
        assertTrue(trades.get(0).isExpired());
    }

    public static void main(String[] args)
    {
        LOGGER.debug("Debug Message Logged !!!");
        LOGGER.info("Info Message Logged !!!");
        LOGGER.error("Error Message Logged !!!", new NullPointerException("NullError"));
    }
}


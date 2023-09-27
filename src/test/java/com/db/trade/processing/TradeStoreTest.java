/**
 * Trade persistence test class
 * @author  Vikalp
 * @version 1.0
 * @since   2023-09-26
 */
package com.db.trade.processing;

import com.db.trade.processing.entity.Trade;
import com.db.trade.processing.util.TradeValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class TradeStoreTest {


    private TradeStore tradeStore;
    private static final Logger LOGGER = LogManager.getLogger(TradeStoreTest.class.getName());

    @Before
    public void setUp() {
        tradeStore = new TradeStore();
    }

    /*
    @desc : Add basic trade in store
     */
/*    @Test
    public void testAddValidTrade() throws TradeValidationException {
        LOGGER.info("testAddValidTrade!");
        Trade validTrade = new Trade("T1", 1, "CP1", "B1", LocalDate.now(), LocalDate.now(), false);
        tradeStore.addTrade(validTrade);
        List<Trade> trades = tradeStore.getAllTrades();
        assertEquals(1, trades.size());

        assertEquals(validTrade, trades.get(0));
    }*/

    /*
    @desc : Validate trade version
    */
/*    @Test(expected = TradeValidationException.class)
    public void testAddTradeWithLowerVersion() throws TradeValidationException {
        LOGGER.debug("testAddTradeWithLowerVersion!");
        Trade trade1 = new Trade("T1", 2, "CP1", "B1", LocalDate.now(), LocalDate.now(), false);
        Trade trade2 = new Trade("T1", 1, "CP1", "B1", LocalDate.now(), LocalDate.now(), false);
        tradeStore.addTrade(trade1);
        tradeStore.addTrade(trade2); // This should throw an exception
    }*/

    /*
    @desc : Validate` trade maturity
    */
/*    @Test(expected = TradeValidationException.class)
    public void testAddTradeWithExpiredMaturityDate() throws TradeValidationException {
        LOGGER.debug("testAddTradeWithExpiredMaturityDate!");
        Date pastDate = new Date(System.currentTimeMillis() - 86400000); // 24 hours ago
        Trade expiredTrade = new Trade("T2", 1, "CP2", "B2", pastDate, LocalDate.now(), false);
        tradeStore.addTrade(expiredTrade); // This should throw an exception
    }*/

    /*
    @desc : Service to update expired trade based on trade maturity date older than today
    */
/*    @Test
    public void testUpdateExpiredTrades() throws TradeValidationException {
        Date futureDate = new Date(System.currentTimeMillis() + 86400000); // 24 hours from now
        Trade nonExpiredTrade = new Trade("T3", 1, "CP3", "B3", futureDate, LocalDate.now(), false);
        tradeStore.addTrade(nonExpiredTrade);
        tradeStore.updateExpiredTrades();
        List<Trade> trades = tradeStore.getAllTrades();
        assertFalse(trades.isEmpty());
        assertFalse(trades.get(0).isExpired());
    }*/

    /*
     Added trade as per test data
     */
/*    @Test
    public void testAddTrade_NewTrade() throws TradeValidationException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date maturityDate = dateFormat.parse("20/05/2024");
        Date createdDate = new Date();

        Trade trade = new Trade("T1", 1, "CP-1", "B1", maturityDate, createdDate, false);

        tradeStore.addTrade(trade);

        List<Trade> trades = tradeStore.getAllTrades();
        assertEquals(1, trades.size());
        assertEquals(trade, trades.get(0));
    }*/

    /*
    Adding same trade version in store
    */
/*    @Test (expected = TradeValidationException.class)
    public void testAddTrade_SameVersion() throws TradeValidationException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date maturityDate = dateFormat.parse("20/05/2024");
        Date createdDate = new Date();

        Trade trade1 = new Trade("T2", 1, "CP-2", "B1", maturityDate, createdDate, false);
        Trade trade2 = new Trade("T2", 2, "CP-1", "B1", maturityDate, createdDate, false);

        tradeStore.addTrade(trade1);
        tradeStore.addTrade(trade2);

        List<Trade> trades = tradeStore.getAllTrades();
        assertEquals(1, trades.size());
        assertEquals(trade1, trades.get(0));
    }*/

/*    @Test
    public void testAddTrade_LowerVersion() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date maturityDate = dateFormat.parse("20/05/2024");
        Date createdDate = new SimpleDateFormat("dd/MM/yyyy").parse("14/03/2024");

        Trade trade1 = new Trade("T2", 1, "CP-1", "B1", maturityDate, createdDate, false);
        Trade trade2 = new Trade("T2", 2, "CP-2", "B1", maturityDate, createdDate, false);
        try {
            tradeStore.addTrade(trade2);
        } catch (TradeValidationException e) {
            throw new RuntimeException(e);
        }
        assertThrows(TradeValidationException.class, () -> {
                        tradeStore.addTrade(trade1);
        });

        List<Trade> trades = tradeStore.getAllTrades();
        assertEquals(1, trades.size());
        assertEquals(trade2, trades.get(0));
    }*/

/*    @Test
    public void testAddTrade_ExpiredMaturityDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date maturityDate = dateFormat.parse("20/05/2014");
        Date createdDate = new Date();

        Trade trade = new Trade("T3", 3, "CP-3", "B2", maturityDate, createdDate, false);

        assertThrows(TradeValidationException.class, () -> {
            tradeStore.addTrade(trade);
        });

        List<Trade> trades = tradeStore.getAllTrades();
        assertEquals(0, trades.size());
    }*/
/*
    public static void main(String[] args)
    {
        LOGGER.debug("Debug Message Logged !!!");
        LOGGER.info("Info Message Logged !!!");
        LOGGER.error("Error Message Logged !!!", new NullPointerException("NullError"));
    }
    */
}


package com.db.trade.processing.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import com.db.trade.processing.service.TradeStoreService;
import com.db.trade.processing.TradeStoreApplication;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.db.trade.processing.util.*;
import com.db.trade.processing.entity.Trade;

@SpringJUnitConfig(TradeStoreApplication.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(args = "--spring.application.run.mode=DEV")
@TestMethodOrder(OrderAnnotation.class)
class TradeStoreControllerTest {

    @SpyBean
    private TradeStoreController controller;

    @SpyBean
    private TradeStoreService tradeStoreService;

    @Test
    @Order(1)
    void testStoreTrade() {
        StoreStatus status = this.controller
                .storeTrade(new Trade("T6", 2, "CP-1", "B1", LocalDate.of(2024, 5, 20), LocalDate.now(), "N"));

        assertSame(StoreStatus.OK, status);
    }

    @Test
    @Order(2)
    void testStoreTradeExpectedInvalidTradeExceptionForVersion() {
        Trade trade = new Trade("T6", 1, "CP-1", "B1", LocalDate.of(2024, 5, 20), LocalDate.now(), "N");
        assertThrows(InvalidTradeException.class, () -> this.controller.storeTrade(trade));
    }

    @Test
    @Order(3)
    void testStoreTradeExpectedInvalidTradeExceptionForMaturityDate() {
        Trade trade = new Trade("T6", 1, "CP-1", "B1", LocalDate.of(2021, 5, 20), LocalDate.now(), "N");
        assertThrows(InvalidTradeException.class, () -> this.controller.storeTrade(trade));
    }

    @Test
    @Order(4)
    void testFindAllTrades() {
        assertTrue(this.controller.findAllTrades().size() > 0);
    }

    @Test
    @Order(5)
    void testOverrideTradeWithSameVersion() {
        Trade trade = new Trade("T6", 2, "CP-1", "B1", LocalDate.of(2024, 6, 20), LocalDate.now(), "N");

        assertSame(StoreStatus.OK, this.controller.storeTrade(trade));

        assertEquals(trade.getMaturityDate(), this.tradeStoreService.findTrade("T6").get().getMaturityDate());
    }

}

/**
 * Trade Entity class to present trade / It can be backed up DTO for upstream and JDO for
 * persistence
 * @author  Vikalp
 * @version 1.0
 * @since   2023-09-26
 */
package com.db.trade.processing.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.time.LocalDate;

public class Trade {

	private String tradeId;

      private int version;

    private String counterPartyId;
    private String bookId;
    private LocalDate  maturityDate;
    private LocalDate  createdDate;
    private String expired;

    public Trade(String tradeId, int version, String counterPartyId, String bookId, LocalDate  maturityDate, LocalDate  createdDate, String expired) {
        this.tradeId = tradeId;
        this.version = version;
        this.counterPartyId = counterPartyId;
        this.bookId = bookId;
        this.maturityDate = maturityDate;
        this.createdDate = createdDate;
        this.expired = expired;
    }


    public String getTradeId() {
        return tradeId;
    }

    public int getVersion() {
        return version;
    }

    public LocalDate  getMaturityDate() {
        return maturityDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setExpired(String  expired) {
        this.expired = expired;
    }

    public String isExpired(){
        return this.expired;
    }


    public void setExpiredFlag(String expiredFlag) {
        this.expired = expiredFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trade)) return false;
        Trade trade = (Trade) o;
        return version == trade.version && expired == trade.expired && Objects.equals(tradeId, trade.tradeId) && Objects.equals(counterPartyId, trade.counterPartyId) && Objects.equals(bookId, trade.bookId) && Objects.equals(maturityDate, trade.maturityDate) && Objects.equals(createdDate, trade.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeId, version, counterPartyId, bookId, maturityDate, createdDate, expired);
    }
}

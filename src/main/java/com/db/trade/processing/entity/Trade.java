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

public class Trade {

	private String tradeId;

      private int version;

    public Trade(String tradeId, int version, String counterPartyId, String bookId, Date maturityDate, Date createdDate, boolean expired) {
        this.tradeId = tradeId;
        this.version = version;
        this.counterPartyId = counterPartyId;
        this.bookId = bookId;
        this.maturityDate = maturityDate;
        this.createdDate = createdDate;
        this.expired = expired;
    }

    private String counterPartyId;
    private String bookId;
    private Date maturityDate;
    private Date createdDate;
    private boolean expired;


    public String getTradeId() {
        return tradeId;
    }

    public int getVersion() {
        return version;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isExpired(){
        return this.expired;
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

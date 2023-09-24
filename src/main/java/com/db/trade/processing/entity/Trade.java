package com.db.trade.processing.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}

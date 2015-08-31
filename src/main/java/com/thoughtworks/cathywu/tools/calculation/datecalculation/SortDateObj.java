package com.thoughtworks.cathywu.tools.calculation.datecalculation;

import java.util.Date;

/**
 * @author lzwu
 * @since 7/14/15
 */
public class SortDateObj {

    private int orderNo;
    private Date originDate;
    private Date convertedDate;

    public SortDateObj(int orderNo, Date originDate) {
        this.orderNo = orderNo;
        this.originDate = originDate;
    }

    public void setConvertedDate(Date convertedDate) {
        this.convertedDate = convertedDate;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public Date getOriginDate() {
        return originDate;
    }

    public Date getConvertedDate() {
        return convertedDate;
    }
}

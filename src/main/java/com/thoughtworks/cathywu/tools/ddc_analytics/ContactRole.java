package com.thoughtworks.cathywu.tools.ddc_analytics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lzwu
 * @since 7/24/15
 */
public class ContactRole implements Comparable<ContactRole> {
    private String customerCode;
    private String contactId;
    private boolean isPrimary;
    private String contactType;
    private Date createdDate;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    public ContactRole(String customerCode, String contactId, String isPrimary, String contactType, String createdDate) throws ParseException {
        this.customerCode = customerCode;
        this.contactId = contactId;
        this.isPrimary = isPrimary.equals("Y") ? true : false;
        this.contactType = contactType;
        this.createdDate = simpleDateFormat.parse(createdDate);
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public String getContactId() {
        return contactId;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public String getContactType() {
        return contactType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public int compareTo(ContactRole o) {
        return this.getContactType().compareTo(o.contactType);
    }
}

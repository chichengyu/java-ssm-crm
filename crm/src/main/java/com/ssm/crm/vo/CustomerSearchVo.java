package com.ssm.crm.vo;

import lombok.Data;

/**
 * 搜索参数
 */

public class CustomerSearchVo {
    private String customerName;
    private String custSource;
    private String custIndustory;
    private String custLevel;

    public CustomerSearchVo() {
    }

    public CustomerSearchVo(String customerName, String custSource, String custIndustory, String custLevel) {
        this.customerName = customerName;
        this.custSource = custSource;
        this.custIndustory = custIndustory;
        this.custLevel = custLevel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustory() {
        return custIndustory;
    }

    public void setCustIndustory(String custIndustory) {
        this.custIndustory = custIndustory;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    @Override
    public String toString() {
        return "CustomerVo{" +
                "customerName='" + customerName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustory='" + custIndustory + '\'' +
                ", custLevel='" + custLevel + '\'' +
                '}';
    }
}

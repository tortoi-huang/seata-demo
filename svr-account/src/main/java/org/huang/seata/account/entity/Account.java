package org.huang.seata.account.entity;

public class Account {
    private Long id;
    private Long account;

    public Account() {
    }

    public Account(Long id, Long account) {
        this.id = id;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }
}

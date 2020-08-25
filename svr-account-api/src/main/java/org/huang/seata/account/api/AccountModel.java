package org.huang.seata.account.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel implements Serializable {
    private Long id;
    private Long account;
    private Long scoreId;
    private Long score;
}

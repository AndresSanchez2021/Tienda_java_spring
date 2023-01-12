package com.bool.market.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Purchase {

    private int PurchaseId;
    private String clienteId;
    private LocalDateTime date;
    private String paymentMethod;
    private String comment;
    private String state;
    private List<PurchaseItem> item;
}

package org.example.controller;

import org.example.gui.views.start.RegisterPanel;
import org.example.service.AccountServiceImpl;

public class AccountController {

    private final AccountServiceImpl accountService;
    private RegisterPanel registerPanel;

    public AccountController() {
        this.accountService = new AccountServiceImpl();
    }


}

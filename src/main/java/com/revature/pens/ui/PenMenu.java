package com.revature.pens.ui;

import com.revature.pens.services.PenService;
import com.revature.pens.util.annotations.Inject;

public class PenMenu implements IMenu{
    @Inject
    private final PenService penService;

    public PenMenu(PenService penService){
        this.penService = penService;
    }
    @Override
    public void start() {

    }
}

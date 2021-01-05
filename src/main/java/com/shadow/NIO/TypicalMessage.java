package com.shadow.NIO;

public class TypicalMessage {

    private TypeMessages TYPE_MESSAGE;
    private String MESSAGE;

    public TypicalMessage(TypeMessages TYPE_MESSAGE, String MESSAGE) {
        this.TYPE_MESSAGE = TYPE_MESSAGE;
        this.MESSAGE = MESSAGE;
    }

    public TypicalMessage() {
    }

    public TypeMessages getTYPE_MESSAGE() {
        return TYPE_MESSAGE;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }


}

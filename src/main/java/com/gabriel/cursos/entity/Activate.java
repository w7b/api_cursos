package com.gabriel.cursos.entity;

public enum Activate {
    ENABLED(true),
    DISABLE(false);

    private final Boolean status;

    Activate(Boolean status) {
        this.status = status;
    }

    public Boolean isStatus() {
        return status;
    }
}

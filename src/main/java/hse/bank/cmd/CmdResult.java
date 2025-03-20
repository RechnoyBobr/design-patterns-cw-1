package hse.bank.cmd;

import hse.bank.domains.BankAccount;
import hse.bank.domains.Category;
import hse.bank.domains.Operation;
import hse.bank.enums.DomainObjectType;

public class CmdResult<T> {
    private final T data;
    private final String error;

    public CmdResult(T data, String error) {
        this.data = data;
        this.error = error;
    }

    public static <R> CmdResult<R> success(R data) {
        return new CmdResult<>(data, null);
    }

    public static CmdResult<Void> success() {
        return new CmdResult<>(null, null);
    }

    public static <R> CmdResult<R> failure(String error) {
        return new CmdResult<>(null, error);
    }

    public boolean isSuccess() {
        return error == null;
    }

    public BankAccount getBankAccount() {
        if (data instanceof BankAccount) {
            return (BankAccount) data;
        }
        return null;
    }

    public Operation getOperation() {
        if (data instanceof Operation) {
            return (Operation) data;
        }
        return null;
    }
    public Category getCategory() {
        if (data instanceof Category) {
            return (Category) data;
        }
        return null;
    }

    public String getError() {
        return error;
    }
}
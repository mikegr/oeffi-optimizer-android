
package com.mintplex.oeffioptimizer;

public class AsyncTaskResult<T> {

    private Exception ex;
    private T result;

    public AsyncTaskResult(Exception ex) {
        super();
        this.ex = ex;
    }

    public AsyncTaskResult(T result) {
        super();
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public Exception getException() {
        return ex;
    }

    public boolean hasError() {
        return (ex != null);
    }
}

package com.mintplex.oeffioptimizer;

public class LoaderResult<T> {

	private T result;
	private Exception ex;
	
	
	public LoaderResult(T result) {
		super();
		this.result = result;
	}
	
	public LoaderResult(Exception ex) {
		this.ex = ex;
	}

	public T getResult() {
		return result;
	}
	
	public boolean hasError() {
		return (ex != null);
	}
	
	public Exception getError() {
		return ex;
	}
}

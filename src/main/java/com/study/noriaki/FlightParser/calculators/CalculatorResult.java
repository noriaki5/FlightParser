package com.study.noriaki.FlightParser.calculators;

public class CalculatorResult<T> {
    private T result;
    private String resultInfo;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }
}
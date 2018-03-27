package com.ledo.core.vos;

import java.io.Serializable;
import java.util.List;

/**
 * 列表VO
 * Created by konghang
 */
public class ResultsVO<T> implements Serializable {

    /**
     * 当前页返回数据列表
     */
    private List<T> results;

    public static <T> ResultsVO<T> build(List<T> results) {
        return new ResultsVO<T>().setResults(results);
    }

    public List<T> getResults() {
        return results;
    }

    public ResultsVO<T> setResults(List<T> results) {
        this.results = results;
        return this;
    }

    @Override
    public String toString() {
        return "ResultsDTO{" +
                "results=" + results +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultsVO<?> listVO = (ResultsVO<?>) o;

        return !(results != null ? !results.equals(listVO.results) : listVO.results != null);

    }

    @Override
    public int hashCode() {
        return results != null ? results.hashCode() : 0;
    }
}

package com.ledo.core.vos;

import java.io.Serializable;
import java.util.List;

/**
 * Created by konghang
 */
public class ResultsTotalVO<T> implements Serializable {

    /**
     * 当前页返回数据列表
     */
    private List<T> results;

    private Long totalCount;

    public static <T> ResultsTotalVO<T> build() {
        return new ResultsTotalVO<T>();
    }

    public static <T> ResultsTotalVO<T> build(List<T> results) {
        return new ResultsTotalVO<T>().setResults(results).setTotalCount(new Long(results.size()));
    }

    public static <T> ResultsTotalVO<T> build(List<T> results, Long totalCount) {
        return new ResultsTotalVO<T>().setResults(results).setTotalCount(totalCount);
    }

    public List<T> getResults() {
        return results;
    }

    public ResultsTotalVO<T> setResults(List<T> results) {
        this.results = results;
        return this;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public ResultsTotalVO setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    @Override
    public String toString() {
        return "ResultsTotalDTO{" +
                "results=" + results +
                ", totalCount=" + totalCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultsTotalVO<?> that = (ResultsTotalVO<?>) o;

        if (results != null ? !results.equals(that.results) : that.results != null) return false;
        return !(totalCount != null ? !totalCount.equals(that.totalCount) : that.totalCount != null);

    }

    @Override
    public int hashCode() {
        int result = results != null ? results.hashCode() : 0;
        result = 31 * result + (totalCount != null ? totalCount.hashCode() : 0);
        return result;
    }
}

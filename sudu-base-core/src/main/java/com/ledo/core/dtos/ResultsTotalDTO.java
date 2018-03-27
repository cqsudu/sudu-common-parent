package com.ledo.core.dtos;

import java.io.Serializable;
import java.util.List;

/**
 * Created by konghang
 */
public class ResultsTotalDTO<T> implements Serializable {

    /**
     * 当前页返回数据列表
     */
    private List<T> results;

    private Long totalCount;

    public static <T> ResultsTotalDTO<T> build() {
        return new ResultsTotalDTO<T>();
    }

    public static <T> ResultsTotalDTO<T> build(List<T> results) {
        return new ResultsTotalDTO<T>().setResults(results).setTotalCount(new Long(results.size()));
    }

    public static <T> ResultsTotalDTO<T> build(List<T> results, Long totalCount) {
        return new ResultsTotalDTO<T>().setResults(results).setTotalCount(totalCount);
    }

    public List<T> getResults() {
        return results;
    }

    public ResultsTotalDTO<T> setResults(List<T> results) {
        this.results = results;
        return this;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public ResultsTotalDTO setTotalCount(Long totalCount) {
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

        ResultsTotalDTO<?> that = (ResultsTotalDTO<?>) o;

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

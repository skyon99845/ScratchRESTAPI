package POJO;

import java.util.Objects;

public class SingleUserPOJO {
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SingleUserPOJO)) return false;
        SingleUserPOJO that = (SingleUserPOJO) o;
        return data.equals(that.data) && support.equals(that.support);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, support);
    }

    private Data data;
    private Support support;
}

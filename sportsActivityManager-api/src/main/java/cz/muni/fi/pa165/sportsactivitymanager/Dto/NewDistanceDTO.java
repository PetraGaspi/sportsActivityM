package cz.muni.fi.pa165.sportsactivitymanager.Dto;

/**
 * Created by michal on 11/24/15.
 */
public class NewDistanceDTO {

    private long recordId;

    private double newValue;

    public double getNewValue() {
        return newValue;
    }

    public void setNewValue(double newValue) {
        this.newValue = newValue;
    }

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewDistanceDTO)) return false;

        NewDistanceDTO that = (NewDistanceDTO) o;

        if (Double.compare(that.newValue, newValue) != 0) return false;
        if (recordId != that.recordId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (recordId ^ (recordId >>> 32));
        temp = Double.doubleToLongBits(newValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


}

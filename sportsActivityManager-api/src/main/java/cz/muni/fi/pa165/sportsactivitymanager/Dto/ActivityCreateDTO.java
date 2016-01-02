package cz.muni.fi.pa165.sportsactivitymanager.Dto;

import javax.validation.constraints.NotNull;

import javax.validation.constraints.Min;
import java.util.Objects;

/**
 *
 * @author Petra Gasparikova
 */
public class ActivityCreateDTO {

    @NotNull
    private String name;

    @NotNull
    private Boolean measureDistance;

    @Min(0)
    private Double calories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMeasureDistance() {
        return measureDistance;
    }

    public void setMeasureDistance(Boolean measureDistance) {
        this.measureDistance = measureDistance;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.measureDistance);
        hash = 67 * hash + Objects.hashCode(this.calories);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ActivityCreateDTO other = (ActivityCreateDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.measureDistance, other.measureDistance)) {
            return false;
        }
        if (!Objects.equals(this.calories, other.calories)) {
            return false;
        }
        return true;
    }
    
    
}

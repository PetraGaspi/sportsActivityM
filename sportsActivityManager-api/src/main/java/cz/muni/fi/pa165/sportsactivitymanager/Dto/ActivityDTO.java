package cz.muni.fi.pa165.sportsactivitymanager.Dto;

import java.util.Objects;

/**
 * @author Petra Gasparikova
 */
public class ActivityDTO {

    private Long id;
    private String name;
    private Boolean measureDistance;
    private CaloriesDTO calories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public CaloriesDTO getCalories() {
        return calories;
    }

    public void setCalories(CaloriesDTO calories) {
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
        final ActivityDTO other = (ActivityDTO) obj;
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

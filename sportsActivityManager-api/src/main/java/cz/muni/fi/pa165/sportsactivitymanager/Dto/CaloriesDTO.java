package cz.muni.fi.pa165.sportsactivitymanager.Dto;

import java.util.Objects;

/**
 * @author Petra Gasparikova
 */
public class CaloriesDTO {

    private Long id;
    private Double index;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getIndex() {
        return index;
    }

    public void setIndex(Double index) {
        this.index = index;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.index);
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
        final CaloriesDTO other = (CaloriesDTO) obj;
        if (!Objects.equals(this.index, other.index)) {
            return false;
        }
        return true;
    }


}

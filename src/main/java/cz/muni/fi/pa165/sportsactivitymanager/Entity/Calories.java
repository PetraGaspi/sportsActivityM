/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.sportsactivitymanager.Entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Juraj Pleško, 359530
 */
@Entity
public class Calories {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    
    private Double Index;
    
    public Long getId() {
        return Id;
    }


    public Double getIndex() {
        return Index;
    }

    public void setIndex(Double Index) {
        this.Index = Index;
    }

    @Override
    public String toString() {
        return "Calories{" + "Id=" + Id + ", Index=" + Index + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.Id);
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
        final Calories other = (Calories) obj;
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }
        return true;
    }
    
}

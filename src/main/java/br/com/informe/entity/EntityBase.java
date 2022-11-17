package br.com.informe.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class EntityBase<K extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract K getId();


    @Override
    public boolean equals(Object obj) {

        if (this == obj) {

            return true;
        }

        if (obj == null) {

            return false;
        }

        if (getClass() != obj.getClass()) {

            return false;
        }

        EntityBase<?> other = (EntityBase<?>) obj;

        EqualsBuilder eb = new EqualsBuilder();

        eb.append(getId(), other.getId());

        return eb.isEquals();
    }


    @Override
    public int hashCode() {

        return new HashCodeBuilder().append(getId()).toHashCode();
    }

}


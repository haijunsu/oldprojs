/*
 * @(#)SmallAuthorView.java  2005-2-27
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.viewdata;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class SmallAuthorView extends SmallUserView implements Serializable {
    
    public boolean equals(Object other) {
        if ( !(other instanceof SmallAuthorView) ) return false;
        SmallAuthorView castOther = (SmallAuthorView) other;
        return new EqualsBuilder()
            .append(this.getChvPartiEmail().trim().toUpperCase(), castOther.getChvPartiEmail().trim().toUpperCase())
            .append(this.getChvPartiFirstName().trim().toUpperCase(), castOther.getChvPartiFirstName().trim().toUpperCase())
            .append(this.getChvPartiLastName().trim().toUpperCase(), castOther.getChvPartiLastName().trim().toUpperCase())
            .append(this.getInyAuthorRank(), castOther.getInyAuthorRank())
            .isEquals();
    }
    public int hashCode() {
        return new HashCodeBuilder()
        .append(getChvPartiEmail().trim().toUpperCase())
        .append(getChvPartiFirstName().trim().toUpperCase())
        .append(getChvPartiLastName().trim().toUpperCase())
        .append(getInyAuthorRank())
        .toHashCode();
    }
    public String toString() {
        return new ToStringBuilder(this)
            .append("getChvPartiEmail", getChvPartiEmail())
            .append("getChvPartiFirstName", getChvPartiFirstName())
            .append("getChvPartiLastName", getChvPartiLastName())
            .append("getChvPartiMiddleName", getChvPartiMiddleName())
            .append("getInyAuthorRank", getInyAuthorRank())
            .toString();
    }

}

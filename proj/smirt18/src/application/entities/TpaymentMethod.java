package application.entities;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @hibernate.class table="T_PaymentMethod"
 *  
 */
public class TpaymentMethod implements Serializable {

    /** identifier field */
    private String chrPaymentMethodNo;

    /** nullable persistent field */
    private String chvPaymentMethod;

    /** nullable persistent field */
    private String txtPaymentMethodIntro;

    /**
     * @hibernate.id generator-class="assigned" type="java.lang.String"
     *               column="chrPaymentMethodNo"
     *  
     */
    public String getChrPaymentMethodNo() {
        return this.chrPaymentMethodNo;
    }

    public void setChrPaymentMethodNo(String chrPaymentMethodNo) {
        this.chrPaymentMethodNo = chrPaymentMethodNo;
    }

    /**
     * @hibernate.property column="chvPaymentMethod" length="100"
     *  
     */
    public String getChvPaymentMethod() {
        return this.chvPaymentMethod;
    }

    public void setChvPaymentMethod(String chvPaymentMethod) {
        this.chvPaymentMethod = chvPaymentMethod;
    }

    /**
     * @hibernate.property column="txtPaymentMethodIntro" length="2147483647"
     *  
     */
    public String getTxtPaymentMethodIntro() {
        return this.txtPaymentMethodIntro;
    }

    public void setTxtPaymentMethodIntro(String txtPaymentMethodIntro) {
        this.txtPaymentMethodIntro = txtPaymentMethodIntro;
    }

    public String toString() {
        return new ToStringBuilder(this).append("chrPaymentMethodNo",
                getChrPaymentMethodNo()).toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof TpaymentMethod))
            return false;
        TpaymentMethod castOther = (TpaymentMethod) other;
        return new EqualsBuilder().append(this.getChrPaymentMethodNo(),
                castOther.getChrPaymentMethodNo()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getChrPaymentMethodNo())
                .toHashCode();
    }

}
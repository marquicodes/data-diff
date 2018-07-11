package me.markakis.demo.datadiff.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Represents the <code>Diff</code> domain model to be persisted into
 * database.
 * 
 * @author marqui
 */
@Entity
@Table(name = "diff", schema = "demo")
public class Diff {

    @Id
    @Column(name = "id")
    private Integer id;

    @Lob
    @Column(name = "left")
    private String  left;

    @Lob
    @Column(name = "right")
    private String  right;

    /**
     * Default constructor.
     */
    public Diff() {
        super();
    }

    /**
     * Constructs a Diff object having the specified information.
     * 
     * @param id
     *            identifier used for this data comparison.
     */
    public Diff(Integer id) {
        super();
        this.id = id;
    }

    /**
     * Constructs a Diff object having the specified information.
     * 
     * @param id
     *            identifier used for this data comparison.
     * @param left
     *            base64 enconded data used for comparison.
     * @param right
     *            base64 enconded data used for comparison.
     */
    public Diff(Integer id, String left, String right) {
        super();
        this.id = id;
        this.left = left;
        this.right = right;
    }

    /**
     * Returns <code>Diff</code> identifier.
     * 
     * @return <code>Diff</code> identifier.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets <code>Diff</code> identifier.
     * 
     * @param id
     *            <code>Diff</code> identifier.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns <code>Diff</code> left side data.
     * 
     * @return <code>Diff</code> left side data.
     */
    public String getLeft() {
        return left;
    }

    /**
     * Sets <code>Diff</code> left side data.
     * 
     * @param left
     *            <code>Diff</code> left side data.
     */
    public void setLeft(String left) {
        this.left = left;
    }

    /**
     * Returns <code>Diff</code> right side data.
     * 
     * @return <code>Diff</code> right side data.
     */
    public String getRight() {
        return right;
    }

    /**
     * Sets <code>Diff</code> right side data.
     * 
     * @param right
     *            <code>Diff</code> right side data.
     */
    public void setRight(String right) {
        this.right = right;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Diff [id: ").append(id).append(", left: ").append(left).append(", right: ").append(right)
                .append("]");
        return builder.toString();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((left == null) ? 0 : left.hashCode());
        result = prime * result + ((right == null) ? 0 : right.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Diff other = (Diff) obj;
        if (left == null) {
            if (other.left != null)
                return false;
        } else if (!left.equals(other.left))
            return false;
        if (right == null) {
            if (other.right != null)
                return false;
        } else if (!right.equals(other.right))
            return false;
        return true;
    }

}

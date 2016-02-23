package mathlib;

/**
 * Represents a class of a normalized 3D-vector with x-, y- and z-value
 *
 * @author group raspi, CG1, Beuth-Hochschule
 * @version 1.0
 */
public final class Normal3 implements Comparable<Normal3> {
    /**
     * x, y, z represent the coordinates of the Normal3 vector
     */
    public final double x,y,z;

    /**
     * Constructor for the Normal3
     *
     * @param x represents the x-coordinate
     * @param y represents the x-coordinate
     * @param z represents the x-coordinate
     */
    public Normal3(final double x, final double y, final double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Method multiplies the Normal3 by a given double value
     *
     * @param n the value to be multiplied with
     * @return returns a multiplied Normal3
     */
    public Normal3 mul(final double n){
        return new Normal3( x*n ,
                            y*n ,
                            z*n );
    }

    /**
     * Method adds two Normal3 instances (adds the x, y, z values of each instance) and returns a new (added) Normal3
     *
     * @param n the Normal3 instance to be added to the current Normal3, consisting of 3 attributes: x, y, z
     * @return the added Normal3 instance
     */
    public Normal3 add(final Normal3 n){
        return new Normal3( x+n.x ,
                            y+n.y ,
                            z+n.z );
    }

    /**
     * Method calculates the dot-product of the current Normal3 instance and a Vector3 instance
     *
     * @param v Vector3 instance consisting of 3 attributes: x, y, z
     * @return dot-product in type double
     */
    public double dot(final Vector3 v){
        return x*v.x +
               y*v.y +
               z*v.z;
    }

    /**
     * Overridden toString Method
     *
     * @return a String with all attributes and values of the Normal3
     */
    @Override
    public String toString(){
        return "Normal3 instance: \n" +
                         "x: "+x+"\n" +
                         "y: "+y+"\n" +
                         "z: "+z;
    }

    /**
     * Overridden hashCode Method builds the hash code for the Normal3 instance
     *
     * @return an int value representing the hashCode of the Normal3
     */
    @Override
    public int hashCode(){
        int result;
        long hash;

        hash = Double.doubleToLongBits(x);
        result = (int) (hash ^ (hash >>> 32));
        hash = Double.doubleToLongBits(y);
        result = 31 * result + (int) (hash ^ (hash >>> 32));
        hash = Double.doubleToLongBits(z);
        result = 31 * result + (int) (hash ^ (hash >>> 32));
        return result;
    }

    /**
     * Overridden equals Method: indicates whether the values of the given Object are the same or not
     *
     * @param o representing the Object to compare with
     * @return boolean value of the result
     */
    @Override
    public boolean equals(final Object o){
        if ( o == null || !(o instanceof Normal3) ) return false;
        Normal3 n = (Normal3) o;
        return (Double.compare(n.x, this.x) == 0)
            && (Double.compare(n.y, this.y) == 0)
            && (Double.compare(n.z, this.z) == 0);
    }

    /**
     * Comparable Method for Interface Comparable
     * @param n incoming Normal3-Object
     * @return  int value ( 0 if all attributes are equal,
     *                    -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     *                     1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(final Normal3 n) {
        if (Double.compare(this.x, n.x) != 0) return (int) Math.signum(this.x - n.x);
        if (Double.compare(this.y, n.y) != 0) return (int) Math.signum(this.y - n.y);
        if (Double.compare(this.z, n.z) != 0) return (int) Math.signum(this.z - n.z);
        return 0;
    }
}

package mathlib;

import com.sun.istack.internal.NotNull;

/**
 * Point3 includes all mathematical functions for
 * 3 point objects
 *
 * @author group raspi, CG1, Beuth-Hochschule
 * @version 1.0
**/
public final class Point3 implements Comparable<Point3>{
    public final double x,y,z;

    /**
     * Constructor creates a new Point3 instance with it´s coordinates x, y and z
     *
     * @param x representing the x-coordinate of the new Point3 instance
     * @param y representing the y-coordinate of the new Point3 instance
     * @param z representing the z-coordinate of the new Point3 instance
     */
    public Point3(final double x,final double y,final double z){

        this.x=x;
        this.y=y;
        this.z=z;
    }

    /**
     * subtracts a point from the current Point3 instance
     *
     * @param p Point3 instance to be added
     * @return new Vector3 instance
     */
    public Vector3 sub(final Point3 p){

        return new Vector3( x-p.x ,
                            y-p.y ,
                            z-p.z );
    }

    /**
     * subtracts a vector from the current Point3 instance
     *
     * @param v Vector3 instance to be added
     * @return new Point3 instance
     */
    public Point3 sub(final Vector3 v){

        return new Point3(  x-v.x ,
                            y-v.y ,
                            z-v.z );
    }

    /**
     * adds a vector to the current Point3 instance
     *
     * @param v Vector3 instance to be added
     * @return new Point3 instance
     */
    public Point3 add(final Vector3 v){

        return new Point3(  x+v.x ,
                            y+v.y ,
                            z+v.z );
    }

    /**
     * shows the point as a string
     *
     * @return a string with the x, y, z coordinates of a point
     */
    @Override
    public String toString() {return "Point3 instance: P(x: "+x+" / y: "+y+" / z: "+z+")";}

    /**
     * builds the hash code for the Point3 instance
     *
     * @return new hash code as int
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
     * compares two Point3 objects for equality
     *
     * @param o comparable object for checking equality
     * @return boolean, if object o is equal to instance object or not?
     */
    @Override
    public boolean equals(final Object o){
        if ( o == null || !(o instanceof Point3) ) return false;
        Point3 p = (Point3) o;
        return (Double.compare(p.x, this.x) == 0)
            && (Double.compare(p.y, this.y) == 0)
            && (Double.compare(p.z, this.z) == 0);
    }

    /**
     * Comparable Method for Interface Comparable
     * @param p incoming Point3-Object
     * @return  int value ( 0 if all attributes are equal,
     *                    -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     *                     1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */

    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(@NotNull final Point3 p) {
        if (Double.compare(this.x, p.x) != 0) return (int) Math.signum(this.x - p.x);
        if (Double.compare(this.y, p.y) != 0) return (int) Math.signum(this.y - p.y);
        if (Double.compare(this.z, p.z) != 0) return (int) Math.signum(this.z - p.z);
        return 0;
    }
}
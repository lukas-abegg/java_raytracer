package geometry;

import material.Material;
import ray.Ray;
import texture.TexCoord2;
import mathlib.Mat3x3;
import mathlib.Normal3;
import mathlib.Point3;
import mathlib.Vector3;

/**
 * Class represents a Triangle
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe2 2014-11-25
 */
public class Triangle extends Geometry {

    /**
     * points of triangle
     */
    public final Point3 a;
    public final Point3 b;
    public final Point3 c;

    public final Normal3 an;
    public final Normal3 bn;
    public final Normal3 cn;

    public final TexCoord2 tC2a = new TexCoord2(0, 0);
    public final TexCoord2 tC2b = new TexCoord2(0, 0);
    public final TexCoord2 tC2c = new TexCoord2(0, 0);

    
    /**
     * Constructor for Sphere
     *
     * @param material Material of this Sphere
     */
    public Triangle(final Point3 a, final Point3 b, final Point3 c, final Material material) {
        super(material);
        this.a  = a;;
        this.b  = b;
        this.c  = c;
        this.an = calcNormal(this.b, this.a, this.c);
        this.bn = calcNormal(this.a, this.b, this.c);
        this.cn = calcNormal(this.a, this.c, this.b);
    }

    /**
     * Alternative Constructor for Sphere
     *
     * @param material Material of this Sphere         (Color)
     */
    public Triangle(final Material material) {
        super(material);
        this.a  = new Point3(0, 0, 0);;
        this.b  = new Point3(0.6, 0, 0);
        this.c  = new Point3(0, 0, 1);
        this.an = calcNormal(this.b, this.a, this.c);
        this.bn = calcNormal(this.a, this.b, this.c);
        this.cn = calcNormal(this.a, this.c, this.b);
    }

    private Normal3 calcNormal(final Point3 p1, final Point3 p2, final Point3 p3){
        return p1.sub(p2).x(p3.sub(p2)).mul(1 / (p1.sub(p2).x(p3.sub(p2))).magnitude).normalized().asNormal();

               //        p1         p2        p3         p3                p1         p2        p3         p2
        //this.an = this.b.sub(this.a).x(this.c.sub(this.a)).mul(1 / (this.b.sub(this.a).x(this.c.sub(this.a))).magnitude).normalized().asNormal();
        //this.bn = this.a.sub(this.b).x(this.c.sub(this.b)).mul(1 / (this.a.sub(this.b).x(this.c.sub(this.b))).magnitude).normalized().asNormal();
        //this.cn = this.a.sub(this.c).x(this.b.sub(this.c)).mul(1 / (this.a.sub(this.c).x(this.b.sub(this.c))).magnitude).normalized().asNormal();
    }

    /**
     * Method returns a result of an intersection calculation with a Ray-instance
     *
     * @param ray the Ray to calculate the intersections with this Sphere
     * @return the intersections of Ray and this Sphere (returns the closest intersection if there are more than one, null if there are none)
     */
    @Override
    public final Hit hit(final Ray ray) {

        final Mat3x3 matA = new Mat3x3(
                this.a.x - this.b.x, this.a.x - this.c.x, ray.d.x,
                this.a.y - this.b.y, this.a.y - this.c.y, ray.d.y,
                this.a.z - this.b.z, this.a.z - this.c.z, ray.d.z);

        final Vector3 vA = this.a.sub(ray.o);

        if (matA.determinant == 0) {
            return null;
        }

        try {
            final double beta  = matA.changeCol1(vA).determinant / matA.determinant;
            final double gamma = matA.changeCol2(vA).determinant / matA.determinant;
            final double t     = matA.changeCol3(vA).determinant / matA.determinant;
            final double alpha = 1.0 - beta - gamma;

            // check all factors to be sure ray is hit
            if (beta < 0.0 || gamma < 0.0 || beta + gamma > 1.0 || t < super.EPSILON) {
                return null;
            }
            final TexCoord2 tCoord2 = tC2a.mul(alpha).add(tC2b).mul(beta).add(tC2c).mul(gamma);

            return new Hit(t, ray, this, an, tCoord2);
        } catch (ArithmeticException ae) {
            // Division by zero ray located parallel to or onto this Plane
            return null;
        }
    }

    /**
     * shows the Plane as String
     *
     * @return a String with Color color, Point3 c and double r representations of this Sphere
     */
    @Override
    public String toString() {
        return "Sphere instance:\n" +
                super.toString() +
                "\npoint a: \n" + a.toString() +
                "\npoint a: \n" + b.toString() +
                "\npoint a: \n" + c.toString()+
                "\nnormal an: \n" + an.toString()+
                "\nnormal bn: \n" + bn.toString()+
                "\nnormal cn: \n" + cn.toString()+
                "\nnormal cn: \n" + tC2a.toString()+
                "\nnormal cn: \n" + tC2b.toString()+
                "\nnormal cn: \n" + tC2c.toString();
    }

    /**
     * Method builds an evenly distributed hash value for the Sphere instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        int result;

        result = a.hashCode();
        result = 31 * result + b.hashCode();
        result = 31 * result + c.hashCode();
        result = 31 * result + an.hashCode();
        result = 31 * result + bn.hashCode();
        result = 31 * result + cn.hashCode();
        result = 31 * result + tC2a.hashCode();
        result = 31 * result + tC2b.hashCode();
        result = 31 * result + tC2c.hashCode();
        result = 31 * result + super.hashCode();

        return result;
    }

    /**
     * Overridden equals method: indicates whether the values of the given Object are the same or not
     *
     * @param o representing the Object to compare with
     * @return boolean value of the result
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Triangle)) return false;

        Triangle triangle = (Triangle) o;
        return ( super.equals(triangle) )
            && ( this.a.equals(triangle.a) )
            && ( this.b.equals(triangle.b) )
            && ( this.c.equals(triangle.c) )
            && ( this.an.equals(triangle.c) )
            && ( this.bn.equals(triangle.c) )
            && ( this.cn.equals(triangle.c) )
            && ( this.tC2a.equals(triangle.tC2a) )
            && ( this.tC2b.equals(triangle.tC2b) )
            && ( this.tC2c.equals(triangle.tC2c) );
    }

    /**
     * Comparable Method for Interface Comparable
     * @param geo incoming Geometry-Object
     * @return  int value ( 0 if all attributes are equal,
     *                    -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     *                     1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @Override
    public int compareTo(final Geometry geo) {
        Triangle tri = (Triangle) geo;
        if ( !(this.material.equals(geo.material)) ) return super.compareTo(geo);
        if ( !(this.a.    equals(tri.a))  ) return this.a.compareTo(tri.a);
        if ( !(this.b.    equals(tri.b))  ) return this.b.compareTo(tri.b);
        if ( !(this.c.    equals(tri.c))  ) return this.c.compareTo(tri.c);
        if ( !(this.an.    equals(tri.an))  ) return this.an.compareTo(tri.an);
        if ( !(this.bn.    equals(tri.bn))  ) return this.bn.compareTo(tri.bn);
        if ( !(this.cn.    equals(tri.cn))  ) return this.cn.compareTo(tri.cn);
        if ( !(this.tC2a.    equals(tri.tC2a))  ) return this.tC2a.compareTo(tri.tC2a);
        if ( !(this.tC2b.    equals(tri.tC2b))  ) return this.tC2b.compareTo(tri.tC2b);
        if ( !(this.tC2c.    equals(tri.tC2c))  ) return this.tC2c.compareTo(tri.tC2c);

        return 0;
    }
}

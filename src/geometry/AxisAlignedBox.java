package geometry;

import material.Material;
import mathlib.Normal3;
import mathlib.Transform;
import ray.Ray;
import mathlib.Point3;

import java.util.ArrayList;

/**
 * Class AxisAlignedBox represents an axis aligned box Geometry
 */
public class AxisAlignedBox extends Geometry {
    /**
     * left bottom point
     */
    public final Point3 lbf;

    /**
     * right upper point
     */
    public final Point3 run;

    private Node  right     ,
                  top       ,
                  front     ,
                  left      ,
                  bottom    ,
                  back      ;
    private Plane rightPl   ,
                  topPl     ,
                  frontPl   ,
                  leftPl    ,
                  bottomPl  ,
                  backPl    ;


    /**
     * Constructor for AAB
     *
     * @param material material of the box
     */
    public AxisAlignedBox(final Material material) {
        super(material);
        this.lbf = new Point3(-0.5, -0.5, -0.5);
        this.run = new Point3(0.5, 0.5, 0.5);
        this.addSides();
    }

    /**
     * Alternative Constructor for AAB
     *
     * @param lbf left-bottom-front-point
     * @param run right-upper-near-point
     * @param material material of the box
     */
    public AxisAlignedBox(final Point3 lbf, final Point3 run, final Material material) {
        super(material);
        this.lbf = lbf;
        this.run = run;
        this.addPlanes();
    }

    /**
     * Method initializes Sides (Plane-Style)
     */
    private void addPlanes() {
        this.leftPl   = new Plane(this.lbf, new Normal3(-1,  0,  0), this.material); // left   side
        this.rightPl  = new Plane(this.run, new Normal3( 1,  0,  0), this.material); // right  side

        this.bottomPl = new Plane(this.lbf, new Normal3( 0, -1,  0), this.material); // bottom side
        this.topPl    = new Plane(this.run, new Normal3( 0,  1,  0), this.material); // top    side

        this.frontPl  = new Plane(this.run, new Normal3( 0,  0,  1), this.material); // close  side
        this.backPl   = new Plane(this.lbf, new Normal3( 0,  0, -1), this.material); // far    side
    }

    /**
     * Method initializes Sides (Node-Style)
     */
    private void addSides() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(new Plane(material));

        left = new Node(geoList, new Transform().translate(this.lbf).rotateZ(Math.PI / 2));
        right = new Node(geoList, new Transform().translate(this.run).rotateZ(-Math.PI / 2));

        bottom =  new Node(geoList, new Transform().translate(this.lbf).rotateX(Math.PI));
        top =  new Node(geoList, new Transform().translate(this.run));

        front = new Node(geoList, new Transform().translate(this.run).rotateX(Math.PI / 2).rotateZ(Math.PI));
        back = new Node(geoList, new Transform().translate(this.lbf).rotateX(-Math.PI / 2).rotateZ(Math.PI));
    }

    /**
     * Method returns a Hit with the closest t, located within the Box/shape
     *
     * @param ray the Ray to calculate the intersections with this AAB
     * @return the intersections of Ray and this AxisAlignedBox (returns the closest intersection, null if there is none)
     */
    @Override
    public Hit hit(final Ray ray) {

        final ArrayList<Hit> hits = new ArrayList<Hit>();
        /**
         * Adds Hits to Arrays // hereby it checks whether or not fields had been initialized
         */
        final Hit[] nodeHitsLR  = new Hit[] {( left  != null )?( left.hit(ray)  ):( leftPl.hit(ray)  ), ( right  !=null )?( right.hit(ray)  ):( rightPl.hit(ray)  )};
        final Hit[] nodeHitsTBo = new Hit[] {( top   != null )?( top.hit(ray)   ):( topPl.hit(ray)   ), ( bottom !=null )?( bottom.hit(ray) ):( bottomPl.hit(ray) )};
        final Hit[] nodeHitsFBa = new Hit[] {( front != null )?( front.hit(ray) ):( frontPl.hit(ray) ), ( back   !=null )?( back.hit(ray)   ):( backPl.hit(ray)   )};

        for (Hit hit : nodeHitsLR) {
            if (hit != null) {
                Point3 p = ray.at(hit.t);
                if (p.y >= lbf.y && p.y <= run.y && p.z >= lbf.z && p.z <= run.z) {
                    hits.add(hit);
                }
            }
        }
        for (Hit hit : nodeHitsTBo) {
            if (hit != null) {
                Point3 p = ray.at(hit.t);
                if (p.x >= lbf.x && p.x <= run.x && p.z >= lbf.z && p.z <= run.z) {
                    hits.add(hit);
                }
            }
        }
        for (Hit hit : nodeHitsFBa) {
            if (hit != null) {
                Point3 p = ray.at(hit.t);
                if (p.x >= lbf.x && p.x <= run.x && p.y >= lbf.y && p.y <= run.y) {
                    hits.add(hit);
                }
            }
        }
        Hit hit = null;
        for (final Hit h : hits) {
            if (hit == null || h.t < hit.t) {
                hit = h;
            }
        }
        return hit;

    }

    /**
     * shows the Plane as String
     *
     * @return a String with Color color, Point3 a and Normal3 n representations of this Plane
     */
    @Override
    public String toString() {
        return "AxisAlignedBox{" +
                "lbf="      + lbf +
                ", run="    + run +
                ", right="  + ((right  !=null)?(right) :(rightPl))  +
                ", top="    + ((top    !=null)?(top)   :(topPl))    +
                ", front="  + ((front  !=null)?(front) :(frontPl))  +
                ", left="   + ((left   !=null)?(left)  :(leftPl))   +
                ", bottom=" + ((bottom !=null)?(bottom):(bottomPl)) +
                ", back="   + ((back   !=null)?(back)  :(backPl))   +
                '}';
    }

    /**
     * Method builds an evenly distributed hash value for the AAB instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (lbf     != null ? lbf.hashCode()     : 0);
        result = 31 * result + (run     != null ? run.hashCode()     : 0);
        result = 31 * result + (right   != null ? right.hashCode()   : 0);
        result = 31 * result + (rightPl != null ? rightPl.hashCode() : 0);
        result = 31 * result + (top     != null ? top.hashCode()     : 0);
        result = 31 * result + (topPl   != null ? topPl.hashCode()   : 0);
        result = 31 * result + (front   != null ? front.hashCode()   : 0);
        result = 31 * result + (frontPl != null ? frontPl.hashCode() : 0);
        result = 31 * result + (left    != null ? left.hashCode()    : 0);
        result = 31 * result + (leftPl  != null ? leftPl.hashCode()  : 0);
        result = 31 * result + (bottom  != null ? bottom.hashCode()  : 0);
        result = 31 * result + (bottomPl!= null ? bottomPl.hashCode(): 0);
        result = 31 * result + (back    != null ? back.hashCode()    : 0);
        result = 31 * result + (backPl  != null ? backPl.hashCode()  : 0);
        return result;
    }

    /**
     * Overridden equals Method: indicates whether the values of the given Object are the same or not
     *
     * @param o representing the Object to compare with
     * @return boolean value of the result
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AxisAlignedBox)) return false;
        if (!super.equals(o)) return false;

        AxisAlignedBox that = (AxisAlignedBox) o;

        if (this.lbf      != null ? !lbf     .equals(that.lbf)      : that.lbf      != null) return false;
        if (this.run      != null ? !run     .equals(that.run)      : that.run      != null) return false;
        if (this.back     != null ? !back    .equals(that.back)     : that.back     != null) return false;
        if (this.backPl   != null ? !backPl  .equals(that.backPl)   : that.backPl   != null) return false;
        if (this.bottom   != null ? !bottom  .equals(that.bottom)   : that.bottom   != null) return false;
        if (this.bottomPl != null ? !bottomPl.equals(that.bottomPl) : that.bottomPl != null) return false;
        if (this.front    != null ? !front   .equals(that.front)    : that.front    != null) return false;
        if (this.frontPl  != null ? !frontPl .equals(that.frontPl)  : that.frontPl  != null) return false;
        if (this.left     != null ? !left    .equals(that.left)     : that.left     != null) return false;
        if (this.leftPl   != null ? !leftPl  .equals(that.leftPl)   : that.leftPl   != null) return false;
        if (this.right    != null ? !right   .equals(that.right)    : that.right    != null) return false;
        if (this.rightPl  != null ? !rightPl .equals(that.rightPl)  : that.rightPl  != null) return false;
        if (this.top      != null ? !top     .equals(that.top)      : that.top      != null) return false;
        if (this.topPl    != null ? !topPl   .equals(that.topPl)    : that.topPl    != null) return false;

        return true;
    }

    /**
     * Comparable Method for Interface Comparable
     *
     * @param geo incoming Geometry-Object
     * @return int value ( 0 if all attributes are equal,
     * -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     * 1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @Override
    public int compareTo(final Geometry geo) {
        AxisAlignedBox aab = (AxisAlignedBox) geo;
        if (!(this.material.equals(geo.material))) return super.compareTo(geo);
        if (!(this.lbf.equals(aab.lbf))) return this.lbf.compareTo(aab.lbf);
        if (!(this.run.equals(aab.run))) return this.run.compareTo(aab.run);
        if ( (this.right    != null) && !(this.right.equals(aab.right)   )) return this.right.compareTo(aab.right);
        if ( (this.rightPl  != null) && !(this.rightPl.equals(aab.rightPl) )) return this.rightPl.compareTo(aab.rightPl);
        if ( (this.left     != null) && !(this.left.equals(aab.left)    )) return this.left.compareTo(aab.left);
        if ( (this.leftPl   != null) && !(this.leftPl.equals(aab.leftPl)  )) return this.leftPl.compareTo(aab.leftPl);
        if ( (this.bottom   != null) && !(this.bottom.equals(aab.bottom)  )) return this.bottom.compareTo(aab.bottom);
        if ( (this.bottomPl != null) && !(this.bottomPl.equals(aab.bottomPl))) return this.bottomPl.compareTo(aab.bottomPl);
        if ( (this.back     != null) && !(this.back.equals(aab.back)    )) return this.back.compareTo(aab.back);
        if ( (this.backPl   != null) && !(this.backPl.equals(aab.backPl)  )) return this.backPl.compareTo(aab.backPl);
        if ( (this.front    != null) && !(this.front.equals(aab.front)   )) return this.front.compareTo(aab.front);
        if ( (this.frontPl  != null) && !(this.frontPl.equals(aab.frontPl) )) return this.frontPl.compareTo(aab.frontPl);
        return 0;
    }
}
package world;

import color.Color;
import geometry.Geometry;
import geometry.Hit;
import ray.Ray;
import light.Light;

import java.util.ArrayList;

/**
 * Class represents a scenery of several Geometry instances
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe2 2014-11-25
 */
public class World {

    public final Color ambientLight;
    public final double indexOfRefraction;
    /**
     * background color
     */
    public static final Color BACKGROUND_COLOR = new Color(0, 0, 0);

    /* geometries of the world */
    public final ArrayList<Geometry> geoList;
    /* lights of the world */
    public final ArrayList<Light> lights;

    /**
     * Constructor for World
     *  @param ambientLight background color of the World   (Color)
     * @param geoList ArrayList of Geometry instances (ArrayList<Geometry>)
     * @param lights ArrayList of Light instances (ArrayList<Light>)
     */
    public World(final Color ambientLight, ArrayList<Geometry> geoList, ArrayList<Light> lights, final double indexOfRefraction) {
        this.ambientLight = ambientLight;
        this.indexOfRefraction = indexOfRefraction;

        if (lights == null) {
            this.lights = new ArrayList<>();
        } else {
            this.lights = lights;
        }

        /**
         * hier werden Geometry-Objekte der geoList hinzugefügt
         */
        if (geoList == null) {
            this.geoList = new ArrayList<>();

            // some spheres
            /*this.geoList.add(new Sphere(new Point3(1,0,-3),0.5, new Color(1,0,0)));
            this.geoList.add(new Sphere(new Point3(2,0,-3),0.5, new Color(1,0,1)));
            this.geoList.add(new Sphere(new Point3(3,0,-3),0.5, new Color(0,1,0)));
            */

            // some scenes
            /*this.geoList.add(new Plane(new Point3(0,-1,0), new Normal3(0,1,0), new Color(0,1,0)));
            this.geoList.add(new Plane(new Point3(0,-1,5), new Normal3(0,1,0), new Color(0,1,0)));
            this.geoList.add(new Plane(new Point3(0,-1,6), new Normal3(0,1,0), new Color(0,1,0)));
            this.geoList.add(new Plane(new Point3(0,-1,7), new Normal3(0,1,0), new Color(0,1,0)));*/
        }else{
            this.geoList = geoList;
        }
    }

    /**
     * Method tests a given Ray towards all objects contained in this World
     *
     * @param ray given Ray to test for intersection
     * @return possible intersection, or null
     */
    public Hit hit( final Ray ray ){
        Hit closestHit = null;
        for( Geometry geo : geoList ){
            Hit currentHit = geo.hit(ray);
            if ( ( currentHit != null ) && ( ( closestHit == null ) || ( Double.compare(closestHit.t, currentHit.t ) == 1 ) ) ){
                closestHit = currentHit;
            }
        }
        return closestHit;
    }

    /**
     * shows the World instance as String
     *
     * @return a String with Color ambientLight, ArrayList<Geometry> geoList representations of this World
     */
    @Override
    public String toString(){
        String s = "World instance:\n" +
                "ambientLight: \n" + ambientLight.toString();
        for( Geometry geo : geoList ){
            s += "\n"+geo.toString();
        }

        return s;
    }

    /**
     * Method builds an evenly distributed hash value for the World instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode(){
        int result;

        result = ambientLight.hashCode();
        for( Geometry geo : geoList ){
            result = 31 * result + geo.hashCode();
        }
        return result;
    }

    /**
     * Overridden equals method: indicates whether the values of the given Object are the same or not
     *
     * @param o representing the Object to compare with
     * @return boolean value of the result
     */
    @Override
    public boolean equals(Object o){
        if (o == null || this == o || o.getClass() != this.getClass()) return false;

        World world = (World) o;
        if (this.geoList.size() != world.geoList.size()) return false;
        for( Geometry geo : this.geoList ){
            Boolean found = false;
            for( Geometry geo2 : this.geoList){
                if (geo2.equals(geo)){
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return this.ambientLight.equals(world.ambientLight);
    }
}

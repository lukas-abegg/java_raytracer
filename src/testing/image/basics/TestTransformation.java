package testing.image.basics;

import camera.PerspectiveCamera;
import color.Color;
import geometry.*;
import image.basics.RayTracer;
import light.DirectionalLight;
import light.Light;
import light.PointLight;
import material.*;
import mathlib.Normal3;
import mathlib.Point3;
import mathlib.Transform;
import mathlib.Vector3;
import sampling.SamplingPattern;
import texture.ImageTexture;
import texture.SingleColorTexture;
import world.World;

import java.util.ArrayList;

/**
 * TestsTransformer is a class to
 * transform geometries and check if
 * transform works right
 *
 * @author group raspi, CG1, Beuth-Hochschule
 * @version 1.0
 */
public class TestTransformation {

    public static Material sphereLambert_material = new LambertMaterial(new SingleColorTexture( new Color(0, 1, 0)));
    public static Material spherePhong_material   = new PhongMaterial(new SingleColorTexture( new Color(1, 0, 0)), new SingleColorTexture( new Color(1, 1, 1)), 64);
    public static Material aabPhong_material   = new PhongMaterial(new SingleColorTexture( new Color(0.75, 0.75, 0)), new SingleColorTexture( new Color(1, 1, 1)), 64);
    public static Material worldTexture = new SingleColorMaterial(new ImageTexture("earth_day.jpg"));

    //public static Sphere sphere = new Sphere(worldTexture);
    //public static Sphere sphere = new Sphere(sphereLambert_material);
    public static Sphere sphere = new Sphere(spherePhong_material);
    public static AxisAlignedBox aab = new AxisAlignedBox(aabPhong_material);
    public static Plane plane = new Plane(aabPhong_material);

    public static void main(String[] args){
    runTestSphere();
    runTestAAB();
    }

    public static void runTestAAB() {
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(10, 10, 10), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4, new SamplingPattern(10,10));

        Transform transform = new Transform();
        
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(aab);

        // Transformations
        Point3 p = new Point3(3, 0.8, 10);
        
        Node aabNode = new Node(geoList, transform.rotateX(-Math.PI / 8).rotateZ(-Math.PI / 8).rotateY(-Math.PI / 24).scale(p.x, p.y, p.z));

        Light pointLight = new PointLight(new Color(1, 1, 1), new Point3(10, 15, 10), true);
        Light directionalLight = new DirectionalLight(new Color(0.25, 0.25, 0.25), new Vector3(10, 10, 10), true);

        Color ambientLight = new Color(0.25, 0.25, 0.25);

        World world = new World(ambientLight, null, null, 1.33 );
        RayTracer tracer1 = new RayTracer( world, camera );

        tracer1.world.geoList.clear();
        tracer1.world.geoList.add(aabNode);

        tracer1.world.lights.clear();
        tracer1.world.lights.add(pointLight);
        tracer1.world.lights.add(directionalLight);

        tracer1.createImage();
    }
    
    public static void runTestSphere() {
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(15, 15, 15), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4, new SamplingPattern(10,10));

        Transform transform = new Transform();
        
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(sphere);
        
        // Transformations
        Point3 p1 = new Point3(3.5, 3, 0.8);
        Point3 p2 = new Point3(0, 1, -4);

        Node sphereNode = new Node(geoList, transform.rotateZ(-Math.PI / 2).rotateX(Math.PI / 3).rotateY(Math.PI / 3).scale(p1.x, p1.y, p1.z).translate(p2.x, p2.y, p2.z));

        Light pointLight = new PointLight(new Color(0.8, 0.8, 0.8), new Point3(10, 10, 10), true);
        
        Color ambientLight = new Color(0, 0, 0);
        
        World world = new World(ambientLight, null, null, 1 );
        RayTracer tracer1 = new RayTracer( world, camera );
        
        tracer1.world.geoList.clear();
        tracer1.world.geoList.add(sphereNode);
        
        tracer1.world.lights.clear();
        tracer1.world.lights.add( pointLight );

        tracer1.createImage();
    }
}

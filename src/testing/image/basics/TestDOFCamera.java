package testing.image.basics;

import camera.Camera;
import camera.DOFCamera;
import color.Color;
import geometry.Geometry;
import geometry.Node;
import geometry.Plane;
import geometry.Sphere;
import image.basics.RayTracer;
import light.Light;
import light.PointLight;
import material.Material;
import material.ReflectiveMaterial;
import mathlib.Point3;
import mathlib.Transform;
import mathlib.Vector3;
import sampling.SamplingPattern;
import texture.SingleColorTexture;
import world.World;

import java.util.ArrayList;


public class TestDOFCamera {

    public static Material reflectiveMaterial1 = new ReflectiveMaterial(new SingleColorTexture(new Color(0.1, 0.1, 0.1)), new SingleColorTexture(new Color(0, 0, 0)), 64, new SingleColorTexture(new Color(0.5, 0.5, 0.5)));
    public static Material reflectiveMaterial2 = new ReflectiveMaterial(new SingleColorTexture(new Color(1, 0, 0)), new SingleColorTexture(new Color(1, 1, 1)), 64, new SingleColorTexture(new Color(0.5, 0.5, 0.5)));
    public static Material reflectiveMaterial3 = new ReflectiveMaterial(new SingleColorTexture(new Color(0, 1, 0)), new SingleColorTexture(new Color(1, 1, 1)), 64, new SingleColorTexture(new Color(0.5, 0.5, 0.5)));
    public static Material reflectiveMaterial4 = new ReflectiveMaterial(new SingleColorTexture(new Color(0, 0, 1)), new SingleColorTexture(new Color(1, 1, 1)), 64, new SingleColorTexture(new Color(0.5, 0.5, 0.5)));

    public static Plane plane = new Plane(reflectiveMaterial1);
    public static Sphere sphere2 = new Sphere(reflectiveMaterial2);
    public static Sphere sphere3 = new Sphere(reflectiveMaterial3);
    public static Sphere sphere1 = new Sphere(reflectiveMaterial4);

    /**
     * Main Class, tests functions from class ImageCreator_Saver
     *
     * @param args
     */
    public static void main(String[] args) {
        runShaped();
        runUnShaped();
    }

    public static void runUnShaped() {
        DOFCamera camera = new DOFCamera(new Point3(8, 8, 8), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4, 5, 0.05, new SamplingPattern(3, 3));
        runTracer(camera);
    }

    public static void runShaped() {
        DOFCamera camera = new DOFCamera(new Point3(8, 8, 8), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4, 15, 0.1, new SamplingPattern(3, 3));
        runTracer(camera);
    }

    public static void runTracer(Camera camera) {
        Transform transform = new Transform();

        ArrayList<Geometry> geoList1 = new ArrayList<Geometry>();
        geoList1.add(plane);
        
        ArrayList<Geometry> geoList2 = new ArrayList<Geometry>();
        geoList2.add(sphere2);
        ArrayList<Geometry> geoList3 = new ArrayList<Geometry>();
        geoList3.add(sphere3);
        ArrayList<Geometry> geoList4 = new ArrayList<Geometry>();
        geoList4.add(sphere1);


        // Transformations
        Point3 p1 = new Point3(0, 0, 0);
        
        Point3 p2 = new Point3(-3, 1, 0);
        Point3 p3 = new Point3(0, 1, 0);
        Point3 p4 = new Point3(3, 1, 0);

        Node planeNode = new Node(geoList1, transform.translate(p1.x, p1.y, p1.z));
        Node sphereNode1 = new Node(geoList2, transform.translate(p2.x, p2.y, p2.z));
        Node sphereNode2 = new Node(geoList3, transform.translate(p3.x, p3.y, p3.z));
        Node sphereNode3 = new Node(geoList4, transform.translate(p4.x, p4.y, p4.z));

        Light pointLight = new PointLight(new Color(1, 1, 1), new Point3(8, 8, 8), true);

        Color ambientLight = new Color(0.25, 0.25, 0.25);

        World world = new World(ambientLight, null, null, 1);
        RayTracer tracer1 = new RayTracer(world, camera);

        tracer1.world.geoList.clear();
        tracer1.world.geoList.add(planeNode);
        tracer1.world.geoList.add(sphereNode1);
        tracer1.world.geoList.add(sphereNode2);
        tracer1.world.geoList.add(sphereNode3);

        tracer1.world.lights.clear();
        tracer1.world.lights.add(pointLight);

        tracer1.createImage();
    }
}

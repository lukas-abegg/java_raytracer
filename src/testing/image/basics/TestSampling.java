package testing.image.basics;

import camera.Camera;
import camera.PerspectiveCamera;
import color.Color;
import geometry.*;
import image.basics.RayTracer;
import light.DirectionalLight;
import light.Light;
import light.PointLight;
import material.Material;
import material.PhongMaterial;
import material.ReflectiveMaterial;
import mathlib.Point3;
import mathlib.Transform;
import mathlib.Vector3;
import sampling.SamplingPattern;
import texture.SingleColorTexture;
import world.World;

import java.util.ArrayList;


public class TestSampling {

    public static Material reflectiveMaterial1 = new ReflectiveMaterial(new SingleColorTexture(new Color(0.1, 0.1, 0.1)), new SingleColorTexture(new Color(0, 0, 0)), 64, new SingleColorTexture(new Color(0.5, 0.5, 0.5)));
    public static Material reflectiveMaterial2 = new ReflectiveMaterial(new SingleColorTexture(new Color(  1,   0,   0)), new SingleColorTexture(new Color(1, 1, 1)), 64, new SingleColorTexture(new Color(0.5, 0.5, 0.5)));
    public static Material reflectiveMaterial3 = new ReflectiveMaterial(new SingleColorTexture(new Color(  0,   1,   0)), new SingleColorTexture(new Color(1, 1, 1)), 64, new SingleColorTexture(new Color(0.5, 0.5, 0.5)));
    public static Material reflectiveMaterial4 = new ReflectiveMaterial(new SingleColorTexture(new Color(  0,   0,   1)), new SingleColorTexture(new Color(1, 1, 1)), 64, new SingleColorTexture(new Color(0.5, 0.5, 0.5)));

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
        PerspectiveCamera camera1 = new PerspectiveCamera(new Point3(8, 8, 8), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4, new SamplingPattern(1, 1));
        runSampled(camera1);
        
        PerspectiveCamera camera2 = new PerspectiveCamera(new Point3(8, 8, 8), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4, new SamplingPattern(4, 4));
        runSampled(camera2);
    }
    
    public static void runSampled(Camera camera){
        Transform transform = new Transform();
        
        Material aabPhong_material   = new PhongMaterial(new SingleColorTexture( new Color(0.75, 0.75, 0)), new SingleColorTexture( new Color(1, 1, 1)), 64);
        AxisAlignedBox aab = new AxisAlignedBox(aabPhong_material);

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
}

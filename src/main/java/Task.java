
//задача - программа должна верно находить дискриминант квадратного уравнения

public class Task {
    public static double task(double a, double b, double c){
        double discriminant=0, x1=0, x2=0;
        discriminant=Math.pow(b,2)-(4*a*c);
        return discriminant;


    }

    public static double task2(double a, double b, double c) {
        double discriminant=0, x1=0, x2=0;
        discriminant=Math.pow(b,2)-3*a*c;
        return discriminant;
    }
}

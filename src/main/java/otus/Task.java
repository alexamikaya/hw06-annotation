package otus;
//задача - программа должна верно находить дискриминант квадратного уравнения

public class Task implements ClassInterface{
    public String task(double a, double b, double c){
        double discriminant=0, x1=0, x2=0;
        discriminant=Math.pow(b,2)-(4*a*c);
        return String.valueOf(discriminant);


    }

    public String task2(double a, double b, double c) {

        return null;
    }

}

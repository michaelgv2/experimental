import java.util.Comparator;

public class Basement {

    private double const1;
    private double const2;
    private double const3;
    private double const4;
    private double const5;

    Basement(double const1,double const2, double const3, double const4, double const5){
        this.const1=const1;
        this.const2=const2;
        this.const3=const3;
        this.const4=const4;
        this.const5=const5;
    }
    
    private void highbeat(double freq) {
        double K = Math.tan(Math.PI*freq);
        double normal = 1 / (1+K*Math.sqrt(2)+K*K);
        const1=normal;
        const2=const1*-2;
        const3=const1;
        const4=2*(K*K-1)*normal;
        const5=(1-K*Math.sqrt(2)+K*K)*normal;
    }

    private void lowbeat(double freq){
        double K = Math.tan(Math.PI*freq);
        double normal = 1 / (1+K*Math.sqrt(2)+(K*K));
        const1=K*K*normal;
        const2=const1*2;
        const3=const1;
        const4=2*(K*K-1)*normal;
        const5=(1-K*Math.sqrt(2)+(K*K));

    }

    class f_comparator implements Comparator<Float> {
        @Override
        public int compare(Float f1, Float f2) {
            return f1<f2?-1:f1>f2?1:0;
        }
    }
    class i_comparator implements Comparator<Integer> {
        public int compare(Integer i1, Integer i2) {
            return i1<i2?-1:i1>i2?1:0;
        }
    }
}

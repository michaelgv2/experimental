import org.bytedeco.fftw.global.fftw3;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Pointer;


import java.util.Comparator;

public class Basement {

    public class filter{
        double const1;
        double const2;
        double const3;
        double const4;
        double const5;
    }
    public class processing_buffer{
        double period;
        int sample_count= (int) Math.ceil(period);
        int sample_rate;
        float samples, samples_sc, waveform, waveform_sc, tic_wf, slice_wf, tic_c;
        fftw3.fftwf_plan x = new fftw3.fftwf_plan();
        double sigma;
        int be;
        int waveform_max;
        int tic_pulse;
        int tac_pulse;
        int amp;
        int tic;
        int tac;
        int ready;
        int timestamp;
    }

    private void highbeat(filter f, double freq) {
        double K = Math.tan(Math.PI*freq);
        double normal = 1 / (1+K*Math.sqrt(2)+K*K);
        f.const1=normal;
        f.const2=f.const1*-2;
        f.const3=f.const1;
        f.const4=2*(K*K-1)*normal;
        f.const5=(1-K*Math.sqrt(2)+K*K)*normal;
    }

    private void lowbeat(filter f, double freq){
        double K = Math.tan(Math.PI*freq);
        double normal = 1 / (1+K*Math.sqrt(2)+(K*K));
        f.const1=K*K*normal;
        f.const2=f.const1*2;
        f.const3=f.const1;
        f.const4=2*(K*K-1)*normal;
        f.const5=(1-K*Math.sqrt(2)+(K*K));
    }

    private void filtering(filter f, float buffer[], int size){
        double z1=0.0;
        double z2=0.0;
        for(int i=0;i<size;i++){
            double in=buffer[i];
            double out=in*f.const1+z1;
            z1=in*f.const2+z2-f.const4*out;
            z2=in*f.const3-f.const5*out;
            buffer[i]= (float) out;
        }
    }

    //CURRENT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private void init_buffers(processing_buffer p){
        Pointer samples = fftw3.fftwf_malloc(2*p.sample_count*Float.SIZE);
        int samples_sc;
        int waveform;
        int waveform_sc;
        //Pointer fft = fftw3.fftwf_malloc(2*p.sample_count*fftw3.fftwf_complex);
        int sc_fft;
        int tic_wf;
        int slice_wf;
        int tic_fft;
        int slice_fft;
        int tic_c;
        //Object plan_a = fftw3.fftwf_plan_dft_r2c_1d(2*p.sample_count, (FloatPointer) samples, (FloatPointer) fft, fftw3.FFTW_ESTIMATE);
    }


    private float vmax(float v[], int a, int b, int i_max){
        float max = v[a];
        if(i_max!=0) i_max=a;
        for(int i=a+1;i<b;i++){
            if(v[i]>max) {
                max=v[i];
                if(i_max!=0) i_max=i;
            }
        }
        return max;
    }

    private static void noise_reduction(processing_buffer p) {
        float a = p.samples_sc;
        float b = p.samples_sc+p.sample_count;
        int windows = p.sample_rate/50;

        for(int i=0; i<p.sample_count;i++){

        }
    }

    //END BLOCK////////////////////////////////////////////////////////////////
    class float_comparator implements Comparator<Float> {
        @Override
        public int compare(Float f1, Float f2) {
            return f1<f2?-1:f1>f2?1:0;
        }
    }
    class integer_comparator implements Comparator<Integer> {
        public int compare(Integer i1, Integer i2) {
            return i1<i2?-1:i1>i2?1:0;
        }
    }
}

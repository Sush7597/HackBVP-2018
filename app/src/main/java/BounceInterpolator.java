public class BounceInterpolator implements android.view.animation.Interpolator {
    private double Amp = 0.2;
    private double Freq = 20;

    @Override
    public float getInterpolation(float input) {
        return (float) (-1 * Math.pow(Math.E, -input/ Amp) *
                Math.cos(Freq * input) + 1);
    }
}

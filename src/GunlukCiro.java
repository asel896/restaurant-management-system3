public class GunlukCiro implements CiroObserver {

    private double gunlukCiro = 0;

    @Override
    public void update(double tutar) {

        gunlukCiro += tutar;
    }

    public double getGunlukCiro() {

        return gunlukCiro;
    }
}
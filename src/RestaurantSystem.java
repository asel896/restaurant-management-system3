public class RestaurantSystem {

    public static void main(String[] args) {

        // urunler
        Product corba = new Product("Ezogelin", 145);
        Product kebap = new Product("Adana Kebap", 390);

        // masa
        Table masa1 = new Table(1);

        // observer
        GunlukCiro revenueSystem =new GunlukCiro();

        // siparis
        Order siparis = new Order(masa1);

        // observer baglama
        siparis.addObserver(revenueSystem);

        // siparisler
        siparis.urunEkle(corba, 2);
        siparis.urunEkle(kebap, 1);

        // toplam hesap
        System.out.println("Toplam Hesap: " + siparis.toplamHesapla() + " TL");

        // hesap kapatma
        siparis.hesapKapat();

        // gunluk ciro
        System.out.println("\nGünlük Ciro: " + revenueSystem.getGunlukCiro() + " TL");
    }
}
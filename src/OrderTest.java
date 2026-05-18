import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    // Ortak kullanılacak nesneleri tanımlıyoruz
    private Table masa;
    private Order siparis;
    private GunlukCiro gunlukCiro;
    private Product corba;
    private Product kebap;

    @BeforeEach
    public void setUp() {
        // Her testten önce bu kısım otomatik çalışır, kod tekrarını önler
        masa = new Table(1);
        siparis = new Order(masa);

        gunlukCiro = new GunlukCiro();
        siparis.addObserver(gunlukCiro);

        corba = new Product("Ezogelin", 100);
        kebap = new Product("Adana", 200);
    }

    @Test
    public void toplamHesapTesti() {
        siparis.urunEkle(corba, 2); // 200
        siparis.urunEkle(kebap, 1); // 200

        assertEquals(400.0, siparis.toplamHesapla(), "Toplam hesap doğru yapılmalı");
    }

    @Test
    public void hesapKapatmaSiparisDurumuTesti() {
        siparis.urunEkle(corba, 1);
        siparis.hesapKapat();

        assertTrue(siparis.isTamamlandi(), "Hesap kapatıldığında sipariş tamamlandı olmalı");
    }

    @Test
    public void hesapKapatmaMasaBosaltmaTesti() {
        siparis.urunEkle(corba, 1);
        siparis.hesapKapat();

        assertFalse(masa.isOccupied(), "Hesap kapatıldığında masa boşalmalı");
    }

    @Test
    public void hesapKapatmaGunlukCiroTesti() {
        siparis.urunEkle(corba, 3); // 300 TL
        siparis.hesapKapat();

        assertEquals(300.0, gunlukCiro.getGunlukCiro(), "Hesap kapatılınca tutar ciroya eklenmeli");
    }
}
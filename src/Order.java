import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> siparisler = new ArrayList<>();
    private boolean tamamlandi;
    private LocalDate siparisTarihi;
    private Table masa;

    // observer listesi
    private List<CiroObserver> observers = new ArrayList<>();

    public Order(Table masa) {
        this.masa = masa;
        this.tamamlandi = false;
        this.siparisTarihi = LocalDate.now();
    }

    // observer ekleme
    public void addObserver(CiroObserver observer) {
        observers.add(observer);
    }

    // observerlara haber verme
    public void notifyObservers(double tutar) {
        for(CiroObserver observer : observers) {
            observer.update(tutar);
        }
    }

    // urun ekleme
    public void urunEkle(Product product, int adet) {
        siparisler.add(new OrderItem(product, adet));
    }

    // toplam hesap
    public double toplamHesapla() {
        double toplam = 0;
        for(OrderItem item : siparisler) {
            toplam += item.getTotalPrice();
        }
        return toplam;
    }

    // hesap kapatma
    public void hesapKapat() {
        tamamlandi = true;
        // masa bosalir
        masa.setOccupied(false);
        // observer tetiklenir
        notifyObservers(toplamHesapla());
        System.out.println("\n=== HESAP KAPATILDI ===");
        System.out.println("Masa No: " +
                masa.getTableNumber());
        System.out.println("Toplam Tutar: " +
                toplamHesapla() + " TL");
        System.out.println("Sipariş Tarihi: " +
                siparisTarihi);
    }

    public boolean isTamamlandi() {
        return tamamlandi;
    }

    public LocalDate getSiparisTarihi() {
        return siparisTarihi;
    }
}
# 🍽️ Restoran Yönetim Sistemi - Aşama 3

Bu proje, restoran yönetim sisteminin üçüncü aşamasıdır. Sistemde garsonların siparişleri kapatması, masaların durumunun otomatik güncellenmesi ve tamamlanan siparişlerin ardından yönetici (Admin) paneline cironun anlık olarak yansıtılması simüle edilmiştir.

## 🚀 Özellik: Hesaplama, Sipariş Kapatma ve Günlük Ciro (İhtiyaç 3)
* **Otomatik Hesaplama:** Siparişe eklenen her ürünün fiyatı ve adedi dikkate alınarak toplam hesap otomatik olarak hesaplanır.
* **Hesap Kapatma İşlemi:** Garson siparişi tamamlayıp "hesap kapat" dediğinde:
    * İlgili masanın durumu anında **BOŞ** olarak güncellenir.
    * Sipariş tamamlamış olarak sisteme kaydedilir ve tarih bilgisiyle saklanır.
* **Finansal Takip (Admin):** Admin, belirli bir tarihe göre filtrelenmiş günlük toplam ciroyu anlık olarak görüntüleyebilir.

---

## 🛠️ Behavioral (Davranışsal) Tasarım Kalıbı: Observer (Gözlemci) Pattern

**Tanımı:** Nesneler arasında bire-çok (one-to-many) bir bağımlılık ilişkisi tanımlayarak; bir nesnenin (Subject) durumu değiştiğinde, ona bağımlı olan tüm gözlemcilerin (Observers) otomatik olarak haberdar edilmesini ve güncellenmesini sağlayan davranışsal bir tasarım kalıbıdır.

### 🔍 Kod Üzerinde Uygulama Analizi

* **A. Observable / Subject (Gözlemlenen Sınıf) - `Order` Sınıfı:**
  Durumu değişen ana yapıdır. İçerisinde gözlemcilerin (`CiroObserver`) listesini tutar. Garson siparişi kapatıp hesap toplamını kesinleştirdiğinde, kayıtlı tüm gözlemcileri döngüyle tetikler.

* **B. Observer (Gözlemci Arayüzü) - `CiroObserver` Interface:**
  Sistemdeki ciro değişikliklerini dinleyecek olan modüllerin uygulaması gereken ortak arayüzdür. İçerisinde `update(double miktar)` gibi tetikleyici bir metot barındırır.

* **C. Concrete Observer (Somut Gözlemci) - `GunlukCiro` Sınıfı:**
  `CiroObserver` arayüzünü uygulayarak (`implements`) sipariş kapatma olayını canlı olarak dinleyen sınıftır. Yeni bir hesap kapatıldığında otomatik olarak haberdar edilir ve o günün cirosunu günceller.

### ❓ Neden Bu Kalıbı Kullandık?

1. **Gevşek Bağlılık (Loose Coupling):** Sipariş yönetim mekanizması, ciro hesaplama mekanizmasının iç detaylarını bilmek zorunda kalmaz. Sadece "ben hesabı kapattım, ilgilenenlere duyurulur" diyerek notification fırlatır.
2. **Anlık ve Otomatik Tetiklenme:** Garson siparişi kapattığı an, başka hiçbir sınıftan manuel kod çağırmaya gerek kalmadan `GunlukCiro` sınıfı olayı anında yakalar ve güncel bakiyeyi yansıtır.
3. **Genişletilebilirlik (Open/Closed):** Gelecekte sipariş kapandığında sadece ciro değil, aynı zamanda "Mutfak Stok Takip Sistemi" veya "Müşteri Puan Sistemi" gibi yeni modüllerin de haberdar olmasını istersek; mevcut sipariş kodlarına hiç dokunmadan sadece yeni bir Gözlemci (Observer) sınıfı yazıp sisteme kaydedebiliriz.

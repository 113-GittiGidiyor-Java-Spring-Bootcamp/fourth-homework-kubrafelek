## Dördüncü hafta ödevi son teslim tarihi : 06.08.2021(Gelecek hafta pazartesi) - Saat =>  23:30

![homework](https://user-images.githubusercontent.com/45206582/131386439-6727321a-5a50-4c20-9413-ea4013013434.PNG)

##Modeller (Entity)

* Student
* Instructor
* Course
 
-> Toplam da 3 adet model vardır. Controller üzerinde response ve request işlemleri DTO kapsamında gerçekleştirilmiştir.

-> Instructor~Course arasında **OneToMany** ilişkisi - Student~Course arasında **ManyToMany** ilişkisi bulunmaktadır.

## Nasıl kullanabilirim ? 
 
* Cloneladıktan sonra veritabanı oluşturmaya gerek duymaksızın **localhost:8080/[swagger-ui](http://localhost:8080/swagger-ui.html)** şeklinde endpointlere erişim sağlanabilir. 
 
* Database erişimi için **localhost:8080/[h2-console](http://localhost:8080/h2-console/)** diyerek in memory database yapısına erişim sağlanır. 

* **application.yml** içinde ki configurasyon ayarları isteye bağlı değiştirilebilir veya düzenlenebilir.


## Kullanılan Teknolojiler

- Spring Boot
- Spring Web + Data Jpa
- Lombok
- Swagger UI
- H2 Database
- MapStruct

### - Sistemde aynı telefon numarasına sahip 2 ayrı eğitmen bulunamaz.
![](../ekran_görüntüleri/Ekran Resmi 2021-09-04 18.06.01.png)
---
### - Sistemde aynı ders koduna sahip 2 ayrı ders olamaz.
![](../ekran_görüntüleri/Ekran Resmi 2021-09-07 18.01.42.png)
---
### - Öğrenci yaşı 18’den küçük, 40’tan büyük olamaz.
![](../ekran_görüntüleri/Ekran Resmi 2021-09-06 10.45.59.png)
---
### - Bir dersi aynı anda en fazla 20 öğrenci alabilir. 21. öğrenci eklendiğinde bu hata ile karşılaşılır.
![](../ekran_görüntüleri/Ekran Resmi 2021-09-07 18.35.02.png)
---
### - İşlenen Hataların Database Kayıtlırı
![](../ekran_görüntüleri/Ekran Resmi 2021-09-07 18.38.37.png)
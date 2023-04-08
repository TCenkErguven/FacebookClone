# MİKROSERVİS İŞLEMLERİ VE NOTLARIM

## 1. Kurulum adımları

    1.1. Boş bir gradle projesi açtık.
    1.2. dependencies.gradle dosyasını koladık
        1.2.1.ext{} bloğu içerisindeki kütüphaneleri projemize dahil ettik
        1.2.2.versions{} bloğu içerisinde kullanacağımız kütüphaneleri belirledik.
    1.3. build.gradle dosyasını kodladık, bu dosya içinde tüm alt projelerimizde ortak olarak 
    kullanacağımız kütüphaneleri belirledik
    1.4. Uygulamamızın mimarisi gereği ihtiya. duyduğumuz mikroservisleri modül olarak ekledik.
    1.5. Her bir modül için eklememiz gereken aşağıdaki kod bloğunu
    build.gradle dosyalarına ekledik.
```
    buildscript {
        dependencies {
            classpath("org.springframework.bott:spring-boot-gradle-plugin:${versions.springBoot}")
        }
    }
```
    1.6. Tüm modüllerimize monolitik mimaride kullandığımız default kodlamaları ekledik.
    1.7. Eğer bir modül içinde kullanmak istediğimiz özel bağımlılıkları var ise bunları
    build.gradle dosyalarını ekledik.
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

## 2. Mongo DB Kurulum ve Kullanım
### 2.1. Mongo DB Docker üzerinde çalıştırmak

    docker kurulu olan bir sistem üzerinde command satırına girerek aşağıda
    yer alan komutları yazarak docker üzerinden çalıştırınız.


    > docker run --name dockermongo -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=BilgeAdmin -e MONGO_INITDB_ROOT_PASSWORD=root -d mongo
    Admin: BilgeAdmin, Psw: root
## 2.2. MongoDB ye bağlamak

    DİKKAT!!!
    mongodb kullanırken admin kullanıcısı ve şifrelerini veritabanlarına erişmek
    için kullanmayınız.
    Yeni bir veritabanı oluşturmak için admin kullanıcısı ile işlem yapılır ve 
    bu veritabanına atanmak üzere yeni bir kullanıcı oluşturur. Böylelikle
    admin kullanıcısının ihtiyaç kalmadan DB üzerinde işlemler gerçekleştirilir.
    Admin kullanıcısı uygulamalar tarafından erişilmemelidir. Her uygulama için özelleştirilmiş
    kullanıcılar kullanılmalıdır.

    1- Öncelikle bir veritabanı oluşturuyorsunuz.
    2- mongosh kullanarak konsolda 'use DB_ADI' yazıyorsunuz ve ilgili DB'ye geçiş yapıyorsunuz.
    3- Yine aynı ekranda yeni bir kullanıcı oluşturmanız gereklidir. Bu kullanıcı yetkili olacaktır.
    
    > db.createUser({user: "Java7User",pwd: "root",roles: ["readWrite", "dbAdmin"]})

## 3. RabbitMQ Kurulum ve Kullanım
### RabbitMQ Docker üzerinde çalıştırmak

    docker run -d --name my-rabbitmq -e RABBITMQ_DEFAULT_USER=java7 -e RABBITMQ_DEFAULT_PASS=root -p 5672:5672 -p 15672:15672 rabbitmq:3-management
    gradle import -> org.springframework.boot:spring-boot-starter-amqp:VERSION
    Rabbit Config yapılandırılır ve kuyruk yapısı tanımlanır.

## 4. Zipkin Server kurmak ve Kullanmak

    docker run --name zipkinfb -d -p 9411:9411 openzipkin/zipkin

    Zipkin için gerekli bağımlılıklar:
    springCloudSleuth       : "org.springframework.cloud:spring-cloud-starter-sleuth:${versions.springCloud}",
    springCloudZipkin       : "org.springframework.cloud:spring-cloud-sleuth-zipkin:${versions.springCloud}",

    Configuration

    zipkin:
    enabled: true
    base-url: http://localhost:9411
    service:
    name: config-server
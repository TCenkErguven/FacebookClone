package com.cenk.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * !!!!!DİKKAT!!!!!
 * Bu model üzerinden RabbitMQ ya mesaj ileteceğiz ve bu mesajı kuyruğa işlemesini isteyeceğinz.
 * Burada gönderilecek olan sınıf bilgisi rabbitMQ ya base64 olarak iletilecektir. Bu nedenle,
 * sınıfın serileştirilmesi gerekmetedir. Buna riayet etmek için sınıfın Serializable interface'ini
 * implmente etmesi gerekir.
 * !!!!!!ÇOOOOOOOK DİKKKKATTT!!!!!
 * Yine burada oluşturulan sınıf içini. Paket ismi ve sınıf içinde kullanılan tüm parametrelerin
 * aynı olması gerekir. Aksi takdirde nesneniz iletilen tarafta okunamayacaktır.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserModel implements Serializable {
    Long authid;
    String username;
    String email;
}

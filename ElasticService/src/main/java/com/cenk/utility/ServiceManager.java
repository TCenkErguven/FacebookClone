package com.cenk.utility;




import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

/**
 * Bu class'ı miras alacak bir service class'ı T'yi ve ID'yi kendi belirleyecek
 * Çünkkü farklı service sınıfları farklı tiplere veya ID'leri farklı tipte olabilir.
 * @param <T>
 * @param <ID>
 */
public class ServiceManager<T,ID> implements Iservice<T,ID>{

    /**
     * Constructor Injection, ServiceManager sınıfını kullanmak isteyen bir sınıf bu sınıfa
     * kendi JpaRepository nesnesini verecektir.
     */

    private final ElasticsearchRepository<T,ID> repository;
    public ServiceManager(ElasticsearchRepository<T,ID> repository){
        this.repository = repository;
    }

    @Override
    public void save(T t) {
        repository.save(t);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> entities) {
        return repository.saveAll(entities);
    }

    /**
     * UPDATE!!
     * Ekstra bir update komutu yoktur, ID'lerden kayıt ekleme veya update'e karar verir,
     * Eğer ID eksikse update yapılmaya çalışıldığında yeni bir kayıt kaydeder.
     * @param t
     * @return
     */
    @Override
    public T update(T t) {
        return repository.save(t);
    }

    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    /**
     * findById metodu Optional istediği için ekleme yaptık
     * @param id
     * @return
     */
    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<T> findAll() {
        return repository.findAll();
    }
}

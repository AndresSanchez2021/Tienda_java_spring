package com.bool.market.persistence;

import com.bool.market.domain.Purchase;
import com.bool.market.domain.repository.PurchaseRepository;
import com.bool.market.persistence.crud.CompraCrudRepository;
import com.bool.market.persistence.entity.Compra;
import com.bool.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private PurchaseMapper mapper;


    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId).map(compras-> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(product -> product.setCompra(compra));
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}

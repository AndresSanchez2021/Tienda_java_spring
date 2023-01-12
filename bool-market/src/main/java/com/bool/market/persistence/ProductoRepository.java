package com.bool.market.persistence;

import com.bool.market.domain.Product;
import com.bool.market.domain.repository.ProductRepository;
import com.bool.market.persistence.crud.ProductoCrudRepository;
import com.bool.market.persistence.entity.Producto;
import com.bool.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@Component
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository ;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos  = (List<Producto>) productoCrudRepository. findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId){
        List<Producto> productos = productoCrudRepository.findByIdCategoria(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity){
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map( prod -> mapper.toProducts(prod));
    }

    @Override
    public Optional<Product> getProduct(int productId){
        return productoCrudRepository.findById( productId).map(producto -> mapper.toProduct(producto));
    }


    public List<Producto> getByCategoryAndNombre(int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    public Optional<Producto> getProducto(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    @Override
    public Product save(Product product){
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));

    }

    @Override
    public void delete(int productoId){
        productoCrudRepository.deleteById(productoId);
    }
}

package ru.comavp.javaeeexample.rest;

import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.comavp.javaeeexample.model.ProductRepository;
import ru.comavp.javaeeexample.model.dto.ProductDto;
import ru.comavp.javaeeexample.model.entity.Product;
import ru.comavp.javaeeexample.utils.ProductMapper;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.logging.Logger;

@Path("products")
public class ProductResource {

    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Inject
    private ProductRepository productRepository;

    @GET
    @Path("/{id}")
    @HeaderParam("rqUid")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDto findProductById(@PathParam("id") Long id) {
        logger.info("Request to find product by id " + id);
        return ProductMapper.mapToDto(productRepository.findById(id)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND)));
    }

    @GET
    @HeaderParam("rqUid")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDto> findAllProducts() {
        logger.info("Request to find all products");
        return ProductMapper.mapToDto(productRepository.findAll());
    }

    @POST
    @HeaderParam("rqUid")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createProduct(ProductDto productDto) {
        logger.info("Request to create product " + productDto.getName());
        try {
            return productRepository.create(ProductMapper.mapToEntity(productDto)).getId().toString();
        } catch (PersistenceException e) {
            logger.info("Error creating product " + productDto.getName());
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @DELETE
    @Path("/{id}")
    @HeaderParam("rqUid")
    public void deleteProductById(@PathParam("id") Long id) {
        logger.info("Request to delete product by id " + id);
        try {
            productRepository.delete(id);
        } catch (IllegalArgumentException e) {
            logger.info("Error deleting product by id" + id);
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @PUT
    @HeaderParam("rqUid")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Product updateProduct(ProductDto modifyProductDto) {
        logger.info("Request to update product " + modifyProductDto.getName());
        try {
            return productRepository.update(ProductMapper.mapToEntity(modifyProductDto));
        } catch (PersistenceException e) {
            logger.info("Error updating product " + modifyProductDto.getName());
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
}

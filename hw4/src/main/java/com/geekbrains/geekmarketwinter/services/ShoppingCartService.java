package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.Product;
import com.geekbrains.geekmarketwinter.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ShoppingCartService {
    private ProductService productService;
    private final Map<String, HttpSession> id2session = new ConcurrentHashMap<>();

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public ShoppingCart getCurrentCart(HttpSession session) {
        storeSessionInfo(session);

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    public void storeSessionInfo(@NonNull HttpSession session) {
        id2session.put(session.getId(), session);
    }

    @Nullable
    public ShoppingCart getCurrentCart(String sessionId) {
        //так делать нельзя, но и хранить корзину по сессии - так себе затея
        final HttpSession session = id2session.get(sessionId);
        if (session == null) {
            return null;
        }

        return getCurrentCart(session);
    }

    public void resetCart(HttpSession session) {
        session.removeAttribute("cart");
    }

    public void addToCart(HttpSession session, Long productId) {
        Product product = productService.getProductById(productId);
        addToCart(session, product);
    }

    public void addToCart(HttpSession session, Product product) {
        ShoppingCart cart = getCurrentCart(session);
        cart.add(product);
    }

    public void removeFromCart(HttpSession session, Long productId) {
        Product product = productService.getProductById(productId);
        removeFromCart(session, product);
    }

    public void removeFromCart(HttpSession session, Product product) {
        ShoppingCart cart = getCurrentCart(session);
        cart.remove(product);
    }

    public void setProductCount(HttpSession session, Long productId, Long quantity) {
        ShoppingCart cart = getCurrentCart(session);
        Product product = productService.getProductById(productId);
        cart.setQuantity(product, quantity);
    }

    public void setProductCount(HttpSession session, Product product, Long quantity) {
        ShoppingCart cart = getCurrentCart(session);
        cart.setQuantity(product, quantity);
    }

    public double getTotalCost(HttpSession session) {
        return getCurrentCart(session).getTotalCost();
    }
}

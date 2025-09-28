package iuh.fit.module3.model;

import java.util.ArrayList;
import java.util.List;

public class CartBean {
    private List<CartItemBean> items;

    public CartBean() {
        items = new ArrayList<>();
    }
    public  void  addProduct(Product p){
        for (CartItemBean item: items){
            if (item.getProduct().getId() == p.getId()){
                item.setQuantity(item.getQuantity() +1);
                return;
            }
        }
        items.add(new CartItemBean(p, 1));
    }

    public  void  removeProduct (int productId){
        items.removeIf(item -> item.getProduct().getId() == productId);
    }

    //cap nhat so luong
    public void updateProductQuantity(int productId, int quantity) {
        for (CartItemBean item : items) {
            if (item.getProduct().getId() == productId) {
               if (quantity > 0){
                   item.setQuantity(quantity);
               } else {
                   removeProduct(productId);
               }
                return;
            }
        }
    }

    //tinh tong tien
    public double getTotalPrice() {
        double total = 0;
        for (CartItemBean item : items) {
            total += item.getSubTotal();
        }
        return total;
    }

    public void  clear(){ items.clear(); }

}

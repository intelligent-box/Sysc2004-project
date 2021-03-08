import java.math.BigDecimal;

public class ShoppingCart extends Inventory {
    private String nick;
    public ShoppingCart(String nickname){
        super();
        nick = nickname;
    }

    @Override
    public boolean addProduct(Product newProduct) {
        return super.addProduct(newProduct, 1);
    }

    public void removeProduct(Product oldProduct) {
        getQuantity().remove(getProducts().indexOf(oldProduct));
        getProducts().remove(getProducts().indexOf(oldProduct));
        return;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nickname) {
        this.nick = nickname;
    }

    public BigDecimal checkout(){
        BigDecimal sum = new BigDecimal(0);
        for (int i = this.getProducts().size()-1; i >= 0; i--) {
            sum = sum.add(this.getProducts().get(i).getPrice().multiply(BigDecimal.valueOf(this.getQuantity().get(i))));
        }
        return sum;
    }
}

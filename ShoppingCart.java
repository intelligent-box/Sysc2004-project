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
}

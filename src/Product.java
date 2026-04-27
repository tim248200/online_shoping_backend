public abstract class Product {
    private int id;
    private String title;
    private int price;

    abstract void callPrice(double price);
    abstract void showInfo();

    public Product(int id, String title, int price){}

    public Product(int id, int price, String title){
        this.id = id;
        this.title = title;
        this.price = 0;
    }

    public String getTitle(){
        return title;
    }

    public int getId(){
        return id;
    }

    public int getPrice(){
        return price;
    }

    public void setTitle(){
        this.title = title;
    }

    public void setPrice(){
        this.price = price;
    }

    public void setId(){
        this.id = id;
    }
}
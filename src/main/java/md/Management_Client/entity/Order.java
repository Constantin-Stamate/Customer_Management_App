package md.Management_Client.entity;

public class Order {
    private Long id;
    private double amount;
    private String date;
    private Long idCustomer;

    public Order(Long id, String date, double amount, Long idCustomer) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.idCustomer = idCustomer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", idCustomer=" + idCustomer +
                '}';
    }
}

package abstractStockFactory;

public interface abstractWarehouseFactory<T> {
    T create(int stockType);
}

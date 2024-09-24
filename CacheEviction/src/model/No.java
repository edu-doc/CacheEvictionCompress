package model;

public class No {
    
    public No proximo;

    public ServiceOrder order;

    public No (ServiceOrder order){
        this.order = order;
    }

    public ServiceOrder getOrder(){
        return order;
    }

    @Override
    public String toString() {
        return "["+order.codigoServico+"]";
    }

}

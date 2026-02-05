package models;

import java.util.List;
import java.util.Stack;

public class Pedido {
    private String cliente;
    private String codigoPostal;
    private List<Integer> prioridades;
    private int zona;
    private int urgencia;

    public Pedido(String cliente, String codigoPostal, List<Integer> prioridades) {
        this.cliente = cliente;
        this.codigoPostal = codigoPostal;
        this.prioridades = prioridades;
        calcularZona();
        calcularUrgencia();
    }

    private void calcularZona() {
        String[] parte = codigoPostal.split("-");
        zona = Integer.parseInt(parte[1]);
    }

    private void calcularUrgencia() {
        int suma = 0;
        Stack<Character> letras = new Stack<>();
        for (int p : prioridades) {
            if (p % 3 == 0) {
                suma += p;
            }
        }
        for (char c : cliente.toLowerCase().toCharArray()) {
            if ("aeiou".contains(String.valueOf(c))) {
                letras.add(c);
            }
        }
        urgencia = suma * letras.size();
    }

    public String getCliente() {
        return cliente;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public List<Integer> getPrioridades() {
        return prioridades;
    }

    public int getZona() {
        return zona;
    }

    public int getUrgencia() {
        return urgencia;
    }

    @Override
    public String toString() {
        return "Pedido [cliente=" + cliente + ", codigoPostal=" + codigoPostal + ", prioridades=" + prioridades + "]";
    }
}

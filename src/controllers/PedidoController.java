package controllers;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import models.Pedido;

public class PedidoController {
    public Stack<Pedido> filtrarPorZona(List<Pedido> pedidos, int umbral) {
        Stack<Pedido> stack = new Stack<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getZona() > umbral) {
                stack.push(pedido);
            }
        }
        return stack;

    }

    public TreeSet<Pedido> ordenarPorZona(Stack<Pedido> pila) {
        Comparator<Pedido> comparar = new Comparator<Pedido>() {
            public int compare(Pedido p1, Pedido p2) {
                if (p1.getZona() != p2.getZona()) {
                    return p2.getZona() - p1.getZona();
                }
                return p1.getCliente().compareToIgnoreCase(p2.getCliente());
            }
        };
        TreeSet<Pedido> set = new TreeSet<>(comparar);
        set.addAll(pila);
        return set;
    }

    public TreeMap<Integer, Queue<Pedido>> agruparPorUrgencia(List<Pedido> pedidos) {
        TreeMap<Integer, Queue<Pedido>> mapa = new TreeMap<>();
        for (Pedido pedido : pedidos) {
            int urgencia = pedido.getUrgencia();
            if (!mapa.containsKey(urgencia)) {
                mapa.put(urgencia, new LinkedList<>());
            }
            mapa.get(urgencia).add(pedido);
        }
        return mapa;
    }

    public Stack<Pedido> explotarGrupo(Map<Integer, Queue<Pedido>> mapa) {
        Queue<Pedido> mejorGrupo = null;
        int mejorUrgencia = -1;

        for (Map.Entry<Integer, Queue<Pedido>> entry : mapa.entrySet()) {
            int urgencia = entry.getKey();
            Queue<Pedido> grupo = entry.getValue();
            if (mejorGrupo == null || grupo.size() > mejorGrupo.size()
                    || (grupo.size() == mejorGrupo.size() && urgencia > mejorUrgencia)) {
                mejorGrupo = grupo;
                mejorUrgencia = urgencia;
            }
        }
        Stack<Pedido> stack = new Stack<>();
        if (mejorGrupo != null) {
            stack.addAll(mejorGrupo);
        }
        return stack;
    }

}

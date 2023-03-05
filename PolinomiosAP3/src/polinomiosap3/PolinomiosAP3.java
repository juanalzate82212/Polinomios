package polinomiosap3;

import javax.swing.JOptionPane;

public class PolinomiosAP3 {

    public static void main(String[] args) {
        String S = "";
        S = "Ingrese el polinomio";
        int Vp[] = Separacion(S), VAux[];
        int opc1, opc2, x;

        do {
            opc1 = Integer.parseInt(JOptionPane.showInputDialog("Menu principal \n"
                    + "¿Como desea organizar el polinomio?\n"
                    + "1. Forma 1\n"
                    + "2. Forma 2\n"
                    + "3. Forma 3\n"
                    + "0. Salir"));

            switch (opc1) {
                case 1:
                    VPF1 F1 = new VPF1(Vp[1]);
                    F1.PolForma1(Vp);
                    /*for (int i = 0; i < F1.getDU(); i++) {
                        System.out.print("[" + F1.getVPF1(i) + "]");
                    }*/
                    do {
                        opc2 = Integer.parseInt(JOptionPane.showInputDialog("Menu Forma 1 \n"
                                + "1. Insertar\n"
                                + "2. Eliminar\n"
                                + "3. Mostrar Vector\n"
                                + "4. Mostrar Forma\n"
                                + "5. Evaluar\n"
                                + "6. Sumar\n"
                                + "7. Multiplicar\n"
                                + "8. Sumar con forma 3 y resultando en un forma 2\n"
                                + "0. Salir\n"));

                        switch (opc2) {
                            case 1:
                                S = "Ingrese nuevo monomio";
                                VAux = Separacion(S);
                                F1.Insertar(VAux);
                                JOptionPane.showMessageDialog(null, "El monomio fue insertado");
                                break;
                            case 2:
                                F1.Eliminar(Integer.parseInt(JOptionPane.showInputDialog("Ingrese coeficiente a eliminar")));
                                break;
                            case 3:
                                F1.Mostrar();
                                break;
                            case 4:
                                F1.MostrarForma();
                                break;
                            case 5:
                                x = Integer.parseInt(JOptionPane.showInputDialog("Ingrese un valor para x"));
                                F1.Evaluar(x);
                                break;
                            case 6:
                                S = "Ingrese el polinomio que le desea sumar";
                                VAux = Separacion(S);
                                VPF1 F11 = new VPF1(VAux[1]);
                                F11.PolForma1(VAux);
                                VPF1 FSum;
                                if (F1.getVPF1(0) > F11.getVPF1(0)) {
                                    FSum = new VPF1(F1.getVPF1(0));
                                    FSum.setVPF1(0, F1.getVPF1(0));
                                } else {
                                    FSum = new VPF1(F11.getVPF1(0));
                                    FSum.setVPF1(0, F11.getVPF1(0));
                                }
                                F1.Sumar(F11, FSum);
                                break;
                            case 7:
                                S = "Ingrese el polinomio que desea multiplicar: ";
                                VAux = Separacion(S);
                                VPF1 V2 = new VPF1(VAux[1]);
                                V2.PolForma1(VAux);
                                F1.Multiplicar(V2);
                                break;
                            case 8:
                                S = "Ingrese el polinomio que le desea sumar";
                                VAux = Separacion(S);
                                VPF3 F3 = new VPF3();
                                F3.PolForma3(VAux);
                                int i = 0,
                                 cont = 0;
                                int k = 1;
                                Nodo j = F3.getPunta();
                                VPF2 F2;
                                if (i > j.getE()) {
                                    F2 = new VPF2(i + 1);
                                } else {
                                    F2 = new VPF2(j.getE() + 1);
                                }
                                i++;
                                while (i <= F1.getDU() || j != null) {
                                    if (i > F1.getDU()) {
                                        F2.setVPF2(k, j.getC());
                                        k++;
                                        F2.setVPF2(k, j.getE());
                                        k++;
                                        j = j.getLiga();
                                        cont++;
                                    }
                                    if (j == null) {
                                        F2.setVPF2(k, F1.getVPF1(i));
                                        k++;
                                        F2.setVPF2(k, F1.getDU() - i);
                                        k++;
                                        i++;
                                        cont++;
                                    }
                                    if (i <= F1.getDU() && j != null) {
                                        if (F1.getDU() - i > j.getE()) {
                                            F2.setVPF2(k, F1.getVPF1(i));
                                            k++;
                                            F2.setVPF2(k, F1.getDU() - i);
                                            k++;
                                            i++;
                                            cont++;
                                        } else {
                                            if (j.getE() > F1.getDU() - i) {
                                                F2.setVPF2(k, j.getC());
                                                k++;
                                                F2.setVPF2(k, j.getE());
                                                k++;
                                                j = j.getLiga();
                                                cont++;
                                            } else {
                                                int aux = F1.getVPF1(i) + j.getC();
                                                if (aux != 0) {
                                                    F2.setVPF2(k, aux);
                                                    k++;
                                                    F2.setVPF2(k, j.getE());
                                                    k++;
                                                    cont++;
                                                }
                                                i++;
                                                j = j.getLiga();
                                            }
                                        }
                                    }
                                }
                                F2.setVPF2(0, cont);
                                F2.setDU(cont * 2);
                                JOptionPane.showMessageDialog(null, "Se sumaron los polinomios, el resultado es:");
                                F2.Mostrar();
                                F2.MostrarForma();
                                break;
                        }
                    } while (opc2 != 0);
                    break;

                case 2:
                    int i = 0,
                     B = 1;
                    while (i < Vp.length && B == 1) {
                        if (Vp[i] == 0) {
                            B = 2;
                            i++;
                        } else {
                            i++;
                        }
                    }
                    if (i % 2 == 0) {
                        i = i / 2;
                    } else {
                        i = (i - 1) / 2;
                    }
                    VPF2 F2 = new VPF2(i);
                    F2.PolForma2(Vp);
                    do {
                        opc2 = Integer.parseInt(JOptionPane.showInputDialog("Menu Forma 1 \n"
                                + "1. Insertar\n"
                                + "2. Eliminar\n"
                                + "3. Mostrar Vector\n"
                                + "4. Mostrar Forma\n"
                                + "5. Evaluar\n"
                                + "6. Multiplicar con forma 3, resultado en forma 1\n"
                                + "0. Salir\n"));

                        switch (opc2) {
                            case 1:
                                S = "Ingrese nuevo monomio";
                                VAux = Separacion(S);
                                F2.Insertar(VAux);
                                JOptionPane.showMessageDialog(null, "El monomio fue insertado");
                                break;
                            case 2:
                                F2.Eliminar(Integer.parseInt(JOptionPane.showInputDialog("¿Cuál término desea eliminar? Inserte su exponente")));
                                break;
                            case 3:
                                F2.Mostrar();
                                break;
                            case 4:
                                F2.MostrarForma();
                                break;
                            case 5:
                                x = Integer.parseInt(JOptionPane.showInputDialog("Ingrese un valor para x"));
                                F2.Evaluar(x);
                                break;
                            case 6:
                                S = "Ingrese el polinomio que le desea multiplicar";
                                VAux = Separacion(S);
                                VPF3 F3 = new VPF3();
                                F3.PolForma3(VAux);
                                Nodo j = F3.getPunta();
                                VPF1 Aux = new VPF1(F2.getVPF2(2) + j.getE());
                                VPF1 Producto = new VPF1(F2.getVPF2(2) + j.getE());
                                VPF1 Resultado = new VPF1(F2.getVPF2(2) + j.getE());
                                int Pos;
                                i = 1;
                                while (i <= F2.getDU()) {
                                    j = F3.getPunta();
                                    while (j != null) {
                                        Pos = Aux.getDU() - (F2.getVPF2(i + 1) + j.getE());
                                        Aux.setVPF1(Pos, F2.getVPF2(i) * j.getC());
                                        j = j.getLiga();
                                    }
                                    int a = 1;
                                    while (a <= Aux.getDU() && a <= Producto.getDU()) {
                                        Resultado.setVPF1(a, Aux.getVPF1(a) + Producto.getVPF1(a));
                                        a++;
                                    }
                                    a = 1;
                                    while (a <= Aux.getDU()) {
                                        Aux.setVPF1(a, 0);
                                        Producto.setVPF1(a, 0);
                                        a++;
                                    }
                                    a = 1;
                                    while (a <= Resultado.getDU()) {
                                        Producto.setVPF1(a, Resultado.getVPF1(a));
                                        a++;
                                    }
                                    a = 1;
                                    while (a <= Resultado.getDU()) {
                                        Resultado.setVPF1(a, 0);
                                        a++;
                                    }
                                    i += 2;
                                }
                                j = F3.getPunta();
                                Producto.setVPF1(0, F2.getVPF2(2) + j.getE());
                                JOptionPane.showMessageDialog(null, "Se multiplicaron los polinomios, el resultado es:");
                                Producto.Mostrar();
                                Producto.MostrarForma();
                                break;
                        }
                    } while (opc2 != 0);
                    break;
                case 3:
                    VPF3 F3 = new VPF3();
                    F3.PolForma3(Vp);
                    do {
                        opc2 = Integer.parseInt(JOptionPane.showInputDialog("Menu Forma 1 \n"
                                + "1. Insertar\n"
                                + "2. Eliminar\n"
                                + "3. Mostrar Vector\n"
                                + "4. Mostrar Forma\n"
                                + "5. Evaluar\n"
                                + "6. Sumar\n"
                                + "7. Multiplicar\n"
                                + "0. Salir\n"));

                        switch (opc2) {
                            case 1:
                                S = "Ingrese nuevo monomio";
                                VAux = Separacion(S);
                                F3.Insertar(VAux);
                                JOptionPane.showMessageDialog(null, "El monomio fue insertado");
                                break;
                            case 2:
                                F3.Eliminar(Integer.parseInt(JOptionPane.showInputDialog("¿Cuál término desea eliminar? Inserte su exponente")));
                                break;
                            case 3:
                                F3.Mostrar();
                                break;
                            case 4:
                                F3.MostrarForma();
                                break;
                            case 5:
                                x = Integer.parseInt(JOptionPane.showInputDialog("Ingrese un valor para x"));
                                F3.Evaluar(x);
                                break;
                            case 6:
                                S = "Ingrese el polinomio que le desea sumar";
                                VAux = Separacion(S);
                                VPF3 F33 = new VPF3();
                                F33.PolForma3(VAux);
                                VPF3 FSum = new VPF3();
                                F3.Sumar(F33, FSum);
                                break;
                            case 7:
                                S = "Ingrese el polinomio que le desea multiplicar";
                                VAux = Separacion(S);
                                VPF3 FM = new VPF3();
                                FM.PolForma3(VAux);
                                F3.Multiplicar(FM);
                                break;
                        }
                    } while (opc2 != 0);
                    break;
            }
        } while (opc1 != 0);
    }

    public static int[] Separacion(String S) {
        int j = 0;
        String s = "";
        String M = JOptionPane.showInputDialog(S);
        char Vc[] = M.toCharArray();
        String Vs[];
        if (Vc.length == 1) {
            Vs = new String[Vc.length + 1];
        } else {
            Vs = new String[Vc.length];
        }


        /*for (int i = 0; i < Vc.length; i++) 
        {
            System.out.print("[" + Vc[i] + "]");
        }*/
        for (int i = 0; i < Vc.length; i++) {

            if (Character.isDigit(Vc[i]) || Vc[i] == '-') {
                if (Vc[i] == '-' || Vc[i] == '+') {
                    if (!s.equals("")) {
                        Vs[j] = s;
                        j++;
                        Vs[j] = Integer.toString(0);
                        j++;
                        s = "";
                    }

                }

                s = s + Vc[i];

                if (i == Vc.length - 1) {
                    Vs[j] = s;
                    j++;
                    Vs[j] = Integer.toString(0);
                }
            } else {
                if (Vc[i] == 'x') {
                    if (i == Vc.length - 1) {
                        Vs[j] = s;
                        Vs[j + 1] = Integer.toString(1);
                    } else {
                        if (s.equals("") || s.equals("-")) {
                            s = s + 1;
                            Vs[j] = s;
                            j++;
                            i++;
                            s = "";
                        } else {
                            Vs[j] = s;
                            j++;
                            i++;
                            s = "";
                        }

                        if (Vc[i] == '^') {
                            Vs[j] = Character.toString(Vc[i + 1]);
                            i++;
                            j++;
                        } else {
                            Vs[j] = Integer.toString(1);
                            j++;
                            i--;
                        }
                    }
                }
            }
        }

        int Vp[] = new int[Vs.length];
        int n = 0;
        System.out.print("\n");

        while (n < Vp.length && Vs[n] != null) {
            Vp[n] = Integer.parseInt(Vs[n]);
            System.out.print("[" + Vp[n] + "]");
            n++;
        }

        System.out.println("");
        int Aux = 0;

        for (int i = 1; i < Vp.length; i += 2) {
            for (int p = i + 2; p < Vp.length; p += 2) {
                if (Vp[i] < Vp[p]) {
                    Aux = Vp[i];
                    Vp[i] = Vp[p];
                    Vp[p] = Aux;
                    Aux = Vp[i - 1];
                    Vp[i - 1] = Vp[p - 1];
                    Vp[p - 1] = Aux;
                }
            }
        }

        /*for (int i = 0; i < Vp.length; i++) {
            System.out.print("[" + Vp[i] + "]");
        }*/
        return Vp;

    }
}

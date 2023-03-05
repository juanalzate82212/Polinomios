package polinomiosap3;

import javax.swing.JOptionPane;

public class VPF1 {

    private int DU;
    private int VPF1[];

    public VPF1() {
        DU = 0;
        VPF1 = new int[20];
    }

    public VPF1(int EM) {
        DU = EM + 1;
        VPF1 = new int[DU + 1];
    }

    public int getDU() {
        return DU;
    }

    public void setDU(int DU) {
        this.DU = DU;
    }

    public int[] getVPF1() {
        return VPF1;
    }

    public void setVPF1(int[] VA) {
        VPF1 = VA;
    }

    public int getVPF1(int pos) {
        return VPF1[pos];
    }

    public void setVPF1(int pos, int d) {
        VPF1[pos] = d;
    }

    //Metodos
    public void PolForma1(int Vp[]) {
        int j = 1;
        this.setVPF1(0, Vp[1]);
        /*for (int i = 1; i < this.getDU(); i++) {
            this.setVPF1(i, Vp[j]);
            j += 2;
        }*/
        this.setVPF1(1, Vp[j - 1]);
        if (Vp.length > 3) {
            j += 2;
            for (int i = 2; i <= DU; i++) {
                if (Vp[j] == Vp[j - 2] - 1) {
                    this.setVPF1(i, Vp[j - 1]);
                } else {
                    this.setVPF1(i, 0);
                    i++;
                    this.setVPF1(i, Vp[j - 1]);
                }
                j += 2;
            }
        }
    }

    public void Ajustar() {
        int i = 1;
        int cont = 0;

        while (i <= DU && VPF1[i] == 0) {
            cont++;
            i++;
        }
        while (i <= DU) {
            VPF1[i - cont] = VPF1[i];
            i++;
        }
        VPF1[0] = VPF1[0 - cont];
        this.setDU(VPF1[0] + 1);
    }

    public void RedimensionarMas(int EM, int VP[]) {
        int VA[] = new int[EM + 2];
        int Pos, j = 1;
        VA[0] = VP[1];
        VA[1] = VP[0];
        int i = (EM - this.getVPF1(0)) + 1;
        while (i <= EM + 1) {
            VA[i] = this.getVPF1(j);
            i++;
            j++;
        }
        this.setVPF1(VA);
        this.setDU(EM + 1);
    }

    public void Insertar(int Vp[]) {
        if (Vp[1] > this.getVPF1(0)) {
            RedimensionarMas(Vp[1], Vp);
        } else {
            int Pos = DU - Vp[1];
            VPF1[Pos] = VPF1[Pos] + Vp[0];
        }
        if (VPF1[1] == 0) {
            Ajustar();
        }
    }

    public void Eliminar(int C) {
        int B = 1;
        for (int i = 1; i <= DU; i++) {
            if (VPF1[i] == C) {
                VPF1[i] = 0;
                B = 2;
            }
        }
        if (B == 2) {
            Ajustar();
            JOptionPane.showMessageDialog(null, "El coeficiente fue eliminado");
        } else {
            JOptionPane.showMessageDialog(null, "El coeficiente no se encontró");
        }
        if (VPF1[1] == 0) {
            Ajustar();
        }
    }

    public void Mostrar() {
        String s = "";
        for (int i = 0; i <= DU; i++) {
            s = s + "[" + VPF1[i] + "]";
        }
        JOptionPane.showMessageDialog(null, s);
    }

    public void MostrarForma() {
        String s = "";
        int Exp;
        for (int i = 1; i <= DU; i++) {
            if (VPF1[i] != 0) {
                Exp = DU - i;
                if (Exp != 1 && Exp != 0) {
                    if (VPF1[i] < 0 || i == 1) {
                        s = s + VPF1[i] + "x^" + Exp;
                    } else {
                        s = s + "+" + VPF1[i] + "x^" + Exp;
                    }
                } else {
                    if (Exp == 1) {
                        if (VPF1[i] < 0 || i == 1) {
                            s = s + VPF1[i] + "x";
                        } else {
                            s = s + "+" + VPF1[i] + "x";
                        }
                    } else {
                        if (VPF1[i] < 0 || i == 1) {
                            s = s + VPF1[i];
                        } else {
                            s = s + "+" + VPF1[i];
                        }
                    }
                }
            }
            //s = s + "[" + VPF1[i] + "]";
        }
        JOptionPane.showMessageDialog(null, s);
    }

    public void Sumar(VPF1 F11, VPF1 FSum) {
        int i = 0, j = 0, k = 1, sum;

        i++;
        j++;
        while (i <= DU || j <= F11.getDU()) {
            if (DU - i > F11.getDU() - j) {
                //SUMA[k] = this.getVPF1(i);
                FSum.setVPF1(k, this.getVPF1(i));
                i++;
            } else {
                if (DU - i < F11.getDU() - j) {
                    FSum.setVPF1(k, F11.getVPF1(j));
                    j++;
                } else {
                    sum = this.getVPF1(i) + F11.getVPF1(j);
                    FSum.setVPF1(k, sum);
                    i++;
                    j++;
                }
            }
            k++;
        }
        JOptionPane.showMessageDialog(null, "Los polinomios fueron sumados, el resultado es:");
        FSum.MostrarForma();
    }

    public void Multiplicar(VPF1 V2) {
        VPF1 Aux = new VPF1(this.getVPF1(0) + V2.getVPF1(0));
        VPF1 Producto = new VPF1(this.getVPF1(0) + V2.getVPF1(0));
        VPF1 Resultado = new VPF1(this.getVPF1(0) + V2.getVPF1(0));
        int Pos;
        int i = 1;
        while (i <= this.getDU()) {
            int j = 1;
            while (j <= V2.getDU()) {
                Pos = Aux.getDU() - ((this.getDU() - i) + (V2.getDU() - j));
                Aux.setVPF1(Pos, this.getVPF1(i) * V2.getVPF1(j));
                j++;
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
            i++;
        }
        Producto.setVPF1(0, this.getVPF1(0) + V2.getVPF1(0));
        JOptionPane.showMessageDialog(null, "Se multiplicaron los polinomios, el resultado es:");
        Producto.MostrarForma();
    }

    public void Evaluar(int x) {
        double Result = 0;
        for (int i = 1; i <= DU; i++) {
            Result = Result + (this.getVPF1(i) * Math.pow(x, DU - i));
        }
        JOptionPane.showMessageDialog(null, "El polinomio evaluado con x = " + x + ", dio como resultado:\n" + Result);
    }

}

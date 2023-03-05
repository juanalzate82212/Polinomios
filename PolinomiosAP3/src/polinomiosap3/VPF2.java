package polinomiosap3;

import javax.swing.JOptionPane;

public class VPF2 {
    
    private int DU;
    private int VPF2[];

    public VPF2() {
        DU = 0;
        VPF2 = new int[20];
    }

    public VPF2(int NT) {
        DU = NT * 2;
        VPF2 = new int[DU + 1];
    }

    public int getDU() {
        return DU;
    }

    public void setDU(int x) {
        DU = x;
    }

    public int[] getVPF2() {
        return VPF2;
    }

    public void setVPF2(int VA[]) {
        VPF2 = VA;
    }

    public int getVPF2(int pos) {
        return VPF2[pos];
    }

    public void setVPF2(int pos, int d) {
        VPF2[pos] = d;
    }

    public void PolForma2(int Vp[]) {
        this.setVPF2(0, (DU / 2));
        for (int i = 1; i <= DU; i++) {
            this.setVPF2(i, Vp[i - 1]);
        }
    }

    public void Ajustar() {
        int i = 1;
        while (i <= DU) {
            if (this.getVPF2(i) == 0) {
                this.setVPF2(0, this.getVPF2(0) - 1);
                this.setDU(this.getVPF2(0) * 2);
                while (i <= DU) {
                    this.setVPF2(i, this.getVPF2(i + 2));
                    this.setVPF2(i + 1, this.getVPF2(i + 3));
                    i += 2;
                }
                i = 1;
            } else {
                i += 2;
            }
        }
    }

    public void Insertar(int Vp[]) {
        for (int i = 2; i <= DU; i += 2) {
            if (this.getVPF2(i) == Vp[1]) {
                this.setVPF2(i - 1, this.getVPF2(i - 1) + Vp[0]);
            }
        }
        Ajustar();
    }

    public void Eliminar(int x) {
        int i = 2, B = 1;
        while (i <= DU) {
            if (this.getVPF2(i) == x) {
                B = 2;
                this.setVPF2(i - 1, 0);
                i += 2;
            } else {
                i += 2;
            }
        }
        if (B == 2) {
            Ajustar();
            JOptionPane.showMessageDialog(null, "El término fue eliminado");
        } else {
            JOptionPane.showMessageDialog(null, "El término no se encontró");
        }
    }

    public void Mostrar() {
        String s = "";
        for (int i = 0; i <= DU; i++) {
            s = s + "[" + VPF2[i] + "]";
        }
        JOptionPane.showMessageDialog(null, s);
    }

    public void MostrarForma() {
        String s = "";
        int Exp;
        for (int i = 1; i <= DU; i += 2) {
            if (this.getVPF2(i + 1) == 1) {
                if (i == 1 || this.getVPF2(i) < 0) {
                    s = s + this.getVPF2(i) + "x";
                } else {
                    s = s + "+" + this.getVPF2(i) + "x";
                }
            }
            if (this.getVPF2(i + 1) == 0) {
                if (i == 1 || this.getVPF2(i) < 0) {
                    s = s + this.getVPF2(i);
                } else {
                    s = s + "+" + this.getVPF2(i);
                }
            }
            if (this.getVPF2(i + 1) != 0 && this.getVPF2(i + 1) != 1) {
                if (i == 1 || this.getVPF2(i) < 0) {
                    s = s + this.getVPF2(i) + "x^" + this.getVPF2(i + 1);
                } else {
                    s = s + "+" + this.getVPF2(i) + "x^" + this.getVPF2(i + 1);
                }
            }

        }
        JOptionPane.showMessageDialog(null, s);
    }

    public void Evaluar(int x) {
        double Res = 0;
        for (int i = 1; i <= DU; i += 2) {
            Res = Res + (this.getVPF2(i) * Math.pow(x, this.getVPF2(i + 1)));
        }
        JOptionPane.showMessageDialog(null, "El polinomio evaluado con x = " + x + ", dio como resultado:\n" + Res);
    }

    public void Sumar() {

    }
}

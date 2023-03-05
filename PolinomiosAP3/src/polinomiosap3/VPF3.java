package polinomiosap3;

import javax.swing.JOptionPane;

public class VPF3 {

    private Nodo Punta;

    public VPF3() {
        Punta = null;
    }

    public Nodo getPunta() {
        return Punta;
    }

    public void setPunta(Nodo Punta) {
        this.Punta = Punta;
    }

    public void InsertarFinal(int c, int e) {
        Nodo p, x = new Nodo(c, e);
        if (Punta == null) {
            Punta = x;
        } else {
            p = Punta;
            while (p.getLiga() != null) {
                p = p.getLiga();
            }
            p.setLiga(x);
        }
    }

    public void Insertar(int Vp[]) {
        Nodo x = new Nodo(Vp[0], Vp[1]);
        int B;
        if (Punta == null) {
            Punta = x;
        } else {
            Nodo a = Punta;
            Nodo p = a.getLiga();
            if (a.getE() > x.getE()) {
                while ((p != null) && x.getE() < p.getE()) {
                    a = p;
                    p = p.getLiga();
                }
                if (p == null) {
                    InsertarFinal(x.getC(), x.getE());
                } else {
                    if (x.getE() > p.getE()) {
                        a.setLiga(x);
                        x.setLiga(p);
                    } else {
                        p.setC(p.getC() + x.getC());
                        if (p.getC() == 0) {
                            Eliminar(p.getE());
                        }
                    }
                }
            } else {
                if (a.getE() == x.getE()) {
                    a.setC(a.getC() + x.getC());
                } else {
                    Punta = x;
                    x.setLiga(a);
                }
            }
        }
    }

    public void Eliminar(int Exp) {
        if (Punta == null) {
            JOptionPane.showMessageDialog(null, "No hay polinomio");
        } else {
            Nodo a = Punta;
            Nodo p = a.getLiga();
            if (a.getE() != Exp) {
                while (p != null && p.getE() != Exp) {
                    a = p;
                    p = p.getLiga();
                }
                if (p == null) {
                    JOptionPane.showMessageDialog(null, "No se encontró un término con ese exponente");
                } else {
                    Nodo b = p.getLiga();
                    p.setLiga(null);
                    a.setLiga(b);
                    JOptionPane.showMessageDialog(null, "Se eliminó el término");
                }
            } else {
                Punta = a.getLiga();
                a.setLiga(null);
                JOptionPane.showMessageDialog(null, "Se eliminó el término");
            }
        }
    }

    public void PolForma3(int Vp[]) {
        int i = 0, B = 1;
        while (i < Vp.length && B == 1) {
            if (Vp[i] == 0) {
                B = 2;
                i++;
            } else {
                i++;
            }
        }
        if (i % 2 != 0) {
            i = (i - 1);
        }

        for (int j = 0; j < i; j += 2) {
            this.InsertarFinal(Vp[j], Vp[j + 1]);
        }
    }

    public void Mostrar() {
        if (Punta == null) {
            JOptionPane.showMessageDialog(null, "La lista se encuentra vacía");
        } else {
            Nodo p = Punta;
            String s = "";
            while (p != null) {
                s = s + "[" + p.getC() + "|" + p.getE() + "]-->";
                p = p.getLiga();
            }
            JOptionPane.showMessageDialog(null, s);
        }
    }

    public void MostrarForma() {
        Nodo i = Punta;
        String s = "";
        while (i != null) {
            if (i == Punta || i.getC() < 0) {
                if (i.getE() == 1) {
                    s = s + i.getC() + "x";
                }
                if (i.getE() == 0) {
                    s = s + i.getC();
                }
                if (i.getE() != 1 && i.getE() != 0) {
                    s = s + i.getC() + "x^" + i.getE();
                }
            } else {
                if (i.getE() == 1) {
                    s = s + "+" + i.getC() + "x";
                }
                if (i.getE() == 0) {
                    s = s + "+" + i.getC();
                }
                if (i.getE() != 1 && i.getE() != 0) {
                    s = s + "+" + i.getC() + "x^" + i.getE();
                }
            }
            i = i.getLiga();
        }
        JOptionPane.showMessageDialog(null, s);
    }

    public void Evaluar(int x) {
        double Res = 0;
        Nodo i = Punta;
        while (i != null) {
            Res = Res + (i.getC() * Math.pow(x, i.getE()));
            i = i.getLiga();
        }
        JOptionPane.showMessageDialog(null, "El polinomio evaluado con x = " + x + ", dio como resultado:\n" + Res);
    }

    public void Sumar(VPF3 F33, VPF3 FSum) {
        Nodo i = Punta;
        Nodo j = F33.getPunta();
        int aux;
        while (i != null || j != null) {
            if (i == null) {
                FSum.InsertarFinal(j.getC(), j.getE());
                j = j.getLiga();
            }
            if (j == null) {
                FSum.InsertarFinal(i.getC(), i.getE());
                i = i.getLiga();
            }
            if (i != null && j != null) {
                if (i.getE() > j.getE()) {
                    FSum.InsertarFinal(i.getC(), i.getE());
                    i = i.getLiga();
                } else {
                    if (i.getE() < j.getE()) {
                        FSum.InsertarFinal(j.getC(), j.getE());
                        j = j.getLiga();
                    } else {
                        aux = i.getC() + j.getC();
                        if (aux != 0) {
                            FSum.InsertarFinal(aux, i.getE());
                        }
                        i = i.getLiga();
                        j = j.getLiga();
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Se sumaron los polinomios, el resultado es:");
        FSum.MostrarForma();
    }

    public void Multiplicar(VPF3 FM) {
        VPF3 Aux = new VPF3();
        VPF3 Producto = new VPF3();
        VPF3 Resultado = new VPF3();
        if (FM.getPunta() == null) {
            JOptionPane.showMessageDialog(null, "No se está multiplicando por ningún polinomio");
        } else {
            Nodo i = Punta;
            while (i != null) {
                Nodo j = FM.getPunta();
                while (j != null) {
                    Aux.InsertarFinal(i.getC() * j.getC(), i.getE() + j.getE());
                    j = j.getLiga();
                }
                
                Nodo a = Aux.getPunta();
                Nodo b = Producto.getPunta();
                int aux;
                while (a != null || b != null) {
                    if (a == null) {
                        Resultado.InsertarFinal(b.getC(), b.getE());
                        b = b.getLiga();
                    }
                    if (b == null) {
                        Resultado.InsertarFinal(a.getC(), a.getE());
                        a = a.getLiga();
                    }
                    if (a != null && b != null) {
                        if (a.getE() > b.getE()) {
                            Resultado.InsertarFinal(a.getC(), a.getE());
                            a = a.getLiga();
                        } else {
                            if (a.getE() < b.getE()) {
                                Resultado.InsertarFinal(b.getC(), b.getE());
                                b = b.getLiga();
                            } else {
                                aux = a.getC() + b.getC();
                                if (aux != 0) {
                                    Resultado.InsertarFinal(aux, a.getE());
                                }
                                a = a.getLiga();
                                b = b.getLiga();
                            }
                        }
                    }
                }
                Aux.setPunta(null);
                Producto.setPunta(null);
                a = Resultado.getPunta();
                while(a != null){
                    Producto.InsertarFinal(a.getC(), a.getE());
                    a = a.getLiga();
                }
                Resultado.setPunta(null);
                i = i.getLiga();
            }
        }
        JOptionPane.showMessageDialog(null, "Se multiplicaron los polinomios, el resultado es:");
        Producto.MostrarForma();
    }
    
    
    
    
}

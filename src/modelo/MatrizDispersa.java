/*
 * Copyright (C) 2020 Oscar Arenas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package modelo;

/**
 *
 * @author Oscar Arenas
 */
public class MatrizDispersa {

    // Campos
    private int[][] tripletas;
    private int n;
    public final int filas;
    public final int columnas;

    // Métodos
    public MatrizDispersa(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        tripletas = new int[1][3];
        n = 0;
    }

    public boolean agregar(int fila, int columna, int valor) {
        if (valor != 0 && fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            if (tripletas.length == n) {
                cambiarCapacidad(2 * n);
            }
            int i = 0;
            // Buscamos el punto de inserción de la tripleta por fila
            while (i < n && tripletas[i][0] < fila) {
                i++;
            }
            // Buscamos el punto de inserción de la tripleta por columna
            while (i < n && tripletas[i][0] == fila && tripletas[i][1] < columna) {
                i++;
            }

            if (i < n && tripletas[i][0] == fila && tripletas[i][1] == columna) {
                return false; // No se permiten dos valores en la misma posicion
            }

            for (int j = n; j > i; j--) {
                tripletas[j][0] = tripletas[j - 1][0];
                tripletas[j][1] = tripletas[j - 1][1];
                tripletas[j][2] = tripletas[j - 1][2];
            }

            tripletas[i][0] = fila;
            tripletas[i][1] = columna;
            tripletas[i][2] = valor;

            n++;
            return true;
        }
        return false;
    }

    public boolean eliminar(int fila, int columna) {
        if (n > 0 && fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            int i = 0;
            while (i < n && tripletas[i][0] < fila) {
                i++;
            }
            while (i < n && tripletas[i][0] == fila && tripletas[i][1] < columna) {
                i++;
            }
            if (i < n && tripletas[i][0] == fila && tripletas[i][1] == columna) {
                // Eliminar fila y decrementar la cantidad tripletas
                n--;
                for (int j = i; j < n; j++) {
                    tripletas[j][0] = tripletas[j + 1][0];
                    tripletas[j][1] = tripletas[j + 1][1];
                    tripletas[j][2] = tripletas[j + 1][2];
                }

                if (tripletas.length / 4 == n) {
                    cambiarCapacidad(tripletas.length / 2);
                }
                return true;
            }
        }
        return false;
    }

    private void cambiarCapacidad(int nc) {
        if (nc > 0 && nc >= n) {
            int[][] auxiliar = new int[nc][3];

            for (int i = 0; i < n; i++) {
                auxiliar[i] = tripletas[i];
            }
            tripletas = auxiliar;
        }
    }

    @Override
    public String toString() {
        String cadena = "";

        if (n > 0) {
            String[] etiquetas = {"Fila", "Columna", "Valor"};
            int[] mayorAncho = {4, 7, 5};

            // Buscamos el valor de la matriz con más caracteres
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 3; j++) {
                    String numero = tripletas[i][j] + "";
                    if (numero.length() > mayorAncho[j]) {
                        mayorAncho[j] = numero.length();
                    }
                }
            }

            for (int j = 0; j < 3; j++) {
                mayorAncho[j]++;
                int delta = mayorAncho[j] - etiquetas[j].length();
                for (int k = 0; k < delta; k++) {
                    cadena += " ";
                }
                cadena += etiquetas[j];
            }
            cadena += "\n";

            // Formamos la cadena con los valores de la matriz por filas
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 3; j++) {
                    String numero = tripletas[i][j] + "";

                    int delta = mayorAncho[j] - numero.length();
                    for (int k = 0; k < delta; k++) {
                        cadena += " ";
                    }
                    cadena += tripletas[i][j];
                }
                cadena += "\n";
            }
        }
        return cadena;
    }
}

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
package pruebamatrizdispersa;

import edu.oharenas.aleatorio.Aleatorio;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.MatrizDispersa;

/**
 *
 * @author Oscar Arenas
 */
public class PruebaMatrizDispersa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MatrizDispersa matrizDispersa = new MatrizDispersa(50, 100);

        matrizDispersa.agregar(23, 5, 57);
        matrizDispersa.agregar(0, 79, 8);
        matrizDispersa.agregar(43, 39, -98);

        boolean ans = matrizDispersa.agregar(1, 3, 42);

        if (ans) {
            System.out.println("Valor insertado");
        } else {
            System.out.println("No se insertó el valor");
        }

        System.out.println(matrizDispersa);

        matrizDispersa.eliminar(43, 39);
        System.out.println("Después de borrar:");
        System.out.println(matrizDispersa);

        System.out.println(" - Tripletas: " + matrizDispersa.cantidadTripletas());
    }
}

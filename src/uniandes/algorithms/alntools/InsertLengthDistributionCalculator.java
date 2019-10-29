package uniandes.algorithms.alntools;

import java.io.IOException;
import java.util.Iterator;

import ngsep.alignments.ReadAlignment;
import ngsep.alignments.io.ReadAlignmentFileReader;

/**
 * Skeleton of a class implementing a program to calculate 
 * the insert length distribution from a given BAM file  
 * @author Jorge Duitama
 *
 */
public class InsertLengthDistributionCalculator {

	//TODO: Definir un atributo para guardar la distribucion de tamaño de inserto
	
	/**
	 * Main method to run the program
	 * @param args Array with one element, the path to the alignments file
	 * @throws Exception If the file can not be read
	 */
	public static void main(String[] args) throws Exception {
		InsertLengthDistributionCalculator instance = new InsertLengthDistributionCalculator();
		instance.processAlignmentsFile(args[0]);
		instance.printDistribution ();
	}
	/**
	 * Updates the insert length distribution processing the given file
	 * @param filename Path to the BAM file to process
	 * @throws IOException If the file can not be read
	 */
	public void processAlignmentsFile(String filename) throws IOException {
		
		try (ReadAlignmentFileReader reader = new ReadAlignmentFileReader(filename)){
			Iterator<ReadAlignment> it = reader.iterator();
			while (it.hasNext()) {
				ReadAlignment aln = it.next();
				// TODO: Pedir al objeto aln el insert length que se predice a partir del alineamiento de la lectura y su par
				// Si el insert length es positivo, se procesa su valor en la distribucion
				// verificar si la lectura esta alineada y si el alineamiento es primario
			}
		}	
	}

	/**
	 * Prints the distribution to standard output
	 */
	public void printDistribution() {
		// TODO: Implementar metodo
		
	}

}

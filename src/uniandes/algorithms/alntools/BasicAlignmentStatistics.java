package uniandes.algorithms.alntools;

import java.io.IOException;
import java.util.Iterator;

import ngsep.alignments.ReadAlignment;
import ngsep.alignments.io.ReadAlignmentFileReader;
/**
 * Skeleton of a class implementing a program to calculate alignment statistics
 * from a BAM file
 * @author Jorge Duitama
 *
 */
public class BasicAlignmentStatistics {

	private int totalReads = 0;
	private int totalAlignments = 0;
	private int alignedReads = 0;
	private int unalignedReads = 0;
	private int pairProperlyAligned = 0;
	private int mateInDifferentSequence = 0;
	private int mateUnaligned = 0;
	private int uniqueAlignments = 0;
	private int secondaryAlignments = 0;
	private int [] alignmentQualitiesDistribution = new int [256];
	
	/**
	 * Main method to run the program
	 * @param args Array with one element, the path to the alignments file
	 * @throws Exception If the file can not be read
	 */
	public static void main(String[] args) throws Exception {
		BasicAlignmentStatistics instance = new BasicAlignmentStatistics();
		instance.processAlignmentsFile(args[0]);
		instance.printStatistics();
	}

	/**
	 * Updates the statistics processing the given file
	 * @param filename Path to the BAM file to process
	 * @throws IOException If the file can not be read
	 */
	public void processAlignmentsFile(String filename) throws IOException {
		
		try (ReadAlignmentFileReader reader = new ReadAlignmentFileReader(filename)){
			Iterator<ReadAlignment> it = reader.iterator();
			while (it.hasNext()) {
				ReadAlignment aln = it.next();
				// TODO: Utilzar los metodos del objeto aln para actualzar las estadisticas
			}
		}
	}
	/**
	 * Prints the statistics to standard output
	 */
	public void printStatistics() {
		// TODO: Implementar metodo
		
	}

}

package uniandes.algorithms.alntools;

import java.util.List;

import ngsep.alignments.ReadAlignment;
import ngsep.discovery.AlignmentsPileupGenerator;
import ngsep.discovery.PileupListener;
import ngsep.discovery.PileupRecord;
import ngsep.genome.ReferenceGenome;
import ngsep.sequences.QualifiedSequence;

/**
 * Skeleton of a program that implements a simple algorithm to identify SNPs relative to a 
 * reference genome from an alignments file in SAM format
 * 
 * @author Jorge Duitama
 *
 */
public class SimpleSNPDetector implements PileupListener {

	private ReferenceGenome genome;
	
	/**
	 * Main method that executes the program
	 * @param args Array with two arguments. The path to the reference genome and the path to the alignments file
	 * @throws Exception If the files can not be read
	 */
	public static void main(String[] args) throws Exception {
		String genomeFile = args[0];
		String alignmentsFile = args[1];
		SimpleSNPDetector instance = new SimpleSNPDetector();
		instance.genome = new ReferenceGenome(genomeFile);
		AlignmentsPileupGenerator generator = new AlignmentsPileupGenerator();
		generator.addListener(instance);
		generator.processFile(alignmentsFile);
	}

	@Override
	public void onPileup(PileupRecord record) {
		String seqName = record.getSequenceName();
		int referencePos = record.getPosition();
		char referenceBase = genome.getReferenceBase(seqName, referencePos);
		List<ReadAlignment> alnsPos = record.getAlignments();
		
		// TODO: Recorrer la lista de alineamientos y para cada uno ubicar la base
		// en la lectura que corresponde a la posicion referencePos.
		// Imprimir los datos de todas las posiciones que tengan al menos un llamado a una base diferente a la referencia
	}

	@Override
	public void onSequenceEnd(QualifiedSequence seq) {
		System.out.println("Finished sequence: "+seq.getName());
		
	}

	@Override
	public void onSequenceStart(QualifiedSequence seq) {
		System.out.println("Started sequence: "+seq.getName());
		
	}

}

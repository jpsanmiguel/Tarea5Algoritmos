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
 */
public class SimpleSNPDetector implements PileupListener {

    private ReferenceGenome genome;

    /**
     * Main method that executes the program
     *
     * @param args Array with two arguments. The path to the reference genome and the path to the alignments file
     * @throws Exception If the files can not be read
     */
    public static void main(String[] args) throws Exception {
        String genomeFile = "C:\\Users\\Sanmi\\Desktop\\yeastGenome.fa";
        String alignmentsFile = "C:\\Users\\Sanmi\\Desktop\\Unselected_bowtie2_sorted.bam";
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

        int[] numeroLecturas = new int[4];
        for (ReadAlignment aln : alnsPos) {
            CharSequence secuenciaActual = aln.getAlleleCall(referencePos);
            if (secuenciaActual != null && secuenciaActual.length() > 0) {
                char baseActual = secuenciaActual.charAt(0);
                if (baseActual == 'A') {
                    numeroLecturas[0]++;
                } else if (baseActual == 'C') {
                    numeroLecturas[1]++;
                } else if (baseActual == 'G') {
                    numeroLecturas[2]++;
                } else if (baseActual == 'T') {
                    numeroLecturas[3]++;
                }
                if (baseActual != referenceBase) {
                    System.out.println("Nombre secuencia: " + aln.getSequenceName());
                    System.out.println("Posicion SNP: " + referencePos);
                    System.out.println("Referencia base: " + referenceBase);
                    // TODO Quinto punto
                    int totalLecturesMin = (int) (0.2 * (numeroLecturas[0] + numeroLecturas[1] + numeroLecturas[2] + numeroLecturas[3]));
                    auxMethod(referenceBase, numeroLecturas, totalLecturesMin);
                }
            }
        }
    }

    private void auxMethod(char referenceBase, int[] numeroLecturas, int totalLecturesMin){
		if (referenceBase != 'A' && numeroLecturas[0] != 0 && numeroLecturas[0] > totalLecturesMin) {
			System.out.println("Base Alternativa: A");
			System.out.println("# Lecturas: " + numeroLecturas[0]);
		}
		if (referenceBase != 'C' && numeroLecturas[1] != 0 && numeroLecturas[1] > totalLecturesMin) {
			System.out.println("Base Alternativa: C");
			System.out.println("# Lecturas: " + numeroLecturas[1]);
		}
		if (referenceBase != 'G' && numeroLecturas[2] != 0 && numeroLecturas[2] > totalLecturesMin) {
			System.out.println("Base Alternativa: G");
			System.out.println("# Lecturas: " + numeroLecturas[2]);
		}
		if (referenceBase != 'T' && numeroLecturas[3] != 0 && numeroLecturas[3] > totalLecturesMin) {
			System.out.println("Base Alternativa: T");
			System.out.println("# Lecturas: " + numeroLecturas[3]);
		}
	}

    @Override
    public void onSequenceEnd(QualifiedSequence seq) {
        System.out.println("Finished sequence: " + seq.getName());

    }

    @Override
    public void onSequenceStart(QualifiedSequence seq) {
        System.out.println("Started sequence: " + seq.getName());

    }

}

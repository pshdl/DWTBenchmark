import java.io.PrintStream;
import java.util.Random;

import test.testDWTSimulationGenerator;

import com.google.common.base.Stopwatch;

public class DWTTest {
	public static void main(String[] args) throws Exception {
		final testDWTSimulationGenerator gen = new testDWTSimulationGenerator(true, true);
		try (final PrintStream inputFile = new PrintStream("TestDWTSimInputData.txt"); final PrintStream outputFile = new PrintStream("TestDWTSimOuputData.txt");) {
			final Random r = new Random();
			gen.initConstants();
			final int width = 1024;
			final int max = width * width;
			final long[] data = new long[max];
			for (int i = 0; i < data.length; i++) {
				data[i] = r.nextInt() & 0x3ff;
				inputFile.printf("%03x%n", data[i]);
			}
			// System.out.println(Arrays.toString(data));
			while (true) {
				final Stopwatch sw = new Stopwatch();
				gen.de_henningholm_da_DWTSimulation_width_tile = width;
				gen.de_henningholm_da_DWTSimulation_height_tile = width;
				gen.de_henningholm_da_DWTSimulation_count_resolutions = 1;
				// System.arraycopy(data, 0,
				// gen.de_henningholm_da_DWTSimulation_VFBC_SimulationInst_memory,
				// 0, max);
				gen.de_henningholm_da_DWTSimulation_address_destination = max * 4;
				gen.de_henningholm_da_DWTSimulation_clk = 1;
				gen.de_henningholm_da_DWTSimulation_rst = 1;
				gen.run();
				gen.de_henningholm_da_DWTSimulation_rst = 0;
				sw.start();
				gen.de_henningholm_da_DWTSimulation_dwt_start = 1;
				gen.run();
				do {
					gen.run();
				} while (gen.de_henningholm_da_DWTSimulation_dwt_done == 0);
				System.out.println("Total:" + sw);
				// for (final long element :
				// gen.de_henningholm_da_DWTSimulation_VFBC_SimulationInst_memory)
				// {
				// outputFile.printf("%03x%n", element);
				// }
			}
		}
	}
}

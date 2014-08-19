import 'testDWTSimulation.dart';

void main(List<String> arguments) {
  testDWTSimulation gen = new testDWTSimulation(true, true);
  gen.initConstants();
  final int width = 1024;
  final int max = width * width;
  while (true) {
    gen.de_henningholm_da_DWTSimulation_width_tile = width;
    gen.de_henningholm_da_DWTSimulation_height_tile = width;
    gen.de_henningholm_da_DWTSimulation_count_resolutions = 1;
    gen.de_henningholm_da_DWTSimulation_address_destination = max * 4;
    gen.de_henningholm_da_DWTSimulation_clk = 1;
    gen.de_henningholm_da_DWTSimulation_rst = 1;
    gen.run();
    gen.de_henningholm_da_DWTSimulation_rst = 0;
    final Stopwatch sw = new Stopwatch();
    sw.start();
    gen.de_henningholm_da_DWTSimulation_dwt_start = 1;
    gen.run();
    do {
      gen.run();
    } while (gen.de_henningholm_da_DWTSimulation_dwt_done == 0);
    print("Total:${sw.elapsed.inMilliseconds}");
  }
}
